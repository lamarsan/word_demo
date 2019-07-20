package com.lamarsan.word_demo.controller;

import com.lamarsan.word_demo.service.WordService;
import org.docx4j.openpackaging.exceptions.Docx4JException;
import org.docx4j.openpackaging.packages.WordprocessingMLPackage;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;
import java.util.Map;

/**
 * className: WordDocx4jController
 * description: TODO
 *
 * @author hasee
 * @version 1.0
 * @date 2019/7/15 11:54
 */
@RestController
@RequestMapping("/wordDocx4j")
public class WordDocx4jController {
    @Autowired
    WordService wordService;

    @PostMapping(value = "/wordImport")
    public void importWord(@RequestParam("file") MultipartFile file) throws IOException {
        if (file == null) {
            System.out.println("文件不能为空");
        }
        WordprocessingMLPackage wordMLPackage = null;
        try {
            wordMLPackage = WordprocessingMLPackage.load(file.getInputStream());
        } catch (Docx4JException e) {
            e.printStackTrace();
        }
        //获取word所有内容
        List<Object> list = wordMLPackage.getMainDocumentPart().getContent();
        //将文件中的内容分隔成每一道题
        List<Map<String, String>> questionMapList = wordService.getSubjectList(list);

    }
}
