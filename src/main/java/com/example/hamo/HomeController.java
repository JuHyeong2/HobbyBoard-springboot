package com.example.hamo;

import java.net.InetAddress;
import java.net.UnknownHostException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import com.amazonaws.services.s3.AmazonS3Client;
import com.example.hamo.board.model.service.BoardService;
import com.example.hamo.board.model.vo.Board;
import com.example.hamo.board.model.vo.Category;
import com.example.hamo.board.model.vo.Image;

import jakarta.servlet.http.HttpServletRequest;
import lombok.RequiredArgsConstructor;

@Controller
@RequiredArgsConstructor
public class HomeController {
	private final BoardService bService;
	private final AmazonS3Client amazonS3;
	
	// aws S3 버켓 이름
	@Value("${cloud.aws.s3.bucket}")
	private String bucket;
	
	@GetMapping("/")
	public String main(Model model, HttpServletRequest request) {
		String ipAddress = getClientIp(request);
		String userAgent = request.getHeader("User-Agent");
		
		HashMap<String, String> map = new HashMap<String, String>();
		map.put("ipAddress", ipAddress);
		map.put("userAgent", userAgent);
		
		int result = bService.insertLog(map);
		
		ArrayList<Board> banner = bService.getBanner();
//		for (Board board : banner) {
//            if (board.getThumbnailUrl() != null) {
//                board.setThumbnailUrl(amazonS3.getUrl(bucket, board.getThumbnailUrl()).toString());
//            }
//        }
		ArrayList<Image> imgList = bService.selectImageListBoard();
		for (Board board : banner) {
			for(Image img : imgList) {
				if(board.getBoardNo() == img.getBuNo()) {
					board.setThumbnailUrl(amazonS3.getUrl(bucket, img.getImgRename()).toString());
					break;
				}
			}
		}		
    
		// 썸네일 이미지 가져오기
		ArrayList<Board> list = bService.selectBoardList();
		BoardList(list);
		ArrayList<Category> category = bService.selectCategory();
		
		model.addAttribute("list", list);
		model.addAttribute("category",category);
		model.addAttribute("banner", banner);
		
		return "index";
	
	}

	@GetMapping("/category/{categoryNo}")
	public String boardCategory(@PathVariable("categoryNo") int categoryNo, Model model) {
		ArrayList<Board> list = bService.boardCategory(categoryNo);
		BoardList(list);
		
		model.addAttribute("list", list);
		return "index :: #boardGrid";
	}
	
	
	// 썸네일 이미지 가져오기
	private void BoardList(ArrayList<Board> list) {
		ArrayList<Image> imgList = bService.selectImageListBoard();
		for(Board  board : list) {
			for(Image img : imgList) {
				if(board.getBoardNo() == img.getBuNo()) {
					board.setThumbnailUrl(amazonS3.getUrl(bucket, img.getImgRename()).toString());
					break;
				}
			}
		}

		// 참여자 가져오기
		ArrayList<Board> participants = bService.participantsByBoard();
		System.out.println(participants);
		for(Board b : list) {
			for(Board b2 : participants) {
				if(b.getBoardNo() == b2.getBoardNo()) {
					b.setParticipants(b2.getParticipants());
				}
			}
		}
		
	}

	// client 실ip 가져오는 메소드
	public static String getClientIp(HttpServletRequest request) {
	    String clientIp = null;
	    boolean isIpInHeader = false;

	    List<String> headerList = new ArrayList<>();
	    headerList.add("X-Forwarded-For");
	    headerList.add("HTTP_CLIENT_IP");
	    headerList.add("HTTP_X_FORWARDED_FOR");
	    headerList.add("HTTP_X_FORWARDED");
	    headerList.add("HTTP_FORWARDED_FOR");
	    headerList.add("HTTP_FORWARDED");
	    headerList.add("Proxy-Client-IP");
	    headerList.add("WL-Proxy-Client-IP");
	    headerList.add("HTTP_VIA");
	    headerList.add("IPV6_ADR");

	    for (String header : headerList) {
	        clientIp = request.getHeader(header);
	        if (StringUtils.hasText(clientIp) && !"unknown".equalsIgnoreCase(clientIp)) {
	            isIpInHeader = true;
	            break;
	        }
	    }

	    if (!isIpInHeader) {
	        clientIp = request.getRemoteAddr();
	    }
	    
	    if ("0:0:0:0:0:0:0:1".equals(clientIp) || "127.0.0.1".equals(clientIp)) {
	        InetAddress address = null;
			try {
				address = InetAddress.getLocalHost();
			} catch (UnknownHostException e) {
				e.printStackTrace();
			}
	        clientIp = address.getHostAddress();
	    }

	    
	    return clientIp;
	}
	
	// client 실ip 가져오는 메소드
	public static String getClientIp(HttpServletRequest request) {
	    String clientIp = null;
	    boolean isIpInHeader = false;

	    List<String> headerList = new ArrayList<>();
	    headerList.add("X-Forwarded-For");
	    headerList.add("HTTP_CLIENT_IP");
	    headerList.add("HTTP_X_FORWARDED_FOR");
	    headerList.add("HTTP_X_FORWARDED");
	    headerList.add("HTTP_FORWARDED_FOR");
	    headerList.add("HTTP_FORWARDED");
	    headerList.add("Proxy-Client-IP");
	    headerList.add("WL-Proxy-Client-IP");
	    headerList.add("HTTP_VIA");
	    headerList.add("IPV6_ADR");

	    for (String header : headerList) {
	        clientIp = request.getHeader(header);
	        if (StringUtils.hasText(clientIp) && !"unknown".equalsIgnoreCase(clientIp)) {
	            isIpInHeader = true;
	            break;
	        }
	    }

	    if (!isIpInHeader) {
	        clientIp = request.getRemoteAddr();
	    }
	    
	    if ("0:0:0:0:0:0:0:1".equals(clientIp) || "127.0.0.1".equals(clientIp)) {
	        InetAddress address = null;
			try {
				address = InetAddress.getLocalHost();
			} catch (UnknownHostException e) {
				e.printStackTrace();
			}
	        clientIp = address.getHostAddress();
	    }

	    
	    return clientIp;
	}
}