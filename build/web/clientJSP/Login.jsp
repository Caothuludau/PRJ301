<%-- 
    Document   : Login
    Created on : Mar 5, 2023, 3:55:08 PM
    Author     : CaoThuLuDau
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Login</title>
        <!-- Font Icon -->
        <link rel="stylesheet" href="fonts/material-icon/css/material-design-iconic-font.min.css">
        <!-- Main css -->
        <link rel="stylesheet" href="css/style.css">
    </head>
    <body>
        <%
        Boolean incorrect = false; 
        if (request.getAttribute("reLogin") != null) {
            incorrect = true;
        }
        %>
        <!-- Login Form -->
        <section class="sign-in">
            <div class="container">
                <div class="signin-content">
                    <div class="signin-image">
                        <figure><img src="images/signin-image.jpg" alt="sing up image"></figure>
                        <a href="ClientControllerURL?go=signup" class="signup-image-link">Create an account</a>
                    </div>
                    <div class="signin-form">
                        <h2 class="form-title">Sign in</h2>
                        <form class="register-form" id="login-form" action="ClientControllerURL" method="POST">
                            <input type="hidden" name="go" value="reLogin"/>
                            <div class="form-group">
                                <label for="username"><i class="zmdi zmdi-account material-icons-name"></i></label>
                                <input type="text" name="username" id="your_name" placeholder="User ID"/>
                            </div>
                            <div class="form-group">
                                <label for="password"><i class="zmdi zmdi-lock"></i></label>
                                <input type="password" name="password" id="password" placeholder="Password"/>
                            </div>
                            <div class="form-group">
                                <input type="checkbox" name="remember-me" id="remember-me" class="agree-term" />
                                <label for="remember-me" class="label-agree-term"><span><span></span></span>Remember me</label>
                            </div>
                            <%if (incorrect) {%>
                                <div id="error">
                                    <div class="alert alert-warning" style="color:red" role="alert">
                                        Username or password is incorrect!
                                    </div>
                                </div>
                            <%}%>
                            <div class="form-group form-button">
                                <input type="submit" name="action" id="signin" class="form-submit" value="Login"/>
                            </div>
                        </form>
                        <div class="social-login">
                            <span class="social-label">Or login with</span>
                            <ul class="socials">
                                <li><a href="#"><i class="display-flex-center zmdi zmdi-facebook"></i></a></li>
                                <li><a href="#"><i class="display-flex-center zmdi zmdi-twitter"></i></a></li>
                                <li><a href="#"><i class="display-flex-center zmdi zmdi-google"></i></a></li>
                            </ul>
                        </div>
                    </div>
                </div>
            </div>
        </section>
        <!-- JS -->
        <script src="vendor/jquery/jquery.min.js"></script>
        <script src="js/main.js"></script>
    </body>
</html>
