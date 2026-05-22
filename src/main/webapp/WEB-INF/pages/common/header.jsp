<%@page import="com.jobportal.pojo.User"%>
<%@page import="java.nio.file.attribute.UserPrincipal"%>
<%@page import="java.security.Principal"%>
<%	User huser = (User)request.getAttribute("user"); 
%>
<header class="header">

    <div class="logo">JobPortal</div>

    <!-- Hamburger -->
    <div class="hamburger" onclick="toggleMenu()">
        <span></span>
        <span></span>
        <span></span>
    </div>

    <!-- Navigation -->
    <nav id="navMenu">

        <a href="home" class="nav-link">Home</a>

        <a href="/user/jobs" class="nav-link">Jobs</a>

        <!-- If user NOT logged in -->
        <% if(request.getUserPrincipal()==null||huser==null){ %>

            <a href="login" class="nav-link btn-login">Login</a>

        <% } else { %>

            <!-- Profile Dropdown -->
            <div class="profile-menu">

                <img src="/profilePic/<%=huser.getProfilePicture()%>"
                     class="profile-pic"
                     onclick="toggleProfileMenu()">

                <div class="dropdown" id="profileDropdown">

                    <a href="/userdetail">My Profile</a>

                    <a href="/logout">Logout</a>

                </div>

            </div>

        <% } %>

    </nav>

</header>