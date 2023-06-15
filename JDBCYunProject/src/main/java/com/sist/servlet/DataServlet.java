package com.sist.servlet;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.sist.dao.EmpDAO;

/**
 * Servlet implementation class DataServlet
 */
@WebServlet("/DataServlet")
public class DataServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		PrintWriter out = response.getWriter();
		EmpDAO dao = new EmpDAO();
		List<Integer> mList = dao.empGetMgrData();
		List<Integer> dList = dao.empGetDeptnoData();
		List<Integer> sList = dao.empGetSalData();
		List<String> jList = dao.empGetJobData();
		
		  out.write("<!DOCTYPE html> ");
	      out.write("<html> ");
	      out.write("<head> ");
	      out.write("<meta charset=\"UTF-8\"> ");
	      out.write("<title>Insert title here</title> ");
	      out.write(" ");
	      out.write("  <link rel=\"stylesheet\" href=\"https://maxcdn.bootstrapcdn.com/bootstrap/3.4.1/css/bootstrap.min.css\"> ");
	      out.write("<style>");
	      out.write(".container{margin-top:50px}");
	      out.write(".row{margin:0px autolwidth:600px}");
	      out.write(".h1{text-align:center}");
	      out.write("</style>");
	      out.write(" ");
	      out.write("</head> ");
	      out.write("<body> ");
	      out.write("<div class=container>");
	      out.write("<h1>사원 수정</h1>");
	      out.write("<div class=row>");	
	      out.write("<form method=post action=EmpUpdateServlet>");	// 인서트가 가지고 있는 doPost를 호출해라 => doPost한테 데이터를 보낸다	
	      out.write("<table class=table>"); 
	      out.write("<tr>");
	      out.write("<th width=20% class=\"text-right success\">이름</td>");
	      out.write("<td width=80%><input type=text name=ename class=input-sm size=15>");
	      out.write("</tr>");
	      out.write("<tr>");
	      out.write("<th width=20% class=\"text-right success\">직위</td>");
	      out.write("<td width=80%>");
	      out.write("<select name=job class=input-sm>");
	      
	      for(String j:jList) {
	    	  out.write("<option>"+j+"</option>");
	      }
	      
	      out.write("</select>");  
	      out.write("</td>");  
	      out.write("</tr>");
	      out.write("<tr>");
	      out.write("<th width=20% class=\"text-right success\">사수번호</td>");
	      out.write("<td width=80%>");
	      out.write("<select name=job class=input-sm>");
	      
	      for(Integer m:mList) {
	    	  out.write("<option>"+m+"</option>");
	      }
	     
	      out.write("</select>");
	      out.write("</tr>");
	      out.write("<tr>");
	      out.write("<th width=20% class=\"text-right success\">급여</td>");
	      out.write("<td width=80%>"); 
	      out.write("<select name=job class=input-sm>");
	      
	      for(Integer s:sList) {
	    	  out.write("<option>"+s+"</option>");
	      }
	     
	      out.write("</select>"); 
	      out.write("</tr>");
	      out.write("<tr>");
	      out.write("<th width=20% class=\"text-right success\">성과급</td>");
	      out.write("<td width=80%><input type=number name=comm class=input-sm size=15 max=500 mix=100 step=50></td>");		// 100부터 500까지 가는데 50씩 올라간다 => step : 증가값
	      out.write("</tr>");
	      out.write("<tr>");
	      out.write("<th width=20% class=\"text-right success\">부서번호</td>");
	      out.write("<td width=80%><input type=text name=ename class=input-sm size=15>");  
	      out.write("<select name=job class=input-sm>");
	      
	      for(Integer d:dList) {
	    	  out.write("<option>"+d+"</option>");
	      }
	     
	      out.write("</select>"); 
	      out.write("</tr>");
	      out.write("<tr>");
	      out.write("<td colspan=2 class=text-center>");  
	      out.write("<button class=\"btn btn-sm btn-primary\">수정</button>&nbsp;");
	      out.write("<input type=button value=취소 class=\"btn btn-sm btn-primary\" onclick=\"javascript:history.back()\">");    
	      out.write("</td>");    
	      out.write("</tr>");    
	      out.write("</table>");   
	      out.write("</form>");   
	      out.write("</div>");
	      out.write("</div>");
	      out.write("</body>");
	      out.write("</html>");
		
		

	}
		
		

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
