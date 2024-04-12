<%-- 
    Document   : index
    Created on : Oct 22, 2023, 3:27:27 AM
    Author     : hieu09097248
--%>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="en">
    <head>
        <meta charset="UTF-8">
        <meta name="viewport" content="width=device-width, initial-scale=1.0">
        <title>LỊCH SỬ ĐẶT HẸN</title>
        <link rel="stylesheet" href="Resource/css/bookingListStyle.css">
        <link rel="preconnect" href="https://fonts.googleapis.com">
        <link rel="preconnect" href="https://fonts.gstatic.com" crossorigin>
        <link rel="stylesheet" type="text/css" href="//fonts.googleapis.com/css?family=Quicksand" />
        <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/6.4.2/css/all.min.css">
    </head>
    <body>
        <c:if test="${sessionScope.User == null || sessionScope.User.roleId ne 'TL'}">
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
            <c:set var="uslist" value="${requestScope.BOOKING_LIST}"/>
            <c:if test="${uslist !=null}">

                <c:if test="${not empty requestScope.BOOKING_LIST}">
                    <table>
                        <thead>
                            <tr>
                                <td class="th-id">Mã đơn</td>
                                <td class="th-stu">Tiệm xăm</td>
                                <td class="th-time">Thời gian</td>
                                <td class="th-datebook">Ngày đặt</td>
                                <td class="th-datecome">Ngày đến</td>
                                <td class="th-status">Trình trạng</td>
                                <td class="th-detail">Chi tiết</td>
                                <td class="th-cancel">Hủy lịch</td>
                            </tr>
                        </thead>
                        <tbody>
                            <c:forEach items="${uslist}" var="booking">
                                <tr>
                                    <td class="tb-id" name="bookingID">${booking.getBookingID()} </td>
                                    <td class="tb-stu">${booking.getStudioName()}</td>
                                    <td class="tb-time">${booking.getSlotTimeStart()}-${booking.getSlotTimeEnd()}</td>
                                    <td class="tb-datebook">${booking.getCurrentDate()}</td>
                                    <td class="tb-datecome">${booking.getBookingDate()}</td>
                                    <td class="tb-status">${booking.getStatus()}</td>

                            <form action="MainController" method="POST">
                                <input type="hidden" name="action" value="DetailBooking">
                                <input type="hidden" name="bookingID" value="${booking.getBookingID()}">
                                <td class="detail">
                                    <input class="btn-detail" type="submit" value="CHI TIẾT">
                                </td>
                            </form>
                            <c:choose>
                                <c:when test="${booking.getStatus() == 'Đã duyệt' || booking.getStatus() == 'Đang xử lý'}">
                                    <form action="MainController" method="post">
                                        <input type="hidden" name="action" value="DeleteBookingTL">
                                        <input type="hidden" name="studioID" value="${booking.studioID}">
                                        <input type="hidden" name="bookingID" value="${booking.getBookingID()}">
                                        <td class="cancel">
                                            <input class="btn-cancel" type="submit" value="HỦY LỊCH" onclick="return confirm('Xác nhận huỷ lịch')">
                                        </td>
                                    </form>
                                </c:when>
                                <c:otherwise>
                                    <td> </td>
                                </c:otherwise>
                            </c:choose>

                            </tr>
                        </c:forEach>
                        </tbody>
                    </table>
                </c:if>
            </c:if>
            <p class="msg-error">${requestScope.ERROR}</p>
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
