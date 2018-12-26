package org.zyyd.base.service;


import com.alibaba.fastjson.JSONArray;

import org.springframework.web.multipart.MultipartFile;
import org.zyyd.base.entity.BaseUser;
import org.zyyd.base.entity.vo.BaseUserVO;
import org.zyyd.base.entity.vo.Message;
import org.zyyd.base.entity.vo.ResultVo;

import java.util.List;

/**  上传
 * ClassName: FileService
 * @Description: 
 * @author pengbin <pengbin>
 * @date  2018/11/16 15:43
 */
public interface FileService {


    Message saveTempFiles(MultipartFile[] file,String id);


}
