package com.test.domain;

import java.util.Date;
import java.util.List;

import lombok.Data;

@Data
public class MemberVO {

	private String userid;
	private String userpw;
	private String userName;
	private String enabled;
	private String email;
	
	private Date regDate;
	private Date updateDate;
	//Member 클래스는 내부적으로 여러개의 사용자 권한을 가질수 있다
	private List<AuthVO> authList;
	
	
}
