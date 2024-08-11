package com.example.BookService.service;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.io.OutputStream;
import java.sql.Timestamp;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import org.jxls.common.Context;
import org.jxls.util.JxlsHelper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.example.BookService.dto.HistoryPaymentAudioBookDto;
import com.example.BookService.dto.HistoryPaymentEbookDto;
import com.example.BookService.dto.HistoryPaymentMemberDto;
import com.example.BookService.dto.TotalDataHomeDto;
import com.example.BookService.entity.HistoryPayment;
import com.example.BookService.input.HistoryPaymentInput;
import com.example.BookService.output.HistoryPaymentOutput;
import com.example.BookService.repository.HistoryPaymentRepository;

@Service
public class HistoryPaymentService {
	@Autowired
	private HistoryPaymentRepository historyPaymentRepository;
	@Value("${TEMPLATE_PATH}")
    private String templateFolder;
    @Value("${FILE_TEMP_UPLOAD_PATH}")
    private String fileNameFullFolder;
	
	public HistoryPayment findByUserIdAndBookId(Long userId, Long bookId) {
		return historyPaymentRepository.findByUserIdAndBookId(userId, bookId);
	}
	
	public HistoryPayment save(HistoryPayment historyPayment) {
		return historyPaymentRepository.save(historyPayment);
	}
	
	public HistoryPaymentOutput getHisByPage(int page, int size, Long userId){
		Pageable pageable = PageRequest.of(page, size);
		Page<HistoryPayment> pageHis = historyPaymentRepository.findByUserId(userId, pageable);
		HistoryPaymentOutput historyPaymentOutput = new HistoryPaymentOutput();
		historyPaymentOutput.setPage(page+1);
		historyPaymentOutput.setTotalPage(pageHis.getTotalPages());
		historyPaymentOutput.setHistoryPayments(pageHis.getContent());
		return historyPaymentOutput;
	}
	
	public List<HistoryPayment> findHisPaymentBook(Long userId){
		List<Object[]> objects = historyPaymentRepository.findHisPaymentBook(userId);
		List<HistoryPayment> historyPayments = new ArrayList<>();
		for(Object[] oj: objects) {
			if(oj != null) {
				HistoryPayment historyPayment = new HistoryPayment();
				historyPayment.setHistory_payment_id(Long.parseLong(String.valueOf(oj[0])));
				historyPayment.setCodePayment(String.valueOf(oj[1]));
				historyPayment.setBookId(Long.parseLong(String.valueOf(oj[2])));
				historyPayment.setContentPayment(String.valueOf(oj[3]));
				historyPayment.setMoneyPayment(Long.parseLong(String.valueOf(oj[4])));
				DateFormat df = new SimpleDateFormat("YYYY-MM-DD HH:MM:SS.FFFFFF");
				try {
					historyPayment.setCreatedDate(df.parse(String.valueOf(oj[5])));
				} catch (ParseException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
//				historyPayment.setTermId(Long.parseLong(String.valueOf(oj[6])));
				historyPayment.setUserId(Long.parseLong(String.valueOf(oj[7])));
				historyPayment.setBookName(String.valueOf(oj[8]));
				historyPayments.add(historyPayment);
			}
		}
		return historyPayments;
	}
	
	public List<HistoryPayment> findHisPaymentAudioBook(Long userId){
		List<Object[]> objects = historyPaymentRepository.findHisPaymentAudioBook(userId);
		List<HistoryPayment> historyPayments = new ArrayList<>();
		for(Object[] oj: objects) {
			if(oj != null) {
				HistoryPayment historyPayment = new HistoryPayment();
				historyPayment.setHistory_payment_id(Long.parseLong(String.valueOf(oj[0])));
				historyPayment.setCodePayment(String.valueOf(oj[1]));
				historyPayment.setAudioBookId(Long.parseLong(String.valueOf(oj[2])));
				historyPayment.setContentPayment(String.valueOf(oj[3]));
				historyPayment.setMoneyPayment(Long.parseLong(String.valueOf(oj[4])));
				DateFormat df = new SimpleDateFormat("YYYY-MM-DD HH:MM:SS.FFFFFF");
				try {
					historyPayment.setCreatedDate(df.parse(String.valueOf(oj[5])));
				} catch (ParseException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
//				historyPayment.setTermId(Long.parseLong(String.valueOf(oj[6])));
				historyPayment.setUserId(Long.parseLong(String.valueOf(oj[7])));
				historyPayment.setBookName(String.valueOf(oj[8]));
				historyPayments.add(historyPayment);
			}
		}
		return historyPayments;
	}
	
	public List<HistoryPayment> findHisPaymentRegiscard(Long userId){
		List<Object[]> objects = historyPaymentRepository.findHisPaymentRegiscard(userId);
		List<HistoryPayment> historyPayments = new ArrayList<>();
		for(Object[] oj: objects) {
			if(oj != null) {
				HistoryPayment historyPayment = new HistoryPayment();
				historyPayment.setHistory_payment_id(Long.parseLong(String.valueOf(oj[0])));
				historyPayment.setCodePayment(String.valueOf(oj[1]));
				historyPayment.setContentPayment(String.valueOf(oj[3]));
				historyPayment.setMoneyPayment(Long.parseLong(String.valueOf(oj[4])));
				DateFormat df = new SimpleDateFormat("YYYY-MM-DD HH:MM:SS.FFFFFF");
				try {
					historyPayment.setCreatedDate(df.parse(String.valueOf(oj[5])));
				} catch (ParseException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				historyPayment.setTermId(Long.parseLong(String.valueOf(oj[6])));
				historyPayment.setUserId(Long.parseLong(String.valueOf(oj[7])));
				historyPayments.add(historyPayment);
			}
		}
		return historyPayments;
	}
	
	public List<HistoryPaymentEbookDto> searchPaymentEbook(HistoryPaymentInput historyPaymentInput){
		List<Object[]> objects = historyPaymentRepository.searchPaymentEbook(
				historyPaymentInput.getBookName(), 
				historyPaymentInput.getBookCategory(),
				historyPaymentInput.getUserName(),
				historyPaymentInput.getFromDate(),
				historyPaymentInput.getToDate());
		List<HistoryPaymentEbookDto> historyPaymentEbookDtos = new ArrayList<>();
		for(Object[] oj: objects) {
			if(oj != null) {
				HistoryPaymentEbookDto historyPaymentEbookDto = new HistoryPaymentEbookDto();
				historyPaymentEbookDto.setId(Long.parseLong(String.valueOf(oj[0])));
				historyPaymentEbookDto.setCodePayment(String.valueOf(oj[1]));
				historyPaymentEbookDto.setContentPayment(String.valueOf(oj[2]));
				historyPaymentEbookDto.setMoneyPayment(Long.parseLong(String.valueOf(oj[3])));
				DateFormat df = new SimpleDateFormat("YYYY-MM-DD HH:MM:SS.FFFFFF");
				try {
					historyPaymentEbookDto.setCreatedDate(df.parse(String.valueOf(oj[4])));
				} catch (ParseException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				historyPaymentEbookDto.setBookName(String.valueOf(oj[5]));
				historyPaymentEbookDto.setBookCategory(String.valueOf(oj[6]));
				historyPaymentEbookDto.setUserName(String.valueOf(oj[7]));
				historyPaymentEbookDtos.add(historyPaymentEbookDto);
			}
		}
		return historyPaymentEbookDtos;
	}
	
	public String buildExportEbookResultFile(List<HistoryPaymentEbookDto> historyPaymentEbookDtos) {
        String templateFolder = this.templateFolder;
        String fileName = "Report_ebook_payment_result" + Calendar.getInstance().getTimeInMillis() + ".xls";
        String fileNameFull = this.fileNameFullFolder + File.separator + fileName;
        String fileTemplate = "Template_report_ebook_payment.xls";
        File templateFile = new File(templateFolder, fileTemplate);
        try {
            InputStream is = new FileInputStream(templateFile);
            File fileResult = new File(fileNameFull);
            OutputStream os = new FileOutputStream(fileResult);
            Context context = new Context();
            context.putVar("historyPaymentEbookDto", historyPaymentEbookDtos);
            JxlsHelper.getInstance().processTemplate(is, os, context);
            return fileNameFull;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }
	
	public String buildExportAudioResultFile(List<HistoryPaymentAudioBookDto> historyPaymentAudioBookDtos) {
        String templateFolder = this.templateFolder;
        String fileName = "Report_ebook_payment_result" + Calendar.getInstance().getTimeInMillis() + ".xls";
        String fileNameFull = this.fileNameFullFolder + File.separator + fileName;
        String fileTemplate = "Template_report_audio_book_payment.xls";
        File templateFile = new File(templateFolder, fileTemplate);
        try {
            InputStream is = new FileInputStream(templateFile);
            File fileResult = new File(fileNameFull);
            OutputStream os = new FileOutputStream(fileResult);
            Context context = new Context();
            context.putVar("historyPaymentAudioBookDto", historyPaymentAudioBookDtos);
            JxlsHelper.getInstance().processTemplate(is, os, context);
            return fileNameFull;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }
	
	public List<HistoryPaymentAudioBookDto> searchPaymentAudioBook(HistoryPaymentInput historyPaymentInput){
		List<Object[]> objects = historyPaymentRepository.searchPaymentAudioBook(
				historyPaymentInput.getAudioBookName(), 
				historyPaymentInput.getAudioBookCategory(),
				historyPaymentInput.getUserName(),
				historyPaymentInput.getFromDate(),
				historyPaymentInput.getToDate());
		List<HistoryPaymentAudioBookDto> historyPaymentAudioBookDtos = new ArrayList<>();
		for(Object[] oj: objects) {
			if(oj != null) {
				HistoryPaymentAudioBookDto historyPaymentAudioBookDto = new HistoryPaymentAudioBookDto();
				historyPaymentAudioBookDto.setId(Long.parseLong(String.valueOf(oj[0])));
				historyPaymentAudioBookDto.setCodePayment(String.valueOf(oj[1]));
				historyPaymentAudioBookDto.setContentPayment(String.valueOf(oj[2]));
				historyPaymentAudioBookDto.setMoneyPayment(Long.parseLong(String.valueOf(oj[3])));
				DateFormat df = new SimpleDateFormat("YYYY-MM-DD HH:MM:SS.FFFFFF");
				try {
					historyPaymentAudioBookDto.setCreatedDate(df.parse(String.valueOf(oj[4])));
				} catch (ParseException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				historyPaymentAudioBookDto.setAudioBookName(String.valueOf(oj[5]));
				historyPaymentAudioBookDto.setAudioBookCategory(String.valueOf(oj[6]));
				historyPaymentAudioBookDto.setUserName(String.valueOf(oj[7]));
				historyPaymentAudioBookDtos.add(historyPaymentAudioBookDto);
			}
		}
		return historyPaymentAudioBookDtos;
	}
	
	public List<HistoryPaymentMemberDto> searchPaymentMember(HistoryPaymentInput historyPaymentInput){
		List<Object[]> objects = historyPaymentRepository.searchPaymentMember(
				historyPaymentInput.getTerm(),
				historyPaymentInput.getUserName(),
				historyPaymentInput.getFromDate(),
				historyPaymentInput.getToDate());
		List<HistoryPaymentMemberDto> historyPaymentMemberDtos = new ArrayList<>();
		for(Object[] oj: objects) {
			if(oj != null) {
				HistoryPaymentMemberDto historyPaymentMemberDto = new HistoryPaymentMemberDto();
				historyPaymentMemberDto.setCodePayment(String.valueOf(oj[0]));
				historyPaymentMemberDto.setUserName(String.valueOf(oj[1]));
				historyPaymentMemberDto.setTerm(Long.parseLong(String.valueOf(oj[2])));
				historyPaymentMemberDto.setMoneyPayment(Long.parseLong(String.valueOf(oj[3])));
				DateFormat df = new SimpleDateFormat("YYYY-MM-DD HH:MM:SS.FFFFFF");
				try {
					historyPaymentMemberDto.setCreatedDate(df.parse(String.valueOf(oj[4])));
					historyPaymentMemberDto.setContentPayment(String.valueOf(oj[5]));
					historyPaymentMemberDto.setStartDate(df.parse(String.valueOf(oj[6])));
					historyPaymentMemberDto.setEndDate(df.parse(String.valueOf(oj[7])));
				} catch (ParseException e) {
					e.printStackTrace();
				}
				historyPaymentMemberDtos.add(historyPaymentMemberDto);
			}
		}
		return historyPaymentMemberDtos;
	}
	
	public String buildExportMemberResultFile(List<HistoryPaymentMemberDto> historyPaymentMemberDtos) {
        String templateFolder = this.templateFolder;
        String fileName = "Report_member_payment_result" + Calendar.getInstance().getTimeInMillis() + ".xls";
        String fileNameFull = this.fileNameFullFolder + File.separator + fileName;
        String fileTemplate = "Template_report_member_payment.xls";
        File templateFile = new File(templateFolder, fileTemplate);
        try {
            InputStream is = new FileInputStream(templateFile);
            File fileResult = new File(fileNameFull);
            OutputStream os = new FileOutputStream(fileResult);
            Context context = new Context();
            context.putVar("historyPaymentMemberDto", historyPaymentMemberDtos);
            JxlsHelper.getInstance().processTemplate(is, os, context);
            return fileNameFull;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }
	
	public TotalDataHomeDto totalDataHomeDto(){
		TotalDataHomeDto totalDataHomeDto = new TotalDataHomeDto();
		totalDataHomeDto.setTotalReadingBook(historyPaymentRepository.getTotalReadingBoook());
		totalDataHomeDto.setTotalAudioBook(historyPaymentRepository.getTotalReadingAudioBook());
		totalDataHomeDto.setTotalPaymentInMonth(historyPaymentRepository.getTotalPaymentInMonth());
		totalDataHomeDto.setTotalMoneyPaymentInMonth(historyPaymentRepository.getTotalMoneyPaymentInMonth());
		totalDataHomeDto.setTotalMember(historyPaymentRepository.getTotalMember());
		totalDataHomeDto.setTotalMemberRegisInMonth(historyPaymentRepository.getTotalMemberRegisInMonth());
		return totalDataHomeDto;
	}
}
