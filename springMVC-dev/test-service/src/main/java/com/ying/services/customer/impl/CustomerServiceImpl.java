package com.ying.services.customer.impl;


import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.stereotype.Service;

import com.ying.common.util.XmenUtils;
import com.ying.client.account.dto.AccountDTO;
import com.ying.dao.mapper.CustomerMapper;
import com.ying.dao.po.CustomerPO;
import com.ying.services.customer.ICustomerService;

//@Service("customerService")
public class CustomerServiceImpl implements ICustomerService{
	
	@Autowired
	private CustomerMapper customerMapper;


	public AccountDTO getCustomer(int customerId) {
		CustomerPO customerPO = customerMapper.getCustomerDetail(customerId);
		
		return conver2DTO(customerPO);
		
	}	
	
	private AccountDTO conver2DTO(CustomerPO customerPO){
		if(customerPO == null){
			return null;
		}
		AccountDTO accountDTO = new AccountDTO();
		accountDTO.setId(customerPO.getId());
		accountDTO.setRegisterDatatime(XmenUtils.formatDate(customerPO.getCreateTime(), XmenUtils.DATE_FORMAT_SECONDS));
		accountDTO.setLoginDatatime(XmenUtils.formatDate(customerPO.getLoginDatatime(), XmenUtils.DATE_FORMAT_SECONDS));
		accountDTO.setUserName(customerPO.getUserName());
		accountDTO.setAge(customerPO.getAge());
		accountDTO.setSex(customerPO.getSex());
		accountDTO.setRoleNum(customerPO.getRoleNum());
		accountDTO.setAccount(customerPO.getLoginAccount());
		accountDTO.setStatus(customerPO.getStatus());
		return accountDTO;
	}
}
