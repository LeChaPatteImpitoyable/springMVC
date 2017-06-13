package com.doshr.xmen.backend.client.account.response;

import com.doshr.xmen.backend.client.account.dto.AccountDTO;
import com.doshr.xmen.backend.client.base.BaseResponse;

public class GetAccountResp extends BaseResponse {

	/**
	 * 
	 */
	private static final long serialVersionUID = 8102274764104270123L;
	
	private AccountDTO accountDTO;

	public AccountDTO getAccountDTO() {
		return accountDTO;
	}

	public void setAccountDTO(AccountDTO accountDTO) {
		this.accountDTO = accountDTO;
	}
	
	

}
