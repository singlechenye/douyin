package com.cy.douyin.service;

import com.qiniu.common.QiniuException;

public interface PublishService  {
    public void uploadVideo(byte[] bytes,String title,String type) throws QiniuException;
}
