package com.test.springboot02.controller;

import com.test.springboot02.dto.ReplyDTO;
import com.test.springboot02.service.ReplyService;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

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

    @PostMapping(value = "/register",produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Long> registerReply(@RequestBody ReplyDTO replyDTO){
        Long replyId = replyService.register(replyDTO);

        return new ResponseEntity<>(replyId,HttpStatus.OK);

    }

    @DeleteMapping("/{rno}")
    public ResponseEntity<Long> deleteReply(@PathVariable("rno") Long rno){
        replyService.remove(rno);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @PutMapping(value = "/{rno}",produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Long> modifyReply(@PathVariable("rno") Long rno, @RequestBody ReplyDTO replyDTO){
        replyService.modify(rno,replyDTO);
        return new ResponseEntity<>(HttpStatus.OK);
    }
}
