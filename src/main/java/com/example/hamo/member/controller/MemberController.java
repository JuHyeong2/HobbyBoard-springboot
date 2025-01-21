package com.example.hamo.member.controller;

<<<<<<< HEAD
import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
=======

import java.util.ArrayList;
>>>>>>> 00f091061b9b4a441d7267fae366aa68c70a08ba
import java.util.HashMap;

import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.SessionAttributes;
import org.springframework.web.bind.support.SessionStatus;
<<<<<<< HEAD
import org.springframework.web.multipart.MultipartFile;

import com.example.hamo.board.model.vo.Board;
=======

import com.example.hamo.common.util.EmailCertificationUtil;
>>>>>>> 00f091061b9b4a441d7267fae366aa68c70a08ba
import com.example.hamo.common.util.SmsCertificationUtil;
import com.example.hamo.member.model.service.MemberService;
import com.example.hamo.member.model.vo.Member;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import lombok.RequiredArgsConstructor;

@Controller
@RequiredArgsConstructor
@SessionAttributes("loginUser")
public class MemberController {
	
	private final MemberService mService;
	private final BCryptPasswordEncoder bcrypt;
	private final SmsCertificationUtil smsUtil;
<<<<<<< HEAD
	
	// 로그인 화면으로 가는 메서드
	@GetMapping("/member/login")
=======
	private final EmailCertificationUtil emailUtil;
	
	// 로그인 화면으로 가는 메서드
	@GetMapping("/member/login")	
>>>>>>> 00f091061b9b4a441d7267fae366aa68c70a08ba
	public String loginView() {
		
		return "member/login";
	}
	
	// 로그인 기능을 하는 메소드
	@PostMapping("/member/login")
	@ResponseBody
	public String login(@ModelAttribute("Member") Member m, Model model, HttpSession session) {
		Member loginUser = mService.login(m);
		if(loginUser != null && bcrypt.matches(m.getMemberPwd(), loginUser.getMemberPwd())) {
			model.addAttribute("loginUser", loginUser);
//			session.setAttribute("loginUser", loginUser);
			System.out.println(session.getAttribute("loginUser"));
			System.out.println("success");
<<<<<<< HEAD
=======

>>>>>>> 00f091061b9b4a441d7267fae366aa68c70a08ba
			return "success";
		}else {
			return "fail";
		}
<<<<<<< HEAD
	}

//	public String login(@ModelAttribute("Member") Member m, Model model) {
//		Member loginUser = mService.login(m);
//		if(loginUser != null && bcrypt.matches(m.getMemberPwd(), loginUser.getMemberPwd())) {
//			model.addAttribute("loginUser", loginUser);
//			return "success";
//		}else {
//			return "fail";
//		}
//	}

	
	@GetMapping("/member/logout")
	public String logOut(SessionStatus session) {
		session.setComplete();
		
		return "redirect:/";
	}
	
	
	// Home으로 가는 모든 버튼
=======
	}
	



//	public String login(@ModelAttribute("Member") Member m, Model model) {
//		Member loginUser = mService.login(m);
//		if(loginUser != null && bcrypt.matches(m.getMemberPwd(), loginUser.getMemberPwd())) {
//			model.addAttribute("loginUser", loginUser);
//			return "success";
//		}else {
//			return "fail";
//		}
//	}


	
	@GetMapping("/member/logout")
	public String logOut(SessionStatus session) {
		session.setComplete();
		
		return "redirect:/";
	}
	
	
	// Home으로 가는 모든 버튼

	@GetMapping("/home")
	public String home(Model model, HttpSession session) {

		return "index";
	}

>>>>>>> 00f091061b9b4a441d7267fae366aa68c70a08ba
//	@GetMapping("/home")
//	public String home() {
//		ArrayList<Board> list = bService.selectBoardList();
//		System.out.println("list : " + list);
//		
//		model.addAttribute("list", list);
//		
//		return "index";
//	}
<<<<<<< HEAD
=======

>>>>>>> 00f091061b9b4a441d7267fae366aa68c70a08ba
	
	// 회원가입 페이지로 이동
	@GetMapping("/member/signUp")
	public String signUp() {
		return "member/signUp";
	}
	
	@PostMapping("/member/signUp")
	public String signUp(@ModelAttribute("Member") Member member ) {
		
		member.setMemberPwd(bcrypt.encode(member.getMemberPwd()));
		int result = mService.insertMember(member);
		
		return "member/login";
	}
	
	// 회원가입 -> 휴대폰 인증번호 전송시 호출되는 메서드
	@PostMapping("/member/sendSMS")
	@ResponseBody
	public String sendSms(@RequestParam("phone") String phone, HttpServletResponse response) {
		System.out.println(phone);
		String certificationCode = Integer.toString((int)(Math.random() * (999999 - 100000 + 1)) + 100000); // 6자리 인증 코드를 랜덤으로 생성
		smsUtil.sendSMS(phone, certificationCode);
		
		return certificationCode;
	}
<<<<<<< HEAD
=======

		
	
	@PostMapping("/member/sendEmail")
	@ResponseBody
	public String sendEmail(@RequestParam("email") String email) {
		System.out.println("email : " + email);
		String certificationCode = Integer.toString((int)(Math.random() * (999999 - 100000 + 1)) + 100000); // 6자리 인증 코드를 랜덤으로 생성
		emailUtil.sendEmail(email, certificationCode);
		return certificationCode;
	}
>>>>>>> 00f091061b9b4a441d7267fae366aa68c70a08ba
	
	@PostMapping("/member/idCheck")
	@ResponseBody
	public int idCheck(@RequestParam("userId") String userId) {
//		System.out.println(userId);
		int result = mService.idCheck(userId);
		System.out.println(result);
		return result;
	}
	
	@PostMapping("/member/findId")
	@ResponseBody
<<<<<<< HEAD
	public Member findId(@RequestParam("phone") int phone) {
//		System.out.println(userId);
		Member m = mService.findId(phone);
=======
	public Member findId(@RequestParam("value") String value, @RequestParam("column") String column) {
//		System.out.println(userId);
		HashMap<String, String> map = new HashMap<String, String>();
		map.put("column", "member_" + column);
		map.put("value", value);
		Member m = mService.findId(map);
>>>>>>> 00f091061b9b4a441d7267fae366aa68c70a08ba
		System.out.println(m.getMemberId());
		return m;
	}
	
	// 아이디 찾기 페이지로 이동
	@GetMapping("/member/findId")
	public String findId(Model model, HttpServletRequest request) {
		model.addAttribute("loc", request.getRequestURI());
		System.out.println(model.getAttribute("loc").toString());
		return "member/findId";
	}
	
	// 비밀번호 찾기 페이지로 이동
	@GetMapping("/member/findPwd")
	public String findPwd(Model model, HttpServletRequest request) {
		model.addAttribute("loc", request.getRequestURI());
		System.out.println(model.getAttribute("loc").toString());
		return "member/findPwd";
<<<<<<< HEAD
=======
	}
	
	// 새 비밀번호 페이지로 이동
	@PostMapping("/member/findPwd2")
	public String findPwd2View(@ModelAttribute("Member") Member m, Model model) {
		model.addAttribute("member", m);
		return "member/findPwd2";
	}
	
	@PostMapping("/member/newPwd")
	public String updatePwd(@ModelAttribute("Member") Member m) {
		m.setMemberPwd(bcrypt.encode(m.getMemberPwd()));
		int result = mService.updatePwd(m);
		return "member/login";
>>>>>>> 00f091061b9b4a441d7267fae366aa68c70a08ba
	}
	
	// 내 정보 페이지로 이동
	
	@GetMapping("/member/myPage")
	public String myPage(HttpSession session, Model model) {
		Member loginUser = (Member)session.getAttribute("loginUser");
		if(loginUser != null) {
			model.addAttribute("loginUser", loginUser);
		}	
		return "user-inform/myPage";
	}


	
	
	// 내 정보 수정 페이지로 이동 
	@GetMapping("/member/editMyPage")
	public String editMyPage(HttpSession session, Model model) {
		Member loginUser = (Member)session.getAttribute("loginUser");
		if(loginUser == null) {
			return "redirect:/member/login";
		}
		model.addAttribute("m", loginUser);
		return "user-inform/editMyPage";
	}
<<<<<<< HEAD
		
	
	@PostMapping("/member/editMyPage")
    public String updateMember(@ModelAttribute Member member, @RequestParam(value="newPassword",required=false) String newPassword, HttpSession session,Model model) {
        Member loginUser = (Member) session.getAttribute("loginUser");
        if (loginUser == null) {
            return "redirect:/login";
        }
        
        if (member.getMemberName() == null || member.getMemberName().isEmpty()) {
        	member.setMemberName(loginUser.getMemberName());
        }
        if (member.getMemberBirth() == null) {
        	member.setMemberBirth(loginUser.getMemberBirth());
        }
        if (member.getMemberGender() == null || member.getMemberGender().isEmpty()) {
        	member.setMemberGender(loginUser.getMemberGender());
        }
        if (member.getMemberNickname() == null || member.getMemberNickname().isEmpty()) {
        	member.setMemberNickname(loginUser.getMemberNickname());
        }
        if (member.getMemberEmail() == null || member.getMemberEmail().isEmpty()) {
        	member.setMemberEmail(loginUser.getMemberEmail());
        }
        if (member.getMemberPhone() == null || member.getMemberPhone().isEmpty()) {
        	member.setMemberPhone(loginUser.getMemberPhone());
        }

        member.setMemberId(loginUser.getMemberId());

        
        
        
        if (newPassword != null && !newPassword.isEmpty()) {
            member.setMemberPwd(bcrypt.encode(newPassword));
        }else {
        	member.setMemberPwd(loginUser.getMemberPwd());
        }

        int result = mService.updateMember(member);
        if (result > 0) {
            Member updatedMember = loginUser;
            model.addAttribute("loginUser", updatedMember);
            return "redirect:/member/myPage";
        } else {
            return "user-inform/editMyPage";
        }
			
    }

	
	
//	@GetMapping("/member/checkPwd")
//	public String checkPwd() {
//		return "user-inform/checkPwd";
//	}
	
	
//	//비밀번호 확인 페이지로 이동
	@PostMapping("/member/checkPwd")
	@ResponseBody
	public String checkPwd(@RequestParam("password") String password,HttpSession session){
		if(password == null || password.isEmpty()) {
			System.out.println(password);
			return "error";
		}
		
		Member loginUser = (Member)session.getAttribute("loginUser");
		System.out.println("password : " + password);
		System.out.println("loginUser.getMemberPwd() : " + loginUser.getMemberPwd());
		
		
		if(loginUser != null && bcrypt.matches(password, loginUser.getMemberPwd())) {
			return "success";
		}else {
			return "fail";
		}
		
	}
	
	@GetMapping("/member/checkPwd")
	public String checkPwdView() {
		return "user-inform/checkPwd";
	}
	
	
	//내가 참여한 활동 페이지로 이동
	@GetMapping("/member/myactivite")
	public String myActivite(HttpSession session,Model model) {
		Member loginUser = (Member)session.getAttribute("loginUser");
		if(loginUser == null) {
			return "redirect:/member/login";
		}
		String id = loginUser.getMemberId();
		ArrayList<Board> list = mService.selectMyActivite(id);
		System.out.println("activite : " + list.size());
		for(Board board : list) {
			System.out.println("board11 : " + board);
		}
		
		model.addAttribute("list",list);
		model.addAttribute("count", list.size());
		return "user-inform/myactivite";
	}
	
	// 내가 쓴 게시글 페이지로 이동
	@GetMapping("/member/mypost")
	public String myPost(HttpSession session, Model model) {
		Member loginUser = (Member)session.getAttribute("loginUser");
		if(loginUser == null) {
			return "redirect:/member/login";
		}
		
		ArrayList<Board> list = mService.selectMyPost(loginUser.getMemberId());
		HashMap<Integer,ArrayList<Member>> map = new HashMap<>();
		
		for(Board board : list) {
			ArrayList<Member> participants = mService.participants(board.getBoardNo());
			map.put(board.getBoardNo(), participants);
			
		}
		
		model.addAttribute("list", list);
		model.addAttribute("map", map);
		model.addAttribute("count", list.size());
		
		System.out.println("list : " +list);
		System.out.println("map : " +map);
		System.out.println("listsize : " +list.size());
		
		return "user-inform/mypost";
	}
	
	@PostMapping("/member/handleParticipant")
	@ResponseBody
	public String handleParticipant(@RequestParam("action") String action,
            @RequestParam("boardNo") int boardNo,
            @RequestParam("participantId") String participantId) {
		
		boolean result = mService.handleParticipant(action,boardNo,participantId);
		return result ? "success" : "fail";
	}
	
	@PostMapping("")
	public void insertProfile( HttpSession session,@RequestParam("file") ArrayList<MultipartFile> files) {
		Member loginUser = (Member)session.getAttribute("loginUser");
			for(int i=0; i < files.size(); i++) {
				MultipartFile upload = files.get(i);
				if(!upload.getOriginalFilename().equals("")) {
					 String[] returnArr = saveFile(upload);
					 if(returnArr[1] != null) {
						 
					 }
				}
			}
		
	}
	public String[] saveFile(MultipartFile upload) {
		String savePath ="c:\\imageFiles";
		File folder = new File(savePath);
		if(!folder.exists()) {
			folder.mkdir();
		}
		
		SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMddHHssSSS");
		int ranNum = (int)(Math.random()*100000);
		String imgName = upload.getOriginalFilename();
		String imgRename = sdf.format(new Date()) + ranNum + imgName.substring(imgName.lastIndexOf('.'));
		String renamePath = folder + "\\" + imgRename;
		try {
			upload.transferTo(new File(renamePath));
		} catch (IllegalStateException | IOException e) {
			e.printStackTrace();
		}
		String[] returnArr = new String[2];
		returnArr[0] = savePath;
		returnArr[1] = imgRename;
		return returnArr;
	}
=======
>>>>>>> 00f091061b9b4a441d7267fae366aa68c70a08ba
}