package com.example.pctol.pojo.VO;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @author hp
 * @date 2024/4/2
 */
@AllArgsConstructor
@NoArgsConstructor
@Data
public class TopicTotalVO {
    private Integer total;
    private Integer rdsNumber;
    private Integer mltNumber;
    private Integer jdgeNumber;
    private Integer fitbNumber;
    private Integer vocaNumber;

    public TopicTotalVO(Integer rdsNumber, Integer mltNumber, Integer jdgeNumber, Integer fitbNumber, Integer vocaNumber){
        this.rdsNumber=rdsNumber;
        this.mltNumber=mltNumber;
        this.jdgeNumber=jdgeNumber;
        this.fitbNumber=fitbNumber;
        this.vocaNumber=vocaNumber;
        total=rdsNumber+mltNumber+jdgeNumber+fitbNumber+vocaNumber;
    }

    public TopicTotalVO setTopicTotal(Integer rdsNumber, Integer mltNumber, Integer jdgeNumber, Integer fitbNumber, Integer vocaNumber){
        this.rdsNumber=rdsNumber;
        this.mltNumber=mltNumber;
        this.jdgeNumber=jdgeNumber;
        this.fitbNumber=fitbNumber;
        this.vocaNumber=vocaNumber;
        total=rdsNumber+mltNumber+jdgeNumber+fitbNumber+vocaNumber;
        return this;
    }
}
