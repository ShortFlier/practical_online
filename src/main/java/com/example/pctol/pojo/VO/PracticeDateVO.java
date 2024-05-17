package com.example.pctol.pojo.VO;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Set;

/**
 * @author hp
 * @date 2024/5/17
 */
@AllArgsConstructor
@NoArgsConstructor
@Data
public class PracticeDateVO {
    private Set<LocalDate> dateTime;
    private List<String> date;
    private List<Integer> number;

}
