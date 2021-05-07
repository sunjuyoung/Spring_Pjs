package com.test.springboot02.service;

import com.test.springboot02.dto.ReplyDTO;

import java.util.List;

public interface ReplyService {

    Long register(ReplyDTO replyDTO);

    List<ReplyDTO> getList(Long bno);

    void remove(Long id);

    void modify(Long id,ReplyDTO replyDTO);
}
