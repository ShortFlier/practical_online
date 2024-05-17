package com.example.pctol.pojo.VO;

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
public class MemberVO {
    private Long stuId;
    private String name;
    private LocalDateTime createTime;
    private Long teamId;

    private int[] finishs;

    private String finishRate;

    public void setFinish(int[] arr){
        finishs=arr;
        //计算完成率
        int u=0,v=0;
        for (int i = 0; i < 5; i++) {
            if(arr[i]!=0){
                v++;
                if(arr[i+5]>=arr[i])
                    u++;
            }
        }
        finishRate=(int)((double)u/v*100)+"%";
    }
}
