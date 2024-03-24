package com.example.pctol.common.properties;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

/**
 * @author hp
 * @date 2024/3/24
 */
@Component
@Data
public class JWTproperties {
    /**
     * 管理端员工生成jwt令牌相关配置
     */
//    设置jwt签名加密时使用的秘钥
    private String adminSecretKey="practical_online_login";
//    设置jwt过期时间(1天)
    private long adminTtl=86400000;
//    设置前端传递过来的令牌名称
    private String adminTokenName="admintoken";

    /**
     * 教师生成jwt令牌相关配置
     */
    private String teacherSecretKey="practical_online_login";
    private long teacherTtl=86400000;
    private String teacherTokenName="teachertoken";

    /**
     * 学生生成jwt令牌相关配置
     */
    private String studentSecretKey="practical_online_login";
    private long studentTtl=86400000;
    private String studentTokenName="studenttoken";
}
