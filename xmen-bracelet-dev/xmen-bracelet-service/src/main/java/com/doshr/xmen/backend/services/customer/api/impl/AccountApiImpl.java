package com.doshr.xmen.backend.services.customer.api.impl;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;

import com.doshr.xmen.backend.client.account.api.AccountApi;
import com.doshr.xmen.backend.client.account.dto.AccountDTO;
import com.doshr.xmen.backend.client.account.request.GetAccountReq;
import com.doshr.xmen.backend.client.account.response.GetAccountResp;
import com.doshr.xmen.backend.client.base.RespCode;
import com.doshr.xmen.backend.client.base.RespStatus;
import com.doshr.xmen.backend.common.exception.BackendException;
import com.doshr.xmen.backend.services.customer.ICustomerService;

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
