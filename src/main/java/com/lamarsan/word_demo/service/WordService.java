package com.lamarsan.word_demo.service;

import java.util.List;
import java.util.Map;

/**
 * className: WordService
 * description: TODO
 *
 * @author hasee
 * @version 1.0
 * @date 2019/7/15 12:00
 */
public interface WordService {
    List<Map<String, String>> getSubjectList(List<Object> list);
}
