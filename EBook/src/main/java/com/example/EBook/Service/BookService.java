package com.example.EBook.Service;

import org.springframework.data.domain.Pageable;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.AbstractMap;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.Date;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.example.EBook.Repository.BookRepository;
import com.example.EBook.Repository.UserActionRepository;
import com.example.EBook.entity.BookEntity;
import com.example.EBook.entity.UserAction;
import com.example.EBook.entity.UserActionGridDb;
import com.example.EBook.input.BookInput;
import com.example.EBook.output.BookOutput;
import java.util.Properties;
import java.util.Scanner;
import java.util.stream.Collectors;

import com.toshiba.mwcloud.gs.Collection;
import com.toshiba.mwcloud.gs.Container;
import com.toshiba.mwcloud.gs.GSException;
import com.toshiba.mwcloud.gs.GridStore;
import com.toshiba.mwcloud.gs.GridStoreFactory;
import com.toshiba.mwcloud.gs.Query;
import com.toshiba.mwcloud.gs.Row;
import com.toshiba.mwcloud.gs.RowKey;
import com.toshiba.mwcloud.gs.RowSet;
import com.toshiba.mwcloud.gs.TimeSeries;

import org.apache.mahout.cf.taste.impl.neighborhood.ThresholdUserNeighborhood;
import org.apache.mahout.cf.taste.impl.common.FastByIDMap;
import org.apache.mahout.cf.taste.impl.model.GenericDataModel;
import org.apache.mahout.cf.taste.impl.model.GenericPreference;
import org.apache.mahout.cf.taste.impl.model.GenericUserPreferenceArray;
import org.apache.mahout.cf.taste.impl.model.file.FileDataModel;
import org.apache.mahout.cf.taste.impl.recommender.GenericUserBasedRecommender;
import org.apache.mahout.cf.taste.model.DataModel;
import org.apache.mahout.cf.taste.model.Preference;
import org.apache.mahout.cf.taste.model.PreferenceArray;
import org.apache.mahout.cf.taste.impl.similarity.CityBlockSimilarity;
import org.apache.mahout.cf.taste.neighborhood.UserNeighborhood;
import org.apache.mahout.cf.taste.recommender.UserBasedRecommender;
import org.apache.mahout.cf.taste.similarity.UserSimilarity;
import org.apache.mahout.cf.taste.recommender.RecommendedItem;
import org.apache.mahout.cf.taste.impl.similarity.PearsonCorrelationSimilarity;

@Service
public class BookService {
	private static final int BUFFER_SIZE = 1024 * 8;
	@Value("${book.path}")
    private String uploadFolder;
	
	@Autowired
	private BookRepository bookRepository;
	@Autowired
	private UserActionRepository userActionRepository;
	
	public static class UserGridDb{
		@RowKey Date ts;
		Long userId;
		Long categoryId;
		Float views;
		public Date getTs() {
			return ts;
		}
		public void setTs(Date ts) {
			this.ts = ts;
		}
		public Long getUserId() {
			return userId;
		}
		public void setUserId(Long userId) {
			this.userId = userId;
		}
		public Long getCategoryId() {
			return categoryId;
		}
		public void setCategoryId(Long categoryId) {
			this.categoryId = categoryId;
		}
		public Float getViews() {
			return views;
		}
		public void setViews(Float views) {
			this.views = views;
		}
		public UserGridDb(Date ts, Long userId, Long categoryId, Float views) {
			super();
			this.ts = ts;
			this.userId = userId;
			this.categoryId = categoryId;
			this.views = views;
		}
		public UserGridDb() {
			super();
		}
		
	}
	
	public BookOutput getBookByPage(int page, int size, BookInput bookInput){
		Pageable pageable = PageRequest.of(page, size);
        Page<BookEntity> bookPage = null;
        if(bookInput.getBookType() == 1l) {
        	bookPage = bookRepository.findByBookType(bookInput.getBookType() ,pageable);
        }else if(bookInput.getBookType() == 2l){
        	bookPage = bookRepository.findByBookType(bookInput.getBookType() ,pageable);
        }else if(bookInput.getBookType() == 3l) {
        	bookPage = bookRepository.findByBookType(bookInput.getBookType() ,pageable);
        }else {
        	bookPage = bookRepository.findAll(pageable);
        }
        BookOutput bookOutput = new BookOutput();
        bookOutput.setPage(page+1);
        bookOutput.setTotalPage(bookPage.getTotalPages());
        bookOutput.setBookEntityList(bookPage.getContent());
        return bookOutput;
	}
	
	public List<BookEntity> findAll() {
		return bookRepository.findAll();
	}
	
	public BookOutput findByBookCategory(int page, int size,String bookCategory) {
        Pageable pageable = PageRequest.of(page, size);
        Page<BookEntity> bookPage = bookRepository.findByBookCategory(bookCategory, pageable);
        BookOutput bookOutput = new BookOutput();
        bookOutput.setPage(page+1);
        bookOutput.setTotalPage(bookPage.getTotalPages());
        bookOutput.setBookEntityList(bookPage.getContent());
        return bookOutput;
    }
	
	public List<BookEntity> findTopBook() {
        return bookRepository.findTop10ByOrderByViewDesc();
    }

    public List<BookEntity> findByBookName(String bookName) {
        return bookRepository.findByBookNameContaining(bookName);
    }
    
    public BookOutput searchBookByPage(BookInput bookInput, int page, int size){
		Pageable pageable = PageRequest.of(page, size);
        Page<BookEntity> bookPage = bookRepository.searchBookByPage(bookInput.getBookName(), bookInput.getBookAuthor(), bookInput.getBookCategory(), bookInput.getBookType(), pageable);
        BookOutput bookOutput = new BookOutput();
        bookOutput.setPage(page+1);
        bookOutput.setTotalPage(bookPage.getTotalPages());
        bookOutput.setBookEntityList(bookPage.getContent());
        return bookOutput;
	}
    
    public BookEntity addNewReadingBook(MultipartFile file, BookEntity bookEntity) throws IOException {
    	File fileUpload = convertToFile(file);
    	Date date = new Date();
    	String fileName = file.getOriginalFilename().substring(0, file.getOriginalFilename().lastIndexOf('.'))+date.getTime()+".pdf";
    	File dirUpload = new File(uploadFolder);
    	saveFile(fileUpload, fileName, dirUpload);
    	bookEntity.setPreview(fileName);
    	bookEntity.setLastUpdate(new Date());
    	bookEntity.setFavorite(0l);
    	bookEntity.setView(0l);
    	bookEntity.setPurchases(0l);
    	bookEntity = bookRepository.save(bookEntity);
    	return bookEntity;
    }
    
    public File convertToFile(MultipartFile multipartFile) throws FileNotFoundException, IOException {
	   File file = new File("src/main/resources/targetFile.pdf");

	    try (OutputStream os = new FileOutputStream(file)) {
	       os.write(multipartFile.getBytes());
	    }
	    return file;
	}
    
    public static void saveFile(File f, String fileName, File desDir) throws IOException {
        InputStream stream = new FileInputStream(f);
        OutputStream out = new FileOutputStream(desDir.getAbsolutePath() + File.separator + fileName);
        int bytesRead = 0;
        byte[] buffer = new byte[BUFFER_SIZE];
        while ((bytesRead = stream.read(buffer, 0, BUFFER_SIZE)) != -1) {
            out.write(buffer, 0, bytesRead);
        }
        stream.close();
        out.close();
    }
    
    public void ViewFilePdf(String preview, Long userId, String category) {
    	BookEntity bookEntity = bookRepository.findByPreview(preview);
    	bookEntity.setView(bookEntity.getView() + 1l);
    	bookRepository.save(bookEntity);
    	
    	if(userId != null && category != null) {
    		Long categoryId = null;
        	if(category.equals("Doanh nhân - Bài học kinh doanh")) {
        		categoryId = 1l;
        	}else if(category.equals("Phát triển cá nhân")) {
        		categoryId = 2l;
        	}else if(category.equals("Tài chính cá nhân")) {
        		categoryId = 3l;
        	}else if(category.equals("Ngôn tình")) {
        		categoryId = 4l;
        	}else if(category.equals("Marketing - Bán hàng")) {
        		categoryId = 5l;
        	}else if(category.equals("Khởi nghiệp - Làm giàu")) {
        		categoryId = 6l;
        	}else if(category.equals("Viễn tưởng - Giả tưởng")) {
        		categoryId = 7l;
        	}else if(category.equals("Trinh thám - Kinh dị")) {
        		categoryId = 8l;
        	}
        	UserAction userAction = userActionRepository.findByUserIdAndCategoryId(userId, categoryId);
        	if(userAction == null) {
        		userAction = new UserAction();
        		userAction.setUserId(userId);
        		userAction.setCategory(category);
        		userAction.setCategoryId(categoryId);
        		userAction.setViews(1f);
        	}else {
        		userAction.setViews(userAction.getViews() + 1f);
        	}
        	userActionRepository.save(userAction);
    	}
    }
    
    public BookEntity detailBook(Long bookId) {
    	return bookRepository.findById(bookId).orElse(null);
    }
    
    public BookOutput getBookPaid(int page, int size, Long userId) {
    	Pageable pageable = PageRequest.of(page, size);
    	Page<Long> pageIds = bookRepository.getBookPaid(userId, pageable);
    	List<BookEntity> bookEntities = bookRepository.findAllById(pageIds.getContent());
    	BookOutput bookOutput = new BookOutput();
    	bookOutput.setPage(page+1);
    	bookOutput.setTotalPage(pageIds.getTotalPages());
    	bookOutput.setBookEntityList(bookEntities);
    	return bookOutput;
    }
    
    public UserGridDb convertUserToUserGridDb(UserAction userAction) {
    	UserGridDb userGridDb = new UserGridDb();
    	userGridDb.setTs(new Date());
    	userGridDb.setUserId(userAction.getUserId());
    	userGridDb.setCategoryId(userAction.getCategoryId());
    	userGridDb.setViews(userAction.getViews());
    	return userGridDb;
    }
    
    public BookEntity edit(MultipartFile file, BookEntity bookEntity) throws IOException {
    	if(file != null) {
    		File fileUpload = convertToFile(file);
        	Date date = new Date();
        	String fileName = file.getOriginalFilename().substring(0, file.getOriginalFilename().lastIndexOf('.'))+date.getTime()+".pdf";
        	File dirUpload = new File(uploadFolder);
        	saveFile(fileUpload, fileName, dirUpload);
        	bookEntity.setLastUpdate(new Date());
        	bookEntity.setPreview(fileName);
        	bookEntity = bookRepository.save(bookEntity);
    	}else {
    		bookEntity = bookRepository.save(bookEntity);
    	}
    	return bookEntity;
    }
    
    public List<BookEntity> recomentForUser(Long userId) {
    	List<BookEntity> bookEntities = new ArrayList<>();
    	try {
    		List<UserAction> userActions = userActionRepository.findAll();
	        Map<Long, Map<Long, Float>> userItemRatings = new HashMap<>();
	        for (UserAction action : userActions) {
	            Long uId = action.getUserId();
	            Long categoryId = action.getCategoryId();
	            Float view = action.getViews();
	
	            userItemRatings.computeIfAbsent(uId, k -> new HashMap<>()).put(categoryId, view);
	        }
	        
	        if(userItemRatings.get(userId) == null) {
	        	return new ArrayList<>();
	        }
	        Map<Long, Float> recommendations = generateRecommendations(userId, userItemRatings, 3);
	        for (Map.Entry<Long, Float> recommendation : recommendations.entrySet()) {
	        	String category = "";
	        	if(recommendation.getKey().equals(1l)) {
	        		category = "Doanh nhân - Bài học kinh doanh";
	        	}else if(recommendation.getKey().equals(2l)) {
	        		category = "Phát triển cá nhân";
	        	}else if(recommendation.getKey().equals(3l)) {
	        		category = "Tài chính cá nhân";
	        	}else if(recommendation.getKey().equals(4l)) {
	        		category = "Ngôn tình";
	        	}else if(recommendation.getKey().equals(5l)) {
	        		category = "Marketing - Bán hàng";
	        	}else if(recommendation.getKey().equals(6l)) {
	        		category = "Khởi nghiệp - Làm giàu";
	        	}else if(recommendation.getKey().equals(7l)) {
	        		category = "Viễn tưởng - Giả tưởng";
	        	}else if(recommendation.getKey().equals(8l)) {
	        		category = "Trinh thám - Kinh dị";
	        	}
	        	List<BookEntity> listByCate = bookRepository.findTopBookCategoryLimit(category, PageRequest.of(0, 10));
	        	if(listByCate.size() > 0) {
		            Collections.shuffle(listByCate);
		            List<BookEntity> randomBooks = listByCate.stream().limit(4).collect(Collectors.toList());
		        	bookEntities.addAll(randomBooks);
	        	}
	        }
	        return bookEntities;
		} catch (Exception ex) {
            ex.printStackTrace();
        }
        return null;
	}
    
    // Method to calculate the CityBlock (Manhattan) distance between two users' preferences
    public static double calculateCityBlockDistance(Map<Long, Float> user1, Map<Long, Float> user2) {
        double distance = 0.0;
        int commonItems = 0;

        for (Long categoryId : user1.keySet()) {
            if (user2.containsKey(categoryId)) {
                distance += Math.abs(user1.get(categoryId) - user2.get(categoryId));
                commonItems++;
            }
        }

        // Return a large distance if there are no common items, as they cannot be compared meaningfully
        return commonItems > 0 ? distance : Double.MAX_VALUE;
    }

    // Method to find neighbors within a certain threshold using CityBlock distance
    public static List<Long> getTopNClosestNeighbors(Long userId, Map<Long, Map<Long, Float>> preferenceMap, int topN) {
        List<Map.Entry<Long, Double>> userDistances = new ArrayList<>();

        Map<Long, Float> targetUserPrefs = preferenceMap.get(userId);
        for (Map.Entry<Long, Map<Long, Float>> entry : preferenceMap.entrySet()) {
            Long otherUserId = entry.getKey();
            if (!otherUserId.equals(userId)) {
                double distance = calculateCityBlockDistance(targetUserPrefs, entry.getValue());
                userDistances.add(new AbstractMap.SimpleEntry<>(otherUserId, distance));
            }
        }

        // Sort users by distance in ascending order
        userDistances.sort(Comparator.comparingDouble(Map.Entry::getValue));

        // Extract the top N closest neighbors
        List<Long> closestNeighbors = new ArrayList<>();
        for (int i = 0; i < userDistances.size(); i++) {
            closestNeighbors.add(userDistances.get(i).getKey());
        }

        return closestNeighbors;
    }
    
 // Method to generate recommendations for a given user
    public static Map<Long, Float> generateRecommendations(Long userId, Map<Long, Map<Long, Float>> preferenceMap, int topN) {
        List<Long> closestNeighbors = getTopNClosestNeighbors(userId, preferenceMap, topN);
        Map<Long, Float> topRecommendations = new LinkedHashMap	<>();
        
        for(int i = 0; i< topN; i++) {
		    Map<Long, Float> targetUserPrefs = preferenceMap.get(userId);
		    Map<Long, Float> neighborPrefs = preferenceMap.get(closestNeighbors.get(i));
		
		    for (Map.Entry<Long, Float> entry : neighborPrefs.entrySet()) {
		    	Long categoryId = entry.getKey();
		        topRecommendations.put(categoryId, topRecommendations.getOrDefault(categoryId, 0.0f) + entry.getValue());
		    }
        }
        
        Map<Long, Float> result = new LinkedHashMap<>();
        
        topRecommendations.entrySet()
        .stream()
        .sorted(Map.Entry.<Long, Float>comparingByValue(Comparator.reverseOrder()))
        .forEachOrdered(entry -> result.put(entry.getKey(), entry.getValue()));

        return result;
    }
    
    public void delete(Long bookId) {
    	bookRepository.deleteById(bookId);
    }
}
