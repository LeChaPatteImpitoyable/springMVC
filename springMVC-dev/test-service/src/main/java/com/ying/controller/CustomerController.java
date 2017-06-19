package com.ying.controller;


import org.apache.ibatis.annotations.Param;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import com.ying.client.account.api.AccountApi;
import com.ying.client.account.request.GetAccountReq;
import com.ying.client.account.response.GetAccountResp;

@Controller
@RequestMapping("/customer")
public class CustomerController extends JsonScreen{
	
	@Autowired
	private AccountApi accountApi;
	
	@RequestMapping("/getCustomer")
	public void getCustomer(@Param("customerId") int customerId){
		GetAccountReq req = new GetAccountReq();
		req.setCustomerId(customerId);
		GetAccountResp resp = accountApi.getAccount(req);
		returnResult(resp);
	}
	
	@RequestMapping("/oneCustomer")
	public ModelAndView  getCustomerInfo(@Param("customerId")int customerId){
		GetAccountReq req = new GetAccountReq();
		req.setCustomerId(customerId);
		GetAccountResp resp = accountApi.getAccount(req);
		
		ModelAndView mv = new ModelAndView();
		mv.setViewName("oneCustomer");
		mv.addObject("customer", resp.getAccountDTO());
		return mv;
	}
}
