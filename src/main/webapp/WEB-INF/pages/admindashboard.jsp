<%@page import="java.util.Map"%>
<%@page import="java.util.List"%>
<%@page import="com.jobportal.pojo.Job"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
	<meta charset="UTF-8">
	<title>Admin - JobPortal</title>
 	<link rel="stylesheet" href="/css/style.css">
    <link rel="stylesheet" href="/css/admincss.css">
    <link rel="stylesheet" href="/css/responsivecss.css">

</head>
<body>
<div class="top-bar">
    <button class="menu-btn" onclick="toggleSidebar()">☰</button>
    <h2>Admin Dashboard</h2>

    <button class="theme-btn" onclick="toggleTheme()">🌙</button>
</div>

<div class="admin-container">
	<!--	Side Bar  -->
    <%@include file="common/sidebar.jsp" %>
<%	Map<String,Integer> number = (Map<String,Integer>)request.getAttribute("counts");
	List<Job> jobs = (List<Job>)request.getAttribute("joblst");
	String msg=(String)request.getAttribute("msg");
%>
    <main class="main-content">

        <div class="stats">

            <div class="card stat-card">
                <h3>Jobs</h3>
                <p><%=number.get("jobs") %></p>
            </div>

            <div class="card stat-card">
                <h3>Users</h3>
                <p><%=number.get("users") %></p>
            </div>

            <div class="card stat-card">
                <h3>Applications</h3>
                <p><%=number.get("applications") %></p>
            </div>

        </div>

        <div class="table-section">
            <h2>Recent Jobs</h2>

            <table>
                <tr>
                    <th>Title</th>
                    <th>Company</th>
                    <th>Location</th>
                    <th>Action</th>
                </tr>
				<%for(Job get: jobs){ %>
                <tr>
                    <td><%=get.getTitle() %></td>
                    <td><%=get.getCompany() %></td>
                    <td><%=get.getLocation() %></td>
                    <td><a href="/admin/jobform/edit?id=<%=get.getId() %>" > <button>Edit</button></a></td>
                </tr>
                <%} %>
            </table>
        </div>

    </main>

</div>

<script src="/js/jscript.js"></script>
</body>
</html>