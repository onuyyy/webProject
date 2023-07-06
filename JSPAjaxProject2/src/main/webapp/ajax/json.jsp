

<%@page import="com.sist.manager.MovieManager"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%
	String no=request.getParameter("no");
	MovieManager m=new MovieManager();
	String json=m.movieListData(Integer.parseInt(no));

%>
<%=json%>