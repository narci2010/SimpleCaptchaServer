package org.small.model;

/**
 * Result 返回实体类Model
 *
 * @author jiang haiguo
 * @date 2016/7/29
 */
public class Result {

    private boolean success;

    private Object data;

    public static Result ok(Object data) {
        Result r = new Result();
        r.success = true;
        r.data = data;
        return r;
    }

    public static Result error( Object data) {
        Result r = new Result();
        r.success = false;
        r.data = data;
        return r;
    }

    public boolean getSuccess() {
        return success;
    }

    public void setSuccess(boolean success) {
        this.success = success;
    }

    public Object getData() {
        return data;
    }

    public void setData(Object data) {
        this.data = data;
    }
}