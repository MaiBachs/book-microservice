package com.example.BookService.controller;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.InputStream;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.InputStreamResource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.example.BookService.dto.HistoryPaymentAudioBookDto;
import com.example.BookService.dto.HistoryPaymentEbookDto;
import com.example.BookService.dto.HistoryPaymentMemberDto;
import com.example.BookService.dto.TotalDataHomeDto;
import com.example.BookService.entity.HistoryPayment;
import com.example.BookService.entity.MemberManager;
import com.example.BookService.input.HistoryPaymentInput;
import com.example.BookService.output.HistoryPaymentOutput;
import com.example.BookService.service.HistoryPaymentService;

import lombok.RequiredArgsConstructor;

@RestController
//@CrossOrigin(origins = "*")
@RequestMapping("/api/book-service/book")
@RequiredArgsConstructor
public class BookController {
	@Autowired
	private HistoryPaymentService historyPaymentService;
	
	@GetMapping(value = "/check-paid")
	public ResponseEntity<?> checkPaid(@RequestParam("userId") Long userId, @RequestParam("bookId") Long bookId){
		HistoryPayment historyPayment = historyPaymentService.findByUserIdAndBookId(userId, bookId);
		return ResponseEntity.ok(historyPayment);
	}
	// no use
	@PostMapping(value = "/get-history-by-page")
    public ResponseEntity<HistoryPaymentOutput> findBookByPage(@RequestBody HistoryPaymentInput historyPaymentInput){
        HistoryPaymentOutput historyPaymentOutput = historyPaymentService.getHisByPage(historyPaymentInput.getPage()-1, historyPaymentInput.getSize(), historyPaymentInput.getUserId());
        return ResponseEntity.ok(historyPaymentOutput);
    }
	
	@GetMapping(value = "/get-history-payment-book-by-user")
    public ResponseEntity<?> findHisPaymentBook(@RequestParam("userId") Long userId){
        List<HistoryPayment> historyPayments = historyPaymentService.findHisPaymentBook(userId);
        return ResponseEntity.ok(historyPayments);
    }
	
	@GetMapping(value = "/get-history-payment-audio-book-by-user")
    public ResponseEntity<?> findHisPaymentAudioBook(@RequestParam("userId") Long userId){
        List<HistoryPayment> historyPayments = historyPaymentService.findHisPaymentAudioBook(userId);
        return ResponseEntity.ok(historyPayments);
    }
	
	@GetMapping(value = "/get-history-payment-regiscard-by-user")
    public ResponseEntity<?> findHisPaymentRegiscard(@RequestParam("userId") Long userId){
        List<HistoryPayment> historyPayments = historyPaymentService.findHisPaymentRegiscard(userId);
        return ResponseEntity.ok(historyPayments);
    }
	
	@PostMapping(value = "/search-payment-ebook")
	public ResponseEntity<List<HistoryPaymentEbookDto>> searchPaymentEbook(@RequestBody HistoryPaymentInput historyPaymentInput){
		List<HistoryPaymentEbookDto> historyPaymentEbookDtos = historyPaymentService.searchPaymentEbook(historyPaymentInput);
		return ResponseEntity.ok(historyPaymentEbookDtos);
	}
	
	@PostMapping(value = "/report-payment-ebook")
	public ResponseEntity<?> reportPaymentEbook(@RequestBody HistoryPaymentInput historyPaymentInput){
		List<HistoryPaymentEbookDto> historyPaymentEbookDtos = historyPaymentService.searchPaymentEbook(historyPaymentInput);
		File fileToDownload = new File(historyPaymentService.buildExportEbookResultFile(historyPaymentEbookDtos));
        if (fileToDownload != null) {
            try {
                InputStream inputStream = new FileInputStream(fileToDownload);
                String fileName = "export_penalty.xls";
                HttpHeaders headers = new HttpHeaders();
                headers.setContentType(MediaType.APPLICATION_OCTET_STREAM);
                headers.setContentDispositionFormData("attachment", fileName);
                InputStreamResource inputStreamResource = new InputStreamResource(inputStream);
                return ResponseEntity.ok()
                        .headers(headers)
                        .body(inputStreamResource);
            } catch (FileNotFoundException e) {
                throw new RuntimeException(e);
            }
        }
        return ResponseEntity.notFound().build();
	}
	
	@PostMapping(value = "/search-payment-audio-book")
	public ResponseEntity<List<HistoryPaymentAudioBookDto>> searchPaymentAudioBook(@RequestBody HistoryPaymentInput historyPaymentInput){
		List<HistoryPaymentAudioBookDto> historyPaymentAudioBookDtos = historyPaymentService.searchPaymentAudioBook(historyPaymentInput);
		return ResponseEntity.ok(historyPaymentAudioBookDtos);
	}
	
	@PostMapping(value = "/report-payment-audio-book")
	public ResponseEntity<?> reportPaymentAudioBook(@RequestBody HistoryPaymentInput historyPaymentInput){
		List<HistoryPaymentAudioBookDto> historyPaymentAudioBookDtos = historyPaymentService.searchPaymentAudioBook(historyPaymentInput);
		File fileToDownload = new File(historyPaymentService.buildExportAudioResultFile(historyPaymentAudioBookDtos));
        if (fileToDownload != null) {
            try {
                InputStream inputStream = new FileInputStream(fileToDownload);
                String fileName = "export_penalty.xls";
                HttpHeaders headers = new HttpHeaders();
                headers.setContentType(MediaType.APPLICATION_OCTET_STREAM);
                headers.setContentDispositionFormData("attachment", fileName);
                InputStreamResource inputStreamResource = new InputStreamResource(inputStream);
                return ResponseEntity.ok()
                        .headers(headers)
                        .body(inputStreamResource);
            } catch (FileNotFoundException e) {
                throw new RuntimeException(e);
            }
        }
        return ResponseEntity.notFound().build();
	}
	
	@PostMapping(value = "/search-payment-member")
	public ResponseEntity<List<HistoryPaymentMemberDto>> searchPaymentMember(@RequestBody HistoryPaymentInput historyPaymentInput){
		List<HistoryPaymentMemberDto> historyPaymentMemberDtos = historyPaymentService.searchPaymentMember(historyPaymentInput);
		return ResponseEntity.ok(historyPaymentMemberDtos);
	}
	
	@PostMapping(value = "/report-payment-member")
	public ResponseEntity<?> reportPaymentMember(@RequestBody HistoryPaymentInput historyPaymentInput){
		List<HistoryPaymentMemberDto> historyPaymentMemberDtos = historyPaymentService.searchPaymentMember(historyPaymentInput);
		File fileToDownload = new File(historyPaymentService.buildExportMemberResultFile(historyPaymentMemberDtos));
        if (fileToDownload != null) {
            try {
                InputStream inputStream = new FileInputStream(fileToDownload);
                String fileName = "export_penalty.xls";
                HttpHeaders headers = new HttpHeaders();
                headers.setContentType(MediaType.APPLICATION_OCTET_STREAM);
                headers.setContentDispositionFormData("attachment", fileName);
                InputStreamResource inputStreamResource = new InputStreamResource(inputStream);
                return ResponseEntity.ok()
                        .headers(headers)
                        .body(inputStreamResource);
            } catch (FileNotFoundException e) {
                throw new RuntimeException(e);
            }
        }
        return ResponseEntity.notFound().build();
	}
	
	@GetMapping(value = "/total-data-home")
	public ResponseEntity<TotalDataHomeDto> totalDataHome(){
		TotalDataHomeDto totalDataHomeDto = historyPaymentService.totalDataHomeDto();
		return ResponseEntity.ok(totalDataHomeDto);
	}
}
