package com.example.pctol.pojo.VO;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @author hp
 * @date 2024/5/16
 */
@AllArgsConstructor
@NoArgsConstructor
@Data
public class StuTeamVO {
    private Long id;
    private String name;
    private Long th_id;
    private String thName;
    //完成，数字表示
    private int[] finishs;
    //完成，百分比表示
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
