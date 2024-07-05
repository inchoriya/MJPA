package com.icia.mjpa.controller;

import com.icia.mjpa.dto.MemberDTO;
import com.icia.mjpa.service.MemberService;
import jakarta.servlet.http.HttpSession;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.ModelAndView;

@Controller
@RequiredArgsConstructor
public class MemberController {

    private final MemberService msvc;

    private final HttpSession session;

    // 회원가입 페이지로 이동
    @GetMapping("/mJoin")
    public String mJoin() {
        return "join";
    }

    // 회원가입
    @PostMapping("/mJoin")
    public ModelAndView mJoin(@ModelAttribute MemberDTO member){
        System.out.println("\nJPA 회원가입\n[1]member : " + member);
        return msvc.mJoin(member);
    }

    // mList : 회원목록
    @GetMapping("/mList")
    public ModelAndView mList(){
        System.out.println("\nJPA 회원목록");
        return msvc.mList();
    }

    // 상세보기 : mView
    @GetMapping("/mView/{mId}")
    public ModelAndView mView(@PathVariable String mId){
        return msvc.mView(mId);
    }

    // modiForm : 회원상세보기 페이지로
    @GetMapping("/modiForm/{mId}")
    public ModelAndView modiForm(@PathVariable String mId){
        return msvc.modiForm(mId);
    }

    // mModify : 회원수정
    @PostMapping("/mModify")
    public ModelAndView mModify(@ModelAttribute MemberDTO member){
        return msvc.mModify(member);
    }

    // mDelete : 회원삭제하기
    @GetMapping("/mDelete/{mId}")
    public ModelAndView mDelete(@PathVariable String mId){
        return msvc.mDelete(mId);
    }

    // mLogin : 로그인 페이지 이동
    @GetMapping("/mLogin")
    public String mLogin() {
        return "login";
    }

    @PostMapping("/mLogin")
    public ModelAndView mLogin(@ModelAttribute MemberDTO member){
        System.out.println("\nJPA 로그인\n[1]member : " + member);
        return msvc.mLogin(member);
    }

    // mLogin : 로그인 페이지 이동
    @GetMapping("/mLogout")
    public String mLogout() {
        session.invalidate();
        return "index";
    }

}
