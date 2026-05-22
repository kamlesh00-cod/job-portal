<%@page import="com.jobportal.pojo.Job"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>

<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Job Details</title>

<link rel="stylesheet" href="/css/style.css">
<link rel="stylesheet" href="/css/responsivecss.css">

</head>
<body>

<%@ include file="common/header.jsp" %>
<%
 Job job = (Job)request.getAttribute("jobDetail");
	String msg=(String)request.getAttribute("jobDetailMSG");
%>
<section class="hero">
    <h1>Job Details</h1>
</section>

<section class="apply-container">

    <div class="card job-card">
		<%if(job==null||job.getTitle()==null){
			
		 %>
		 	 <h3>Description</h3>
        <p><b><% out.print(msg); %></b>. There is a problem to show full details of job. Please try again later!</p>
		 <%} %>
        <h2><%=job.getTitle() %></h2>

        <p><b>Company:</b><%=job.getCompany() %></p>
        <p><b>Location:</b> <%=job.getLocation() %></p>
        <p><b>JobType:</b> <%=job.getJobType() %></p>

        <hr>

        <h3>Description</h3>
        <p><%=job.getDescription() %></p>

    <%--     <h3>Requirements</h3>
        <p>${job.requirements}</p> --%>

        <a href="/user/apply?id=<%=job.getId() %>" class="btn">
            Apply Now
        </a>

    </div>

</section>

<%@ include file="common/footer.jsp" %>
<script src="/js/jscript.js"></script>
<script src="/js/actionjs.js"></script>
</body>
</html>