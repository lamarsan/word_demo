package com.lamarsan.word_demo.service.impl;

import com.lamarsan.word_demo.service.WordService;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * className: WordServiceImpl
 * description: TODO
 *
 * @author hasee
 * @version 1.0
 * @date 2019/7/15 12:01
 */
@Service
public class WordServiceImpl implements WordService {
    @Override
    public List<Map<String, String>> getSubjectList(List<Object> list) {
        List<Map<String, String>> subjectList = new ArrayList<Map<String,String>>();
        StringBuffer subjectItem =new StringBuffer();
        int count =0;
        int qNum = 0;
        //划分题目
        //以数字开头并且包含.表示一个新的题目开始
        String regex = "^\\d{1,100}\\.";
        Pattern pattern = Pattern.compile(regex);
        Matcher m = null;
        Map<String, String> tempMap = new HashMap<String, String>();
        String qtype ="";
        String oldQtype = "";
        String line ="";
        for (int i = 0; i < list.size(); i++) {
            line = list.get(i).toString();
            m = pattern.matcher(line);
            if(m.find()){//题干
                count++;
                if(qNum>0){//不是文件文件第一个题干，将之前的buffer保存
                    tempMap = new HashMap<String, String>();
                    tempMap.put("qtype",oldQtype);
                    tempMap.put("content", subjectItem.toString());
                    subjectList.add(tempMap);
                    oldQtype=qtype;
                    subjectItem = new StringBuffer();
                    subjectItem.append(line);
                }else{//文件第一个题干，创建新的buffer，并将题干放入buffer
                    subjectItem = new StringBuffer();
                    subjectItem.append(line);
                }
                qNum++;
            }else if(line.startsWith("【单选题】")){
                qtype = "1";
                if(count==0){
                    oldQtype=qtype;
                }
            }else if(line.startsWith("【多选题】")){
                qtype = "2";
                if(count==0){
                    oldQtype=qtype;
                }
            }else if(line.startsWith("【判断题】")){
                qtype = "3";
                if(count==0){
                    oldQtype=qtype;
                }
            }else if(line.startsWith("【不定项选择题】")){
                qtype = "4";
                if(count==0){
                    oldQtype=qtype;
                }
            }else{
                subjectItem.append(line);
            }
            //List<String> resList = DocxUtils.getShortStr(subjectItem.toString());
    		/*if(i==list.size()-1){
    			tempMap = new HashMap<String, Object>();
				tempMap.put("qtype",oldQtype);
				tempMap.put("content", subjectItem.toString());
				tempMap.put("imgMsgList", resList);
				subjectList.add(tempMap);
    		}*/
        }
        return subjectList;
    }
}

