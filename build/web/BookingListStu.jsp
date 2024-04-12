<%-- 
    Document   : BookingListStu
    Created on : Oct 26, 2023, 1:37:18 PM
    Author     : ASUS
--%>

<%@page import="DTO.UserDTO"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <meta name="viewport" content="width=device-width, initial-scale=1.0">
        <title>LỊCH SỬ ĐẶT HẸN</title>
        <link rel="stylesheet" href="Resource/css/bookingListStyle.css">
        <link rel="preconnect" href="https://fonts.googleapis.com">
        <link rel="preconnect" href="https://fonts.gstatic.com" crossorigin>
        <link rel="stylesheet" type="text/css" href="//fonts.googleapis.com/css?family=Quicksand" />
        <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/6.4.2/css/all.min.css">
    </head>
    <body>
        <c:if test="${sessionScope.User == null || (sessionScope.User.roleId ne 'SM' && sessionScope.User.roleId ne 'SS')}">
            <c:redirect url="HomeController">

            </c:redirect>
        </c:if>
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
                if (us != null && us.getRoleId().equals("SM")) {
            %>
            <a class="btn-notification" href="MainController?action=Notification"><i class="fa-solid fa-bell"></i> Thông báo</a>
            <a class="btn-signupstudio-af" href="MainController?action=ViewAccountStu">Quản lý nhân viên</a>
            <div class="avatar-item">
                <img onclick="drop()" class="avatar-img"
                     src="https://th.bing.com/th/id/OIP.7Z0skq54kBfcHRPjlsvATgHaG_?pid=ImgDet&rs=1">
                <div id="subnav-content-id" class="subnav-content">
                    <a href="MainController?action=StudioManagerInfo"><i class="fa-solid fa-gear"></i>Thông tin tiệm xăm</a>
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
                    <a href="MainController?action=StudioManagerInfo"><i class="fa-solid fa-gear"></i>Thông tin tiệm xăm</a>
                    <a href="MainController?action=userprofile"><i class="fa-solid fa-user"></i>Tài khoản của tôi</a>
                    <a href="MainController?action=BookListStu"><i class="fa-solid fa-calendar-days"></i>Lịch hẹn</a>
                    <a href="MainController?action=logoutservlet"><i class="fa-solid fa-right-from-bracket"></i>Đăng xuất</a>
                </div>
            </div>
            <%
                }
            %>
        </div>
        <h1 class="title">LỊCH SỬ ĐẶT HẸN</h1>
        <!--        <div class="filter">
                    <select>
                        <option>Xếp theo: Ngày đặt gần</option>
                        <option>Xếp theo: Ngày đặt xa</option>
                        <option>Xếp theo: Ngày đến gần</option>
                        <option>Xếp theo: Ngày đến xa</option>
                        <option>Xếp theo: Tình trạng</option>
                    </select>
                </div>-->

        <div class="table">
            <table>
                <thead>
                    <tr>
                        <td class="th-id">Mã đơn</td>
                        <td class="th-stu">Tên khách hàng</td>
                        <td class="th-time">Thời gian</td>
                        <td class="th-datebook">Ngày đặt</td>
                        <td class="th-datecome">Ngày đến</td>
                        <td class="th-status">Trình trạng</td>
                        <td class="th-detail">Chi tiết</td>
                        <td class="th-cancel">Hủy lịch</td>
                    </tr>
                </thead>
                <tbody>
                    <c:forEach var="booking" items="${requestScope.LIST_BOOKING_STU}">
                        <tr>
                            <td class="tb-id" name="bookingID">${booking.bookingID}</td>
                            <td class="tb-stu">${booking.userFullName}</td>
                            <td class="tb-time">${booking.slotTimeStart}-${booking.slotTimeEnd}</td>
                            <td class="tb-datebook">${booking.currentDate}</td>
                            <td class="tb-datecome">${booking.bookingDate}</td>
                            <td class="tb-status">${booking.status}</td>

                    <form action="MainController" method="POST">
                        <input type="hidden" name="action" value="DetailBookingStu">
                        <input type="hidden" name="bookingID" value="${booking.bookingID}">
                        <td class="detail">
                            <input class="btn-detail" type="submit" value="CHI TIẾT">
                        </td>
                    </form>
                    <c:choose>
                        <c:when test="${booking.status == 'Đã duyệt' || booking.status == 'Đang xử lý'}">
                            <form action="MainController" method="post">
                                <input type="hidden" name="action" value="DeleteBookingStu">
                                <input type="hidden" name="tattooLoverID" value="${booking.userID}">
                                <input type="hidden" name="studioID" value="${booking.studioID}">
                                <input type="hidden" name="bookingID" value="${booking.bookingID}">
                                <td class="cancel">
                                    <input class="btn-cancel" type="submit" value="HỦY LỊCH" onclick="return confirm('Xác nhận huỷ lịch')">
                                </td>
                            </form>
                        </c:when>
                        <c:otherwise>
                            <td></td>
                        </c:otherwise>
                    </c:choose>
                    </tr>
                </c:forEach> 
                </tbody>
            </table>

            <p class="msg-error">${requestScope.ERROR}</p>'
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

        <script>

            function drop() {
                var divs = document.getElementById("subnav-content-id");
                divs.classList.toggle("show");
            }

        </script>
    </body>
</html>
