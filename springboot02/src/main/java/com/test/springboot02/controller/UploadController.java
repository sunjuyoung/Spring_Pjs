package com.test.springboot02.controller;

import com.test.springboot02.config.AppProperties;
import com.test.springboot02.dto.UploadResultDTO;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.util.FileCopyUtils;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.net.URLDecoder;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@Log4j2
@RestController
@RequiredArgsConstructor
public class UploadController {

    private final AppProperties appProperties;

    private String getUploadPath() {
        return appProperties.getUploadPath();
    }

    @PostMapping(value="/uploadAjax")
    public ResponseEntity<List<UploadResultDTO>> uploadFile(MultipartFile[] uploadFiles){
        String uploadPath = getUploadPath();
        List<UploadResultDTO> resultDTOS = new ArrayList<>();

        for(MultipartFile uploadFile : uploadFiles){
            if(uploadFile.getContentType().startsWith("image") == false){
                log.warn("this file is not image type");
                return new ResponseEntity<>(HttpStatus.FORBIDDEN);

            }
            //실제 파일 이름 IE나 Edge는 전체 경로가 들어온다
            String originName = uploadFile.getOriginalFilename();
            String fileName = originName.substring(originName.lastIndexOf("\\")+1);

            String folderPath = makeFolder(uploadPath);

            //UUID
            String uuid = UUID.randomUUID().toString();
            String saveName = uploadPath+ File.separator+folderPath+File.separator+uuid+"_"+fileName;

            Path savePath = Paths.get(saveName);
            try{
                //파일저장
                uploadFile.transferTo(savePath);

                //섬네일 생성  s_
/*                String thumbnailSaveName = uploadPath + File.separator + folderPath + File.separator + "s_"+ uuid +"_"+fileName;
                File thumbnailFile = new File(thumbnailSaveName);
                Thumbnailator.createThumbnail(savePath.toFile(),thumbnailFile,100,100);*/

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
            File file = new File(getUploadPath()+File.separator+srcFileName);
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




    private String makeFolder(String uploadPath) {
        String str = LocalDate.now().format(DateTimeFormatter.ofPattern("yyyy/MM/dd"));
        String folderPath = str.replace("/", File.separator);

        //폴더 생성
        File uploadPathFolder = new File(uploadPath,folderPath);

        if(uploadPathFolder.exists() == false){
            uploadPathFolder.mkdirs();
        }
        return folderPath;
    }


}
