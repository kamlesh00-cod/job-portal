<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Login- JobPortal</title>
<meta name="viewport" content="width=device-width, initial-scale=1.0">
<link rel="stylesheet" href="/css/style.css">
<link rel="stylesheet" href="/css/responsivecss.css">
</head>
<body>
<%@include file="common/header.jsp" %>
<div class="auth-container ">

    <div class="auth-box">
        <h2>Login</h2>

        <form action="${pageContext.request.contextPath}/login" method="post">
            <input type="text" name="username" placeholder="Email" required>
           
			<div class="password-box">
    			<input type="password" id="loginPassword" name="password" placeholder="Password" required>

    			<span class="toggle-eye" onclick="togglePassword('loginPassword', this)"><svg  width="22" height="22" viewBox="0 0 24 24" fill="none" stroke="currentColor" stroke-width="2" stroke-linecap="round" stroke-linejoin="round">

	           <path d="M1 12s4-8 11-8 11 8 11 8-4 8-11 8-11-8-11-8z"/>
	           <circle cx="12" cy="12" r="3"/>
			 </svg></span>
			</div>
			<p  style="text-align: right;"><a href="/forgot">Forgot Password</a></p>	
            <button type="submit">Login</button>
        </form>
<br>
         <p><span>Don't have an account?</span> <a href="register">Register</a></p>
    </div>

</div>

<%@ include file="common/footer.jsp" %>
<script type="text/javascript" src="/js/jscript.js"></script>
</body>
</html>