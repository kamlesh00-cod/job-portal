<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>ForgotPage</title>
<link rel="stylesheet" href="/css/style.css">
<link rel="stylesheet" href="/css/responsivecss.css">
</head>
<body>
<%@include file="common/header.jsp" %>
<div class="auth-container">

    <div class="auth-box">
        <h2>Reset Login</h2>

        <form action="" method="post" id="forgotPassForm">
            
            <input type="email" id="email" name="email" placeholder="Email" onblur="validateEmail()" required>
            
            <div id="emailMsg" class="msg"></div>
            
            <button type="button" id="sendOtpPass">Send OTP</button>
            
            <div id="otpSection" style="display:none;">
            	<input type="text" id="otpInput" placeholder="Enter OTP">
            	<button type="button" id="verifyOtp">Verify OTP</button>
             
           	 	<div id="otpMsg" class="msg"></div>
            </div>
            <div id="passwordSection" style="display:none;">
            <input type="password" id="regPassword" name="password" placeholder="Password" required oninput="checkStrength(this.value)">
            
            	<div class="strength-bar" >
            		<div id="strengthFill" class="strength-fill"></div>
            	</div>
            	<div id="errorBox" class="error-box"></div>
            
            	<input type="password" name="confirmPassword" placeholder="Confirm Password" required>

            	<button type="submit"  >Change Password</button>
            </div>
        </form>
																
      
    </div>

</div>

<%@ include file="common/footer.jsp" %>
<script type="text/javascript"  src="/js/jscript.js"></script>
<script type="text/javascript"  src="/js/actionjs.js"></script>

</body>
</html>