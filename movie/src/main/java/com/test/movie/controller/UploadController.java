package com.test.movie.controller;

import com.test.movie.dto.UploadResultDTO;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@RestController
@Log4j2
public class UploadController {

    /*
    1.이미지 파일만 저장
    2.동일한 이름 덮어쓰기문제
    3.파일저장 폴더이름 설정
    * */
    
    @Value("${com.test.upload.path}")//application.properties 변수
    private String uploadPath;

    @PostMapping("/uploadAjax")
    public ResponseEntity<List<UploadResultDTO>> uploadFile(MultipartFile[] uploadFiles){

        List<UploadResultDTO> resultDTOS = new ArrayList<>();

        for(MultipartFile uploadFile: uploadFiles){
            //이미지파일
            if(uploadFile.getContentType().startsWith("image")==false){
                log.warn("this file is not image type");
                return new ResponseEntity<>(HttpStatus.FORBIDDEN); //이미지 파일이 아니면 403 Forbidden
            }

            //IE,Edge인 경우 파일이름에 전체경로가 들어온다
            String orginalName = uploadFile.getOriginalFilename();
            String fileName = orginalName.substring(orginalName.lastIndexOf("\\")+1);
            log.info("filename : " + fileName);

            //날짜 폴더 새성
            String folderPath = makeFolder();

            //UUID
            String uuid = UUID.randomUUID().toString();
            String saveName = uploadPath+File.separator+folderPath+File.separator+uuid+"_"+fileName;

            Path savePath = Paths.get(saveName);

            try{
                uploadFile.transferTo(savePath);
                resultDTOS.add(new UploadResultDTO(fileName,uuid,folderPath));
            }catch (IOException e){
                e.printStackTrace();
            }

        }
        return new ResponseEntity<>(resultDTOS,HttpStatus.OK);

    }

    private String makeFolder(){
        String str = LocalDate.now().format(DateTimeFormatter.ofPattern("yyyy/MM/dd"));

        String folderPath = str.replace("\\", File.separator);

        //폴더 생성
        File uploadPathFolder = new File(uploadPath,folderPath);

        if(uploadPathFolder.exists() == false){
            uploadPathFolder.mkdirs();
        }
        return folderPath;
    }


}
