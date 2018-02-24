package com.example.module_web.public_data;

/**
 * Created by Administrator on 2018/2/22.
 */

public class Constant {
    public static final String MAIN_HOME = "控制面板";
    public static final String[] GROUPS = {"订单管理", "水浒传", "三国演义", "红楼梦"};
    public static final String[][] CHILDREN = {
            {"详情订单", "订单分析", "订单发货", "订单图片"},
            {"会员查看"},
            {"曹操", "刘备", "孙权", "诸葛亮", "周瑜"},
            {"贾宝玉", "林黛玉", "薛宝钗", "王熙凤"}
    };
    public static final String DB_NAME = "left_menu.db";
    public static final int DB_VERSION = 1;
    public static final String TABLE_NAME_GROUP = "groupp";
    public static final String COLUMN_GROUP_NAME_ID = "_id";
    public static final String COLUMN_GROUP_NAME_KIND = "kind";
    public static final String COLUMN_GROUP_NAME_TYPE = "type";

    public static final String TABLE_NAME_CHILD = "child";
    public static final String COLUMN_CHILD_NAME_ID = "_id";
    public static final String COLUMN_CHILD_NAME_MODULE = "module";
    public static final String COLUMN_CHILD_NAME_TYPE = "type";
}
