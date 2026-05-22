<%@page import="com.jobportal.pojo.User"%>
<%@page import="com.jobportal.pojo.Job"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>

<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Apply Job</title>

<link rel="stylesheet" href="/css/style.css">
<link rel="stylesheet" href="/css/responsivecss.css">

</head>
<body>

<jsp:include page="common/header.jsp" />

<!-- HERO -->
<section class="hero">
    <h1>Apply for Job</h1>
    <p>Fill the details and submit your application</p>
</section>
<%
 Job job = (Job)request.getAttribute("job");
	String msg=(String)request.getAttribute("message");
    User user = (User)request.getAttribute("user");
%>
<!-- APPLY FORM -->
<section class="apply-container">

    <div class="job-header">
	<%if(job==null){
			
		 %>
		 	 <h3>Description</h3>
        <p><b><% out.print(msg); %></b>. There is a problem to apply job. Please try again later!</p>
		 <%} %>
        <h2 class="job-title"><%=job.getTitle() %></h2>

        <div class="job-meta">

            <div class="meta-item">
                <span class="label">Company:</span>
                <span class="value"><%=job.getCompany()%></span>
            </div>

            <div class="meta-item">
                <span class="label">Location:</span>
                <span class="value"><%=job.getLocation() %></span>
            </div>

       <%--      <div class="meta-item">
                <span class="label">Salary:</span>
                <span class="value"><%=job.getTitle()%></span>
            </div> --%>

        </div>
    </div>

    <div class="card job-card apply-card">

        <form action="" method="post" enctype="multipart/form-data" id="applyJobForm">

            <!-- Hidden Job ID -->
            <input type="hidden" name="jobId" value="<%=job.getId()%>">
			<input type="hidden" name="userId" value="<%=user.getId()%>">
            <!-- User Info -->
            <label>Name</label>
            <input type="text" name="name" class="input-box" value="<%=user.getName()%>">

            <label>Email</label>
            <input type="email" name="email" class="input-box" value="<%=user.getEmail()%>" readonly="readonly">

            <!-- Resume Upload -->
            <label>Upload Resume</label>
            <input type="file" name="resume" class="input-box" accept=".pdf" required>

            <!-- Cover Letter -->
            <label>Cover Letter</label>
            <textarea name="coverLetter" class="input-box" rows="5"
                placeholder="Write something about yourself..."></textarea>

            <!-- Submit -->
            <button type="submit" class="btn" style="width:100%; margin-top:10px;">
                Submit Application
            </button>

        </form>

    </div>

</section>

<jsp:include page="common/footer.jsp" />
<script type="text/javascript" src="/js/actionjs.js"></script>
</body>
</html>