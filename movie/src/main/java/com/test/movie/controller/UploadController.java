package com.test.movie.controller;

import com.test.movie.dto.UploadResultDTO;
import lombok.extern.log4j.Log4j2;
import net.coobird.thumbnailator.Thumbnailator;
import org.apache.coyote.Response;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.util.FileCopyUtils;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;
import java.net.URLEncoder;
import java.nio.file.Files;
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
                //파일저장
                uploadFile.transferTo(savePath);

                //섬네일 생성  s_
                String thumbnailSaveName = uploadPath + File.separator + folderPath + File.separator + "s_"+ uuid +"_"+fileName;
                File thumbnailFile = new File(thumbnailSaveName);
                Thumbnailator.createThumbnail(savePath.toFile(),thumbnailFile,100,100);

                resultDTOS.add(new UploadResultDTO(fileName,uuid,folderPath));
            }catch (IOException e){
                e.printStackTrace();
            }

        }
        return new ResponseEntity<>(resultDTOS,HttpStatus.OK);

    }

    @GetMapping("/display")
    public ResponseEntity<byte[]> getFile(String fileName){
        //URL인코딩된 파일 이름을 파라미터로 받아서 byte[]로 만들어서 브라우저로 전송
        ResponseEntity<byte[]> result = null;

        try {
            String srcFileName = URLDecoder.decode(fileName,"UTF-8");
            log.info("filename : "+srcFileName);
            File file = new File(uploadPath+File.separator+srcFileName);
            log.info("file : "+file);
            HttpHeaders header = new HttpHeaders();

            //파일 확장자에 따른 MIME타입 처리
            header.add("Content-Type", Files.probeContentType(file.toPath()));
            //파일 데이터 처리
            result = new ResponseEntity<>(FileCopyUtils.copyToByteArray(file),header,HttpStatus.OK);

        }catch (Exception e){
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }

        return result;
    }


    @PostMapping("/removeFile")
    public ResponseEntity<Boolean> removeFile(String fileName){
        String srcFileName = null;
        log.info("filename : " + fileName);

        try {
            srcFileName = URLDecoder.decode(fileName,"UTF-8");
            File file = new File(uploadPath+File.separator+srcFileName);
            boolean result = file.delete();

            //섬네일삭제
            log.info("parent: "+file.getParent() +" ::::"+ file.getName());
            File thumbanil = new File(file.getParent(),"s_"+file.getName());

            result = thumbanil.delete();

            return new ResponseEntity<>(result,HttpStatus.OK);
        }catch (UnsupportedEncodingException e){
            return new ResponseEntity<>(false,HttpStatus.INTERNAL_SERVER_ERROR);

        }
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
