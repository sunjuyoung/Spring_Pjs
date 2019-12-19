package com.test.controller;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.nio.file.Files;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
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

import com.test.domain.AttachFileDTO;
import com.test.domain.BoardVO;
import com.test.domain.PageDTO;
import com.test.service.BoardService;

import lombok.Setter;
import lombok.extern.log4j.Log4j;
import net.coobird.thumbnailator.Thumbnailator;

@Controller
@RequestMapping("/board")
@Log4j
public class BoardController {
	
	@Setter(onMethod_  = {@Autowired})
	 private BoardService service;
	


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
	@PostMapping(value="/register")
	public String register(BoardVO vo, RedirectAttributes rttr) {
		
		
		try {
			service.insert(vo);
			rttr.addFlashAttribute("result", "게시물 등록");
		}catch(Exception e) {
			rttr.addFlashAttribute("result","실패");
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
	
	//년/월/일  폴더 생성
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
	 * 파일업로드
	 */
	@GetMapping(value= {"/uploadForm","/uploadAjax"})
	public void uploadForm() {
		
		log.info("upload form");
	}

	
	/**
	 * 파일업로드
	 * @param uploadFile
	 * @param model
	 */
	@PostMapping(value="/uploadFormAction",produces=MediaType.APPLICATION_JSON_UTF8_VALUE)
	@ResponseBody
	public ResponseEntity<List<AttachFileDTO>> uploadFormAction (MultipartFile[] uploadFile, Model model) {
		String uploadFolder = "C:\\upload";
		List<AttachFileDTO> list = new ArrayList<>();
		
		//make folder
		String uploadFolderPath = getFolder();
		File uploadPath = new File(uploadFolder,uploadFolderPath);
		log.info("uploadPath : " + uploadPath);
		
		if(uploadPath.exists() == false) {
			uploadPath.mkdirs();
		}
		
		
		
		for(MultipartFile multipartFile : uploadFile) {
			AttachFileDTO dto = new AttachFileDTO();
			
			//IE 경우 파일 경로전체가 출력된다 
			String uploadFileName = multipartFile.getOriginalFilename();
			uploadFileName = uploadFileName.substring(uploadFileName.lastIndexOf("\\") + 1);
			dto.setFileName(uploadFileName); //파일네임 저장
			
			//중복 방지를 위한 UUID
			UUID uuid = UUID.randomUUID();
			dto.setUuid(uuid.toString()); // uuid 저장
			uploadFileName = uuid.toString()+"_"+uploadFileName;
		
			try {
				File saveFile = new File(uploadPath , uploadFileName);
				multipartFile.transferTo(saveFile);
				dto.setUploadPath(uploadFolderPath);  //저장경로 저장
				
				//이미지파일 체크 및 섬네일 생성
				if(checkImageType(saveFile)) {
					dto.setImage(true);  //이미지 여부 저장
					//InputStream 과 io.File객체를 이용해서 파일생성
					FileOutputStream thumbnail = new FileOutputStream(new File(uploadPath, "s_"+uploadFileName));
					
					Thumbnailator.createThumbnail(multipartFile.getInputStream(),thumbnail,100,100);
					thumbnail.close();
				}
				
				list.add(dto);
			}catch (Exception e) {
				log.error(e.getMessage());
			}
		}
		
		return new ResponseEntity<>(list,HttpStatus.OK);
		
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
