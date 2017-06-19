package com.ying.client.account.api;

import com.ying.client.account.request.GetAccountReq;
import com.ying.client.account.response.GetAccountResp;

public interface AccountApi{

	
	public GetAccountResp getAccount(GetAccountReq req);
}
