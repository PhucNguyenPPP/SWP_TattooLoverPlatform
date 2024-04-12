<%-- 
    Document   : studioList
    Created on : Oct 17, 2023, 8:36:01 AM
    Author     : ASUS
--%>

<%@page import="DTO.UserDTO"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@page import="java.text.DecimalFormat"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <meta name="viewport" content="width=device-width, initial-scale=1.0">
        <title>DANH SÁCH TIỆM XĂM</title>
        <link rel="stylesheet" href="Resource/css/studioListStyle.css">
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
                    <img onclick="drop()" class="avatar-img"
                         src="https://th.bing.com/th/id/OIP.7Z0skq54kBfcHRPjlsvATgHaG_?pid=ImgDet&rs=1">
                    <div id="subnav-content-id" class="subnav-content">
                        <a href="MainController?action=StudioManagerInfo"><i class="fa-solid fa-gear"></i>Thông tin tiệm xăm</a>
=======
                <a class="btn-notification" href="MainController?action=Notification"><i class="fa-solid fa-bell"></i> Thông báo</a>
                <a class="btn-signupstudio-af"></a>
                <div class="avatar-item">
                    <img onclick="drop()" class="avatar-img"
                         src="https://th.bing.com/th/id/OIP.7Z0skq54kBfcHRPjlsvATgHaG_?pid=ImgDet&rs=1">
                    <div id="subnav-content-id" class="subnav-content">
                        <a href="MainController?action=StudioManagerInfo"><i class="fa-solid fa-gear"></i>Thông tin studio</a>
>>>>>>> 46860fd9c8195799510ba7b99c8b013849670722
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

            <div class="content-title">
                <h1>DANH SÁCH TIỆM XĂM</h1>
                <form action="MainController">
                    <!--                    <select name="action" onchange="this.form.submit()">
                                            <option>Sắp xếp theo:</option>
                                            <option value="nameUp">Sắp xếp theo: Tên tiệm xăm (A->Z)</option>
                                            <option value="nameDown">Sắp xếp theo: Tên tiệm xăm (Z->A)</option>
                                            <option value="ratingUp">Sắp xếp theo: Đáng giá tăng dần</option>
                                            <option value="ratingDown">Sắp xếp theo: Đáng giá giảm dần</option>
                                        </select>-->
                    <%
                        request.setAttribute("LIST_STUDIO", request.getAttribute("LIST_STUDIO"));
                    %>
                </form>
            </div>
            <div class="content">
                <div class="filter">
                    <form>
                        <h2>BỘ LỌC TÌM KIẾM</h2>

                        <h3>Theo dịch vụ</h3>


                        <c:forEach var="service" items="${requestScope.LIST_SERVICE}">
                            <div class="filter-option">
                                <c:choose>
                                    <c:when test="${service.serviceID == requestScope.SERVICE_ID}">
                                        <input type="radio" name="service" value="${service.serviceID}" checked="">
                                        <label>${service.name}</label>
                                    </c:when>
                                    <c:otherwise>
                                        <input type="radio" name="service" value="${service.serviceID}">
                                        <label>${service.name}</label>
                                    </c:otherwise>
                                </c:choose>
                            </div>
                        </c:forEach>
                        <h3>Theo chi tiết dịch vụ</h3>
                        <c:forEach var="service_detail" items="${requestScope.LIST_SERVICE_DETAIL}">
                            <div class="filter-option">
                                <c:choose>
                                    <c:when test="${service_detail.serviceDetailID == requestScope.SERVICE_DETAIL_ID}">
                                        <input type="radio" name="serviceDetail" value="${service_detail.serviceDetailID}" checked="">
                                        <label>${service_detail.name}</label>
                                    </c:when>
                                    <c:otherwise>
                                        <input type="radio" name="serviceDetail" value="${service_detail.serviceDetailID}">
                                        <label>${service_detail.name}</label>
                                    </c:otherwise>
                                </c:choose>
                            </div>
                        </c:forEach>

                        <h3>Theo khu vực</h3>

                        <c:forEach var="city" items="${requestScope.LIST_CITY}">
                            <div class="filter-option">
                                <c:choose>
                                    <c:when test="${city.cityID == requestScope.CITY_ID}">
                                        <input type="radio" name="city" value="${city.cityID}" checked="">
                                        <label>${city.cityName}</label>
                                    </c:when>
                                    <c:otherwise>
                                        <input type="radio" name="city" value="${city.cityID}">
                                        <label>${city.cityName}</label>
                                    </c:otherwise>
                                </c:choose>
                            </div>

                        </c:forEach>

                        <h3>Theo đánh giá</h3>

                        <div class="filter-option">
                            <c:choose>
                                <c:when test="${requestScope.FEEDBACK == 5}">
                                    <input type="radio" name="feedback" value="5" checked="">
                                    <label>5 sao</label>
                                </c:when>
                                <c:otherwise>
                                    <input type="radio" name="feedback" value="5">
                                    <label>5 sao</label>
                                </c:otherwise>
                            </c:choose>

                        </div>

                        <div class="filter-option">
                            <c:choose>
                                <c:when test="${requestScope.FEEDBACK == 4}">
                                    <input type="radio" name="feedback" value="4" checked="">
                                    <label>4 sao trở lên</label>
                                </c:when>
                                <c:otherwise>
                                    <input type="radio" name="feedback" value="4">
                                    <label>4 sao trở lên</label>
                                </c:otherwise>
                            </c:choose>
                        </div>

                        <div class="filter-option">
                            <c:choose>
                                <c:when test="${requestScope.FEEDBACK == 3}">
                                    <input type="radio" name="feedback" value="3" checked="">
                                    <label>3 sao trở lên</label>
                                </c:when>
                                <c:otherwise>
                                    <input type="radio" name="feedback" value="3">
                                    <label>3 sao trở lên</label>
                                </c:otherwise>
                            </c:choose>
                        </div>

                        <div class="filter-option">
                            <c:choose>
                                <c:when test="${requestScope.FEEDBACK == 2}">
                                    <input type="radio" name="feedback" value="2" checked="">
                                    <label>2 sao trở lên</label>
                                </c:when>
                                <c:otherwise>
                                    <input type="radio" name="feedback" value="2">
                                    <label>2 sao trở lên</label>
                                </c:otherwise>
                            </c:choose>

                        </div>

                        <div class="filter-option">
                            <c:choose>
                                <c:when test="${requestScope.FEEDBACK == 1}">
                                    <input type="radio" name="feedback" value="1" checked="">
                                    <label>1 sao trở lên</label>
                                </c:when>
                                <c:otherwise>
                                    <input type="radio" name="feedback" value="1">
                                    <label>1 sao trở lên</label>
                                </c:otherwise>
                            </c:choose>

                        </div>
                        <input type="hidden" name="searchStudio" value="${param.searchStudio}">
                        <button class="btn-filter" type="submit" name="action" value="SortServiceStudio"><i class="fa-solid fa-filter"></i>Sắp xếp</button>
                    </form>
                </div>

                <div class="studio-list">
                    <p class="msg-error">${requestScope.ERROR}</p>
                    <c:forEach var="stu" items="${requestScope.LIST_STUDIO}">
                        <form action="MainController">
                            <div class="studio-item">
                                <div class=" studio-content">
                                    <div class="studio-avatar">                
                                        <c:forEach var="stu_ava" items="${requestScope.LIST_AVATAR}">
                                            <c:set var="stu_avatarID" value="${stu_ava.studioID}"/>
                                            <c:if test="${stu.id == stu_avatarID}">
                                                <img class="studio-avatar" src="data:image/jpeg;base64,${stu_ava.imgLink}">
                                            </c:if>
                                        </c:forEach>
                                    </div>

                                    <div class="studio-infor">
                                        <div class="studio-infor-item">
                                            <label>Tên Studio:</label>
                                            <p>${stu.name}</p>
                                        </div>

                                        <div class="studio-infor-item">
                                            <label>Địa chỉ:</label>
                                            <p>${stu.address},  ${stu.district}, ${stu.city}</p>
                                        </div>

                                        <div class="studio-infor-item">
                                            <label>Giờ làm việc:</label>
                                            <p>${stu.timeStart} - ${stu.timeEnd}</p>
                                        </div>

                                        <div class="studio-infor-item">
                                            <label>Dịch vụ:</label>
                                            <p> 
                                                <c:forEach var="stu_service" items="${requestScope.LIST_SERVICE_STUDIO_INFOR}">
                                                    <c:set var="stu_serviceID" value="${stu_service.studioID}"/>
                                                    <c:if test="${stu.id == stu_serviceID}">
                                                        ${stu_service.serviceName} |
                                                    </c:if>
                                                </c:forEach>

                                                <c:forEach var="stu_service_detail" items="${requestScope.LIST_SERVICE_DETAIL_STUDIO_INFOR}">
                                                    <c:set var="stu_serviceDetailID" value="${stu_service_detail.studioID}"/>
                                                    <c:if test="${stu.id == stu_serviceDetailID}">
                                                        ${stu_service_detail.serviceDetailName} |
                                                    </c:if>
                                                </c:forEach>
                                            </p>
                                        </div>

                                        <div class="studio-infor-item">
                                            <label>Giá từ:</label>
                                            <p> <c:forEach var="min" items="${requestScope.LIST_MIN_PRICE}">
                                                    <c:if test="${stu.id == min.studioID}">
                                                        <fmt:formatNumber  value="${min.price}" pattern="###,###,###"/> VND-
                                                    </c:if>
                                                </c:forEach>
                                                <c:forEach var="max" items="${requestScope.LIST_MAX_PRICE}">
                                                    <c:if test="${stu.id == max.studioID}">
                                                        <fmt:formatNumber  value="${max.price}" pattern="###,###,###"/> VND
                                                    </c:if>
                                                </c:forEach>
                                            </p>
                                        </div>

                                        <div class="studio-infor-item">
                                            <label>Đánh giá:</label>
                                            <p>
                                                <c:forEach var="rating" items="${requestScope.LIST_RATING}">
                                                    <c:if test="${stu.id == rating.studioID && rating.rating != 0}">
                                                        ${rating.rating} <i class="fa-solid fa-star"></i>
                                                    </c:if>

                                                    <c:if test="${stu.id == rating.studioID && rating.rating == 0}">
                                                        Chưa có đánh giá
                                                    </c:if>
                                                </c:forEach>
                                            </p>
                                        </div>
                                    </div>
                                </div>
                                <input type="hidden" name="studioID" value="${stu.id}">
                                <button class="btn-view" type="submit" name="action" value="ViewStudio">THÔNG TIN CHI TIẾT</button>
                            </div>
                        </form>
                    </c:forEach>

                </div>
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
    </body>
</html>
