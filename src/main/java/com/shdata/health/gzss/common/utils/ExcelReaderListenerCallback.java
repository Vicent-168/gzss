package com.shdata.health.gzss.common.utils;


import java.util.List;
import java.util.Map;

public interface ExcelReaderListenerCallback<T> {
    /**
     * 数据处理
     * @param data 数据
     * @param headMap 表头
     */
    void convertData(List<T> data, Map<Integer,String> headMap);
}
