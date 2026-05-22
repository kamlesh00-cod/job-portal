<%@page import="com.jobportal.enums.UserStatus"%>
<%@page import="com.jobportal.pojo.User"%>
<%@page import="java.util.List"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>

<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Manage Users - Admin</title>
   <meta name="viewport" content="width=device-width, initial-scale=1.0">
<link rel="stylesheet" href="/css/style.css">
<link rel="stylesheet" href="/css/admincss.css">
<link rel="stylesheet" href="/css/responsivecss.css">

</head>

<body>

	<!-- TOP BAR -->
	<div class="top-bar">

		<button class="menu-btn" onclick="toggleSidebar()">☰</button>

		<h2>Manage Users</h2>

		<button class="theme-btn" onclick="toggleTheme()">🌙</button>

	</div>

	<div class="admin-container">
		<!--	Side Bar  -->
		<%@include file="common/sidebar.jsp" %>

		<!-- MAIN CONTENT -->
		<main class="main-content">

			<!-- PAGE TITLE -->
			<div class="table-section">

				<div class="table-header">

					<h2>All Users</h2>

					<input type="text" placeholder="Search User ..." class="search-box" id="searchUser">
					<p id="msgP"></p>
				</div>

				<!-- USER TABLE -->
				<%
					List<User> allusers = (List<User>)request.getAttribute("users");
				%>
				<table >

					<thead>
						<tr>
							<th>ID</th>
							<th>Name</th>
							<th>Email</th>
							<th>Role</th>
							<th>Status</th>
							<th>Actions</th>
						</tr>
					</thead>

					<tbody id="userTableBody">

						<!-- USER List -->

												
						<%for(User user : allusers){ %>
						<tr>
							<td><%=user.getId() %></td>
							<td><%=user.getName() %></td>
							<td><%=user.getEmail() %></td>
							<td><%=user.getRole() %></td>

							<td><span class="blocked-status"><%=user.getStatus() %> </span></td>

							<td>
								<% if(user.getStatus()==UserStatus.ACTIVE){ %>
										<button class="status-btn" data-id="<%=user.getId()%>">Block</button>
								<%}else{ %>
										<button class="status-btn" data-id="<%=user.getId()%>">Activate</button>
										<%} %>
								<button class="deleteUser-btn" data-id="<%=user.getId()%>">Delete</button>

							</td>
						</tr>
						<!-- EMPTY STATE -->
						<%}
							if(allusers.isEmpty()){%>
								<tr>
									<td><span>Theres no users.</span></td>
								</tr>

							<% }
						 %>
					</tbody>

				</table>

			</div>

		</main>

	</div>

	<script src="/js/jscript.js"></script>
	<script src="/js/actionjs.js"></script>
	<script src="/js/search.js"></script>
</body>
</html>