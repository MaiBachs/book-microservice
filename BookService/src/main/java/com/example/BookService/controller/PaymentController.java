package com.example.BookService.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.example.BookService.config.VNPayService;
import com.example.BookService.entity.MemberManager;
import com.example.BookService.service.MemberManagerService;

import jakarta.servlet.http.HttpServletRequest;

@Controller
@RequestMapping("/api/book-service/payment")
public class PaymentController {
	@Autowired
    private VNPayService vnPayService;
	@Autowired
	private MemberManagerService memberManagerService;
	
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
        	MemberManager memberManager = new MemberManager();
        	memberManager.setUserId(Long.valueOf(info[1]));
        	memberManager.setTerm(Long.valueOf(info[2]));
        	memberManagerService.register(memberManager);
        }

        return paymentStatus == 1 ? "ordersuccess" : "orderfail";
    }
}
