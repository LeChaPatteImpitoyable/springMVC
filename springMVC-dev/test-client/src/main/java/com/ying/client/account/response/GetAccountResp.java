package com.ying.client.account.response;

import com.ying.client.account.dto.AccountDTO;
import com.ying.client.base.BaseResponse;

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
