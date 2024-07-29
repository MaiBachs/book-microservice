package com.example.BookService.controller;

import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.example.BookService.config.VNPayService;
import com.example.BookService.entity.HistoryPayment;
import com.example.BookService.entity.MemberManager;
import com.example.BookService.service.HistoryPaymentService;
import com.example.BookService.service.MemberManagerService;

import jakarta.servlet.http.HttpServletRequest;

@Controller
@RequestMapping("/api/book-service/payment")
public class PaymentController {
	@Autowired
    private VNPayService vnPayService;
	@Autowired
	private MemberManagerService memberManagerService;
	@Autowired
	private HistoryPaymentService historyPaymentService;
	
	@GetMapping("/vnpay-payment")
    public String GetMapping(HttpServletRequest request, Model model){
        int paymentStatus =vnPayService.orderReturn(request);

        String orderInfo = request.getParameter("vnp_OrderInfo");
        String paymentTime = request.getParameter("vnp_PayDate");
        String transactionId = request.getParameter("vnp_TransactionNo");
        String totalPrice = request.getParameter("vnp_Amount");

        model.addAttribute("orderId", orderInfo);
        model.addAttribute("totalPrice", totalPrice);
        model.addAttribute("paymentTime", paymentTime);
        model.addAttribute("transactionId", transactionId);
        
        if(paymentStatus == 1) {
        	String[] info = orderInfo.split("-");
        	if(info[3].equals("forRegisMember")) {
        		MemberManager memberManager = new MemberManager();
            	memberManager.setUserId(Long.valueOf(info[1]));
            	memberManager.setTerm(Long.valueOf(info[2]));
            	memberManagerService.register(memberManager);
            	
            	HistoryPayment historyPayment = new HistoryPayment();
        		historyPayment.setUserId(Long.valueOf(info[1]));
        		historyPayment.setTermId(Long.valueOf(info[2]));
        		historyPayment.setMoneyPayment(Long.valueOf(totalPrice));
        		historyPayment.setCreatedDate(new Date());
        		historyPayment.setCodePayment(transactionId);
        		historyPayment.setContentPayment("Thanh toán đăng kí gói hội viên "+Long.valueOf(info[2])+" tháng");
        		historyPaymentService.save(historyPayment);
        		
        	}else if(info[3].equals("forBuyBook")){
        		HistoryPayment historyPayment = new HistoryPayment();
        		historyPayment.setUserId(Long.valueOf(info[1]));
        		historyPayment.setBookId(Long.valueOf(info[2]));
        		historyPayment.setMoneyPayment(Long.valueOf(totalPrice));
        		historyPayment.setCreatedDate(new Date());
        		historyPayment.setCodePayment(transactionId);
        		historyPayment.setContentPayment("Thanh toán mua sách trả phí ");
        		historyPaymentService.save(historyPayment);
        	}
        }

        return paymentStatus == 1 ? "ordersuccess" : "orderfail";
    }
}
