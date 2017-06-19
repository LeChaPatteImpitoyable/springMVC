package com.ying.services.customer.api.impl;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;

import com.ying.client.account.api.AccountApi;
import com.ying.client.account.dto.AccountDTO;
import com.ying.client.account.request.GetAccountReq;
import com.ying.client.account.response.GetAccountResp;
import com.ying.client.base.RespCode;
import com.ying.client.base.RespStatus;
import com.ying.common.exception.BackendException;
import com.ying.services.customer.ICustomerService;

public class AccountApiImpl implements AccountApi {
	private static final Logger LOG = LoggerFactory.getLogger(AccountApiImpl.class);

	@Autowired
	private ICustomerService customerService;
	
	@Override
	public GetAccountResp getAccount(GetAccountReq req) {
		GetAccountResp resp = new GetAccountResp();
		try {
			int customerId = req.getCustomerId();
			AccountDTO accountDTO = customerService.getCustomer(customerId);
			resp.setAccountDTO(accountDTO);
		} catch (BackendException e) {
			LOG.error(e.getMessage(), e);
			resp.setRespStatus(new RespStatus(e.getRespCode()));
		} catch (Exception e) {
			LOG.error(e.getMessage(), e);
			resp.setRespStatus(new RespStatus(RespCode.SYSTEM_ERROR));
		}
		return resp;
	}

}
