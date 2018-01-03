package com.wangshuai.efnews.home.json;

/**
 * @author wangshuai
 */
public class BaseJson {

    /**
     * 是否有错
     */
    private Boolean hasError = false;
    /**
     * 返回代码
     * 0 成功
     */
    private String code = "0";

    /**
     * 有错为错误信息，没错为成功提示信息
     */
    private String message = "操作成功！";

    /**
     * @return the hasError
     */
    public Boolean getHasError() {
        return hasError;
    }

    /**
     * @param hasError the hasError to set
     */
    public void setHasError(Boolean hasError) {
        this.hasError = hasError;
    }

    /**
     * @return the message
     */
    public String getMessage() {
        return message;
    }

    /**
     * @param message the message to set
     */
    public void setMessage(String message) {
        this.message = message;
    }

    /**
     * @return the code
     */
    public String getCode() {
        return code;
    }

    /**
     * @param code the code to set
     */
    public void setCode(String code) {
        this.code = code;
    }

}
