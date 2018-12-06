package org.zyyd.base.service;


import org.zyyd.base.entity.BaseArea;
import org.zyyd.base.entity.vo.BaseAreaVO;
import org.zyyd.base.entity.vo.Message;

import java.util.List;
import java.util.Set;

/**  地区管理基本的一些方法
 * ClassName: BaseAreaService
 * @Description: 
 * @author pengbin <pengbin>
 * @date  2018/11/16 15:43
 */
public interface BaseAreaService {










    /**
     *   查询地区
     * @Description:
     * @param basePermission，id为空则全查
     * @return
     * @throws
     * @author pengbin <pengbin>
     * 2018/11/20 11:48
     */
    List<BaseArea> listBaseArea(BaseArea baseArea);


    /**
     *   查询地区的子地区
     * @Description:
     * @param basePermission，id为空则全查
     * @return
     * @throws
     * @author pengbin <pengbin>
     * 2018/11/20 11:48
     */
    List<BaseArea> listBaseAreaByParentCode(BaseArea baseArea);
    
    
    /** 更新编辑的地区
     * @Title: updateBaseArea
     * @Description:
     * @author pengbin <pengbin>
     * @date 2018/12/6 14:22
     * @param 
        * @param baseAreaVO
     * @return Message
     * @throws
     */
    Message updateBaseArea(BaseAreaVO baseAreaVO);


    /** 添加一个新的地区
     * @Title:
     * @Description:
     * @author pengbin <pengbin>
     * @date 2018/12/6 15:31
     * @param
        * @param null
     * @return
     * @throws
     */
    Message saveBaseArea(BaseAreaVO baseAreaVO);


    /** 删除某个地区
     * @Title:deleteBaseArea
     * @Description:删除某个地区
     * @author pengbin <pengbin>
     * @date 2018/12/6 15:32
     * @param
        * @param null
     * @return
     * @throws
     */
    Message deleteBaseArea(BaseAreaVO baseAreaVO);


    /**更新地区的排序
     * @Title: updateBaseAreaSeqNo
     * @Description:  更新地区的排序
     * @author pengbin <pengbin>
     * @date 2018/12/6 15:57
     * @param
        * @param BaseAreaVO
     * @return
     * @throws
     */
    Message updateBaseAreaSeqNo(BaseAreaVO baseAreaVO);
}
