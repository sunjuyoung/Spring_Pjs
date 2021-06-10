package com.test.springboot02.controller;


import com.test.springboot02.dto.MovieDTO;
import com.test.springboot02.service.MovieService;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

@Log4j2
@Controller
@RequestMapping("/movie")
@RequiredArgsConstructor
public class MovieController {

    private final MovieService movieService;

    @GetMapping("/register")
    public String registerForm(){

        return "movie/register";
    }

    @PostMapping("/register")
    public String register(MovieDTO movieDTO, RedirectAttributes redirectAttributes){
        log.info("MovieDTO : "+ movieDTO);
        Long mno =  movieService.register(movieDTO);
        redirectAttributes.addFlashAttribute("msg",mno);
        return "redirect:/movie/list";
    }
}
