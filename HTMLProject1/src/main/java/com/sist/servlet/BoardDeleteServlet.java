package com.sist.servlet;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.sist.dao.BoardDAO;


@WebServlet("/BoardDeleteServlet")
public class BoardDeleteServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	// 화면 출력 => HTML => 비밀번호 입력
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		response.setContentType("text/html;charset=utf-8");
		// 클라이언트가 전송한 값을 받는다
		// BoardDeleteServlet?no=
		String no = request.getParameter("no");	// > 변수 값 받음
		PrintWriter out = response.getWriter();
		out.println("<html>");
		out.println("<head>");
		out.println("<link rel = stylesheet href=html/table.css>");
		out.println("</head>");
		out.println("<body>");
		out.println("<center>");
		out.println("<h1>삭제하기</h1>");
		out.println("<form method=post action=BoardDeleteServlet>");
		out.println("<table class_table_content width=300>");
		out.println("<tr>");
		out.println("<th width=30%>비밀번호</th>");
		out.println("<td width=70%><input type=pasword name=pwd size=15 required>");
		out.println("<input type = hidden name=no value="+no+">");
		
		// 사용자에게 보여주면 안 되는 데이터 => 화면 출력없이 데이터를 전송 : hidden
		out.println("</td></tr>");
		out.println("<tr>");
		out.println("<td colspan=2 align=center>");
		out.println("<input type = submit value=삭제>");
		out.println("<input type = button value = 취소 onclick=\"javascript:history.back()\">");
		out.println("</td>");
		out.println("</tr>");
		out.println("</table>");
		out.println("</form>");
		out.println("</center>");
		out.println("</body>");
		out.println("</html>");

		
	}

	
	
	// 요청에 대한 처리 담당 => 삭제 처리
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		
		response.setContentType("text/html;charset=utf-8");
		// 사용자가 전송한 값 받기
		String no = request.getParameter("no");
		String pwd = request.getParameter("pwd");
		
		// 디코딩은 한글일 때만 쓴다
		// 숫자, 알파벳은 깨지지 않는다   1,3 동시 취급
		
		PrintWriter out = response.getWriter();
		// DAO
		BoardDAO dao = BoardDAO.newInstance();
		boolean bCheck = dao.boardDelete(Integer.parseInt(no), pwd);
		
		if(bCheck==true) {
			response.sendRedirect("BoardListServelt?no="+no);
			
		} else {
			out.println("<script>");
			out.println("alert(\"비밀번호 틀립니다!\");");
			out.println("history.back();");			
			out.println("</script>");
		}
		
		
	}

}
