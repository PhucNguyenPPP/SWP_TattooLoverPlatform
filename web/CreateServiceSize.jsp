<%-- 
    Document   : CreateServiceSize
    Created on : Nov 1, 2023, 2:44:06 PM
    Author     : hieu09097248
--%>
<%@page import="DTO.UserDTO"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="en">
    <head>
        <meta charset="UTF-8" />
        <meta name="viewport" content="width=device-width, initial-scale=1.0" />
        <title>KÍCH THƯỚC DỊCH VỤ</title>
        <link rel="stylesheet" href="./Resource/css/createServiceSizeStyle.css" />
        <link rel="preconnect" href="https://fonts.googleapis.com" />
        <link rel="preconnect" href="https://fonts.gstatic.com" crossorigin />
        <link
            rel="stylesheet"
            type="text/css"
            href="//fonts.googleapis.com/css?family=Quicksand"
            />
        <link
            rel="stylesheet"
            href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/6.4.2/css/all.min.css"
            />
    </head>
    <body>
        <c:if test="${sessionScope.User == null || (sessionScope.User.roleId ne 'AD' && sessionScope.User.roleId ne 'SST')}">
            <c:redirect url="HomeController">

            </c:redirect>
        </c:if>

       
        <div class="container" id="blur">
            <div class="header">
                <a class="btn-logo" href="MainController?action=homepage"><img src="Resource/img/logo.jpg" ></a>
                <form action="MainController">
                    <input class="input-search" type="text" placeholder="Tìm kiếm" name="searchStudio" value="${param.searchStudio}" />
                    <button class="search-icon" type="submit" name="action" value="SearchStudio">
                        <i class="fa-solid fa-magnifying-glass"></i>
                    </button>
                </form>
                <%
                    UserDTO us = (UserDTO) session.getAttribute("User");
                    if (us != null && us.getRoleId().equals("AD")) {
                %>
                <a class="btn-notification" href="MainController?action=Notification"><i class="fa-solid fa-bell"></i> Thông báo</a>
                <a class="btn-signupstudio-af" href="MainController?action=ViewAccountSystem">Quản lý tài khoản</a>
                <div class="avatar-item">
                    <img onclick="drop()" class="avatar-img"
                         src="https://th.bing.com/th/id/OIP.7Z0skq54kBfcHRPjlsvATgHaG_?pid=ImgDet&rs=1">
                    <div id="subnav-content-id" class="subnav-content">
                        <a href="MainController?action=userprofile"><i class="fa-solid fa-user"></i>Tài khoản của tôi</a>
                        <a href="MainController?action=ViewService"><i class="fa-solid fa-gear"></i>Dịch vụ</a>
                        <a href="MainController?action=logoutservlet"><i class="fa-solid fa-right-from-bracket"></i>Đăng xuất</a>
                    </div>
                </div>
                <%
                } else if (us != null && us.getRoleId().equals("SST")) {
                %>
                <a class="btn-notification" href="MainController?action=Notification"><i class="fa-solid fa-bell"></i> Thông báo</a>
                <a class="btn-signupstudio-af" href="MainController?action=ViewAccountSystem">Quản lý tài khoản</a>
                <div class="avatar-item">
                    <img onclick="drop()" class="avatar-img"
                         src="https://th.bing.com/th/id/OIP.7Z0skq54kBfcHRPjlsvATgHaG_?pid=ImgDet&rs=1">
                    <div id="subnav-content-id" class="subnav-content">
                        <a href="MainController?action=userprofile"><i class="fa-solid fa-user"></i>Tài khoản của tôi</a>
                        <a href="MainController?action=ViewService"><i class="fa-solid fa-gear"></i>Dịch vụ</a>
                        <a href="MainController?action=logoutservlet"><i class="fa-solid fa-right-from-bracket"></i>Đăng xuất</a>
                    </div>
                </div>
                <%
                    }
                %>

            </div>
            <h1>
                TẠO CHI TIẾT DỊCH VỤ
            </h1>

            <form class="form-service" action="MainController" method="POST">   
                <p>Tên kích thước</p>
                <input type="text" name="name" required="">
                <p class="msg-error">${requestScope.ERROR}</p>
                <div class="btn">
                    <button class="btn-create" name="action" value="ServiceSizeCreate">TẠO</button>
                </div>  
            </form>

            <div class="footer">
                <div class="footer-service">
                    <h1>Dịch vụ đặt hình xăm</h1>
                    <p>
                        Lorem ipsum dolor sit amet, consectetur adipiscing elit.
                        Pellentesque neque lectus, aliquam a lobortis id, maximus at odio.
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
    </body>
</html>

