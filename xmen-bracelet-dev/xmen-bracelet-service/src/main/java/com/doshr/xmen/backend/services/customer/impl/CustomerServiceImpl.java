package com.doshr.xmen.backend.services.customer.impl;


import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.stereotype.Service;

import com.doshr.xmen.backend.client.account.dto.AccountDTO;
import com.doshr.xmen.backend.dao.mapper.CustomerMapper;
import com.doshr.xmen.backend.dao.po.CustomerPO;
import com.doshr.xmen.backend.services.customer.ICustomerService;

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
		accountDTO.setBlacklist(customerPO.getBlacklist());
		accountDTO.setLoginDatatime(customerPO.getLoginDatatime());
		accountDTO.setRegisterDatatime(customerPO.getRegisterDatatime());
		accountDTO.setUserName(customerPO.getUserName());
		accountDTO.setMobile(customerPO.getMobile());
		accountDTO.setAge(customerPO.getAge());
		accountDTO.setCustomerType(customerPO.getCustomerType());
		accountDTO.setIdStatus(customerPO.getIdStatus());
		accountDTO.setSex(customerPO.getSex());
		accountDTO.setType(customerPO.getType());
		return accountDTO;
	}
}
