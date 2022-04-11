package com.qiuwanchi.seo.dto;

import lombok.Data;

import java.io.Serializable;

@Data
public class LeavingMessageDto implements Serializable {

    private String name;

    private String telephone;

    private String message;

}