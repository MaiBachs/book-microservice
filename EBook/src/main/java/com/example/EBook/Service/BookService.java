package com.example.EBook.Service;

import org.springframework.data.domain.Pageable;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
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
import org.apache.mahout.cf.taste.recommender.RecommendedItem;

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
        	}else if(category.equals("Quản trị - Lãnh đạo")) {
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
    
    public List<BookEntity> recomentForUser(Long userId) {
    	try {
    		List<UserAction> userActions = userActionRepository.findByUserId(userId);
//    		List<UserGridDb> userGridDbs = new ArrayList<>();
//	    	Properties props = new Properties();
//	    	props.setProperty("notificationMember", "127.0.0.1:10001");
//	        props.setProperty("clusterName", "myCluster");
//	    	props.setProperty("user", "admin");
//	    	props.setProperty("password", "admin");
//	        GridStore store = GridStoreFactory.getInstance().getGridStore(props);
//	        
//	        Container<Object, Row> container = store.getContainer("UserAction");
//	        TimeSeries<UserGridDb> ts = null;
//	        if (container != null) {
//	        	store.dropContainer("UserAction");
//	        	ts = store.putTimeSeries("UserAction", UserGridDb.class);
//	        }else {
//	        	ts = store.putTimeSeries("UserAction", UserGridDb.class);
//	        }
//	        for(UserAction u: userActions) {
//	        	UserGridDb userGridDb = convertUserToUserGridDb(u);
//	        	userGridDbs.add(userGridDb);
//	        	ts.append(userGridDb);
//	        }
//			Collection<String, UserGridDb> coll = store.putCollection("ts", UserGridDb.class);
//			coll.put(userGridDbs);
//			Query<UserGridDb> query = coll.query("select *");
//	        RowSet<UserGridDb> res = query.fetch();
	        
	        Map<Long, Map<Long, Float>> userItemRatings = new HashMap<>();
	        for (UserAction action : userActions) {
	            Long uId = action.getUserId();
	            Long categoryId = action.getCategoryId();
	            Float view = action.getViews();
	
	            userItemRatings.computeIfAbsent(uId, k -> new HashMap<>()).put(categoryId, view);
	        }
	        
	        FastByIDMap<PreferenceArray> preferenceMap = new FastByIDMap<>();
	        for (Map.Entry<Long, Map<Long, Float>> entry : userItemRatings.entrySet()) {
	            Long uId = entry.getKey();
	            Map<Long, Float> itemRatings = entry.getValue();
	            List<Preference> preferences = new ArrayList<>();
	            for (Map.Entry<Long, Float> itemRating : itemRatings.entrySet()) {
	                preferences.add(new GenericPreference(uId, itemRating.getKey(), itemRating.getValue()));
	            }
	            preferenceMap.put(uId, new GenericUserPreferenceArray(preferences));
	        }
	        
	        DataModel model = new GenericDataModel(preferenceMap);
	        CityBlockSimilarity similarity = new CityBlockSimilarity(model);
	        UserNeighborhood neighborhood = new ThresholdUserNeighborhood(3.0,similarity, model);
	        com.example.EBook.mahoutCustom.interfaceMahout.UserBasedRecommender recommender = new com.example.EBook.mahoutCustom.GenericUserBasedRecommender(model, neighborhood, similarity);
	        // UserID and number of items to be recommended
	        List<RecommendedItem> recommended_items = recommender.recommend(userId, 5); 
	            
	        for (RecommendedItem r : recommended_items) {
	        	System.out.println(r);
	        }
//	        store.close();
//		} catch (GSException e) {
//			// TODO Auto-generated catch block
//			e.printStackTrace();
		} catch (Exception ex) {
            ex.printStackTrace();
        }
        return null;
	}
    
    public BookEntity edit(MultipartFile file, BookEntity bookEntity) throws IOException {
    	if(file != null) {
    		File fileUpload = convertToFile(file);
        	Date date = new Date();
        	String fileName = file.getOriginalFilename().substring(0, file.getOriginalFilename().lastIndexOf('.'))+date.getTime()+".pdf";
        	File dirUpload = new File(uploadFolder);
        	saveFile(fileUpload, fileName, dirUpload);
        	bookEntity.setLastUpdate(new Date());
        	bookEntity = bookRepository.save(bookEntity);
    	}else {
    		bookEntity = bookRepository.save(bookEntity);
    	}
    	return bookEntity;
    }
}
