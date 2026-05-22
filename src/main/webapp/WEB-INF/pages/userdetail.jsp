
<%@page import="com.jobportal.pojo.User"%>
<%@page import="java.util.Map"%>
<%@ page contentType="text/html;charset=UTF-8" %>
<!DOCTYPE html>
<html>
<head>

    <title>Profile</title>

    <meta name="viewport" content="width=device-width, initial-scale=1.0">

    <link rel="stylesheet" href="/css/style.css">
    <link rel="stylesheet" href="/css/admincss.css">
    <link rel="stylesheet" href="/css/responsivecss.css">

 </head>

<body>
<% boolean admin = request.isUserInRole("ADMIN");
	boolean isUser= request.isUserInRole("USER");
	User user = (User)request.getAttribute("user");
	String fileName = (String)request.getAttribute("userResume");
%>
<%if(admin==true){ %>
<div class="top-bar">
    <button class="menu-btn" onclick="toggleSidebar()">☰</button>

    <h2>Applicants Profile</h2>

    <button class="theme-btn" onclick="toggleTheme()">🌙</button>
</div>

<div class="admin-container">
	<!--	Side Bar  -->
  <%@include file="common/sidebar.jsp" %>
  <!-- Main Content -->
    <main class="main-content">
<%} %>
<%if(admin==false){%>
	<%@ include file="common/header.jsp" %>
<%} %>
<div class="profile-page">

    <div class="profile-card">

        <!-- Cover -->
        <div class="profile-cover">

            <div class="profile-avatar">
                <img src="/profilePic/<%=user.getProfilePicture()%>" id="profileImg" style="cursor:pointer;">
                <%if(admin==false){ %>
                	<div class="avatar-edit">
                	<label for="pictureFile">
                		<input type="hidden" id="userId" value="<%=user.getId()%>">
                		<input type="file" id="pictureFile" name="picture" accept="image/*" hidden="hidden" >
                		<svg width="20" height="20" viewBox="0 0 24 24" fill="none" stroke="currentColor" stroke-width="2" stroke-linecap="round" stroke-linejoin="round">

   						 	<path d="M12 20h9"></path>
    						<path d="M16.5 3.5a2.1 2.1 0 0 1 3 3L7 19l-4 1 1-4 12.5-12.5z"></path>
						</svg>
                	</label>
                	</div>
                <%} %>
            </div>
						<p id="uploadStatus"></p>
        </div>
        <!-- Content -->
        <div class="profile-content">

            <div class="profile-top">

                <div class="profile-info">
                    <h1><%=user.getName().toUpperCase()%></h1>
                    <!-- <p>Java Full Stack Developer</p> -->
                </div>
<% if(admin==true){ %> 
		<a href="/resume_link/<%= fileName%>" target="_blank">
                <button class="btn-upload">
                    View Resume
                </button></a>
<%} %>
            </div>

            <!-- Stats -->
            <%Map<String, Integer> userAppCount = (Map<String, Integer>)request.getAttribute("userAppCount"); %>
            <div class="profile-stats">

                <div class="stat-box">
                    <h2><%=userAppCount.get("applications") %></h2>
                    <p>Applications</p>
                </div>

              <!--   <div class="stat-box">
                    <h2>8</h2>
                    <p>Saved Jobs</p>
                </div> -->

                <div class="stat-box">
                    <h2><%=userAppCount.get("interviews") %></h2>
                    <p>Interviews</p>
                </div>

            </div>
<%if(admin==true){ %>
            <!-- Form -->
            <form class="profile-form">

                <div class="form-group">
                    <label>Full Name</label>
                    <input type="text"
                           value="<%=user.getName()%>" readonly="readonly">
                </div>

                <div class="form-group">
                    <label>Email</label>
                    <input type="email"
                           value="<%=user.getEmail()%>" readonly="readonly">
                </div>


                <div class="form-group">
                    <label>Location</label>
                    <input type="text"
                           value="Bhopal" readonly="readonly">
                </div>
                
              <!--   <div class="form-group">
                    <label>Phone</label>
                    <input type="text"
                           value="9876543210">
                </div> -->

            
                <div class="form-group full-width">
                    <label>Bio - <small>(Skill,Mobile no. & More)</small> </label>

                    <textarea readonly="readonly">
		<%=user.getBio()%>
                    </textarea >
                </div>

            </form>
<%} %>

<%if(admin==false) {%>
			<!-- Form -->
            <form class="profile-form" id="updateUserForm">
				<input type="hidden" name="id" value="<%=user.getId()%>">
                <div class="form-group" >
                    <label>Full Name</label>
                    <input type="text" name="name"
                           value="<%=user.getName()%>" >
                </div>

                <div class="form-group">
                    <label>Email</label>
                    <input type="email" name="email"
                           value="<%=user.getEmail()%>" >
                </div>


               <!--  <div class="form-group">
                    <label>Location</label>
                    <input type="text"
                           value="Bhopal" >
                </div> -->
                
              <!--   <div class="form-group">
                    <label>Phone</label>
                    <input type="text"
                           value="9876543210">
                </div> -->

               <!--  <div class="form-group full-width">
                    <label>Skills</label>
                    <input type="text"
                           value="Java, Spring Boot, MySQL">
                </div> -->

                <div class="form-group full-width">
                    <label>Bio - <small>(Skill,Mobile no. & More)</small> </label>

                    <textarea name="bio">
		<%=user.getBio()%>
                    </textarea >
                </div>

            <div class="profile-actions">

                <button class="btn-save" type="submit">
                    Save Changes
                </button>

            </div>
            </form>
<%} %>
        </div>

    </div>

</div>
<%if(admin==false){%>
	<%@ include file="common/footer.jsp" %>
	
<%}else{ %>
<p><a href="/admin/applicants" class="back-btn">← Back</a></p>
		</main>
</div>
<%} %>
<script type="text/javascript" src="/js/actionjs.js"></script>
<script type="text/javascript" src="/js/jscript.js"></script>
</body>
</html>

