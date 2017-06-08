package com.android.ui;

/**
 * The basic information of database
 * Created by HarryWang on 2017/4/30.
 */

public class DBInfo {
    public static class DB {
        /**
         * 数据库名称
         */
        public static final String DB_NAME = "wardrobe.db";
        /**
         * 数据库版本（从1开始，正向递增）
         */
        public static final int VERSION = 1;
    }

    public static class Table {
        /**
         * 衣服信息表名称
         */
        public static final String CLOTHES_TABLE = "clothes";
        /**
         * 主键,编号
         */
        public static final String ID = "id";
        /**
         * 衣服类型
         */
        public static final String TYPE = "type";
        /**
         * 上装或下装标记
         */
        public static final String UPORDOWN ="upordown";
        /**
         * 颜色
         */
        public static final String COLOR = "color";
        /**
         * 关键词标签
         */
        public static final String KEYWORDS = "keywords";
        /**
         * 季节
         */
        public static final String SEASON = "season";
        /**
         * 照片
         */
        public static final String PHOTO = "photo";
        /**
         * 评分
         */
        public static final String RATING="rating";
        /**
         * 是否喜欢搭配
         */
        public static final String LIKEMATCH="likematch";

        /**
         * 创建用户表语句
         */
        public static final String CREATE_CLOTHES_TABLE = "create table if not exists "
                + CLOTHES_TABLE
                + "(" + UPORDOWN + " text,"+ TYPE + " text,"+RATING+" real, "+LIKEMATCH+" integer,"
                +ID+" integer(4) primary key,"+ COLOR + " text, "+KEYWORDS+" text, "+SEASON+" text, "+PHOTO+" text); ";
        /**
         * 删除用户表空间语句
         */
        public static final String DROP_CLOTHES_TABLE="drop table"+CLOTHES_TABLE;

    }

}
