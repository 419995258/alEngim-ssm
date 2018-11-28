package org.zyyd.base.service;



import org.zyyd.base.entity.BaseUser;

import java.util.List;
import java.util.Map;

/**
 * Created by pb on 2018/5/22.
 */
public interface UserService {

    /**
     * 通过user条件查询list
     * @Description:  
     * @param baseUser
     * @return userList
     * @throws
     * @author pengbin <pengbin>
     * 2018/11/15 16:24
     */
    List<BaseUser> getUserList(BaseUser baseUser);

    /**
     * 查询某个user
     * @Description: 
     * @param baseUser
     * @return user
     * @throws
     * @author pengbin <pengbin>
     * 2018/10/31 14:15
     */
    BaseUser getUser(BaseUser baseUser) throws Exception;

    /**
     * 获取user通过username
     * @Description: 
     * @param
     * @return
     * @throws
     * @author pengbin <pengbin>
     * 2018/10/31 14:26
     */
    BaseUser getUserByUserName(String userName);


   
}
