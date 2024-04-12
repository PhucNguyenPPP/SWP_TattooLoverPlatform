<%-- 
    Document   : CreateNewPassword
    Created on : Nov 14, 2023, 10:20:36 PM
    Author     : ASUS
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <meta name="viewport" content="width=device-width, initial-scale=1.0" />
        <title>TẠO MẬT KHẨU MỚI</title>
        <link rel="stylesheet" href="Resource/css/createNewPasswordStyle.css">
        <link rel="preconnect" href="https://fonts.googleapis.com">
        <link rel="preconnect" href="https://fonts.gstatic.com" crossorigin>
        <link rel="stylesheet" type="text/css" href="//fonts.googleapis.com/css?family=Quicksand" />
        <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/6.4.2/css/all.min.css">
    </head>
    <body>
        <div class="container">
            <div class="header">
                <a class="btn-logo" href="MainController?action=homepage"><img src="Resource/img/logo.jpg" ></a>
                <form action="MainController">
                    <input class="input-search" type="text" placeholder="Tìm kiếm" name="searchStudio" value="${param.searchStudio}" />
                    <button class="search-icon" type="submit" name="action" value="SearchStudio">
                        <i class="fa-solid fa-magnifying-glass"></i>
                    </button>
                </form>
                <a class="btn-notification" href="MainController?action=login">Đăng nhập |</a>
                <a class="btn-signupstudio" href="MainController?action=signup">Đăng ký   | </a>
                <a class="btn-Signup" href="MainController?action=studiosignup">Đăng ký tiệm xăm</a>
            </div>
            <h2>TẠO MẬT KHẨU MỚI</h2>
            <div class="container2">
                <form action="MainController" method="POST">
                    <div class="EMAIL">
                        <label for="email" >Mật khẩu mới: </label>
                        <input type="password" name="password" required="">
                    </div>
                    <div class="OTP">
                        <label for="OTP">Xác nhận mật khẩu: </label>
                        <input type="password" name="confirmPassword" required="">
                    </div>
                    <p class="msg-error">${requestScope.PASS_MSG}</p>
                    <a class="CONFIRM"><button type="submit" name="action" value="getNewPassword">XÁC NHẬN</button></a>
                    
                </form>
                    
                <form action="MainController" method="POST">
                    <div class="CANCEL"><button type="submit" name="action" value="homepage">QUAY VỀ</button></div>
                </form>
            </div>
            <div class="footer">

                <div class="footer-service">
                    <h1>Dịch vụ đặt hình xăm</h1>
                    <p>
                        Nền tảng đặt lịch xăm hình của chúng tôi 
                        sẽ giúp bạn kết nối với các nghệ sĩ tài năng, để bạn có thể thỏa mãn đam mê 
                        nghệ thuật trên cơ thể một cách dễ dàng và vui vẻ. Hãy đặt lịch cho tác phẩm 
                        của bạn ngay bây giờ!
                    </p>
                </div>

                <div class="footer-contact">
                    <h1>Liên hệ</h1>
                    <i class="fa-brands fa-facebook"></i>
                    <i class="fa-brands fa-instagram"></i>
                    <i class="fa-brands fa-twitter"></i>
                    <i class="fa-brands fa-facebook-messenger"></i>
                </div>

                <div class="footer-address">
                    <h1>Địa chỉ</h1>
                    <p>Địa chỉ: Chung Cư Hado Centrosa 200 đường 3/2, Q10, Hồ Chí Minh</p>
                </div>


            </div>
        </div>



        <script>

            function drop() {
                var divs = document.getElementById("subnav-content-id");
                divs.classList.toggle("show");
            }

        </script>
        <script src="js/app.js"></script>
    </body>
</html>
