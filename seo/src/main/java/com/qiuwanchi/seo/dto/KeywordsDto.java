package com.qiuwanchi.seo.dto;

import lombok.Data;

import java.io.Serializable;

@Data
public class KeywordsDto implements Serializable {

    private String words;

    private int count;
}
