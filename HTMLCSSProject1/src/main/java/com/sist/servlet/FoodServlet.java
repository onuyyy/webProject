package com.sist.servlet;
import java.util.*;
import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.sist.dao.FoodDAO;
import com.sist.dao.FoodVO;

import java.io.*;
/*
 * 					     sql 문장 전송
 * 브라우저 <========= 자바 =========> 오라틀
 * 			html 변환  ㅣ  실행된 데이터 읽기
 * 					Servlet / JSP(웹파일)
 * 				    -------   --- 
 * 					자바로만	 자바+html
 * 			Servlet : 서버에서 실행된 가벼운 프로그램
 * 						=> 수정시마다 컴파일을 한다
 * 						=> 보완: JSP
 * 						=> servlet,jsp => 호출하는 것이 아니라 톰캣에 의해 호출이 된다 => url 주소를 통해 호출
 * 
 * 			호출 순서
 * 				=> new FoodServlet() => 톰캣이 호출
 * 					1) init() : 변수 초기화, 필요한 값 읽어올 때
 * 							=> 자동로그인, 보안, ...
 * 					2) service() : 사용자가 요청한 HTML을 만드는 위치		==> 브라우저로 값을 전송하는 위치
 * 																		---------------------- JSP (메소드 영역)
 * 																								=> 호출 시에 자동으로 Servlet으로 변경 (한 개의 클래스를 만든다)
 * 							=> doGet() : GET
 * 							=> doPost() : POST
 * 					3) destroy() : 메모리 해제
 * 							=> 페이지 이동, 새로고침, 브라우저 종료, ...
 */

@WebServlet("/FoodServlet")
public class FoodServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
 
    public FoodServlet() {
        super();
       
    }


	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
	
	response.setContentType("text/html;charset=UTF-8");
	FoodDAO dao = FoodDAO.newInstance();
	List<FoodVO> list = dao.foodAllData();
	PrintWriter out = response.getWriter();
	// 브라우저에 출력하는 내용물 -> html
	
	out.println("<html>");
	out.println("<head>");
	out.println("<script src=\"http://code.jquery.com/jquery.js\"></script>");
	out.println("<script>");
	out.println("$(function(){");
	out.println("$('#keyword').keyup(function(){");
	out.println("let k=$(this).val();");
	out.println("$('#user-table > tbody > tr').hide();");
	out.println("let temp=$('user-table > tbody > tr > td:nth-child(5n+2):contains('\"+k+\"')');");
	out.println("$(temp).parent().show();");
	out.println("})");
	out.println("})");
	out.println("</script>");
	out.println("</head>");
	
	out.println("<body>");
	out.println("<center>");
	out.println("<h1>맛집 목록</h1>");
	out.println("<table border=1 bordercolor=black width=960>");
	out.println("<tr>");
	out.println("<td>");
	out.println("<input type=text size=30 id=keyword>");
	out.println("</td>");
	out.println("</tr>");
	out.println("</table>");
	out.println("<table border=1 bordercolor=black width=960 id=user-table>");
	out.println("<thead>");	// => 한 번만 실행할 때
	out.println("<tr>");
	out.println("<th></th>");
	out.println("<th>맛집명</th>");
	out.println("<th>주소</th>");
	out.println("<th>전화</th>");
	out.println("<th>음식종류</th>");
	out.println("</tr>");
	out.println("</thead>");
	out.println("<tbody>");		// => 실제 값을 출력하는 위치
	
	for(FoodVO vo:list) {
		out.println("<tr>");
		out.println("<td><img src="+vo.getPoster()+" width=30 height=30></td>");
		out.println("<td>"+vo.getName()+"</td>");
		out.println("<td>"+vo.getAddress()+"</td>");
		out.println("<td>"+vo.getPhone()+"</td>");
		out.println("<td>"+vo.getType()+"</td>");
		out.println("</tr>");
	}
	
	
	out.println("</tbody>");
	out.println("</table>");
	out.println("</center>");
	out.println("</body>");
	out.println("</html>");

	
	
	
	}
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
	
	}

}
