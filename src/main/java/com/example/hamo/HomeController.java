package com.example.hamo;

import java.net.InetAddress;
import java.net.UnknownHostException;
import java.sql.Date;
import java.time.LocalDate;
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
import com.example.hamo.admin.model.vo.Dashboard;
import com.example.hamo.board.model.service.BoardService;
import com.example.hamo.board.model.vo.Board;
import com.example.hamo.board.model.vo.Category;
import com.example.hamo.board.model.vo.Image;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;
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
	public String main(Model model, HttpServletRequest request, HttpSession session) {
		ArrayList<Dashboard> logList = bService.selectAllLog();
		HashMap<String, String> map = new HashMap<String, String>();
		String ipAddress = getClientIp(request);
		String userAgent = request.getHeader("User-Agent");
//		System.out.println(ipAddress + userAgent);
//		System.out.println(logList);
//		System.out.println(LocalDate.now());
		if (!logList.isEmpty()) {
		    boolean isTodayExists = false; // 오늘 날짜의 로그가 이미 있는지 확인
		    boolean isIpAgentDuplicated = false; // IP + User-Agent 중복 여부 확인

		    Date today = new Date(System.currentTimeMillis());
		    LocalDate todayLocalDate = today.toLocalDate();
//		    System.out.println(today);
		    for (Dashboard log : logList) {
		        if (log.getIpAddress().equals(ipAddress) || log.getUserAgent().equals(userAgent)) {
		            isIpAgentDuplicated = true; // 동일한 IP + User-Agent 발견
		            Date visitDate = log.getVisitDate();
		            LocalDate visitLocalDate = visitDate.toLocalDate();
//		            System.out.println(visitDate);
		            if (visitLocalDate.equals(todayLocalDate)) {
		                isTodayExists = true; // 오늘 날짜의 로그가 이미 존재함
		                break; // 더 이상 체크할 필요 없으므로 탈출
		            }
		        }
		    }

		    if (!isIpAgentDuplicated || !isTodayExists) { 
		        // IP + User-Agent가 완전히 새로운 경우 or 오늘 날짜의 로그가 없는 경우 → 삽입
		        System.out.println("로그 삽입됨 (새로운 IP/UserAgent 또는 날짜 다름)");
		        map.put("ipAddress", ipAddress);
		        map.put("userAgent", userAgent);
		        bService.insertLog(map);
		    } else {
		        // 오늘 날짜의 로그가 이미 존재하므로 삽입하지 않음
		        System.out.println("오늘 날짜의 동일한 로그가 존재하여 삽입하지 않음");
		    }

		} else {
		    // 로그가 비어 있으면 무조건 삽입
		    System.out.println("로그가 비어있음 → 로그 삽입");
		    map.put("ipAddress", ipAddress);
		    map.put("userAgent", userAgent);
		    bService.insertLog(map);
		}

		ArrayList<Board> banner = bService.getBanner();
//		for (Board board : banner) {
//            if (board.getThumbnailUrl() != null) {
//                board.setThumbnailUrl(amazonS3.getUrl(bucket, board.getThumbnailUrl()).toString());
//            }
//        }
//		ArrayList<Image> imgList = bService.selectImageListBoard();
//		for (Board board : banner) {
//			for(Image img : imgList) {
//				if(board.getBoardNo() == img.getBuNo()) {
//					board.setThumbnailUrl(amazonS3.getUrl(bucket, img.getImgRename()).toString());
//					break;
//				}
//			}
//		}		
    
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
}