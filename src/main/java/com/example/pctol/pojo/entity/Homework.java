package com.example.pctol.pojo.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

/**
 * @author hp
 * @date 2024/5/15
 */
@AllArgsConstructor
@NoArgsConstructor
@Data
public class Homework {
    private Long teamId;
    private String difficulty;
    private Long subjectId;
    private LocalDateTime createTime;
    private LocalDateTime updateTime;
    private LocalDateTime overTime;
    private int radios;
    private int muls;
    private int judg;
    private int fitb;
    private int vocal;

    public boolean good(){
        return teamId!=null&&difficulty!=null&&subjectId!=null&&overTime!=null;
    }

    public Homework(Homework original) {
        this.teamId = original.getTeamId();
        this.difficulty = original.getDifficulty();
        this.subjectId = original.getSubjectId();
        this.createTime = original.getCreateTime();
        this.updateTime = original.getUpdateTime();
        this.overTime = original.getOverTime();
        this.radios = original.getRadios();
        this.muls = original.getMuls();
        this.judg = original.getJudg();
        this.fitb = original.getFitb();
        this.vocal = original.getVocal();
    }

    public int getNumber(int index){
        switch (index){
            case 0: return radios;
            case 1: return muls;
            case 2: return judg;
            case 3: return fitb;
            case 4: return vocal;
            default:return 0;
        }
    }
}
