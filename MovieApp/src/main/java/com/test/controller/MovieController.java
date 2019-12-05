package com.test.controller;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLEncoder;
import java.util.HashMap;
import java.util.Map;

import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.google.gson.JsonParser;

@Controller
@RequestMapping(value = "/movie")
public class MovieController {

	@RequestMapping(value = "/movieMain.do")
	public String movieMain(ModelMap model) {

		return "movie/movieMain";
	}

	@RequestMapping(value = "/movieMain.json")
	public @ResponseBody Map<String,String> movieList(@RequestParam Map<String,String> params) {
		String bookName;
	
		if("".equals(params.get("query")) || params.get("query") == null) {
			
			bookName = "배트맨";
		}else {
			bookName =  params.get("query");
			
		}
		
		Map<String,String> resultMap = new HashMap<>();
		
		String result = "";
		Integer amount = Integer.parseInt(params.get("display"));
		String clientId = "__nrAJSkyIyXZcu51oT7";// 애플리케이션 클라이언트 아이디값";
		String clientSecret = "YUxjXONUas";// 애플리케이션 클라이언트 시크릿값";

		try {
			
			String text = URLEncoder.encode(bookName, "utf-8");
			String apiURL;
			System.out.println(bookName);
			
			
				 apiURL = "https://openapi.naver.com/v1/search/movie.json?encoding=utf-8&query="+text +"&display="+amount;// json 결과
		
			
			
			URL url = new URL(apiURL);
			HttpURLConnection con = (HttpURLConnection) url.openConnection();
		
			con.setRequestMethod("GET");
			con.setRequestProperty("X-Naver-Client-Id", clientId);
			con.setRequestProperty("X-Naver-Client-Secret", clientSecret);
			int responseCode = con.getResponseCode();
			BufferedReader br;
			
			if (responseCode == 200) { // 정상 호출
				br = new BufferedReader(new InputStreamReader(con.getInputStream(),"UTF-8"));
			} else { // 에러 발생
				br = new BufferedReader(new InputStreamReader(con.getErrorStream(),"UTF-8"));
			}
			
			String inputLine =null;
			StringBuffer response = new StringBuffer();
		
			while ((inputLine = br.readLine()) != null) {
				response.append(inputLine);
			}
			
			resultMap.put("result",response.toString());
			
			
			br.close();
			System.out.println(response.toString());
			System.out.println(result);
			
			
			return resultMap;
			
		} catch (Exception e) {
			System.out.println(e);
			return null;
		}
		

	}
}



