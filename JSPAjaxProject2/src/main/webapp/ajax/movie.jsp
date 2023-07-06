<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8" import="java.util.*,com.sist.dao.*"%>
    
    <%
    	FoodDAO dao=new FoodDAO();
    	List<FoodVO> list=dao.foodListData();
    		
    %>
    
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.4.1/css/bootstrap.min.css">
<style type="text/css">
.container{
   margin-top: 50px;
}
.row {
  margin: 0px auto;
  width:960px;
}
</style>
</head>
<body>

<div class="container">
	<div class="row">
		<%
			for(FoodVO vo:list) {
		%>
					<div class="col-md-3">
					    <div class="thumbnail">
					     <%--  <a href="detail.jsp?fno=<%=vo.getFno() %>"> --%>
					        <img src="<%=vo.getPoster() %>"  style="width:100%" class="images" data-fno="<%=vo.getFno()%>">
					       	 <div class="caption">
					          <p><%=vo.getName() %></p>
					        </div>
					<!--       </a> -->
					    </div>
				    </div>	
		<%		
			}
		%>
	</div>
</div>


</body>
</html>