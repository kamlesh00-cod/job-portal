<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>HomePage</title>
<link rel="stylesheet" href="/css/style.css">
<link rel="stylesheet" href="/css/responsivecss.css">
</head>
<body>

<%@include file="common/header.jsp" %>
<section class="hero">
    <h1>Find Your Dream Job</h1>
    <p>Explore thousands of opportunities in one place.</p>
    <a href="/user/jobs" class="btn">Browse Jobs</a>
</section>

<section class="features">
    <div class="card">
        <h2>Search Jobs</h2>
        <p>Find jobs by category, location, or keyword.</p>
    </div>
    <div class="card">
        <h2>Apply Easily</h2>
        <p>Quick and simple job application process.</p>
    </div>
    <div class="card">
        <h2>Get Hired</h2>
        <p>Connect with top companies and recruiters.</p>
    </div>
</section>

<%@ include file="common/footer.jsp" %>
<script type="text/javascript"  src="/js/jscript.js"></script>

</body>
</html>