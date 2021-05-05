package com.test.springboot02.controller;

import com.test.springboot02.dto.ReplyDTO;
import com.test.springboot02.service.ReplyService;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequiredArgsConstructor
@Log4j2
@RequestMapping("/reply")
public class ReplyController {

    private final ReplyService replyService;

    @GetMapping(value = "/list/{bno}",produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<List<ReplyDTO>> replyList(@PathVariable("bno") Long bno){

        List<ReplyDTO> result =  replyService.getList(bno);
        return new ResponseEntity<>(result,HttpStatus.OK);
    }
}
