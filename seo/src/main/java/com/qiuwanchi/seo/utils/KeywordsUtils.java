package com.qiuwanchi.seo.utils;

import com.qiuwanchi.seo.dto.KeywordsDto;
import com.qiuwanchi.seo.service.IProjectService;
import com.qiuwanchi.seo.service.ISubProjectService;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import java.util.*;

@Component
public class KeywordsUtils {

    private static IProjectService projectService;

    @Autowired
    private IProjectService projectService2;

    private static ISubProjectService subProjectService;

    @Autowired
    private ISubProjectService subProjectService2;

    @PostConstruct
    public void init(){
        projectService = projectService2;
        subProjectService = subProjectService2;
    }

    public static List<KeywordsDto> getHotLabel(){
        // 热门标签
        List<String> hotLabelList1 = projectService.selectKeywords();
        List<String> hotLabelList2 = subProjectService.selectKeywords();
        Map<String,Integer> map = new TreeMap<>();
        keywordsCount(map,hotLabelList1);
        keywordsCount(map,hotLabelList2);
        List<KeywordsDto> keywordsDtoList = new ArrayList<>();
        for (Map.Entry<String,Integer> entry : map.entrySet()){
            if(StringUtils.isBlank(entry.getKey())){
                continue;
            }
            KeywordsDto keywordsDto = new KeywordsDto();
            keywordsDto.setCount(entry.getValue());
            keywordsDto.setWords(entry.getKey());
            keywordsDtoList.add(keywordsDto);
        }

        keywordsDtoList.sort(new Comparator<KeywordsDto>() {
            @Override
            public int compare(KeywordsDto o1, KeywordsDto o2) {
                return o2.getCount() - o1.getCount();
            }
        });

        if(keywordsDtoList.size() > 10){
            keywordsDtoList = keywordsDtoList.subList(0,9);
        }

        return keywordsDtoList;
    }

    private static void keywordsCount(Map<String,Integer> map, List<String> hotLabelList){
        for (String keywords : hotLabelList){
            String[] arr = keywords.split(",");

            for(String words : arr){
                Integer count = map.get(words);
                if (Objects.isNull(count)){
                    count = 1;
                }else {
                    count = count + 1;
                }

                map.put(words, count);
            }
        }
    }

}