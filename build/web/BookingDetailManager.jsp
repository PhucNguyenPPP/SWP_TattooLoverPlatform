<%-- 
    Document   : BookingDetailManager.jsp
    Created on : Oct 27, 2023, 4:08:11 PM
    Author     : thanh
--%>

<%@page import="DTO.UserDTO"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="en">

    <head>
        <meta charset="UTF-8">
        <meta name="viewport" content="width=device-width, initial-scale=1.0">
        <title>CHI TIẾT LỊCH HẸN</title>
        <link rel="stylesheet" href="Resource/css/bookingDetailManager.css">
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
            <h1 class="msg-error">${requestScope.ERROR}</h1>
            <div class="title">THÔNG TIN LỊCH HẸN CHI TIẾT</div>
            <div class="detail">

                <div class="detail-p1">
                    <div class="all">
                        <div class="detail-p1-name">
                            <label>Tên người đặt:</label>
                            <p>${requestScope.BOOKING.userFullName}</p>
                        </div>
                        <div class="detail-p1-status">
                            <label>Tình trạng:</label>
                            <p>${requestScope.BOOKING.status}</p>
                        </div>
                    </div>
                    <div class="two">
                        <label>ID người đặt:</label>
                        <p>${requestScope.BOOKING.userID}</p>
                    </div>

                </div>
                <div class="underline"></div>

                <div class="detail-p2">
                    <div class="detail-p2-col1">
                        <div class="detail-p2-id">
                            <label>Mã đơn:</label>
                            <p>${requestScope.BOOKING.bookingID}</p>
                        </div>

                        <div class="detail-p2-daybook">
                            <label>Ngày đặt:</label>
                            <p>${requestScope.BOOKING.currentDate}</p>
                        </div>
                    </div>

                    <div class="detail-p2-col2">
                        <div class="detail-p2-daycome">
                            <label>Ngày đến:</label>
                            <p>${requestScope.BOOKING.bookingDate}</p>
                        </div>

                        <div class="detail-p2-time">
                            <label>Thời gian:</label>
                            <p>${requestScope.BOOKING.slotTimeStart} - ${requestScope.BOOKING.slotTimeEnd}</p>
                        </div>
                    </div>

                </div>
                <div class="information">
                    <div class="element">
                        <label>Họ tên khách hàng:</label>
                        <p>${requestScope.BOOKING.fullName}</p>
                    </div>
                    <div class="element2">
                        <label>Số điện thoại: </label>
                        <p>${requestScope.BOOKING.phoneNumber}</p>
                    </div>
                </div>
                <form action="MainController">
                    <div class="detail-p3">
                        <div class="detail-p3-col1">
                            <c:forEach var="bDetail" items="${requestScope.BOOKING_DETAIL_LIST}" varStatus="counter">
                                <input type="hidden" name="bookingStudioServiceID" value="${bDetail.bookingStudioServiceID}">
                                <div class="detail-content">
                                    <div class="detail-content-col1">
                                        <h1 class="count">${counter.count}.</h1>
                                        <div class="content-service">
                                            <label>Dịch vụ:</label>
                                            <p>${bDetail.serviceName}</p>
                                        </div>

                                        <div class="content-size">
                                            <label>Kích thước hình xăm:</label>
                                            <p>${bDetail.serviceSizeName}</p>
                                        </div>

                                        <div class="content-servicedetail">
                                            <label>Chi tiết:</label>
                                            <p>${bDetail.serviceDetailName}</p>
                                        </div>
                                    </div>


                                    <div class="detail-content-col2">
                                        <div class="content-price">
                                            <label>Giá:</label>
                                            <p><fmt:formatNumber  value="${bDetail.price}" pattern="###,###,###"/> VND</p>
                                        </div>

                                        <div class="content-amount">
                                            <label>Số lượng:</label>
                                            <p>${bDetail.amount}</p>
                                        </div>
                                        <div class="content-artist">
                                            <label>Thợ xăm:</label>
                                            <c:choose>
                                                <c:when test="${requestScope.BOOKING.status == 'Đang xử lý'}">
                                                    <select class="select-artist" name="artistID">
                                                        <c:forEach var="artist" items="${requestScope.ARTIST_LIST}">
                                                            <option value="${artist.id}">${artist.fullname}</option>
                                                        </c:forEach>
                                                    </select>
                                                </c:when>
                                                <c:otherwise>
                                                    <p>${bDetail.artistName}</p>
                                                </c:otherwise>
                                            </c:choose>
                                        </div>
                                    </div>
                                </div>
                            </c:forEach>

                            <div class="total">
                                <h1>TỔNG THANH TOÁN:</h1>
                                <p>
                                    <fmt:formatNumber  value="${requestScope.BOOKING.totalPrice}" pattern="###,###,###"/> VND
                                </p>
                            </div>
                        </div>

                        <div class="detail-p3-col2">
                            <h1>Mô tả hình xăm:</h1>
                            <p class="text-descript">${requestScope.BOOKING.description}</p>
                            <!-- Chỉ có feedback khi lịch hẹn ở trạng thái hoàn thành -->
                            <c:choose>
                                <c:when test="${requestScope.BOOKING.status == 'Đang xử lý'}">
                                    <div>
                                        <input type="hidden" name="tattooLoverID" value="${requestScope.BOOKING.userID}">
                                        <input type="hidden" name="studioName" value="${requestScope.BOOKING.studioName}">
                                        <input type="hidden" name="bookingID" value="${requestScope.BOOKING.bookingID}">
                                        <button class="btn-inspect" type="submit" name="action" value="Inspect">XÁC NHẬN</button>
                                        <button class="btn-back" type="button" onclick="history.back()">QUAY VỀ</button>
                                    </div>
                                </c:when>
                                <c:when test="${requestScope.BOOKING.status == 'Đã duyệt'}">
                                    <div>
                                        <input type="hidden" name="tattooLoverID" value="${requestScope.BOOKING.userID}">
                                        <input type="hidden" name="studioName" value="${requestScope.BOOKING.studioName}">
                                        <input type="hidden" name="bookingID" value="${requestScope.BOOKING.bookingID}">
                                        <button class="btn-finish" type="submit" name="action" value="FinishBooking">HOÀN THÀNH</button>
                                        <button class="btn-back" type="button" onclick="history.back()">QUAY VỀ</button>
                                    </div>
                                </c:when>

                                <c:when  test="${requestScope.BOOKING.status == 'Hoàn thành'}">
                                    <div class="feedback">
                                        <div class="feedback-rate">
                                            <p>Đánh giá: </p>
                                            <div class="rate">
                                                <p class="star-af-feedback">${requestScope.BOOKING.rating}<i class="fa-solid fa-star"></i></p>
                                            </div>
                                        </div>
                                        <label class="lb-cmt">Bình luận:</label>
                                        <p class="cmt-af-feedback"> ${requestScope.BOOKING.commentation}</p>
                                        <div>
                                            <button class="btn-af-back" type="button" onclick="history.back()">QUAY VỀ</button>
                                        </div>
                                    </div>
                                </c:when>
                                <c:when  test="${requestScope.BOOKING.status == 'Đã hủy'}">
                                    <div>
                                        <button class="btn-af-back" type="button" onclick="history.back()">QUAY VỀ</button>
                                    </div>
                                </c:when>
                            </c:choose>
                        </div>
                    </div>
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
            /*drop cua avatar*/
            function drop() {
                var divs = document.getElementById("subnav-content-id");
                divs.classList.toggle("show");
            }
        </script>
    </body>

</html>
