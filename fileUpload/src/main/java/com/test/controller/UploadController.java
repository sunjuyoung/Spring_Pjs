package com.test.controller;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.nio.file.Files;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.UUID;

import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.util.FileCopyUtils;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import com.test.domain.AttachFileDTO;

import lombok.extern.log4j.Log4j;
import net.coobird.thumbnailator.Thumbnailator;

@Controller
@Log4j
public class UploadController {
	
	
	
	
	
	//섬네일 이미지 생성
	//업로드 된 파일이 이미지안지 판다, 이미지 파일의 경우 섬네일 이미지 생성 및 저장
	//이미지 파일의 판단
	private boolean checkImageType(File file) {
		
		try {
			String contentType = Files.probeContentType(file.toPath());
			
			return contentType.startsWith("image");
		}catch(IOException e) {
			e.printStackTrace();
		}
		
		return false;
	}
	
	
	
	
	//업로드된 파일의 이름과 원본 파일의 이름
	//파일이 저장된 경로
	//업로드된 파일이 이미지인지 아닌지 대한 정보
	
	//파일의 경로 + s_ + uuid 가 붙은 이름
	//display/fileName=2018/11/11/image.jpg
	@GetMapping("/display")
	@ResponseBody
	public ResponseEntity<byte[]> getFile(String fileName){ //문자열로 파일의 경로가 포함된 filename을 파라미터로 받고
		
		log.info("fileName = " + fileName);
		File file = new File("c:\\upload\\"+fileName);
		
		log.info("file :  "  + file);
		
		//byte[]로 이미지 파일을 저장한다
		ResponseEntity<byte[]> result =null;
		
		try {
			HttpHeaders header = new HttpHeaders();
			
			//byte[]로 데이터를 전송할때 브라우저에 보내주는 MIME타입이 파일의 종류에 따라 달라진다
			//이 부분을 해결하기 위해 probeContentType()을 이용해서 적절한 MIME타입 데이터를 Http헤더메시지에 포함할수있도록 처리
			header.add("Content-Type", Files.probeContentType(file.toPath()));
			log.info("filePath :  " + file.toPath());
			result = new ResponseEntity<>(FileCopyUtils.copyToByteArray(file), header,HttpStatus.OK);
			
			
		}catch(IOException e) {
			e.printStackTrace();
			
		}
		
		
		return result;
	}
	
	
	
	
	
	
	
	
	//년/월/일 폴더이름 생성
	private String getFolder() {
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
		
		Date date = new Date();
		
		String str = sdf.format(date);
		
		return str.replace("-",File.separator);
	}
	
	
	
	

	@GetMapping("/uploadForm")
	public void uploadForm() {

		log.info("uploadForm");
	}

	@PostMapping("/uploadFormAction")
	public void uploadFormPost(MultipartFile[] uploadFile, Model model) {

		String uploadFolder = "C:\\upload";

		for (MultipartFile multipartFile : uploadFile) {

			multipartFile.getOriginalFilename();
			File saveFile = new File(uploadFolder, multipartFile.getOriginalFilename());

			try {
				multipartFile.transferTo(saveFile);
			} catch (Exception e) {
				e.getMessage();
			}

		}

	}

	@GetMapping("/uploadAjax")
	public void uploadAjax() {

		log.info("uploadAjax");
	}

	
	//attatchFileDTO 의 리스트를 json 데이터로 반환하는 구조로 변경
	@PostMapping(value= "/uploadAjaxAction", produces= MediaType.APPLICATION_JSON_UTF8_VALUE)
	@ResponseBody
	public ResponseEntity<List<AttachFileDTO>> uploadAjaxAction(MultipartFile[] multipartFile) {

		List<AttachFileDTO> list = new ArrayList<>();
		
		log.info("controller uploadAjaxAction");

		String uploadFolder = "C:\\upload";
		
		String uploadFolderPath = getFolder();
		
		//Make Folder----------------------
		File uploadPath = new File(uploadFolder,getFolder());
		log.info("upload Path : "+ uploadPath);
		
		if(uploadPath.exists() == false) {
			uploadPath.mkdirs();
		}//make yyyy/mm/dd folder
		
		

		for (MultipartFile uploadFile : multipartFile) {
			
			AttachFileDTO atfDto = new AttachFileDTO();

			String fileName = uploadFile.getOriginalFilename();
			
			log.info(fileName);
			
			fileName  = fileName.substring(fileName.lastIndexOf("\\") + 1);

			//DTO filename 저장
			atfDto.setFileName(fileName);
			
			//중복된 이름의 처리 UUID
			UUID uuid = UUID.randomUUID();
			fileName = uuid.toString() + "_" + fileName;
			
			
			
			
		//	File saveFile = new File(uploadFolder, fileName);
			

			try {
				File saveFile = new File(uploadPath, fileName);
				uploadFile.transferTo(saveFile);
				
				atfDto.setUuid(uuid.toString());
				atfDto.setUploadPath(uploadFolderPath);
				
				//check image Type file
				if(checkImageType(saveFile)) {
					
					atfDto.setImage(true);
					
					FileOutputStream thumbnail = new FileOutputStream(new File(uploadPath,"s_"+fileName));
					
					Thumbnailator.createThumbnail(uploadFile.getInputStream(), thumbnail,100,100);
					//Thumbnailator 는 InputStream과 java.io.FIle객체를 이용해서 파일을 생성 뒤에 사이즈에 대한 부분은 파라미터로 width,height 지정
					thumbnail.close();
					
					//uploadAjax 이용해서 이미지 파일을 업로드하면 원본 파일은 그대로 저장 되고 , 파일 이름의 s_로 시작하는 섬네일 파일이 생성되는 것을 볼 수 있습니다.
					//반면 일반 파일의 경우 그냥 파일만 업로드되는 것을 볼 수 있습니다.
					
				}
				
				list.add(atfDto);
				
			} catch (Exception e) {
				log.error(e.getMessage());
			}

		}
		
		return new ResponseEntity<>(list,HttpStatus.OK);
		

	}

}
