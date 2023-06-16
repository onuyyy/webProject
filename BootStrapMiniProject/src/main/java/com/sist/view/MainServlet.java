package com.sist.view;

import java.io.*;
import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;


// 화면 분할 => 모든 이동 (MainServlet) => 레이아웃 (include)

@WebServlet("/MainServlet")
public class MainServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;


	protected void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		// 전송 타입 (response) => html
		response.setContentType("text/html;charset=UTF-8");
		
		// 화면 출력 => 브라우저로 보내기
		PrintWriter out = response.getWriter();
		
		// MainServlet이 값을 가지고 있기 때문에, 아래 코딩을 MainServlet에 적어야 한다 => 안 그러면 한글이 다 깨진다
		request.setCharacterEncoding("UTF-8");
		
		// 사용자가 보내준 값 받기
		String mode=request.getParameter("mode");
		String servlet="";
		if(mode==null)
			mode="1";
		
		
		switch(mode) {
		case "1":
			servlet="FoodCategoryServlet";
			break;
		case "2":
			servlet="FoodListServlet";
			break;
		case "3":
			servlet="FoodDetailServlet";
			break;
		case "4":
			servlet="FoodSearchServlet";
		case "5":
			servlet="SeoulServlet";
			break;
		}
		
		
		  out.write("<!DOCTYPE html> ");
	      out.write("<html> ");
	      out.write("<head> ");
	      out.write("<meta charset=\"UTF-8\"> ");
	      out.write("<title>Insert title here</title> ");
	      out.write(" ");
	      out.write("  <link rel=\"stylesheet\" href=\"https://maxcdn.bootstrapcdn.com/bootstrap/3.4.1/css/bootstrap.min.css\"> ");
	      out.write("  <script src=\"https://ajax.googleapis.com/ajax/libs/jquery/3.6.4/jquery.min.js\"></script> ");
	      out.write("  <script src=\"https://maxcdn.bootstrapcdn.com/bootstrap/3.4.1/js/bootstrap.min.js\"></script> ");
	      out.write(" ");
	      out.write("</head> ");
	      out.write("<body> ");
	      out.write(" ");
	      out.write("<nav class=\"navbar navbar-inverse navbar-fixed-top\"> ");
	      out.write("  <div class=\"container-fluid\"> ");
	      out.write("    <div class=\"navbar-header\"> ");
	      out.write("      <a class=\"navbar-brand\" href=\"MainServlet\">WebSiteName</a> ");
	      out.write("    </div> ");
	      out.write("    <ul class=\"nav navbar-nav\"> ");
	      out.write("      <li class=\"dropdown\"> ");
	      out.write("        <a class=\"dropdown-toggle\" data-toggle=\"dropdown\" href=\"#\">Page 1 ");
	      out.write("        <span class=\"caret\"></span></a> ");
	      out.write("        <ul class=\"dropdown-menu\"> ");
	      out.write("          <li><a href=\"MainServlet\">맛집 목록</a></li> ");
	      out.write("          <li><a href=\"MainServlet?mode=4\">맛집 검색</a></li> ");
	      out.write("        </ul> ");
	      out.write("      </li> ");
	      out.write("      <li class=\"dropdown\"> ");
	      out.write("        <a class=\"dropdown-toggle\" data-toggle=\"dropdown\" href=\"#\">Page 1 ");
	      out.write("        <span class=\"caret\"></span></a> ");
	      out.write("        <ul class=\"dropdown-menu\"> ");
	      out.write("          <li><a href=\"MainServlet?mode=5&type=1\">명소</a></li> ");
	      out.write("          <li><a href=\"MainServlet?mode=5&type=2\">자연&관광</a></li> ");
	      out.write("          <li><a href=\"MainServlet?mode=5&type=3\">쇼핑</a></li> ");			// tables[] 배열의 인덱스 값이 1,2,3 
	      out.write("        </ul> ");
	      out.write("      </li> ");
	      out.write("    </ul> ");
	      out.write("  </div> ");
	      out.write("</nav> ");
	      out.write(" ");
	      
	      
	      // include => <jsp:include page=""> jsp에선 이렇게 바뀐다
	      RequestDispatcher rd = request.getRequestDispatcher(servlet);	// servlet을 rd에 첨부해라
	      rd.include(request, response);
	      
	      out.write("</body> ");
	      out.write("</html>");
		

		
	}

}
