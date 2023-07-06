<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8" import="com.sist.dao.*"%>
    <%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
    <%
    	String fno=request.getParameter("fno");
    	FoodDAO dao=new FoodDAO();
    	FoodVO vo=dao.foodDetailData(Integer.parseInt(fno));
    	request.setAttribute("vo", vo);
    %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.4.1/css/bootstrap.min.css">

<script>
$(function(){
	$('.images').css("sursor","pointer")
	$('.images').click(function(){
		let fno=$(this).attr("data-fno")
		$.ajax({
			type:'post',
			url:'detail.jsp',
			data:{"fno",fno},
			success:function(res) {
				$('#dialog').html(res);
				$('#dialog').dial({
					autoOpen:false,
					width:700,
					height:650,
					modal:true,
				}).dialog("open")
			}
		})
	})
})

</script>
<style type="text/css">
.container{
   margin-top: 50px;
}
.row1 {
  margin: 0px auto;
  width:960px;
}
</style>
</head>
<body>

<div class="container">
	<div class="row row1">

		<table class="table">
			<tr>
				<c:forTokens items="<%=vo.getPoster() %>" delims="^" var="img">
					<td><img src="<%=vo.getPoster() %>" style="width:100%"></td>
				</c:forTokens>
			</tr>
		</table>
	</div>
	<div style="height:10px"></div>
	
		<div class="row">
		<div class="col-sm-7">
			<table class="table">
				<tr>
					<td colspan="2">
						<h3><%=vo.getName() %>&nbsp;<span style="color:orange"><%=vo.getScore() %></span></h3>
					</td>
				</tr>	
				<tr>
					<th width=10% style="color:gray">주소</th>
					<td width=90%>
					<%=vo.getAddress() %><br>
					<sub>지번)<%=vo.getAddress() %></sub>
					</td>
				</tr>
				<tr>
					<th width=10% style="color:gray">전화</th>
					<td width=90%><%=vo.getPhone() %></td>
				</tr>
				<tr>
					<th width=10% style="color:gray">음식 종류</th>
					<td width=90%><%=vo.getType() %></td>
				</tr>	
				<tr>
					<th width=10% style="color:gray">가격대</th>
					<td width=90%>
						<%=vo.getPrice() %>
					</td>
				</tr>
				<tr>
					<th width=10% style="color:gray">주차</th>
					<td width=90%><%=vo.getParking() %></td>
				</tr>
				<tr>
					<th width=10% style="color:gray">영업시간</th>
					<td width=90%>${vo.time }</td>
				</tr>	
				<c:if test="${vo.menu!='no' }"/>
				<tr>
					<th width=10% style="color:gray">메뉴</th>
					<td width=90%>
						<ul>
						<c:forTokens items="${vo.menu }" delims="원" var="m">
							<li>${m }원</li>
						</c:forTokens>
						</ul>
					</td>
				</tr>
			</table>	
		</div>
	</div>
</div>

</body>
</html>






