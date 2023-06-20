package com.cy.douyin.util;

import com.cy.douyin.model.security.CustomUserDetails;
import com.qiniu.common.QiniuException;
import com.qiniu.http.Response;
import com.qiniu.storage.Configuration;
import com.qiniu.storage.Region;
import com.qiniu.storage.UploadManager;
import com.qiniu.util.Auth;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.core.context.SecurityContextHolder;

import static com.cy.douyin.constant.QiniuyunConstants.*;

/**
 * @author 86147
 * create  14/6/2023 下午7:23
 */
public class QiniuUtil {

    public static void uploadBytes(byte[] bytes, String title) throws QiniuException {
        Configuration cfg = new Configuration(Region.autoRegion());
        UploadManager uploadManager = new UploadManager(cfg);
        Auth auth = Auth.create(ACCESS_KEY, SECRET_KEY);
        String upToken = auth.uploadToken(BUCKET);
        Response response = uploadManager.put(bytes, title, upToken);
        if (response.statusCode!=200) throw new QiniuException(response);
    }
}
