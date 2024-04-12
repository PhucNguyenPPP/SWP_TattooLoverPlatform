<%-- 
    Document   : studioBookingForm
    Created on : Oct 20, 2023, 8:51:25 AM
    Author     : ASUS
--%>

<%@page import="DTO.UserDTO"%>
<%@page import="DTO.UserDTO"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <meta name="viewport" content="width=device-width, initial-scale=1.0">
        <title>ĐẶT HẸN</title>
        <link rel="stylesheet" href="Resource/css/studioBookingFormStyle.css">
       <link rel="preconnect" href="https://fonts.googleapis.com">
        <link rel="preconnect" href="https://fonts.gstatic.com" crossorigin>
        <link rel="stylesheet" type="text/css" href="//fonts.googleapis.com/css?family=Quicksand" />
        <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/6.4.2/css/all.min.css">
    </head>
    <body>
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
                        <a href="MainController?action=StudioManagerInfo"><i class="fa-solid fa-gear"></i>Thông tin studio</a>
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
            <h1 class="msg-process">${requestScope.MESSAGE_PROCESS}</h1>
            <div class="title">THÔNG TIN TIỆM XĂM</div>
            <div class="studio-infor">
                <div class="studio-img">
                    <img class="img-main" src="data:image/jpeg;base64,${requestScope.IMG_AVATAR.imgLink}">
                    <div class="img-sub">
                        <c:forEach var="img" items="${requestScope.IMG_SERVICE_LIST}" varStatus="loop">
                            <c:if test="${loop.index < 1}">
                                <img src="data:image/jpeg;base64,${img.imgLink}">
                            </c:if>
                        </c:forEach>

                        <c:forEach var="img" items="${requestScope.IMG_SERVICE_LIST}" varStatus="loop">
                            <c:if test="${loop.index > 1 && loop.index < 3}">
                                <div class="more">
                                    <a href="#" onclick="popup_img()"><img src="data:image/jpeg;base64,${img.imgLink}"></a>
                                    <h3 onclick="popup_img()">XEM THÊM</h3>
                                </div>
                                
                                </c:if>
                            </c:forEach>
                    </div>

                </div>
                <div class="studio-detail">
                    <h1>${requestScope.STUDIO.name}</h1>
                    <div class="content-infor">
                        <div class="label">
                            <label>Địa chỉ:</label>
                            <label>Giờ làm việc:</label>
                            <label>Số điện thoại:</label>
                            <label>Đánh giá:</label>
                        </div>

                        <div class="infor">
                            <p>${requestScope.STUDIO.address},${requestScope.STUDIO.district},${requestScope.STUDIO.city}</p>
                            <p>${requestScope.STUDIO.timeStart}-${requestScope.STUDIO.timeEnd}</p>
                            <p>${requestScope.STUDIO.phoneNumber}</p>
                            <p>
                                 <c:if test="${requestScope.RATING.rating != 0}">
                                    ${requestScope.RATING.rating} <i class="fa-solid fa-star"></i>
                                </c:if>

                                <c:if test="${requestScope.RATING.rating == 0}">
                                    Chưa có đánh giá
                                </c:if>
                            </p>
                        </div>
                    </div>

                    <div class="description">
                        <p>${requestScope.STUDIO.description}</p>
                    </div>
                </div>
            </div>
            <div class="service">
                <h1>DỊCH VỤ</h1>
                <table>
                    <thead>
                        <tr>
                            <td class="th-service">Dịch vụ</td>
                            <td class="th-detail">Chi tiết</td>
                            <td class="th-size">Kích thước</td>
                            <td class="th-price">Đơn giá</td>
                            <td class="th-amount">Số lượng</td>
                            <td class="th-total">Thành tiền</td>
                        </tr>

                    </thead>
                    <tbody id="table_body">
                        <c:forEach var="book_service" items="${sessionScope.LIST_SERVICE_BOOKING}" >
                            <c:set var="total" value="${total + book_service.price * book_service.amount}" />
                            <tr>
                                <td class="tb-service">
                                    <p>${book_service.serviceName}</p>
                                </td>
                                <td class="tb-detail">
                                    <p>${book_service.serviceDetailName}</p>
                                </td>
                                <td class="tb-size">
                                    <p>${book_service.serviceSizeName}</p>
                                </td>
                                <td class="tb-price">
                                    <p>
                                        <fmt:formatNumber  value="${book_service.price}" pattern="###,###,###"/>
                                    </p>
                                </td>
                                <td class="tb-amount">
                                    <p>${book_service.amount}</p>
                                </td>
                                <td class="tb-total">
                                    <p><fmt:formatNumber  value="${book_service.price * book_service.amount}" pattern="###,###,###"/> VND</p>
                                </td>
                            </tr>
                        </c:forEach>
                    </tbody>
                </table>
                <p class="total">TỔNG TIỀN: <fmt:formatNumber  value="${total}" pattern="###,###,###"/> VND</p>
                <h1>THÔNG TIN ĐẶT LỊCH</h1>
                <form action="MainController">
                    <div class="form-booking">
                        <div class="form-content">
                            <div class="input-name">
                                <label>Họ và tên</label>
                                <input type="text" name="fullName" required=""/>
                            </div>

                            <div class="input-phone">
                                <label>Số điện thoại</label>
                                <input type="tel" name="phoneNumber" required="">
                                <p class="msg-error">${requestScope.MESSAGE_ERROR_PHONE}</p>
                            </div>

                            <div class="input-describe">
                                <label>Mô tả hình xăm</label>
                                <textarea rows="4" cols="50" name="description"></textarea>
                            </div>

                            <div class="input-daycome">
                                <label>Ngày đến (mm/dd/yyyy)</label>
                                <input id="datecome" type="date" name=bookDate>
                            </div>

                            <div class="input-time">
                                <label>Thời gian</label>
                                <select name="slot">
                                    <c:forEach var="slot" items="${requestScope.SLOT_LIST}">
                                        <option value="${slot.slotID}">${slot.timeStart}-${slot.timeEnd}</option>
                                    </c:forEach>
                                </select>
                                <p class="msg-error msg-error-slot">${requestScope.MESSAGE_ERROR_SLOT}</p>
                            </div>
                        </div>
                    </div>

                    <input type="hidden" name="studioID" value="${requestScope.STUDIO.id}"/>
                    <input type="hidden" name="totalPrice" value="${total}"/>
                    <button class="btn-book" type="submit" name="action" value="BookForm"><i class="fa-regular fa-calendar-check"></i>ĐẶT LỊCH</button>
                    <button class="btn-back" type="button" onclick="history.back()"><i
                            class="fa-solid fa-arrow-rotate-left"></i>QUAY VỀ</button>
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
        <div id="popup-img">
            <c:forEach var="img" items="${requestScope.IMG_SERVICE_LIST}" varStatus="loop">
                <img src="data:image/jpeg;base64,${img.imgLink}">
            </c:forEach>

            <a href="#" onclick="popup_img()">Close</a>
        </div>

        <script>
            /*drop cua avatar*/
            function drop() {
                var divs = document.getElementById("subnav-content-id");
                divs.classList.toggle("show");
            }

            /*pop up danh sach anh*/
            function popup_img() {
                var blur = document.getElementById('blur');
                blur.classList.toggle('active');
                var popup = document.getElementById('popup-img')
                popup.classList.toggle("active");
            }
            /*giới hạn ngày đến của khách (ngày không ở quá khứ)*/
            let today = new Date();
            day = today.getDate() + 1;
            if (today.getHours() > 22) {
                day += 1;
            }
            month = today.getMonth() + 1; //Bắt đầu từ tháng 1 = 0
            year = today.getFullYear();
            if (day < 10) {
                day = '0' + day;
            }

            if (month < 10) {
                month = '0' + month;
            }

            today = year + '-' + month + '-' + day;

            document.getElementById("datecome").setAttribute('min', today);
            document.getElementById("datecome").setAttribute('value', today);


        </script>
    </body>
</html>
