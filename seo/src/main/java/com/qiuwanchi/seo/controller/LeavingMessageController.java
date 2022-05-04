package com.qiuwanchi.seo.controller;

import com.qiuwanchi.seo.dto.LeavingMessageDto;
import com.qiuwanchi.seo.entity.LeavingMessage;
import com.qiuwanchi.seo.service.ILeavingMessageService;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Date;

@Log4j2
@RestController
public class LeavingMessageController {

    @Autowired
    private ILeavingMessageService leavingMessageService;

    @PostMapping("/leavingMessage")
    public String products(Model model, LeavingMessageDto leavingMessageDto){
        LeavingMessage leavingMessage = new LeavingMessage();

        leavingMessage.setName(leavingMessageDto.getName());
        leavingMessage.setTelephone(leavingMessageDto.getTelephone());
        leavingMessage.setMessage(leavingMessageDto.getMessage());
        leavingMessage.setCreateTime(new Date());
        leavingMessage.setUpdateTime(new Date());

        this.leavingMessageService.save(leavingMessage);

        return "ok";
    }

}
