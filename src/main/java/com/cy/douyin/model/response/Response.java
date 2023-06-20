package com.cy.douyin.model.response;

import lombok.Data;

import java.io.Serializable;

@Data
public class Response implements Serializable {
    private int status_code;
    private String status_msg;

    public Response() {
    }

    public Response(int status_code, String status_msg) {
        this.status_code = status_code;
        this.status_msg = status_msg;
    }
}
