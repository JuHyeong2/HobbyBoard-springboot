package com.example.hamo.board.controller;


import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import com.amazonaws.SdkClientException;
import com.amazonaws.services.s3.AmazonS3Client;
import com.amazonaws.services.s3.model.ObjectMetadata;
import com.example.hamo.admin.model.service.AdminService;
import com.example.hamo.admin.model.vo.Admin;
import com.example.hamo.board.model.service.BoardService;
import com.example.hamo.board.model.vo.Board;
import com.example.hamo.board.model.vo.Image;
import com.example.hamo.board.model.vo.Reply;
import com.example.hamo.common.Pagination;
import com.example.hamo.common.vo.PageInfo;
import com.example.hamo.member.model.vo.Member;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import lombok.RequiredArgsConstructor;


	

@Controller
@RequiredArgsConstructor
@RequestMapping("/board")
public class BoardController {
	private final BoardService bService;
	private final AdminService aService;
	private final AmazonS3Client amazonS3;
	
	// aws S3 버켓 이름
	@Value("${cloud.aws.s3.bucket}")
	private String bucket;
	/*
	 *  게시판 부분 
	 * 
	 * */
	@GetMapping("/{id}")
	public String boarDetails(@PathVariable("id") int bNo, Model model, HttpSession session) {
		Board board = bService.selectBoard(bNo);
		ArrayList<Reply> replyArr = bService.selectReplyList(bNo);
		ArrayList<Image> imageArr = bService.selectImageList(bNo);
		// 댓글 프로필 사진
		ArrayList<Image> imageArr2 = bService.selectUserImageList();
		for(Image img : imageArr2) {
			img.setUrl(amazonS3.getUrl(bucket, img.getImgRename()).toString());
			if(board.getMemberNo() == img.getBuNo()) {
				board.setProfileUrl(amazonS3.getUrl(bucket, img.getImgRename()).toString());
				System.out.println("board.getProfileUrl() : " + board.getProfileUrl());
			}
		}
		
		// Reply에 프로필사진 url넣기
		for(Reply r : replyArr) {
			for(Image img : imageArr2) {
				if(r.getMemberNo() == img.getBuNo()) {
					r.setImageUrl(img.getUrl());
				}
			}
		}
		
		// 게시글 이미지 url가져오기
		for(Image img : imageArr) {
			img.setUrl(amazonS3.getUrl(bucket, img.getImgRename()).toString());
			System.out.println("img : " + img);
		}
		
		Member m = (Member)session.getAttribute("loginUser");
		System.out.println(m);
		if(m != null) {
			HashMap<String, Integer> map = new HashMap<String, Integer>();
			map.put("memberNo", m.getMemberNo());
			map.put("boardNo", bNo);
			int participantResult = bService.selectParticipant(map);
			System.out.println("participantResult : " + participantResult);
			model.addAttribute("participantResult", participantResult);
		}else {
			model.addAttribute("participantResult", 0);
		}
	
		
		// 조회수 +1
		int result = bService.updateBoardCount(board);
		System.out.println(board); 					//테스트
		if(result > 0) {
			model.addAttribute("board", board).addAttribute("list", imageArr).addAttribute("rlist", replyArr);
		}
		if(board.getBoardStatus().equals("N")) {
			model.addAttribute("result", 0);
			return "/";
		} else {
			model.addAttribute("result", 1);
			return "board/boardDetails";
		}
		
	}

	
	
	// 게시글 쓰기 뷰로 이동하는 메소드
	@GetMapping("boardInsert")
	public String boardInsertView() {
		return "board/boardInsert"; 
	}
	
	@PostMapping("boardInsert")
	@Transactional
	public String boardInsert(@ModelAttribute("Board") Board b, HttpSession session, @RequestParam("file") ArrayList<MultipartFile> files) {
		if(files == null || files.isEmpty()) {
			Member m = (Member)session.getAttribute("loginUser");
			b.setMemberNo(m.getMemberNo());
			int result = bService.boardInsert(b);
		}else {
			Member m = (Member)session.getAttribute("loginUser");
			b.setMemberNo(m.getMemberNo());
			int boardResult = bService.boardInsert(b);
			
			ArrayList<Image> list = new ArrayList<Image>();
			for(MultipartFile file : files) {
				if(!file.getOriginalFilename().equals("")) {
					String[] returnArr = saveFiles(file); // 첨부파일 폴더에 파일저장
					if(returnArr[1] != null) {
						Image img = new Image(); // vo에 있는 Image객체임!!!
						img.setImgName(file.getOriginalFilename());
						img.setImgRename(returnArr[1]);
						img.setImgPath(returnArr[0]);
						img.setDelimiter("B");
						System.out.println("b.getBoardNo() : " + b.getBoardNo());
						img.setBuNo(b.getBoardNo());
						list.add(img);
					}
				}
			}
			
			int imageResult = bService.imageInsert(list);
		}

		
		return "redirect:/";
	}
	
//	public String[] saveFile(MultipartFile upload) {
//		String savePath = "c:\\uploadFiles";
//		File folder = new File(savePath);
//		if(!folder.exists()) {
//			folder.mkdirs();
//		}
//		
//		// 같은 폴더에 같은 이름의 파일이 저장되지 않도록 rename -> 년월일시분초밀리랜덤수.확장자
//		SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMddHHmmssSSS");
//		int ranNum = (int)(Math.random()*100000);
//		String originFileName = upload.getOriginalFilename();
////		System.out.println(originFileName);
////		System.out.println(originFileName.lastIndexOf("."));
//		String renameFileName = sdf.format(new Date()) + ranNum + originFileName.substring(originFileName.lastIndexOf("."));
////		System.out.println("renameFileName : " + renameFileName);
//		
//		String renamePath = folder + "\\" + renameFileName;
//		try {
//			upload.transferTo(new File(renamePath));
//		} catch (IllegalStateException | IOException e) {
//			// TODO Auto-generated catch block
//			e.printStackTrace();
//		}
//		String[] returnArr = new String[2];
//		returnArr[0] = savePath;
//		returnArr[1] = renameFileName;
//		return returnArr;
//	}
	
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

	// 게시글 수정 뷰로 이동하는 메소드
	@GetMapping("boardUpdate")
	public String boardUpdateView(@ModelAttribute("Board") Board b, Model model) {
		
		ArrayList<Image> imgArr = bService.selectImageList(b.getBoardNo());
		for(Image img : imgArr) {
			img.setUrl(amazonS3.getUrl(bucket, img.getImgRename()).toString());
		}
		
		model.addAttribute("board", b).addAttribute("imgs", imgArr);
		
		return "board/boardUpdate";
	}
	
	@PostMapping("boardUpdate")
	public String boadUpdate(@ModelAttribute("Board") Board b, HttpSession session, @RequestParam("file") ArrayList<MultipartFile> files) {
		
		return "";
	}
	
	/*
	 *  댓글 부분
	 * 
	 * */
	@GetMapping("insertReply")
	@ResponseBody
	public ArrayList<Reply> insertReply(@ModelAttribute("Reply") Reply r, HttpServletResponse response) {
		response.setContentType("application/json; charset=UTF-8");
		System.out.println(r.toString());
		int result = bService.insertReply(r);
		if(result > 0){
			int bNo = r.getBoardNo();
			ArrayList<Reply> list = bService.selectReplyList(bNo);
			ArrayList<Image> imageArr2 = bService.selectUserImageList();
			for(Reply r : list) {
				for(Image img : imageArr2) {
					if(r.getMemberNo() == img.getBuNo()) {
						r.setImageUrl(img.getUrl());
					}
				}
			}
			return list;
		}else {
			return null;
		}
	}
	
	@GetMapping("noticeView")
	public String noticeView(Model model, @RequestParam(value="page", defaultValue="1")int currentPage,HttpServletRequest request) {
		int listCount =  aService.getListCount();
		PageInfo pi = Pagination.getPageInfo(currentPage, listCount, 6);
		ArrayList<Admin> list = aService.selectNoticeList(pi);
		model.addAttribute("aList", list).addAttribute("pi", pi).addAttribute("loc", request.getRequestURI());
		
		System.out.println(list);
		
		return "board/noticeView";
	}
	
	@GetMapping("/{id}/{page}")
	public String selectNotice(@PathVariable("id") int bId, @PathVariable("page") int page, HttpSession session, Model model) {
		Member m = (Member)session.getAttribute("loginUser");
		Admin a = bService.selectNotice(m, bId);
		
		if(a != null) {
			model.addAttribute("a", a).addAttribute("page", page);
			return "board/noticeDetail";
		} else {
			return null;
		}
	}
	
	
	
	@GetMapping("project")
	public String project() {
		return "board/project";
	}
	
	@PostMapping("participant")
	@ResponseBody
	public int applyForParticipantion(@RequestParam("memberNo") int memberNo, @RequestParam("boardNo") int boardNo) {
		System.out.println("memberNo : " + memberNo);
		HashMap<String, Integer> map = new HashMap<String, Integer>();
		map.put("memberNo", memberNo);
		map.put("boardNo", boardNo);
		int result = bService.insertParticipant(map);
		if(result > 0) {
			return result;
		}else {
			return result;
		}
		
	}

}