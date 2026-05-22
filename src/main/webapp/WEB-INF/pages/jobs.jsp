<%@page import="java.util.List"%>
<%@page import="com.jobportal.pojo.Job"%>
<%@page import="org.springframework.data.domain.Page"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>

<%-- <%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %> --%>

<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Jobs - JobPortal</title>

<link rel="stylesheet" href="/css/style.css">
<link rel="stylesheet" href="/css/responsivecss.css">

</head>
<body>

<%@ include file="common/header.jsp" %>

<!-- HERO SECTION -->
<section class="hero">
    <h1>Available Jobs</h1>
    <p>Find the best opportunities and apply instantly</p>
</section>

<!-- JOB LIST SECTION -->
<%
	Page<Job> jobs = (Page<Job>)request.getAttribute("jobs");
	List<Job> lst = jobs.getContent();
	int currentPage= jobs.getNumber();
	int size = jobs.getSize();
	int totalPages=jobs.getTotalPages();
%>
<section class="features">

   <%
   	 for(Job job : lst){
   %>

        <div class="card job-card">

            <h2>${job.title}<%=job.getTitle() %></h2>

            <p><b>Company:</b> ${job.company}<%=job.getCompany() %></p>

            <p><b>Location:</b> ${job.location}<%=job.getLocation() %></p>

            <p><b>JobType:</b> ${job.salary}<%=job.getJobType() %></p>

          <%--   <p><b>Description:</b>${job.description}<%=job.getDescription() %></p> --%>

              <!-- DETAILS BUTTON (NEW) -->
        <a href="/user/jobs/jobDetail?id=<%=job.getId() %>" class="btn">
            View Details
        </a> 
        <!-- APPLY BUTTON -->
        <a href="/user/apply?id=<%=job.getId() %>" class="btn">
            Apply Now
        </a>

        </div>
<%} %>
   

</section>

<!-- EMPTY STATE -->
<%
	if(jobs.isEmpty()){
%>
    <div class="card" style="text-align:center;">
        <h2>No Jobs Available</h2>
        <p>Please check later for new opportunities.</p>
    </div>
<%} 
%>

<!-- Page views -->
 <div class="pagination">
		<!-- Previous Button -->
<%
 if(!jobs.isFirst()){
%>
	<a href="/user/jobs?page=<%=currentPage-1%>&size=5" class="nav-link">Previous</a><%} 
	//Page Numbers
	for(int i=0;i<totalPages;i++){
		if(i == currentPage){
%>
		<b><%= i+1 %></b>
		<%}else{ %>
		<a href="/user/jobs?page=<%=i%>&size=5" class="nav-link"><%=i+1 %></a><%}
}%>
<!-- Next Button -->
<% if(!jobs.isLast()){%>
		<a href="/user/jobs?page=<%=currentPage+1%>&size=5" class="nav-link">Next</a>
		<%} %>
		</div>
<%@ include file="common/footer.jsp" %>

<script src="/js/jscript.js"></script>
<script src="/js/actionjs.js"></script>
</body>
</html>