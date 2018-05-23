package com.xunmilingnan.statics;

/**
 * @author: 张红�?
 * @date: 2018/4/17 11:06
 * @Description: 用户token*
 */
public class Token {
    /**
     * 令牌
     */
    private String accessToken;
    /**
     * 有效期默�?86400
     */
    private String validTime;
    /**
     * 类型默认Bearer
     */
    private String tokenType;

    public Token(String accessToken, String validTime, String tokenType) {
        this.accessToken = accessToken;
        this.validTime = validTime;
        this.tokenType = tokenType;
    }

    /**
     * 获取令牌
     */
    public String getAccessToken() {
        return accessToken;
    }

    /**
     * 设置令牌
     */
    public void setAccessToken(String accessToken) {
        this.accessToken = accessToken;
    }

    /**
     * 获取有效期默�?86400
     */
    public String getValidTime() {
        return validTime;
    }

    /**
     * 设置有效期默�?86400
     */
    public void setValidTime(String validTime) {
        this.validTime = validTime;
    }

    /**
     * 获取令牌类型默认Bearer
     */
    public String getTokenType() {
        return tokenType;
    }

    /**
     * 设置令牌类型默认Bearer
     */
    public void setTokenType(String tokenType) {
        this.tokenType = tokenType;
    }

}
