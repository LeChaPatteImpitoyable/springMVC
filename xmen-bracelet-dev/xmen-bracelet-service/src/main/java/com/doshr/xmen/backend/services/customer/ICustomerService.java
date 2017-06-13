package com.doshr.xmen.backend.services.customer;

import com.doshr.xmen.backend.client.account.dto.AccountDTO;

public interface ICustomerService {
	
	public AccountDTO getCustomer(int customerId);

}
