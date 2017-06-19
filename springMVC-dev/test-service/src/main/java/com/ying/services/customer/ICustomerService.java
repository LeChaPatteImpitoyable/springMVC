package com.ying.services.customer;

import com.ying.client.account.dto.AccountDTO;

public interface ICustomerService {
	
	public AccountDTO getCustomer(int customerId);

}
