package com.shdata.health.gzss;

import com.shdata.health.common.gen.CodeInfo;
import com.shdata.health.common.gen.GenUtil;


public class XgbGenCode {

    public static void main(String[] args) {
        genSfxshjc();
    }

    private static void genDaJbqk() {
//        CodeInfo codeInfo = new CodeInfo();
//        codeInfo.setPackagePath("com.shdata.health.gzss"); //包路径
//        codeInfo.setModule("sys");//模块
//        codeInfo.setAuthor("xgb");//作者
//        codeInfo.setTableName("CDC_TB_DA_JBQK"); //表名
//        codeInfo.setClassName("DaJbqk"); //java类名
//        codeInfo.setEntityName("档案基本情况"); //实体中文名
//        codeInfo.setSearchs("");//搜索字段，多个字段逗号隔开
//        codeInfo.setExcel(false); //是否需要Excel导入导出
//        codeInfo.setController(true); //是否需要Controller
//        GenUtil.genCode(codeInfo);


    }


    private static void genSfxshjc() {
        CodeInfo codeInfo = new CodeInfo();
        codeInfo.setPackagePath("com.shdata.health.gzss"); //包路径
        codeInfo.setModule("common");//模块
        codeInfo.setAuthor("丁文韬");//作者
        codeInfo.setTableName("CDC_TB_PG_JG_XQ"); //表名
        codeInfo.setClassName("PgJgXq"); //java类名
        codeInfo.setEntityName("评估_结果_详情"); //实体中文名
        codeInfo.setSearchs("");//搜索字段，多个字段逗号隔开
        codeInfo.setExcel(false); //是否需要Excel导入导出
        codeInfo.setController(true); //是否需要Controller
        GenUtil.genCode(codeInfo);
    }



}
