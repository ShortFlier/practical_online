package com.example.pctol.common.utils;

import com.alibaba.excel.context.AnalysisContext;
import com.alibaba.excel.read.listener.ReadListener;
import com.alibaba.excel.util.ListUtils;
import com.alibaba.fastjson.JSON;
import com.example.pctol.server.service.TopicService;
import lombok.SneakyThrows;
import lombok.extern.slf4j.Slf4j;

import java.util.List;

/**
 * excel读取监听器
 *
 * @author hp
 * @date 2024/4/1
 */
@Slf4j
public class DataListener<T> implements ReadListener<T> {
    /**
     * 每隔5条存储数据库，实际使用中可以100条，然后清理list ，方便内存回收
     */
    private static final int BATCH_COUNT = 100;
    /**
     * 缓存的数据
     */
    private List<T> cachedDataList = ListUtils.newArrayListWithExpectedSize(BATCH_COUNT);
    //题目类型
    private int type;

//    写入数据库所需的mapper
    private TopicService topicService;

    public DataListener(TopicService topicService,int type){
        this.type=type;
        this.topicService=topicService;
    }

    /**
     * 这个每一条数据解析都会来调用
     *
     * @param data    one row value. Is is same as {@link AnalysisContext#readRowHolder()}
     * @param
     */
    @SneakyThrows
    @Override
    public void invoke(T data, AnalysisContext analysisContext) {
        log.info("解析到一条数据:{}", JSON.toJSONString(data));
        cachedDataList.add(data);
        // 达到BATCH_COUNT了，需要去存储一次数据库，防止数据几万条数据在内存，容易OOM
        if (cachedDataList.size() >= BATCH_COUNT) {
            saveData();
            // 存储完成清理 list
            cachedDataList = ListUtils.newArrayListWithExpectedSize(BATCH_COUNT);
        }
    }
    /**
     * 所有数据解析完成了 都会来调用
     *
     * @param
     */
    @SneakyThrows
    @Override
    public void doAfterAllAnalysed(AnalysisContext analysisContext) {
        // 这里也要保存数据，确保最后遗留的数据也存储到数据库
        saveData();
        log.info("所有数据解析完成！");
    }

    /**
     * 加上存储数据库
     */
    private void saveData() throws Exception {
        log.info("{}条数据，开始存储数据库！", cachedDataList.size());
        topicService.save(cachedDataList,type);
        log.info("存储数据库成功！");
    }
}
