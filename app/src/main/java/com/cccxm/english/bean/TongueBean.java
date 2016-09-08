package com.cccxm.english.bean;

import java.util.List;

/**
 * 菩提本无树
 * 明镜亦非台
 * 本来无一物
 * 何处惹尘埃
 * 陈小默 16/9/8.
 */

public class TongueBean {

    /**
     * en : How much?
     * ch : 多少钱？
     */

    private List<Trans> list;

    public List<Trans> getList() {
        return list;
    }

    public void setList(List<Trans> list) {
        this.list = list;
    }

    public static class Trans {
        private String en;
        private String ch;

        public String getEn() {
            return en;
        }

        public void setEn(String en) {
            this.en = en;
        }

        public String getCh() {
            return ch;
        }

        public void setCh(String ch) {
            this.ch = ch;
        }
    }
}
