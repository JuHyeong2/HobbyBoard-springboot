package com.example.hamo;

import java.util.ArrayList;
import java.util.HashMap;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import com.amazonaws.services.s3.AmazonS3Client;
import com.example.hamo.board.model.service.BoardService;
import com.example.hamo.board.model.vo.Board;
import com.example.hamo.board.model.vo.Image;

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
	public String main(Model model) {
		
		ArrayList<Board> list = bService.selectBoardList();
		ArrayList<Image> imgList = bService.selectImageListBoard();
		for(Board  board : list) {
			for(Image img : imgList) {
				if(board.getBoardNo() == img.getBuNo()) {
					board.setThumbnailUrl(amazonS3.getUrl(bucket, img.getImgRename()).toString());
					break;
				}
			}
		}
		
		ArrayList<Board> participants = bService.participantsByBoard();
		System.out.println(participants);
		for(Board b : list) {
			for(Board b2 : participants) {
				if(b.getBoardNo() == b2.getBoardNo()) {
					b.setParticipants(b2.getParticipants());
				}
			}
		}
		
		model.addAttribute("list", list);
		
		return "index";
	}
}