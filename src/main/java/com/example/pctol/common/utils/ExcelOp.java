package com.example.pctol.common.utils;

import com.example.pctol.common.constant.FileConstant;
import com.example.pctol.common.properties.BaseContext;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.system.ApplicationHome;
import org.springframework.web.multipart.MultipartFile;
import java.io.File;
import java.io.IOException;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

/**
 * 接受excel先保存到本地
 *
 * @author hp
 * @date 2024/4/1
 */
@Slf4j
public class ExcelOp {
    //excel文件存放在项目文件夹resources中的static中excel

    public String getSavePath() {
        // 这里需要注意的是ApplicationHome是属于SpringBoot的类
        // 获取项目下resources/static/excel路径
        ApplicationHome applicationHome = new ApplicationHome(this.getClass());

        // 保存目录位置根据项目需求可随意更改
        return applicationHome.getDir().getParentFile()
                .getParentFile().getAbsolutePath() + "\\src\\main\\resources\\static\\excel";
    }

    public List<String> save(MultipartFile file, Integer type, String msg) throws Exception{
        if (file.isEmpty()) {
            throw new Exception(FileConstant.NULL_FILE);
        }
        if(!file.getOriginalFilename().contains("xls")){
            throw new Exception(FileConstant.NOT_AN_EXCEL);
        }
        // 给文件重命名
//        String fileName = UUID.randomUUID() + "." + file.getContentType()
//                .substring(file.getContentType().lastIndexOf("/") + 1);
        String fileName=(msg==null?"":(msg+"&"))+BaseContext.getLoginInfo()+"&"+type+"&"+UUID.randomUUID()+"&"+file.getOriginalFilename();
        fileName=fileName.replace(":","");
        String newFileName="已处理&"+fileName;
        log.info("path:{}",fileName);
        try {
            // 获取保存路径
            String path = getSavePath();
            File files = new File(path, fileName);
            file.transferTo(files);
            List<String> paths=new ArrayList<>();
            paths.add(fileName);
            paths.add(newFileName);
            return paths;
        } catch (IOException e) {
            throw e;
        }
    }

    public void delete(String name){

    }


}
