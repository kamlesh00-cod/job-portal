<%@page import="com.jobportal.pojo.Job"%>
<%@page import="java.util.List"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>


<!DOCTYPE html>
<html>

<head>

<meta charset="UTF-8">

<title>Manage Jobs - Admin</title>

<link rel="stylesheet"
	href="/css/style.css">

<link rel="stylesheet"
	href="/css/admincss.css">

<link rel="stylesheet"
	href="/css/responsivecss.css">

</head>

<body>

	<!-- TOP BAR -->
	<div class="top-bar">

		<button class="menu-btn" onclick="toggleSidebar()">☰</button>

		<h2>Manage Jobs</h2>

		<button class="theme-btn" onclick="toggleTheme()">🌙</button>

	</div>

	<div class="admin-container">
		<!--	Side Bar  -->
		<%@include file="common/sidebar.jsp" %>

		<!-- MAIN CONTENT -->
		
<main class="main-content">

    <div class="job-management-container">

        <!-- LEFT SIDE -->
        <% Job udtjob = (Job)request.getAttribute("job"); %>

        <div class="form-section card slide-up">

            <% if(udtjob != null){ %>

                <h2>Update Job</h2>

                <form action="" method="post" id="jobupdate">
						<!-- Hidden  -->
                    <input type="hidden" name="id" value="<%=udtjob.getId()%>">

                    <input type="text" name="title"
                        placeholder="Job Title"
                        value="<%=udtjob.getTitle()%>" required>

                    <input type="text" name="company"
                        placeholder="Company"
                        value="<%=udtjob.getCompany()%>" required>

                    <input type="text" name="location"
                        placeholder="Location"
                        value="<%=udtjob.getLocation()%>" required>

                    <select name="jobType" required>
                        <option value="<%=udtjob.getJobType()%>">
                            <%=udtjob.getJobType()%>
                        </option>

                        <option value="FULLTIME">Full Time</option>
                        <option value="PARTTIME">Part Time</option>
                        <option value="INTERNSHIP">Internship</option>
                        <option value="REMOTE">Remote</option>
                    </select>

                    <textarea name="description" rows="6"
                        placeholder="Job Description"><%=udtjob.getDescription()%></textarea>

                    <input type="submit" class="post-btn" value="Update Job">

                </form>

            <% } else { %>

                <h2>Post New Job</h2>

                <form action="" method="post" id="jobpostForm">

                    <input type="text" name="title"
                        placeholder="Job Title" required>

                    <input type="text" name="company"
                        placeholder="Company" required>

                    <input type="text" name="location"
                        placeholder="Location" required>

                    <select name="jobType" required>

                        <option value="">Select Job Type</option>
                        <option value="FULLTIME">Full Time</option>
                        <option value="PARTTIME">Part Time</option>
                        <option value="INTERNSHIP">Internship</option>
                        <option value="REMOTE">Remote</option>

                    </select>

                    <textarea name="description" rows="6"
                        placeholder="Job Description"></textarea>

                    <button type="submit" class="post-btn">
                        Post Job
                    </button>

                </form>

            <% } %>

        </div>

        <!-- RIGHT SIDE -->
        <div class="list-section card fade-in">

            <div class="table-header">

                <h2>All Jobs</h2>

                <input type="text"
                    id="jobSearch"
                    placeholder="Search Job...."
                    class="search-box">

            </div>

            <% List<Job> lst = (List<Job>)request.getAttribute("joblist"); %>

            <% if(lst == null || lst.isEmpty()){ %>

                <div class="empty-box">

                    <h3>No Jobs Available</h3>

                    <p>Add a new job to display here.</p>

                </div>

            <% } else { %>

                <div class="table-wrapper">

                    <table class="job-table" id="jobTable">

                        <thead>
                            <tr>
                                <th>Title</th>
                                <th>Company</th>
                                <th>Location</th>
                                <th>Type</th>
                                <th>Action</th>
                            </tr>
                        </thead>

                        <tbody>

                            <% for(Job job : lst){ %>

                                <tr>

                                    <td><%=job.getTitle()%></td>

                                    <td><%=job.getCompany()%></td>

                                    <td><%=job.getLocation()%></td>

                                    <td><%=job.getJobType()%></td>

                                    <td class="action-column">

                                        <a href="/admin/jobform/edit?id=<%=job.getId()%>" >
											<button class="edit-btn">Edit</button> 
											
                                                
                                          </a>

                                       
                                            <button class="delete-btn" data-id="<%=job.getId()%>">
                                                Delete
                                            </button>
                                       

                                    </td>

                                </tr>

                            <% } %>

                        </tbody>

                    </table>

                </div>

            <% } %>

        </div>

    </div>

</main>

	</div>

	<script src="/js/jscript.js" type="text/javascript"></script>
	<script type="text/javascript" src="/js/search.js"></script>
	<script type="text/javascript" src="/js/actionjs.js"></script>
</body>
</html>