<%@page import="com.jobportal.pojo.Job"%>
<%@page import="com.jobportal.enums.ApplicationStatus"%>
<%@page import="java.util.Map"%>
<%@page import="com.jobportal.pojo.UserApplication"%>
<%@page import="java.util.List"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Applicants - Admin</title>
   <meta name="viewport" content="width=device-width, initial-scale=1.0">
<link rel="stylesheet" href="/css/style.css">
<link rel="stylesheet" href="/css/admincss.css">
<link rel="stylesheet" href="/css/responsivecss.css">

</head>
<body>

<div class="top-bar">
    <button class="menu-btn" onclick="toggleSidebar()">☰</button>

    <h2>Applicants Management</h2>

    <button class="theme-btn" onclick="toggleTheme()">🌙</button>
</div>

<div class="admin-container">
	<!--	Side Bar  -->
  <%@include file="common/sidebar.jsp" %>

    <!-- Main Content -->
    <main class="main-content">

        <!-- Stats -->
        <%Map<String,Integer> number = (Map<String,Integer>)request.getAttribute("counts");%>
        <div class="stats">

            <div class="card stat-card">
                <h3>Total Applications</h3>
                <p><%=number.get("totalapplication") %></p>
            </div>

            <div class="card stat-card">
                <h3>Pending</h3>
                <p><%=number.get("pendings") %></p>
            </div>

            <div class="card stat-card">
                <h3>Selected</h3>
                <p><%=number.get("selected") %></p>
            </div>

        </div>

        <!-- Applicants Table -->
        <%
        	List<UserApplication> lst = (List<UserApplication>)request.getAttribute("applicants");
        	String msg =(String) request.getAttribute("msg");
        %>
        <div class="table-section">

            <h2>Applicants List</h2>

            <table>

                <thead>
                    <tr>
                        <th>ID</th>
                        <th>Applicant Name</th>
                        <th>Email</th>
                        <th>Job Title</th>
                        <th>Status</th>
                        <th>Resume</th>
                        <th>Action</th>
                    </tr>
                </thead>

                <tbody>

                    <!-- Dynamic Data -->
                    <%if(lst==null||lst.isEmpty()){ %>
                    <tr>
							<td><%out.print(msg); %></td>
						</tr>
					<%} %>
                    <%for(UserApplication app : lst){ 
                    		int id = 0;
                    	
                    	%>
                   

                        <tr>

                            <td><%=app.getId() %></td>
								<%if(app.getUser()==null){ %>
                            <td>User Name</td>
								<%} if(app.getUser()==null){ %>
                            <td>Email</td>
								<%} if(app.getJob()==null){%>
								<td>job Title</td>
  								<%} %>
  								<%if(app.getUser()!=null){ %>
                            <td><%=app.getUser().getName() %></td>
								<%} if(app.getUser()!=null){ %>
                            <td><%=app.getUser().getEmail()%></td>
								<%} if(app.getJob()!=null){%>
								<td><%=app.getJob().getTitle() %></td>
  								<%} %>
                           
                            <td><%=app.getStatus() %></td>

                            <td>
                                <a href="/resume_link/<%=app.getResumeLink() %>" target="_blank">
                                    View Resume
                                </a>
                            </td>

                            <td>
								<%if(app.getUser()!=null){ %>
                                <button onclick="window.location.href='/admin/userdetail?email=<%=app.getUser().getEmail()%>&rsm=<%=app.getResumeLink()%>'"> View </button>
                                <%}else{ %><p>Invalid!</p><%} %>
				<% if(app.getStatus()==ApplicationStatus.PENDING){ %>
                                <button class="approve-btn" data-id="<%=app.getId() %>" data-status="approved"> Approve </button>

                                <button class="reject-btn" data-id="<%=app.getId() %>" data-status="rejected"> Reject </button>
									<%} %>
                            </td>

                        </tr>
					<%}
						%>
						

                </tbody>

            </table>

        </div>

    </main>

</div>

<script src="/js/jscript.js"></script>
<script src="/js/actionjs.js"></script>
</body>
</html>