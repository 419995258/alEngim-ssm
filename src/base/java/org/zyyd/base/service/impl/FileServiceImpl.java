package org.zyyd.base.service.impl;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;

import org.apache.commons.beanutils.BeanUtils;
import org.apache.commons.beanutils.ConvertUtils;
import org.apache.commons.beanutils.converters.DateConverter;
import org.apache.commons.fileupload.disk.DiskFileItem;
import org.apache.commons.lang3.StringUtils;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.mgt.RealmSecurityManager;
import org.apache.shiro.session.Session;
import org.apache.shiro.session.mgt.eis.SessionDAO;
import org.apache.shiro.subject.PrincipalCollection;
import org.apache.shiro.subject.Subject;
import org.apache.shiro.subject.support.DefaultSubjectContext;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.commons.CommonsMultipartFile;
import org.zyyd.base.dao.BaseUserMapper;
import org.zyyd.base.dao.vo.UserMapperExt;
import org.zyyd.base.entity.BaseRole;
import org.zyyd.base.entity.BaseUser;
import org.zyyd.base.entity.BaseUserExample;
import org.zyyd.base.entity.vo.BaseUserVO;
import org.zyyd.base.entity.vo.Message;
import org.zyyd.base.entity.vo.ResultVo;
import org.zyyd.base.service.BaseUserService;
import org.zyyd.base.service.FileService;
import org.zyyd.base.service.RedisService;
import org.zyyd.base.util.BasicService;
import org.zyyd.base.util.DateFormater;
import org.zyyd.base.util.FileOperationUtil;
import org.zyyd.base.util.MD5;
import org.zyyd.base.util.StringContentUtil;

import java.io.File;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Date;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.UUID;

import shiro.ShiroRealm;

@Service
public class FileServiceImpl extends BasicService implements FileService {



    @Override
    public Message saveTempFiles(MultipartFile[] file,String id)  {
        Message message = new Message();
        Map<String, Object> fm = new HashMap<String, Object>();

        try {
            if (file != null && file.length > 0) {
                // Date createTime = DateUtil.getCurrentDate(DateUtil.DATE_STYLE5);

                for (int i = 0; i < file.length; i++) {
                    MultipartFile f = file[i];
                    String fileName = f.getOriginalFilename(); // 文件名
                    String diskFileName = StringContentUtil.getUuid(); // 保存后的文件名
                    CommonsMultipartFile cf = (CommonsMultipartFile) f;
                    DiskFileItem fi = (DiskFileItem) cf.getFileItem();
                    File f1 = fi.getStoreLocation();
                    Message m = FileOperationUtil.saveFileToDisk(f1, fileName,
                            diskFileName, "/upload/" + id);

                    /*
                     * 返回文件url地址
                     */

                    if (m.getSuccess()) {
                        try {
                            fm.put("fileUrl", (String) m.getResult().get("filePath"));
                            message.setSuccess(true);
                            message.setResult(fm);

                            // 删除文件
                            // FileOperationUtil.deleteFile2(path);
                        } catch (Exception e) {
                            Object o = m.getResult().get("savefile");

                            File ef = (File) o;
                            if (ef.exists()) {
                                ef.delete();
                            }
                            throw e;
                        }
                    }
                }
            }
            message.setResult(fm);
        } catch (Exception e) {
            e.printStackTrace();
        }

        return message;
    }

}

