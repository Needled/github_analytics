package com.futurice.test.demo.domain;

//import User;

import java.io.Serializable;
import java.util.Date;

public class Comment implements Serializable {
    private static final long serialVersionUID = 5128896032791651031L;
    private Date createdAt;
    private Date updatedAt;
    private String body;
    private String bodyHtml;
    private String bodyText;
    private long id;
    private String url;
//    private User user;

    public Comment() {
    }

    public Date getCreatedAt() {
        return this.createdAt;
    }

    public Comment setCreatedAt(Date createdAt) {
        this.createdAt = createdAt;
        return this;
    }

    public Date getUpdatedAt() {
        return this.updatedAt;
    }

    public Comment setUpdatedAt(Date updatedAt) {
        this.updatedAt = updatedAt;
        return this;
    }

    public String getBody() {
        return this.body;
    }

    public Comment setBody(String body) {
        this.body = body;
        return this;
    }

    public String getBodyHtml() {
        return this.bodyHtml;
    }

    public Comment setBodyHtml(String bodyHtml) {
        this.bodyHtml = bodyHtml;
        return this;
    }

    public String getBodyText() {
        return this.bodyText;
    }

    public Comment setBodyText(String bodyText) {
        this.bodyText = bodyText;
        return this;
    }

    public long getId() {
        return this.id;
    }

    public Comment setId(long id) {
        this.id = id;
        return this;
    }

    public String getUrl() {
        return this.url;
    }

    public Comment setUrl(String url) {
        this.url = url;
        return this;
    }

//    public User getUser() {
//        return this.user;
//    }
//
//    public Comment setUser(User user) {
//        this.user = user;
//        return this;
//    }
}

