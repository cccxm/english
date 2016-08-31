package com.cccxm.english.bean;

import java.util.ArrayList;
import java.util.List;

/**
 * 菩提本无树
 * 明镜亦非台
 * 本来无一物
 * 何处惹尘埃
 * 陈小默 16/8/31.
 */

public class HttpListResponse<R> {
    private boolean success;
    private String message;
    private ArrayList<R> data;

    public boolean isSuccess() {
        return success;
    }

    public void setSuccess(boolean success) {
        this.success = success;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public ArrayList<R> getData() {
        return data;
    }

    public void setData(ArrayList<R> data) {
        this.data = data;
    }
}
