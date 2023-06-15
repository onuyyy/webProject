package com.sist.servlet;

import java.io.*;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import com.sist.dao.*;


@WebServlet("/StudentInsert")
public class StudentInsert extends HttpServlet {
	private static final long serialVersionUID = 1L;


	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		response.setContentType("text/html;charset=UTF-8");
		PrintWriter out = response.getWriter();
		
		out.println("<html>");
		out.println("<head>");
		out.println("<link rel=\"stylesheet\" href=\"https://maxcdn.bootstrapcdn.com/bootstrap/3.4.1/css/bootstrap.min.css\">");
		out.println("<style>");
		out.println("<.container{margin:50px}>");
		out.println("<.row{margin:0px suto; width:350px;}>");
		out.println("<h1{text-align:center}>");	
		out.println("</style>");
		out.println("</head>");
		out.println("<body>");
		out.println("<div class=container>");
		out.println("<h1>학생 등록</h1>");
		out.println("<div class=row>");
		
		out.println("<form method=post action=StudentInsert>");	// studentinsert의 post 호출해서 처리해라 => submit을 눌러야 form 태그 실행
		// post => doPost
		// 나머지는 => doGet
		/*
		 * 	<form method=get> => doGet 호출
		 * 	<form method=aaa> => doGet 호출
		 * 	<form> => doGet 호출
		 * 		==> post라고 써야 doPost를 호출, 이외에는 doGet 호출 (생략해도 doGet)
		 * 
		 * if(method.equals("post")||method.equals("POST"))
		 * 	doPost()
		 * else
		 * 	doGet()
		 */
		
		out.println("<table class=table>");
		out.println("<tr>");
		out.println("<td width=35%>이름</td>");
		out.println("<td width=65%>");
		out.println("<input type=text name=name sizw=20 class=input-sm");
		out.println("</td>");
		out.println("</tr>");	

		
		out.println("<tr>");
		out.println("<td width=35%>국어</td>");
		out.println("<td width=65%>");
		out.println("<input type=number name=kor siz2=20 class=input-sm max=100 min=0 step=5 value=50>");
		out.println("</td>");
		out.println("</tr>");	
		
		out.println("<tr>");
		out.println("<td width=35%>영어</td>");
		out.println("<td width=65%>");
		out.println("<input type=number name=eng siz2=20 class=input-sm max=100 min=0 step=5 value=50");
		out.println("</td>");
		out.println("</tr>");	
		
		out.println("<tr>");
		out.println("<td width=35%>수학</td>");
		out.println("<td width=65%>");
		out.println("<input type=number name=math siz2=20 class=input-sm max=100 min=0 step=5 value=50");
		out.println("</td>");
		out.println("</tr>");	
		
		out.println("<tr>");
		out.println("<td colspan=2 class=text-center>");
		out.println("<input type=submit value=등록 class=\"btn btn-sm btn-warning\">");
		out.println("<input type=submit value=취소 class=\"btn btn-sm btn-warning\" onclick=\"javascript:history.back()\">");
		out.println("</td>");
		out.println("</tr>");		
		

//		out.println("<a href=StudentInsert? class=\"\btn btn-success\">등록</a>");
//		out.println("</td>");
//		out.println("</tr>");
		out.println("</table>");
		out.println("</form>");
		out.println("</div>");
		out.println("</div>");	
		out.println("</body>");	
		out.println("</html>");
		
	}

	
	
	// doGet에서 입력된 데이터를 받아서 데이터 추가
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		request.setCharacterEncoding("UTF-8");
		// 한글이 넘어와서 디코딩을 함 => 한글이 넘어와야 디코딩 setCharacterEncoding
		// 사용자가 보내준 값을 받는다
		// <input type=text name=name> name이 일치해야 한다 => name 속성을 설정하지 않으면 null 값 출력
		// name 속성 => servlet에서만 적용되는 것이 아니라 jsp/spring 포함
		// 
		String name = request.getParameter("name");
		String kor = request.getParameter("kor");	
		String eng = request.getParameter("eng");		
		String math = request.getParameter("math");
		String hakbun = request.getParameter("hakbun");
				// public String insert(StudentVO vo) => spring
		
		StudentVO vo = new StudentVO();
		vo.setName(name);
		vo.setKor(Integer.parseInt(kor));
		vo.setEng(Integer.parseInt(eng));
		vo.setMath(Integer.parseInt(math));
		vo.setHakbun(Integer.parseInt(hakbun));
		
		StudentDAO dao = StudentDAO.newInstance();
		dao.studentInsert(vo);
		
		response.sendRedirect("StudentList"); // list 화면으로 이동한다
		
		
		
	}

}
