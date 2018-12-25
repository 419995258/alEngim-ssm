package org.zyyd.base.controller;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;

import org.apache.commons.beanutils.BeanUtils;
import org.apache.commons.beanutils.ConvertUtils;
import org.apache.commons.lang3.ObjectUtils;
import org.apache.commons.lang3.StringUtils;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.zyyd.base.entity.BaseArea;
import org.zyyd.base.entity.BasePermission;
import org.zyyd.base.entity.vo.BaseAreaVO;
import org.zyyd.base.entity.vo.Message;
import org.zyyd.base.service.BaseAreaService;
import org.zyyd.base.util.BasicController;
import org.zyyd.base.util.ObjectUtil;
import org.zyyd.engin.controller.AlLearningObjectController;

import java.lang.reflect.InvocationTargetException;
import java.util.Iterator;
import java.util.List;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;

/**
 * ClassName: BaseAreaController
 * @Description: 地区controller
 * @author pengbin <pengbin>
 * @date  2018/12/6 10:56
 */
@Controller
@RequestMapping(value = "base/baseAreaC")
@Api(value = "base/baseAreaC", tags = "地区", description = "地区")
public class BaseAreaController extends BasicController {

    private static Logger logger = LoggerFactory.getLogger(BaseAreaController.class);


    @Autowired
    private BaseAreaService baseAreaService;


    /**
     * @Title: listBaseArea
     * @Description:  查询地区，如果id为空，则全查，否则为查询单个
     * @author pengbin <pengbin>
     * @date 2018/12/6 10:56
     * @param [baseArea]
     * @return org.zyyd.base.entity.vo.Message
     * @throws
     *
     *
     */
    @RequestMapping(value="/listBaseArea",method = RequestMethod.POST)
    @ResponseBody
    @ApiOperation(value = "查询地区")
    @RequiresPermissions("area:list")
    public Message listBaseArea(@RequestBody BaseAreaVO baseAreaVO){
        Message message = new Message();

        BaseArea baseArea = new BaseArea();

        try {

            BeanUtils.copyProperties(baseArea,baseAreaVO);
            List<BaseArea> baseBaseAreaList = baseAreaService.listBaseArea(baseArea);

            if(StringUtils.isBlank(baseArea.getAreaCode())){

                String jsonStr = JSONObject.toJSONString(baseBaseAreaList);
                JSONArray jsonArray = JSON.parseArray(jsonStr);
                for (Iterator<Object> iterator = jsonArray.iterator(); iterator.hasNext(); ) {
                    JSONObject json = (JSONObject) iterator.next();
                    String code = json.getString("areaCode");
                    if("-1".equals(code)){
                        // 数根节点
                        json.put("isParent","true");
                        json.put("open","true");
                    }else{
                        // 设置数都默认不展开
                        json.put("open","false");
                    }

                    json.remove("modUser");
                    json.remove("creUser");
                    json.remove("areaType");

                }
                message.setObj(jsonArray);
                message.setSuccess(true);
            }else{
                if(baseBaseAreaList.size() > 0){
                    message.setObj(baseBaseAreaList.get(0));
                }
                message.setSuccess(true);
            }
        } catch (Exception e) {
            e.printStackTrace();
            message.setMessage("发生异常");
            logger.error(e.toString());
        }

        return message;
    }


    /**
     * @Title: listBaseAreaByParentCode
     * @Description:  查询地区，如果id为空，则全查，否则为查询单个
     * @author pengbin <pengbin>
     * @date 2018/12/6 10:57
     * @param [baseArea]
     * @return org.zyyd.base.entity.vo.Message
     * @throws
     *
     *
     */
    @RequestMapping(value="/listBaseAreaByParentCode",method = RequestMethod.POST)
    @ResponseBody
    @ApiOperation(value = "查询地区的子地区")
    @RequiresPermissions("area:list")
    public Message listBaseAreaByParentCode(@RequestBody BaseAreaVO baseAreaVO){
        Message message = new Message();
        BaseArea baseArea = new BaseArea();

        try {
            BeanUtils.copyProperties(baseArea,baseAreaVO);
            if(StringUtils.isNotBlank(baseArea.getAreaCode())){
                List<BaseArea> baseBaseAreaList = baseAreaService.listBaseAreaByParentCode(baseArea);
                message.setObj(baseBaseAreaList);
                message.setSuccess(true);
            }else{
                message.setMessage("areaCode不能为空");
            }
        } catch (Exception e) {
            e.printStackTrace();
            logger.error(e.toString());
        }

        return message;
    }


    /**
     * @Title: saveBaseArea
     * @Description: 保存修改的地区
     * @author pengbin <pengbin>
     * @date 2018/12/6 14:20
     * @param [baseAreaVO]
     * @return org.zyyd.base.entity.vo.Message
     * @throws
     */
    @RequestMapping(value="/updateBaseArea",method = RequestMethod.POST)
    @ResponseBody
    @ApiOperation(value = "保存编辑的地区")
    @RequiresPermissions("area:update")
    public Message updateBaseArea(@RequestBody BaseAreaVO baseAreaVO){
        Message message = new Message();

        JSONObject jsonObject = new JSONObject();
        message = baseAreaService.updateBaseArea(baseAreaVO);

        return message;
    }


    /**
     * @Title: saveBaseArea
     * @Description:
     * @author pengbin <pengbin>
     * @date 2018/12/6 15:06
     * @param [baseAreaVO]
     * @return org.zyyd.base.entity.vo.Message
     * @throws
     */
    @RequestMapping(value="/saveBaseArea",method = RequestMethod.POST)
    @ResponseBody
    @ApiOperation(value = "添加地区")
    @RequiresPermissions("area:update")
    public Message saveBaseArea(@RequestBody BaseAreaVO baseAreaVO){
        Message message = new Message();

        JSONObject jsonObject = new JSONObject();
        message = baseAreaService.saveBaseArea(baseAreaVO);

        return message;
    }


    /**
     * @Title: deleteBaseArea
     * @Description: 删除某个地区
     * @author pengbin <pengbin>
     * @date 2018/12/6 15:31
     * @param [baseAreaVO]
     * @return org.zyyd.base.entity.vo.Message
     * @throws
     */
    @RequestMapping(value="/deleteBaseArea",method = RequestMethod.POST)
    @ResponseBody
    @ApiOperation(value = "删除地区")
    @RequiresPermissions("area:update")
    public Message deleteBaseArea(@RequestBody BaseAreaVO baseAreaVO){
        Message message = new Message();

        JSONObject jsonObject = new JSONObject();
        message = baseAreaService.deleteBaseArea(baseAreaVO);

        return message;
    }


    /**
     * @Title: updateBaseAreaSeqNo
     * @Description: 更新地区的排序
     * @author pengbin <pengbin>
     * @date 2018/12/6 21:27
     * @param [baseAreaVO]
     * @return org.zyyd.base.entity.vo.Message
     * @throws
     */
    @RequestMapping(value="/updateBaseAreaSeqNo",method = RequestMethod.POST)
    @ResponseBody
    @ApiOperation(value = "更新地区的排序")
    @RequiresPermissions("area:update")
    public Message updateBaseAreaSeqNo(@RequestBody BaseAreaVO baseAreaVO){
        Message message = new Message();

        JSONObject jsonObject = new JSONObject();
        message = baseAreaService.updateBaseAreaSeqNo(baseAreaVO);

        return message;
    }
}
