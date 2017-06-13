package com.doshr.xmen.backend.dao.mapper;

import java.math.BigDecimal;
import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.doshr.xmen.backend.dao.po.CustomerPO;

public interface CustomerMapper
{

	/**
	 * get all fields from customer
	 * （根据客户id得到客户信息）
	 * 查customer表（客户信息表）
	 * @param cid  客户id
	 * @return 返回客户信息
	 */
	public CustomerPO getCustomerDetail ( @Param(value = "cid") int cid );

	
	/**
	 * 
	 * @param mobile
	 * @param password
	 * @return
	 */
    public CustomerPO getCustomerByPwd (@Param(value = "mobile") String mobile, 
                                        @Param(value = "password") String password);

    
	/**
	 * get customers name
	 * @param integers
	 * @return
	 */
	public List<CustomerPO> getCustomersExtend ( @Param(value = "cids") int[] cids );

	/**
	 * get customer by phone
	 * @param mobile
	 * @return
	 */
	public CustomerPO getCustomerByPhone ( String mobile );

	/**
	 * @author sc0tt insert into customer
	 * @param customerPO
	 * @return
	 */
	public int insertCustomer ( CustomerPO customerPO );

	/**
	 * update customer
	 * @param customerPO
	 * @return
	 */
	public int updateCustomer(CustomerPO customerPO);
	
	
	/**
	 * get customer by deviceToken
	 * @param deviceToken
	 * @return
	 */
	public List<CustomerPO> getCustomerByDeviceToken(String deviceToken);
	
	/**
	 * 
	 * @param oldToken
	 * @return
	 */
	public int clearDeviceToken(@Param(value = "deviceToken") String oldToken);
	
	/**
	 * 
	 * @param deviceToken
	 * @param mobile
	 * @return
	 */
	public int updateDeviceToken(@Param(value = "mobile") String mobile, @Param(value = "deviceToken") String deviceToken);
	
	
	/**
	 * 
	 * @param recommendIds
	 * @param id
	 * @return
	 */
	public int updateRecommendIds(@Param(value="recommendIds") String recommendIds,@Param(value="id") int id);
	
	/**
	 * 获取所有用户头像信息，同步到七牛
	 * @return
	 */
	public  List<CustomerPO> getAllCustomerHeaderInfos();
	
	/**
	 * 修改用户的状态
	 * @param customerId
	 * @param status
	 * @return
	 */
	public int updateCustomerStatus(@Param("customerId")int customerId,@Param("status")int status);
	

	/**
	 * 获取冻结用户列表
	 * @param offset
	 * @param length
	 * @param status
	 * @return
	 */
	public List<CustomerPO> getFrozenCustomerList(@Param(value="offset")int offset, @Param(value="length")int length);
	
	
	/**
	 * 更新黑名单列表
	 * @param customerId
	 * @param blackList
	 * @return
	 */
    public int updateBlackListById(@Param(value="customerId")int customerId, @Param(value="blackList") String blackList);
    
    
   
    
    /**
     * 获取用户　
     * 用于订正用户关注店铺数据
     * @param offset
     * @param length
     * @return
     */
    public List<CustomerPO> selectCustomer(@Param(value="offset")int offset,@Param(value="length")int length);

    /**
     * 获取所有用户
     * @return
     */
    public  List<CustomerPO> getAllCustomer();
    
    /**
     * 根据微信标识获取用户信息
     * @param openId
     * @return
     */
    public CustomerPO getAccountByOpenId(@Param(value="openId")String openId);
    
    /**
     * 解除微信绑定
     * @param mobile
     * @param openId
     * @return
     */
    public int clearWechatOpenId(@Param(value="mobile")String mobile,@Param(value="openId")String openId);
    
    /**
     * 绑定微信
     * @param mobile
     * @param openId
     * @return
     */
    public int bindWechat(@Param(value="mobile")String mobile,@Param(value="openId")String openId);
    
    /**
     * 获取总用户数
     * @return
     */
    public BigDecimal getAllAccountNumber();
}
