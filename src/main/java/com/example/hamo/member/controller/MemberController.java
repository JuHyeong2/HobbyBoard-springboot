package com.example.hamo.member.controller;


import java.io.File;
import java.io.IOException;
import java.io.PrintWriter;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;

import org.springframework.beans.factory.annotation.Value;
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
import org.springframework.web.multipart.MultipartFile;

import com.amazonaws.SdkClientException;
import com.amazonaws.services.s3.AmazonS3Client;
import com.amazonaws.services.s3.model.ObjectMetadata;
import com.example.hamo.board.model.vo.Board;
import com.example.hamo.board.model.vo.Image;
import com.example.hamo.common.util.EmailCertificationUtil;
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
	private final EmailCertificationUtil emailUtil;
	private final AmazonS3Client amazonS3;
	
	// aws S3 버켓 이름
	@Value("${cloud.aws.s3.bucket}")
	private String bucket;
	
	// 로그인 화면으로 가는 메서드
	@GetMapping("/member/login")	
	public String loginView() {
		
		return "member/login";
	}
	
	// 로그인 기능을 하는 메소드
	@PostMapping("/member/login")
	@ResponseBody
	public String login(@ModelAttribute("Member") Member m, Model model, HttpSession session) {
		Member loginUser = mService.login(m);
		// 프로필 이미지도 세션에 저장하는게 좋을 듯
		Image userImage = mService.selectImage(loginUser.getMemberNo());
		if(userImage != null) {
			loginUser.setImageUrl(amazonS3.getUrl(bucket, userImage.getImgRename()).toString());
		}
		
		if(loginUser != null && bcrypt.matches(m.getMemberPwd(), loginUser.getMemberPwd())) {
			model.addAttribute("loginUser", loginUser);
//			session.setAttribute("loginUser", loginUser);
			System.out.println(session.getAttribute("loginUser"));
			System.out.println("success");

			return "success";
		}else {
			return "fail";
		}
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

//	@GetMapping("/home")
//	public String home() {
//		ArrayList<Board> list = bService.selectBoardList();
//		System.out.println("list : " + list);
//		
//		model.addAttribute("list", list);
//		
//		return "index";
//	}

	
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

		
	
	@PostMapping("/member/sendEmail")
	@ResponseBody
	public String sendEmail(@RequestParam("email") String email) {
		System.out.println("email : " + email);
		String certificationCode = Integer.toString((int)(Math.random() * (999999 - 100000 + 1)) + 100000); // 6자리 인증 코드를 랜덤으로 생성
		emailUtil.sendEmail(email, certificationCode);
		return certificationCode;
	}
	
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
	public Member findId(@RequestParam("value") String value, @RequestParam("column") String column) {
//		System.out.println(userId);
		HashMap<String, String> map = new HashMap<String, String>();
		map.put("column", "member_" + column);
		map.put("value", value);
		Member m = mService.findId(map);
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
	}
	

	@GetMapping("/member/myPage")
    public String myPage(HttpSession session, Model model) {
        Member loginUser = (Member) session.getAttribute("loginUser");
        if (loginUser == null) {
            return "redirect:/member/login";
        }
        
        Member updatedMember = mService.selectMember(loginUser.getMemberId());
        if (updatedMember == null) {
            return "redirect:/error";
        }
        
        Image image = mService.selectImage(loginUser.getMemberNo());
        
//		for(Image img : imageArr) {
////			strArr.add(amazonS3.getUrl(bucket, img.getImgRename()).toString());
//			img.setUrl(amazonS3.getUrl(bucket, img.getImgRename()).toString());
//		}
//		String profileImage = image.getUrl(amazonS3.getUrl(bucket, image.getImgRename()).toString();
        if(image != null) {
        	image.setUrl(amazonS3.getUrl(bucket, image.getImgRename()).toString());
        }
        


        model.addAttribute("m", updatedMember);
        model.addAttribute("profileImage", image);
        return "user-inform/myPage";
    }



	@GetMapping("/member/editMyPage")
	public String editMyPage(HttpSession session, Model model) {
		Member loginUser = (Member)session.getAttribute("loginUser");
		if(loginUser == null) {
			return "redirect:/member/login";
		}
		Image profileImage = mService.getProfileImage(loginUser.getMemberNo());
	    model.addAttribute("loginUser", loginUser);
	    model.addAttribute("profileImage", profileImage);
	    return "user-inform/editMyPage";
	}
	
	@PostMapping("/member/editMyPage")
	public String updateMember(@RequestParam(value = "file", required = false) MultipartFile file,@ModelAttribute Member member, @RequestParam(value = "newPassword", required = false) String newPassword, HttpSession session) {
	    Member loginUser = (Member) session.getAttribute("loginUser");
	    if (loginUser == null) {
	        return "redirect:/member/login";
	    }
	    
	    if (file != null && !file.isEmpty()) {
	        String fileName = file.getOriginalFilename();
	        
	        String[] files = saveFiles(file);
	        if (files[1] != null) {
	            Image profileImage = new Image();
	            profileImage.setImgName(fileName);
	            profileImage.setImgPath(files[0]);
	            profileImage.setImgRename(files[1]);
	            profileImage.setDelimiter("U");
	            profileImage.setBuNo(loginUser.getMemberNo());
	            
	            mService.saveOrUpdateProfileImage(profileImage);
	        }
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
	    } else {
	    	member.setMemberPwd(loginUser.getMemberPwd());
	    }
	
	    boolean updated = mService.updateMember(member);
	    if (updated) {
	        Member updatedMember = mService.selectMember(loginUser.getMemberId());
	        session.setAttribute("loginUser", updatedMember);
	        
	        System.out.println("1111111111111" + updatedMember);
	        System.out.println("22222222222222" + loginUser);
	        System.out.println("#333333333333333" + member);
	        
	        return "redirect:/member/myPage";
	    } else {
	        return "redirect:/member/editMyPage";
	    }
	}

	private String saveFile(MultipartFile file) {
		String savePath = "C:\\uploadFiles";
		File folder = new File(savePath);
		if (!folder.exists()) {
		    folder.mkdirs();
		}
		
		String originalFileName = file.getOriginalFilename();
		String renameFileName = generateRenamedFileName(originalFileName);
		
		try {
		    file.transferTo(new File(savePath + "\\" + renameFileName));
		    return "/uploadFiles/" + renameFileName; 
		} catch (IOException e) {
		    e.printStackTrace();
		    return null;
		}
	}
	
	public String[] saveFiles(MultipartFile upload) {
		SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMddHHmmssSSS");
		int ranNum = (int)(Math.random()*100000);
		String originFileName = upload.getOriginalFilename();
		String renameFileName = sdf.format(new Date()) + ranNum + originFileName.substring(originFileName.lastIndexOf("."));
		
		ObjectMetadata metadata = new ObjectMetadata();
		metadata.setContentLength(upload.getSize());
		metadata.setContentType(upload.getContentType());
		
		try {
			amazonS3.putObject(bucket, renameFileName, upload.getInputStream(), metadata);
		} catch (SdkClientException | IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		String[] returnArr = new String[2];
		returnArr[0] = amazonS3.getUrl(bucket, renameFileName).toString();
		returnArr[1] = renameFileName;
		
		return returnArr;
	}
	
	private String generateRenamedFileName(String originalFileName) {
	    SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMddHHmmssSSS");
	    int ranNum = (int) (Math.random() * 100000);
	    String extension = originalFileName.substring(originalFileName.lastIndexOf("."));
	    return sdf.format(new Date()) + ranNum + extension;
	}


	
	
	//비밀번호 확인 페이지로 이동
	
	@GetMapping("/member/checkPwd")
	public String checkPwdView() {
		return "user-inform/checkPwd";
	}
	
	
	@PostMapping("/member/checkPwd")
	@ResponseBody
	public String checkPwd(@RequestParam("password") String password,HttpSession session){
		if(password == null || password.isEmpty()) {
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
		HashMap<Integer,ArrayList<Member>> participantsMap = new HashMap<>();
		
		for(Board board : list) {
			ArrayList<Member> participants = mService.participants(board.getBoardNo());
			participantsMap.put(board.getBoardNo(), participants);
		}
		
//		System.out.println("posts : " + list.size());
//		for(Board board : list) {
//			System.out.println("board22 : " + board);
//		}
		model.addAttribute("list", list);
		model.addAttribute("participantsMap", participantsMap);
		model.addAttribute("count", list.size());
		return "user-inform/mypost";
	}
	// 참가자 목록 수락, 거절 
	@PostMapping("/member/handleParticipant")
	@ResponseBody
	public String handleParticipant(@RequestParam("action") String action,
            @RequestParam("boardNo") int boardNo,
            @RequestParam("participantId") int participantId) {
		
		boolean result = mService.handleParticipant(action,boardNo,participantId);
		
		return result ? "success" : "fail";
	}
	
	
	@GetMapping("/member/checknickName")
	public void checknickName(@RequestParam("nickname") String nickname,PrintWriter out) {
		int count = mService.checknickName(nickname);
		out.print(count);
	}
	
}
