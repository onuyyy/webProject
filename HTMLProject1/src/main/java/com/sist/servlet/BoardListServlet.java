package com.sist.servlet;

import java.io.IOException;
import java.io.PrintWriter;
import java.text.SimpleDateFormat;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.*;
import com.sist.dao.*;
import com.sist.dao.BoardVO;


/**
 * Servlet implementation class BoardListSevlet
 */
@WebServlet("/BoardListServlet")

public class BoardListServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		
		
		// JSP
		// 1. 변환 => 전송 (HTML,XML,JSON)
		// 브라우저로 미리 알려준다
		response.setContentType("text/html;charset=UTF-8");
		// XML => text/xml, JSON => text / plain
		PrintWriter out = response.getWriter();
		// 사용자의 브라우저에서 읽어가는 위치를 설정 => OutputStream
		
		String strPage=request.getParameter("page");
			// 사용자가 보내준 값은 파라미터로 받는다 =>request
		if(strPage==null)
			strPage="1";
			int curpage=Integer.parseInt(strPage);
				
		BoardDAO dao = BoardDAO.newInstance();
		List<BoardVO> list = dao.boardListData(curpage);
		
		// 총페이지 받기
		int totalpage = dao.boardTotalPage();
		
		out.println("<html>");
		out.println("<head>");
		out.println("<link rel=stylesheet href=html/table.css>");
		out.println("</head>");
		out.println("<body>");	// >> 브라우저로 요청한 사람에게 보낸다
		out.println("<center>");
		out.println("<h1>자유게시판</h1>");
		out.println("<table width=700 class=table_content>");
		out.println("<tr>");
		out.println("<td>");
		out.println("<a href=BoardInsertServlet>새글</a>");	
		out.println("</td>");
		out.println("</tr>");	
		out.println("<th width=10%>번호</th>");
		out.println("<th width=45%>제목</th>");
		out.println("<th width=15%>이름</th>");
		out.println("<th width=20%>작성일</th>");
		out.println("<th width=10%>조회수</th>");
		out.println("</tr>");	
		
		Date date = new Date();
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
		String today = sdf.format(date);
		
		for(BoardVO vo:list) {
			
		out.println("<tr class=dataTr>");
		out.println("<td width=10% align = center>"+vo.getNo()+"</td>");
		out.println("<td width=45%><a href=BoardDetailServlet?no="+vo.getNo()+">"+vo.getSubject()+"</>");
		
		if(today.equals(vo.getDbday())) {
			out.println("&nbsp;<sup style=\"color:red\">new</sup>");
		}
		
		out.println("</td>");	
		out.println("<td width=15% align = center>"+vo.getName()+"</td>");
		out.println("<td width=20% align = center>"+vo.getDbday()+"</td>");
		out.println("<td width=10% align = center>"+vo.getHit()+"</td>");
		out.println("</tr>");	
		
		}
		
		out.println("<tr>");
		out.println("<td colspan=5 align=center>");
		out.println("<a href=BoardListServlet?page="+(curpage>1?curpage-1:curpage)+">이전</a>");
		out.println(curpage+"0 page /"+totalpage+" pages");
		out.println("<a href=BoardListServlet?page="+(curpage<totalpage?curpage+1:curpage)+">다음</a>");
		out.println("</td>");	
		out.println("</tr>");
		out.println("</table>");		
		out.println("</center>");
		out.println("</body>");
		out.println("</html>");

		// 자바는 컴파일하면 사라지고 html 문장만 남느다
	
	}

}
