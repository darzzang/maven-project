package co.micol.prj.member.command;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import co.micol.prj.comm.Command;
import co.micol.prj.member.service.MemberService;
import co.micol.prj.member.service.MemberVO;
import co.micol.prj.member.serviceImpl.MemberServiceImpl;

public class MemberSelect implements Command {
	String returnPage;	// 돌려줄 페이지를 담을 변수 선언

	@Override
	public String run(HttpServletRequest request, HttpServletResponse response) {
		
		MemberService memberService = new MemberServiceImpl();	// 모델을 사용하기 위해 선언
		MemberVO memberVO = new MemberVO();	// 값을 전달하거나 받기위해(한행의 레코드)
		memberVO.setId(request.getParameter("id"));	// 폼에서 넘어온 아이디 값을 VO객체에 담음 
		
		memberVO = memberService.memberSelect(memberVO);	// 모델을 호출
		if(memberVO != null) {
			request.setAttribute("member", memberVO);	// 결과를 request 객체에 담음
			returnPage = "member/memberSelect";	// 돌려줄 페이지 설정
		} else {
			request.setAttribute("message", "회원 세부 정보 불러오기 실패");
			returnPage = "member/memberFail";
		}
		return returnPage;
	}

}
