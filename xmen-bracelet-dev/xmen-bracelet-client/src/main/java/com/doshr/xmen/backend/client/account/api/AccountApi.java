package com.doshr.xmen.backend.client.account.api;

import com.doshr.xmen.backend.client.account.request.GetAccountReq;
import com.doshr.xmen.backend.client.account.response.GetAccountResp;

public interface AccountApi{

	
	public GetAccountResp getAccount(GetAccountReq req);
}
