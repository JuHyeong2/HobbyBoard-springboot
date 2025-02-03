
package com.example.hamo.member.model.service;

import java.util.ArrayList;
import java.util.HashMap;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.example.hamo.board.model.vo.Board;
import com.example.hamo.board.model.vo.Image;
import com.example.hamo.member.model.mapper.MemberMapper;
import com.example.hamo.member.model.vo.Member;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
@Transactional
public class MemberService {
	private final MemberMapper mMapper;
	
	
	public int insertMember(Member member) {
		
		return mMapper.insertMember(member);
	}

	public int idCheck(String userId) {
		
		return mMapper.idCheck(userId);
	}

	public Member login(Member m) {
		
		return mMapper.login(m);
	}

	public Member findId(int phone) {
		
		return mMapper.findId(phone);
	}
public Member findId(HashMap<String, String> map) {
		
		return mMapper.findId(map);
	}

	public int updatePwd(Member m) {
		
		return mMapper.updatePwd(m);
	}
	

	public Member selectMember(String id) {
		return mMapper.selectMember(id);
	}

//	public int updateMember(Member m) {
//		return mMapper.updateMember(m);
//	}

	public ArrayList<Board> selectMyActivite(String id) {
		return mMapper.selectMyActivite(id);
	}

	public ArrayList<Board> selectMyPost(String memberId) {
		return mMapper.selectMyPost(memberId);
	}

	public boolean updateMember(Member member) {
		return mMapper.updateMember(member) > 0;
	}

	public Member memberId(String memberId) {
		return mMapper.memberId(memberId);
	}


	public String handleParticipant(String action, int boardNo, int participantId) {
	    int result;
	    if ("a".equals(action)) {
	        result = mMapper.acceptParticipant(boardNo, participantId);
	    } else if ("r".equals(action)) {
	        result = mMapper.rejectParticipant(boardNo, participantId);
	    } else {
	        return "error";
	    }
	    return result > 0 ? "success" : "error";
	}

	public ArrayList<Member> participants(int boardNo) {
		return mMapper.participants(boardNo);
	}

	public int checknickName(String nickname) {
		return mMapper.checknickName(nickname);
	}

	

//	public boolean updateProfileImage(int memberNo, Image image) {
//	   Member member = mMapper.selectMember(String.valueOf(memberNo));
//	      if (member == null) {
//	            return false;
//	        }
//
//	        image.setBuNo(member.getMemberNo());
//	        image.setDelimiter("U");
//
//	        Image existingImage = mMapper.getProfileImage(memberNo);
//	        if (existingImage == null) {
//	            return mMapper.insertProfileImage(image) > 0;
//	        } else {
//	            image.setImgNo(existingImage.getImgNo());
//	            return mMapper.updateProfileImage(image) > 0;
//	        }
//	    }

	public boolean saveOrUpdateProfileImage(Image image) {
	        Image existingImage = mMapper.getProfileImage(image.getBuNo());
	        if (existingImage == null) {
	            return mMapper.insertProfileImage(image) > 0;
	        } else {
	            image.setImgNo(existingImage.getImgNo());
	            return mMapper.updateProfileImage(image) > 0;
	        }
	    }

	public Image getProfileImage(int memberNo) {
	       return mMapper.getProfileImage(memberNo);
	    }

	
	public Image selectImage(int memberNo) {
		return mMapper.selectImage(memberNo);
		}





		public String getParticipantStatus(int boardNo, int participantId) {

		    return mMapper.getParticipantStatus(boardNo, participantId);
		}


	public boolean updateBoardStatus(int boardNo) {
	    return mMapper.updateBoardStatus(boardNo) > 0;
	}
	
	public String checkStatus(int boardNo) {
	    Board board = mMapper.getBoardInfo(boardNo);
	    int acceptedParticipants = mMapper.getAcceptedParticipantsCount(boardNo);
	    
	    if (acceptedParticipants >= board.getMaxParticipants()) {
	        return "FULL";
	    } else {
	        return "OPEN";
	    }
	}
}
