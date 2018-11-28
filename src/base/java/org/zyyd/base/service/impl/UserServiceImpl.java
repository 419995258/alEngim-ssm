package org.zyyd.base.service.impl;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.zyyd.base.dao.BaseUserMapper;
import org.zyyd.base.dao.vo.UserMapperExt;
import org.zyyd.base.entity.BaseUser;
import org.zyyd.base.entity.BaseUserExample;
import org.zyyd.base.service.UserService;
import org.zyyd.base.util.BasicService;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

//import org.springframework.web.multipart.MultipartFile;

/**
 * Created by pb on 2018/5/22.
 */

@Service
public class UserServiceImpl extends BasicService implements UserService {


    @Autowired
    private BaseUserMapper baseUserMapper;

    @Autowired
    private UserMapperExt userMapperExt;



    @Override
    public List<BaseUser> getUserList(BaseUser user) {
        List<BaseUser> userList = new ArrayList<>();
        BaseUserExample userExample = new BaseUserExample();
        userExample.createCriteria().andLoginNameEqualTo(user.getLoginName())
                .andPassWordEqualTo(user.getPassWord());
        userList = baseUserMapper.selectByExample(userExample);

        //test1 = userMapper.test();

        //test1 = userMapper.selectRoleByUserId(1);

        //test1 = userMapper.selectRolePermissionByUserId(1);
        return userList;
    }

    /**
     * 
     * @Description:
     * @param    
     * @return 
     * @throws
     * @author pengbin <pengbin>
     * 2018/10/31 14:15
     */
    @Override
    public BaseUser getUser(BaseUser user) throws Exception {
        BaseUserExample userExample = new BaseUserExample();
        userExample.createCriteria().andLoginNameEqualTo(user.getLoginName())
                .andPassWordEqualTo(user.getPassWord());

        List<BaseUser> userList = new ArrayList<>();
        userList = baseUserMapper.selectByExample(userExample);

        if(userList.size() > 0){
            user = userList.get(0);
        }else{
            user = new BaseUser();
        }

        return user;
    }



    /**
     * 获取user通过username
     * @Description:
     * @param    
     * @return 
     * @throws
     * @author pengbin <pengbin>
     * 2018/10/31 14:26
     */
    @Override
    public BaseUser getUserByUserName(String userName)  {
        BaseUser user = new BaseUser();
        BaseUserExample userExample = new BaseUserExample();
        userExample.createCriteria().andLoginNameEqualTo(userName).andUserStatusEqualTo("1");

        List<BaseUser> userList = new ArrayList<>();
        userList = baseUserMapper.selectByExample(userExample);

        if(userList.size() > 0){
            user = userList.get(0);
        }else{
            user = new BaseUser();
        }

        return user;
    }


}
