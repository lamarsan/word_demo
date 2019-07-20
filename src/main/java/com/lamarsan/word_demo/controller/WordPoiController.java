package com.lamarsan.word_demo.controller;

import org.apache.poi.xwpf.usermodel.*;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.FileOutputStream;
import java.io.IOException;

/**
 * className: WordController
 * description: TODO
 *
 * @author hasee
 * @version 1.0
 * @date 2019/7/15 10:35
 */
@RestController
@RequestMapping("/wordPoi")
public class WordPoiController {

    /**
     * 导出word
     *
     * @return
     */
    @GetMapping(value = "/wordExport")
    public void export() throws IOException {
        // 创建Word文件
        XWPFDocument doc = new XWPFDocument();
        // 新建一个段落
        XWPFParagraph p = doc.createParagraph();
        // 设置段落的对齐方式
        p.setAlignment(ParagraphAlignment.CENTER);
        //设置下边框
        p.setBorderBottom(Borders.DOUBLE);
        //设置上边框
        p.setBorderTop(Borders.DOUBLE);
        //设置右边框
        p.setBorderRight(Borders.DOUBLE);
        //设置左边框
        p.setBorderLeft(Borders.DOUBLE);
        //创建段落文本
        XWPFRun r = p.createRun();
        r.setText("POI创建的Word段落文本");
        //设置为粗体
        r.setBold(true);
        //设置颜色
        r.setColor("FF0000");
        // 新建一个段落
        p = doc.createParagraph();
        r = p.createRun();
        r.setText("POI读写Excel功能强大、操作简单。");
        //创建一个表格
        XWPFTable table = doc.createTable(3, 3);
        table.getRow(0).getCell(0).setText("表格1");
        table.getRow(1).getCell(1).setText("表格2");
        table.getRow(2).getCell(2).setText("表格3");
        FileOutputStream out = new FileOutputStream("D:\\记事本\\公司\\word\\sample.doc");
        doc.write(out);
        out.close();
    }

    @PostMapping(value = "/wordImport")
    public void importWord(@RequestParam("file") MultipartFile file) throws IOException {
        if (file == null) {
            System.out.println("文件不能为空");
        }
        // 创建Word文件
        XWPFDocument doc = new XWPFDocument(file.getInputStream());
        //遍历段落
        for (XWPFParagraph p : doc.getParagraphs())
        {
            System.out.println(p.getParagraphText());
        }
        //遍历表格
        for (XWPFTable table : doc.getTables())
        {
            for (XWPFTableRow row : table.getRows()) {
                for (XWPFTableCell cell : row.getTableCells()) {
                    System.out.println(cell.getText());
                }
            }
        }
    }
}
