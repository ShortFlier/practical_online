package com.example.pctol.pojo.VO;

import com.example.pctol.pojo.entity.Topic;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @author hp
 * @date 2024/4/22
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class TopicTestVO extends Topic {

    private Topic topic;

    private String submitAnswer;    //用户回答

    private Integer grade;      //得分

    public TopicTestVO(Topic topic){
        this.topic=topic;
    }

    @Override
    public void setNullAnswer() {
    }

    @Override
    public void setAnswer(String answer) {
    }

    @Override
    public String getAnswer() {
        return null;
    }

    @Override
    public Long getId() {
        return null;
    }
}
