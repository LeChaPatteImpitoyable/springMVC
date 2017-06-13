package com.doshr.xmen.backend.client.base;

import java.io.Serializable;

public abstract class BaseRequest implements Serializable{

    /**
     * 
     */
    private static final long serialVersionUID = -4294510876768494802L;

    private int customerId;


	public int getCustomerId() {
		return customerId;
	}


	public void setCustomerId(int customerId) {
		this.customerId = customerId;
	}
    
    
    
}