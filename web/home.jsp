<%-- 
   Document   : home
   Created on : Oct 12, 2023, 6:00:54 PM
   Author     : ASUS
--%>

<%@page import="DTO.UserDTO"%>
<%@page import="DTO.ServiceDetailDTO"%>
<%@page import="DTO.ServiceDTO"%>
<%@page import="java.util.ArrayList"%>
<%@page import="DTO.ImgDTO"%>
<%@page import="java.util.List"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <meta name="viewport" content="width=device-width, initial-scale=1.0">
        <title>HOME</title>
        <link rel="stylesheet" href="Resource/css/homeTLStyle.css">
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
                <%
                    UserDTO us = (UserDTO) session.getAttribute("User");
                    if (us != null && us.getRoleId().equals("TL")) {
                %>  
                <a class="btn-notification" href="MainController?action=Notification"><i class="fa-solid fa-bell"></i> Thông báo</a>
                <a class="btn-signupstudio-af" href="MainController?action=studiosignup">Đăng ký tiệm xăm</a>
                <div class="avatar-item">
                    <img onclick="drop()" class="avatar-img"
                         src="https://th.bing.com/th/id/OIP.7Z0skq54kBfcHRPjlsvATgHaG_?pid=ImgDet&rs=1">
                    <div id="subnav-content-id" class="subnav-content">
                        <a href="MainController?action=userprofile"><i class="fa-solid fa-user"></i>Tài khoản của tôi</a>
                        <a href="MainController?action=BookList"><i class="fa-solid fa-calendar-days"></i>Lịch hẹn</a>
                        <a href="MainController?action=logoutservlet"><i class="fa-solid fa-right-from-bracket"></i>Đăng xuất</a>
                    </div>
                </div>
                <%
                } else if (us == null) {
                %>
                <a class="btn-login" href="MainController?action=login">Đăng nhập | </a>
                <a class="btn-Signup" href="MainController?action=signup">Đăng ký | </a>
                <a class="btn-signupstudio" href="MainController?action=studiosignup">Đăng ký tiệm xăm</a>
                <%
                } else if (us != null && us.getRoleId().equals("SM")) {
                %>
                <a class="btn-notification" href="MainController?action=Notification"><i class="fa-solid fa-bell"></i> Thông báo</a>
                <a class="btn-signupstudio-af" href="MainController?action=ViewAccountStu">Quản lý nhân viên</a>
                <div class="avatar-item">
                    <img onclick="drop()" class="avatar-img"
                         src="https://th.bing.com/th/id/OIP.7Z0skq54kBfcHRPjlsvATgHaG_?pid=ImgDet&rs=1">
                    <div id="subnav-content-id" class="subnav-content">
<<<<<<< HEAD
                        <a href="MainController?action=StudioManagerInfo"><i class="fa-solid fa-gear"></i>Thông tin tiệm xăm</a>
=======
                        <a href="MainController?action=StudioManagerInfo"><i class="fa-solid fa-gear"></i>Thông tin studio</a>
>>>>>>> 46860fd9c8195799510ba7b99c8b013849670722
                        <a href="MainController?action=userprofile"><i class="fa-solid fa-user"></i>Tài khoản của tôi</a>
                        <a href="MainController?action=BookListStu"><i class="fa-solid fa-calendar-days"></i>Lịch hẹn</a>
                        <a href="MainController?action=logoutservlet"><i class="fa-solid fa-right-from-bracket"></i>Đăng xuất</a>
                    </div>
                </div>
                <%
                } else if (us != null && us.getRoleId().equals("SS")) {
                %>
<<<<<<< HEAD
                <a class="btn-notification1" href="MainController?action=Notification"><i class="fa-solid fa-bell"></i> Thông báo</a>
                <a class="btn-signupstudio-af"></a>
                <div class="avatar-item1">
=======
                <a class="btn-notification" href="MainController?action=Notification"><i class="fa-solid fa-bell"></i> Thông báo</a>
                <a class="btn-signupstudio-af"></a>
                <div class="avatar-item">
>>>>>>> 46860fd9c8195799510ba7b99c8b013849670722
                    <img onclick="drop()" class="avatar-img"
                         src="https://th.bing.com/th/id/OIP.7Z0skq54kBfcHRPjlsvATgHaG_?pid=ImgDet&rs=1">
                    <div id="subnav-content-id" class="subnav-content">
                        <a href="MainController?action=StudioManagerInfo"><i class="fa-solid fa-gear"></i>Thông tin studio</a>
                        <a href="MainController?action=userprofile"><i class="fa-solid fa-user"></i>Tài khoản của tôi</a>
                        <a href="MainController?action=BookListStu"><i class="fa-solid fa-calendar-days"></i>Lịch hẹn</a>
                        <a href="MainController?action=logoutservlet"><i class="fa-solid fa-right-from-bracket"></i>Đăng xuất</a>
                    </div>
                </div>
                <%
                } else if (us != null && us.getRoleId().equals("SA")) {
                %>
<<<<<<< HEAD
                <a class="btn-notification1" href="MainController?action=Notification"><i class="fa-solid fa-bell"></i> Thông báo</a>
                <a class="btn-signupstudio-af"></a>
                <div class="avatar-item1">
=======
                <a class="btn-notification" href="MainController?action=Notification"><i class="fa-solid fa-bell"></i> Thông báo</a>
                <a class="btn-signupstudio-af"></a>
                <div class="avatar-item">
>>>>>>> 46860fd9c8195799510ba7b99c8b013849670722
                    <img onclick="drop()" class="avatar-img"
                         src="https://th.bing.com/th/id/OIP.7Z0skq54kBfcHRPjlsvATgHaG_?pid=ImgDet&rs=1">
                    <div id="subnav-content-id" class="subnav-content">
                        <a href="MainController?action=userprofile"><i class="fa-solid fa-user"></i>Tài khoản của tôi</a>
                        <a href="MainController?action=BookListArtist"><i class="fa-solid fa-calendar-days"></i>Lịch hẹn</a>
                        <a href="MainController?action=logoutservlet"><i class="fa-solid fa-right-from-bracket"></i>Đăng xuất</a>
                    </div>
                </div>
                <%
                } else if (us != null && us.getRoleId().equals("AD")) {
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
            <%
                List<ImgDTO> listImg = new ArrayList<>();
                listImg = (List< ImgDTO>) request.getAttribute("IMG_SERVICE_LIST");
                List<ServiceDTO> listSer = new ArrayList<>();
                List<ServiceDetailDTO> listSerDe = new ArrayList<>();
                listSer = (List<ServiceDTO>) request.getAttribute("SER_LIST");
                listSerDe = (List<ServiceDetailDTO>) request.getAttribute("SER_DE_LIST");
            %>

            <div class="slider">
                <div class="list">
                    <a href="#">
                        <div class="item">
                            <img src="Resource/img/Slider1.png" alt="">
                        </div>
                    </a>
                    <div class="item">
                        <img src="Resource/img/Slider2.png" alt="">
                    </div>

                    <div class="item">
                        <img src="Resource/img/Slider3.png" alt="">
                    </div>

                    <div class="item">
                        <img src="Resource/img/Slider4.png" alt="">
                    </div>

                </div>

                <div class="buttons">
                    <button id="prev"><</button>
                    <button id="next">></button>
                </div>

                <ul class="dots">
                    <li class="active"></li>
                    <li></li>
                    <li></li>
                    <li></li>
                </ul>
            </div>
            <div class="service">
                <h1>DỊCH VỤ</h1>

                <div class="service-list">
                    <%
                        for (ImgDTO img : listImg) {
                            if (img.getServiceID() != null) {
                                for (ServiceDTO ser : listSer) {
                                    if (img.getServiceID().equals(ser.getServiceID())) {

                    %>
                    <div class="service-item">          
                        <a href="MainController?action=ViewStudioService&serviceID=<%=img.getServiceID()%>">
                            <img href="MainController?action=ViewStudioService&serviceID=<%=img.getServiceID()%>" src="data:image/jpeg;base64,<%=img.getImgLink()%>">
                        </a>
                        <a href="MainController?action=ViewStudioService&serviceID=<%=img.getServiceID()%>"><%=ser.getName()%></a>
                    </div>
                    <%
                            }
                        }
                    } else if (img.getServiceDetailID() != null) {
                        for (ServiceDetailDTO ser : listSerDe) {
                            if (img.getServiceDetailID().equals(ser.getServiceDetailID())) {

                    %>
                    <div class="service-item">
                        <a href="MainController?action=ViewStudioService&serviceID=<%=img.getServiceDetailID()%>">
                            <img src="data:image/jpeg;base64,<%=img.getImgLink()%>"></a>
                        <a href="MainController?action=ViewStudioService&serviceID=<%=img.getServiceDetailID()%>"><%=ser.getName()%></a>
                    </div>
                    <%
                                    }
                                }
                            }
                        }
                    %>
                </div>
            </div>
            <div class="footer">

                <div class="footer-service">
                    <h1>Dịch vụ đặt hình xăm</h1>
                    <p>Nền tảng đặt lịch xăm hình của chúng tôi 
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
        <script src="Resource/js/app.js"></script>
    </body>
</html>
