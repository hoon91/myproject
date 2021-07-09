package com.hoonlog.member.controller;

import javax.inject.Inject;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import org.springframework.web.servlet.view.RedirectView;

import com.hoonlog.member.service.MemberService;
import com.hoonlog.member.vo.MemberVO;

@Controller
@RequestMapping(value = "/member")
public class MemberController {
	
	private static final Logger logger = LoggerFactory.getLogger(MemberController.class);
	
	@Inject
	MemberService memberService;
	
	/* 회원가입 GET */
	@RequestMapping(value = "/signup", method = RequestMethod.GET)
	public String getSignin() {
		
		logger.info("회원가입 페이지 이동");
		
		return "member/signup";
	}
	
	/* 회원가입 POST*/
	@RequestMapping(value = "/signup", method = RequestMethod.POST)
	public String postSignup(MemberVO member, @ModelAttribute("detailAddress") String detailAddress) throws Exception {
		
		logger.info("회원 가입 완료");
		
		/* 상세 주소값 받아오기 */
		String address= member.getUseraddress() + " " + detailAddress;
		
		if (detailAddress == null) {
			address = member.getUseraddress();
		}
		member.setUseraddress(address);	
		
		memberService.signup(member);
		return "redirect:/";
	}
	
	/* 아이디 중복체크 */
	@ResponseBody /* return되는 값은 view의 주소가 아닌 데이터임을 나타내는 어노테이션 */
	@RequestMapping(value = "/idCheck", method = RequestMethod.POST)
	public int idCheck(MemberVO member) throws Exception {
		
		logger.info("ID 중복체크");
		
		String userid = member.getUserid();
		int result = memberService.idCheck(userid);
		/* 만약, DB에 ID가 존재하면 1을, 존재하지 않으면 0을 return 할 것임 */
		return result;
	}
	
	/* 로그인 GET */
	@RequestMapping(value = "/login", method = RequestMethod.GET)
	public void getLogin() throws Exception {
		
		logger.info("로그인 화면 이동");
	}

	/* 로그인 POST */
	@RequestMapping(value = "/login", method = RequestMethod.POST)
	public String postLogin(MemberVO member, HttpServletRequest req, RedirectAttributes rttr) throws Exception {
		
		logger.info("로그인 완료");
	   
		MemberVO login = memberService.login(member);  
		HttpSession session = req.getSession();
	 
		if(login != null) {
			session.setAttribute("member", login);
		} else {
			session.setAttribute("member", null);
			rttr.addFlashAttribute("msg", false);
			return "redirect:/member/login";
		}  
	 
		return "redirect:/";
	}
	  
	/* 로그아웃 */
	@RequestMapping(value = "/logout", method = RequestMethod.GET)
	public String signout(HttpSession session) throws Exception {
	 
		logger.info("로그아웃");
	 
		memberService.logout(session);
	   
		return "redirect:/";
	}
	
	/* 내 정보보기 GET */
	@RequestMapping(value = "/userInfo/{userid}", method = RequestMethod.GET)
	public String userInfo(@PathVariable("userid") String userid, Model model) throws Exception {
		
		logger.info("내 정보 보기");
		
		MemberVO member = memberService.userInfo(userid);
		model.addAttribute("userInfo", member);
		
		return "member/userInfo";
	}
	
	/* 내 정보 수정 GET */
	@RequestMapping(value = "/updateUserInfo/{userid}", method = RequestMethod.GET)
	public String updateUserInfo(@PathVariable("userid") String userid, Model model) throws Exception {
		
		logger.info("회원정보 수정 폼");
		
		MemberVO member = memberService.updateUserForm(userid);
		model.addAttribute("userInfo", member);
		
		return "member/updateUserInfo";
	}
	
	/* 내 정보 수정 POST */
	@RequestMapping(value = "/updateUserInfo/{userid}", method = RequestMethod.POST)
	public ModelAndView updateUserInfo(MemberVO member, @ModelAttribute("detailAddress") String detailAddress) throws Exception {
		
		logger.info("회원정보 수정");
		
		/* detailAddress : 상세주소 받아오기 */
    	String adderss = member.getUseraddress() + " " + detailAddress;
		
		if (detailAddress == null) {
			adderss = member.getUseraddress();
		}
		
		member.setUseraddress(adderss);		

        memberService.updateUserInfo(member);
		
		ModelAndView mav = new ModelAndView();

		RedirectView redirectView = new RedirectView();
		redirectView.setUrl("/member/userInfo/" + member.getUserid());
		redirectView.setExposeModelAttributes(false);

		mav.setView(redirectView);

		return mav;	
	}
	
	/* 회원 탈퇴 GET */
	@RequestMapping(value = "/deleteUser/{userid}", method = RequestMethod.GET)
	public String getDeleteUser(@PathVariable("userid") String userid, Model model) throws Exception {
		
		logger.info("회원탈퇴 페이지 이동");
		MemberVO member = memberService.userInfo(userid);
		model.addAttribute("userInfo", member);
		return "member/deleteUser";
	}
	
	/* 회원 탈퇴 POST */
	@RequestMapping(value = "/deleteUser", method = RequestMethod.POST)
	public String deleteUser(MemberVO member, HttpSession session) throws Exception {
		logger.info("회원탈퇴");
		
		memberService.deleteUser(member, session);
		
		return "redirect:/";
	}

	/* 비밀번호 변경 GET */
	@RequestMapping(value = "/changePw/{userid}", method = RequestMethod.GET)
	public String getChangePw(@PathVariable("userid") String userid, Model model) throws Exception {
		
		logger.info("비밀번호 변경 이동");
		MemberVO member = memberService.userInfo(userid);
		model.addAttribute("userInfo", member);

		return "member/changePw";
	}
	
	/* 비밀번호 변경 POST */
	@RequestMapping(value = "/changePw", method = RequestMethod.POST)
	public String postChangePw(MemberVO member, HttpSession session) throws Exception {
		
		logger.info("비밀번호 변경");
		
		memberService.changePw(member, session);
		 
		return "redirect:/";
	}
	
}
