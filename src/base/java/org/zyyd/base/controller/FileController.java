package org.zyyd.base.controller;


import org.apache.commons.lang3.StringUtils;
import org.apache.http.HttpRequest;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;
import org.zyyd.base.entity.BaseUser;
import org.zyyd.base.entity.vo.BaseUserVO;
import org.zyyd.base.entity.vo.Message;
import org.zyyd.base.entity.vo.ResultVo;
import org.zyyd.base.service.BaseUserService;
import org.zyyd.base.service.FileService;
import org.zyyd.base.util.BasicController;

import java.util.HashMap;
import java.util.Map;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;

//@ApiIgnore  //  忽略扫描该类，api
@Controller  //RestController代表controller,但是是和ResponseBody结合
@RequestMapping("base/fileC")
@Api(value = "base/fileC", tags = "上传", description = "上传")
public class FileController extends BasicController {

    @Autowired
    private FileService fileService;

    /**
     * 保存上传文件
     * @Description: TODO
     * @param
     * @return
     * @throws
     * @author pengbin <pengbin>
     * 2018/5/30 15:40
     */
    @ResponseBody
    @RequestMapping(value = "/uploadTemp", method = RequestMethod.POST)
    public Map<String, Object> uploadTemp(@RequestParam(value = "imgs") MultipartFile[] file,String id) throws Exception {
        Message message = new Message();
        Map dataMap = new HashMap();
        // Integer orgId = (Integer) session.getAttribute("currentOrgId");
        message = fileService.saveTempFiles(file,id);

        dataMap.put("success", message.getSuccess());
        if (message.getSuccess()) {
            dataMap.put("fileId", message.getResult().get("fileId"));
            dataMap.put("fileUrl", message.getResult().get("fileUrl"));
        }
        return dataMap;
    }



}
