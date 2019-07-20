package com.lamarsan.word_demo.controller;

import com.lamarsan.word_demo.freemarker.ExportMyWord;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.Map;

/**
 * className: FreemarkerController
 * description: TODO
 *
 * @author hasee
 * @version 1.0
 * @date 2019/7/15 15:40
 */
@RestController
@RequestMapping("/freemarker")
public class FreemarkerController {
    @GetMapping(value = "/wordExport")
    public void export() {
        ExportMyWord emw = new ExportMyWord();
        Map<String, Object> dataMap = new HashMap<String, Object>();
        dataMap.put("name", "傅泽鹏");
        dataMap.put("age", 20);
        dataMap.put("sex", "男");
        dataMap.put("imgheader", emw.getImageStr("D:\\图片\\130.png"));
        dataMap.put("phoneNum", "1358****790");
        dataMap.put("project", "计算机");
        dataMap.put("address", "杭州");
        dataMap.put("natureWork", "全职");
        dataMap.put("industry", "IT");
        dataMap.put("application", "Java开发");
        dataMap.put("time", "2017年-2021年");
        dataMap.put("schoolname", "浙江科技学院");
        dataMap.put("education", "本科");
        dataMap.put("projectExperience", "辩论小程序开发");
        dataMap.put("introduce", "喜欢二次元，编程的大学生");
        emw.createWord(dataMap, "templates/information.ftl", "D:\\记事本\\公司\\简历.doc");
    }
}
