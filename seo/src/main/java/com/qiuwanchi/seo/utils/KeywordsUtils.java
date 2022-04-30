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

    public static List<KeywordsDto> getServiceCaseHotLabel(){
        List<String> solutionKeywordsList = projectService.selectSolutionKeywords();
        List<String> serviceCaseKeywordsList = subProjectService.selectServiceCaseKeywordsList();
        TreeMap<String,Integer> treeMap = new TreeMap<>();
        keywordsCount(treeMap, solutionKeywordsList);
        keywordsCount(treeMap, serviceCaseKeywordsList);
        return getTop10KeywordsDto(treeMap);
    }

    private static List<KeywordsDto> getTop10KeywordsDto(TreeMap<String,Integer> treeMap){
        List<KeywordsDto> keywordsDtoList = new ArrayList<>();
        for (Map.Entry<String,Integer> entry : treeMap.entrySet()){
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

    public static List<KeywordsDto> getHotLabel(){

        List<String> hotLabelList = projectService.getHotLabel();
        // 热门标签
        TreeMap<String,Integer> treeMap = new TreeMap<>();
        keywordsCount(treeMap,hotLabelList);
        return getTop10KeywordsDto(treeMap);
    }

    private static void keywordsCount(Map<String,Integer> map, List<String> hotLabelList){
        for (String keywords : hotLabelList){
            keywords = keywords.replaceAll("，", ",");
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
