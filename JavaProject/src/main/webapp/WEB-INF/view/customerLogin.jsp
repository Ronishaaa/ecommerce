<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Insert title here</title>
<link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/Css/customerLogin.css" />
</head>
<body>
	<div class="login-box">
		<h1>Welcome To Camera</h1>
		<div style="color:grey; margin-bottom:20px;">Sign in your account</div>

		<form action="#">
			<div class="row">
				<div class="col">
					<label for="username">Email</label> 
					<input class="input" type="text" id="username" name="username" placeholder="Enter your email" required="required">
				</div>
			</div>
			
			<div class="row">
				<div class="col">
					<label for="password">Password</label> 
					<input class="input" type="password" id="password" name="password" placeholder="Enter your password" required>
				</div>
			</div>
			
			<button type="submit" class="login-button">Login</button>
			<div>
			Don't have an account ? 
			<a href="<%=request.getContextPath()%>/Register">Sign up</a>
			</div>
			
		</form>

		<%
		// Get the current session
		HttpSession session = request.getSession(false);

		// Check if a session exists
		if (session != null) {
			// Get the session attributes
			String sessionUsername = (String) session.getAttribute("username");
			String sessionRole = (String) session.getAttribute("role");


			// Print the session attributes
			out.print("Session attributes: <br>");
			out.print("Username: " + sessionUsername + "<br>");
			out.print("Email: " + sessionEmail + "<br>");
		} else {
			out.print("No session exists.");
		}

		// Get the cookies
		Cookie[] cookies = request.getCookies();

		// Check if any cookies exist
		if (cookies != null) {
			out.print("Cookies: <br>");
			for (Cookie cookie : cookies) {
				out.print(cookie.getName() + "=" + cookie.getValue() + "<br>");
			}
		} else {
			out.print("No cookies exist.");
		}
		%>

	</div>
	<div >
		<img class='image' alt="camera" src="images/camera2.jpg">
	</div>
</body>
</html>
