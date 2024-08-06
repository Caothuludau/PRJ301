<%-- 
    Document   : Signup
    Created on : Mar 9, 2023, 6:20:14 PM
    Author     : CaoThuLuDau
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Sign Up</title>
        <!-- Font Icon -->
        <link rel="stylesheet" href="fonts/material-icon/css/material-design-iconic-font.min.css">
        <!-- Main css -->
        <link rel="stylesheet" href="css/style.css">
    </head>
    <body>
        <%
            String errCode = (String) request.getAttribute("errCode");
        %>
        <!-- Sign up form -->
        <section class="signup">
            <div class="container">
                <div class="signup-content">
                    <div class="signup-form">
                        <h2 class="form-title">Sign up</h2>
                        <%if (errCode == null) {%>
                        <form action="ClientControllerURL" method="POST" class="register-form" id="register-form">
                            <div class="form-group">
                                <label for="name"><i class="zmdi zmdi-account material-icons-name"></i></label>
                                <input type="text" name="username" id="username" placeholder="User Name" />

                            </div>
                            <div class="form-group">
                                <label for="pass"><i class="zmdi zmdi-lock"></i></label>
                                <input type="password" name="password" id="password" placeholder="Password" />
                            </div>
                            <div class="form-group">
                                <label for="re-pass"><i class="zmdi zmdi-lock-outline"></i></label>
                                <input type="password" name="confirm" id="re_password" placeholder="Confirm your password"/>
                            </div>
                            <div class="form-group">
                                <label for="name"><i class="zmdi zmdi-account material-icons-name"></i></label>
                                <input type="text" name="cusName" id="name" placeholder="Your Name" />

                            </div>
                            <div class="form-group">
                                <label for="email"><i class="zmdi zmdi-email"></i></label>
                                <input type="text" name="address" id="address" placeholder="Your Address" />

                            </div>
                            <div class="form-group">
                                <label for="email"><i class="zmdi zmdi-email"></i></label>
                                <input type="text" name="phone" id="phone" placeholder="Your Phone" />

                            </div>
                            <div class="form-group">
                                <input type="checkbox" name="agree-term" id="agree-term" class="agree-term" />
                                <label for="agree-term" class="label-agree-term"><span><span></span></span>I agree all statements in  <a href="#" class="term-service">Terms of service</a></label>
                            </div>
                            <div class="g-recaptcha" data-sitekey="6LdrA8cgAAAAAFLVSWCgxb-NUDXLrVw6G9M6LW0F"></div>
                            <div id="errorReCaptcha" style="color: red"></div>
                            <div class="form-group form-button">
                                <input type="hidden" name="go" value="reSignup"/>
                                <input value="Sign Up" type="submit" id="signup" class="form-submit"/>
                            </div>
                        </form>
                        <%}%>
                        <%if (errCode != null) {%>
                        <form action="ClientControllerURL" method="POST" class="register-form" id="register-form">
                            <div class="form-group">
                                <label for="name"><i class="zmdi zmdi-account material-icons-name"></i></label>
                                <input type="text" name="username" id="username" placeholder="User Name" value="<%=request.getParameter("username")%>"/>

                            </div>
                            <div class="form-group">
                                <label for="pass"><i class="zmdi zmdi-lock"></i></label>
                                <input type="password" name="password" id="password" placeholder="Password" value="<%=request.getParameter("password")%>"/>
                            </div>
                            <div class="form-group">
                                <label for="re-pass"><i class="zmdi zmdi-lock-outline"></i></label>
                                <input type="password" name="confirm" id="re_password" placeholder="Confirm your password"/>
                            </div>
                            <div class="form-group">
                                <label for="name"><i class="zmdi zmdi-account material-icons-name"></i></label>
                                <input type="text" name="cusName" id="name" placeholder="Your Name" value="<%=request.getParameter("cusName")%>"/>

                            </div>
                            <div class="form-group">
                                <label for="email"><i class="zmdi zmdi-email"></i></label>
                                <input type="text" name="address" id="address" placeholder="Your Address" value="<%=request.getParameter("address")%>"/>

                            </div>
                            <div class="form-group">
                                <label for="email"><i class="zmdi zmdi-email"></i></label>
                                <input type="text" name="phone" id="phone" placeholder="Your Phone" value="<%=request.getParameter("phone")%>"/>

                            </div>
                            <div class="form-group">
                                <input type="checkbox" name="agree-term" id="agree-term" class="agree-term" />
                                <label for="agree-term" class="label-agree-term"><span><span></span></span>I agree all statements in  <a href="#" class="term-service">Terms of service</a></label>
                            </div>
                            <div class="g-recaptcha" data-sitekey="6LdrA8cgAAAAAFLVSWCgxb-NUDXLrVw6G9M6LW0F"></div>
                            <div id="errorReCaptcha" style="color: red"></div>
                            <div class="form-group form-button">
                                <input type="hidden" name="go" value="reSignup"/>
                                <input value="Sign Up" type="submit" id="signup" class="form-submit"/>
                                <div class="error-message" style="color: red"><%=errCode%></div>
                            </div>
                        </form>
                        <%}%>
                    </div>
                    <div class="signup-image">
                        <figure><img src="images/signup-image.jpg" alt="sign up image"></figure>
                        <a href="ClientControllerURL?go=login" class="signup-image-link">I am already member</a>
                    </div>
                </div>
            </div>
        </section>
        <script src="https://www.google.com/recaptcha/api.js" async defer></script>
        <script>
            window.onload = function () {
                let isVail = false;
                const form = document.getElementById("register-form");
                const error = document.getElementById("errorReCaptcha");
                form.addEventListener("submit", function (event) {
                    event.preventDefault();
                    const response = grecaptcha.getResponse();
                    if (response) {
                        form.submit();
                    } else {
                        error.innerHTML("Check reCaptcha")
                    }
                });
            }
        </script>
    </body>
</html>