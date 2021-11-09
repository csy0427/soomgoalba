package com.mycompany.myapp06.member.model.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.mycompany.myapp06.member.model.dao.MemberDao;
import com.mycompany.myapp06.member.model.vo.Member;

@Service("memberService")//네이밍룰 조별로 정하기
public class MemberService {
	@Autowired
	private MemberDao memberDao;
	
	public List<Member> selectMembers(){
		return memberDao.selectMembers();
	}
}
