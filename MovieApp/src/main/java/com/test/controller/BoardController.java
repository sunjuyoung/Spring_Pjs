package com.test.controller;

import java.io.File;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.nio.file.Files;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.FileSystemResource;
import org.springframework.core.io.Resource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.util.FileCopyUtils;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.test.domain.BoardVO;
import com.test.domain.PageDTO;
import com.test.service.BoardService;
import com.test.service.ReplyService;

import lombok.Setter;
import lombok.extern.log4j.Log4j;

@Controller
@RequestMapping("/board")
@Log4j
public class BoardController {
	
	@Setter(onMethod_  = {@Autowired})
	 private BoardService service;
	
	@Setter(onMethod_  = {@Autowired})
	 private ReplyService replyService;
	
	//파일 년/월/일  폴더 생성
	private String getFolder() {
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
		
		Date date = new Date();
		
		String str = sdf.format(date);
		
		//파일 구분자는 os 마다 다르다
		//JVM이 실행되는 os에 맞는 구분자로 변경하는 API
		return str.replace("-", File.separator);
	}
	
	
	//업로드된 파일이 이미지 종류의 파일인지 확인/
	//이미지 파일의 경우 섬네일 이미지 생성및 저장
	private boolean checkImageType(File file) {
		try {
			String contentType = Files.probeContentType(file.toPath());
			
			return contentType.startsWith("image");
		}catch(IOException e) {
			e.printStackTrace();
		}
		return false;
	}
	


	/**
	 * 메인 화면
	 * @param vo
	 * @param model
	 */
	@PreAuthorize("hasAnyRole('ROLE_ADMIN','ROLE_MEMBER')")
	@RequestMapping(value="/mainList.do")
	public void mainList(Model model,BoardVO vo) {
		if(vo.getPageNum() == 0) {
			vo.setAmount(10);
			vo.setPageNum(1);
		}
		//게시물 총 수
		int total = (int)(service.total());
		
		//페이지
		PageDTO dto = new PageDTO(vo.getPageNum(),vo.getAmount(),total);
		
		//검색조건
		dto.setKeyword(vo.getKeyword());
		dto.setType(vo.getType());
		
		model.addAttribute("page", dto );
		model.addAttribute("list",service.getListWithPaging(dto));
		
	}

	/**
	 *조회 수정 화면
	 * @param bno
	 * @param model
	 */
	@PreAuthorize("hasAnyRole('ROLE_ADMIN','ROLE_MEMBER')")
	@GetMapping({"/get","/modify"})
	public void get(@ModelAttribute("vo")BoardVO vo,Model model) {//조회페이지에서 목록 페이지로 이동 때 페이지 파라미터 
		                                         //@ModelAttribute 사용하지 않아도 controller에서 화면으로 파라미터가된객체는 전달된다 (명시적으로 지정함)
		
		
	model.addAttribute("board",service.get(vo.getBno()));
	}
	
	
	@GetMapping("/register")
	public void register() {
		
	}
	
	
	/**
	 * 게시물 입력
	 * @param vo
	 * @param rttr
	 * @return
	 */
	@PostMapping(value="/registerregister")
	public String registerregister(BoardVO vo, RedirectAttributes rttr) {
		
		
		try {
			service.insert(vo);
			rttr.addFlashAttribute("result", "게시물 등록");
		}catch(Exception e) {
			rttr.addFlashAttribute("result","실패");
		}
		
		
		return "redirect:/board/mainList.do";
		
	}
	
	/**
	 * 
	 * @param vo
	 * @param uploadFile
	 * @param model
	 * @return
	 */
	@PostMapping(value="/register",produces=MediaType.APPLICATION_JSON_UTF8_VALUE)
	public String register (BoardVO vo,MultipartFile multipartFile, Model model) {
		log.info("register controller");
		
		if(multipartFile.isEmpty()) {
			service.insert(vo);
		}
		
		if(!multipartFile.isEmpty()) {
			
		
		String uploadFolder = "C:\\upload";

		//make folder
		String uploadFolderPath = getFolder();
		File uploadPath = new File(uploadFolder,uploadFolderPath);
		
		if(uploadPath.exists() == false) {
			uploadPath.mkdirs();
		}
		
		String uploadFileName = multipartFile.getOriginalFilename();
		log.info(uploadFileName);
		uploadFileName = uploadFileName.substring(uploadFileName.lastIndexOf("\\") + 1);
		vo.setFileName(uploadFileName); //파일네임 저장
		
		UUID uuid = UUID.randomUUID();
		vo.setUuid(uuid.toString());
		uploadFileName = uuid.toString()+"_"+uploadFileName;
		
		
		try {
			File saveFile = new File(uploadPath,uploadFileName);
			multipartFile.transferTo(saveFile);
			vo.setUploadPath(uploadFolderPath);
			
			if(checkImageType(saveFile)) {
				vo.setFileType(true);  //이미지 여부 저장
			}
			
			service.insertWithFile(vo);
		}catch(Exception e) {
			
		}
		}
		
		return "redirect:/board/mainList.do";
		
	}
	
	/**
	 * 게시물 수정
	 * @param vo
	 * @param rttr
	 * @return
	 */
	@PostMapping(value="/modify")
	public String modify(BoardVO vo,RedirectAttributes rttr ) {
		log.info("수정");
		int result = service.modify(vo);
		
		return "redirect:/board/get?bno="+vo.getBno();
	}
	
	/**
	 * 
	 * @param vo
	 * @param rttr
	 * @return
	 */
	@PostMapping(value="/remove")
	public String remove(BoardVO vo, RedirectAttributes rttr) {
		
		log.info("삭제");
		String result = "";
		if(service.remove(vo.getBno())) {
			result = vo.getBno()+"번 게시물 삭제 완료";
		}else {
			result = "삭제 실패";
		}
		
		rttr.addFlashAttribute("result",result);
		
		return "redirect:/board/mainList.do";
	}
	

	

	
	/**
	 * 
	 * @param fileName
	 * @return
	 */
	@GetMapping(value="/display")
	@ResponseBody
	public ResponseEntity<byte[]> getFile(String fileName){
		//파일의 경로가 포함된 fileName을 받고 byte[] 전송
		//이때 브라우저에 보내주는 MIME타입이 파일의 종류에따라 달라진다
		//때문에 probeContentType이용해서 적절한 MIME타입 데이터를 Http헤더 메시지에 포함시킨다
		log.info("displayController");
		log.info("@@@@@@@@@"+fileName);
		File file = new File("c:\\upload\\"+fileName);
		
		ResponseEntity<byte[]> result = null;
		
		try {
			HttpHeaders header = new HttpHeaders();
			
			header.add("Content-Type", Files.probeContentType(file.toPath()));
			result = new ResponseEntity<>(FileCopyUtils.copyToByteArray(file),header,HttpStatus.OK);
			
			log.info(result);
			
		}catch(IOException e) {
			e.printStackTrace();
		}
		
		return result;
	}


	/**
	 * 파일 다운로드
	 * @param fileName
	 * @return
	 */
	@GetMapping(value="/download",produces=MediaType.APPLICATION_OCTET_STREAM_VALUE) //application/octet-stream
	@ResponseBody
	public ResponseEntity<Resource> downloadFile(@RequestHeader("User-Agent")String userAgent,String fileName){
		//byte[] 대신 Resource사용해보자
		
		log.info("downloadFile : " +fileName);
		
		Resource resource = new FileSystemResource("c:\\upload\\"+fileName);
		
		log.info("resourec : " +resource);
		
		String resourceName = resource.getFilename();
		//String resourceOriginName = resourceName.substring(resourceName.indexOf("_"+1));
		
		HttpHeaders headers = new HttpHeaders();
		try {
			headers.add("Content-Disposition","attachment; filename"+ new String(resourceName.getBytes("UTF-8"),"ISO-8859-1"));
		}catch(UnsupportedEncodingException e) {
			e.printStackTrace();
		}
		
		
		return new ResponseEntity<Resource>(resource,headers,HttpStatus.OK);
	}
	
	
	
	
	
	
	
}
