package org.small.model;

import org.small.utils.StringUtils;

/**
 * Result 返回实体类Model
 *
 * @author jiang haiguo
 * @date 2016/7/29
 */
public class Result {

    private String success;

    private String message;

    private Object data;

    public static Result ok(String message, Object data) {
        Result r = new Result();
        r.success = "0";
        r.message = message;
        r.data = data;
        return r;
    }

    public static Result error(String message, Object data) {
        Result r = new Result();
        r.success = "1";
        r.message = message;
        r.data = data;
        return r;
    }

    public static Result noAuth() {
        Result r = new Result();
        r.success = "2";
        r.message = StringUtils.string2Unicode("Forbidden");
        return r;
    }

    public String getSuccess() {
        return success;
    }

    public void setSuccess(String success) {
        this.success = success;
    }

    public Object getData() {
        return data;
    }

    public void setData(Object data) {
        this.data = data;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }
}