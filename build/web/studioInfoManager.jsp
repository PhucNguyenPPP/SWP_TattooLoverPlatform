<%-- 
   Document   : home
   Created on : Oct 12, 2023, 6:00:54 PM
   Author     : ASUS
--%>

<%@page import="org.apache.jasper.tagplugins.jstl.core.ForEach"%>
<%@page import="DTO.ServiceDetailDTO"%>
<%@page import="java.util.List"%>
<%@page import="DTO.UserDTO"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <meta name="viewport" content="width=device-width, initial-scale=1.0">
        <title>THÔNG TIN TIỆM XĂM</title>
        <link rel="preconnect" href="https://fonts.googleapis.com">
        <link rel="preconnect" href="https://fonts.gstatic.com" crossorigin>
        <link rel="stylesheet" type="text/css" href="//fonts.googleapis.com/css?family=Quicksand" />
        <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/6.4.2/css/all.min.css">
        <link rel="stylesheet" href="Resource/css/studioInfoManagerStyle.css"/>
    </head>
    <body>
        <c:if test="${sessionScope.User == null || (sessionScope.User.roleId ne 'SM' && sessionScope.User.roleId ne 'SS')}">
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
                    if (us != null && (us.getRoleId().equals("SM"))) {
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
            <h1 class="title">THÔNG TIN TIỆM XĂM</h1>
            <div class="studio-infor">
                <div class="studio-img">
                    <img class="img-main" src="data:image/jpeg;base64,${sessionScope.IMG_AVATAR.imgLink}">
                    <div class="img-sub">
                        <c:forEach var="img" items="${sessionScope.IMG_SERVICE_LIST}" varStatus="loop">
                            <c:if test="${loop.index < 1}">
                                <img src="data:image/jpeg;base64,${img.imgLink}">
                            </c:if>
                        </c:forEach>

                        <c:forEach var="img" items="${sessionScope.IMG_SERVICE_LIST}" varStatus="loop">
                            <c:if test="${loop.index > 1 && loop.index < 3}">
                                <div class="more">
                                    <a href="#" onclick="popup_img()"><img src="data:image/jpeg;base64,${img.imgLink}"></a>
                                    <h3 onclick="popup_img()">XEM THÊM</h3>
                                </div>
                                
                                </c:if>
                            </c:forEach>
                        <div class="add-img">
                            <form action="MainController" method="POST" enctype="multipart/form-data">
                                <input type="file" name="imageStudio" accept=".png, .jpg, .jpeg" required="">
                                <input type="hidden" name="studioID" value="${sessionScope.STUDIO.id}">
                                <button class="btn-submit" type="submit" name="action" value="addImageStu">Thêm hình ảnh</button>
                            </form>
                            <p class="msg">${requestScope.MSG}</p>
                        </div>
                    </div>


                </div>
                <div class="studio-detail">
                    <form action="MainController" method="POST">

                        <h1>${sessionScope.STUDIO.name}</h1>
                        <div class="content-infor">
                            <div class="label">
                                <label>Địa chỉ:</label>
                                <label>Giờ mở cửa:</label>
                                <label>Giờ đóng cửa:</label>
                                <label>Thời lượng dịch vụ:</label>
                                <label>Số điện thoại:</label>
                                <label>Email:</label>
                                <label>Đánh giá:</label>
                                <label>Giới thiệu:</label>
                            </div>

                            <div class="infor">
                                <input readonly="" class="input-info" type="text" required="" value='${sessionScope.STUDIO.address},${sessionScope.STUDIO.district},${requestScope.STUDIO.city}'/><br>
                                <input readonly="" name="timeStart" class='input-info' type='text' required="" value='${sessionScope.STUDIO.timeStart}'/><br>
                                <input readonly="" name="timeEnd" class='input-info' type='text'required="" value='${sessionScope.STUDIO.timeEnd}'/><br>
                                <input readonly="" name="time1slot" class='input-info' type='number' required="" min="1" value='${sessionScope.STUDIO.studioSlotTime}'/><br>
                                <input name="phoneNumber" class='input-info' type='text' required="" value='${sessionScope.STUDIO.phoneNumber}'/><br>
                                <input name="Email" class='input-info' type='text' required="" value='${sessionScope.STUDIO.email}'/><br>
                                <c:if test="${sessionScope.RATING.rating != 0}">
                                    ${sessionScope.RATING.rating} <i class="fa-solid fa-star"></i>
                                </c:if>

                                <c:if test="${sessionScope.RATING.rating == 0}">
                                    Chưa có đánh giá
                                </c:if>
                            </div>

                        </div>
                        <div class="description">
                            <textarea name="description" class="input-des" rows="4" cols="50" required="">${sessionScope.STUDIO.description}
                            </textarea>
                        </div>
                        <button class="btn-submit" type="submit" name="action" value="editManager">Chỉnh sửa thông tin</button>

                    </form>
                </div>

            </div>
            <div class="rate">
                <h1>ĐÁNH GIÁ</h1>
                <div class="rateList">
                    <div id="list">
                        <c:forEach var="item" items="${sessionScope.CMT_FEEDBACK_LIST}">
                            <div class="item">
                                <div class="content">
                                    <p>${item.userFullName}</p>
                                    <p class="star">${item.rating}<i class="fa-solid fa-star"></i></p>
                                    <P>${item.commentation}</P>
                                </div>
                            </div>
                        </c:forEach>
                    </div>
                </div>
                <div class="direction">
                    <button id="prev">
                        < </button>
                    <button id="next"> > </button>
                </div>
            </div>
            <div class="rate">
                <h1>DỊCH VỤ</h1>
                <div class="table-service">
                    <div class="tb">
                        <table>
                            <thead>
                                <tr>
                                    <th>Dịch vụ</th>
                                    <th>Chi tiết</th>
                                    <th>Bảng giá</th>
                                </tr>
                            </thead>
                            <tbody>
                                <c:set var="sizeNormal" value="${sessionScope.SERVICE_DETAIL_NORMAL_LIST}"></c:set>
                                <c:if test="${empty (sizeNormal)}">
                                    <tr>
                                        <td rowspan="${sessionScope.SER_DE_LIST_SIZE+1}"> 
                                            Xăm đơn sắc
                                        </td>
                                    </tr>
                                </c:if>

                                <%
                                    boolean isTitled = false;
                                    boolean isTitled2 = false;
                                %>
                                <c:forEach var="item" items="${sessionScope.SERVICE_DETAIL_NORMAL_LIST}" varStatus="loop">
                                    <tr>
                                        <%
                                            if (!isTitled) {

                                        %>
                                        <td  rowspan = "${sessionScope.SER_DE_LIST_SIZE}" > 
                                            Xăm đơn sắc
                                        </td>
                                        <%         isTitled = true;
                                            }
                                        %>
                                <form action="MainController">
                                    <td>
                                        <a class="mina" href="MainController?action=delService&serviceID=SV001&itemID=${pageScope.item.serviceDetailID}">
                                            <input type="checkbox" checked/>
                                            ${item.serviceDetailName}
                                        </a>
                                    </td>
                                    <td class="btn-edit">
                                        <button class="input-btn" type="submit" name="action" value="editPriceList">Sửa bảng giá</button>
                                        <input type="hidden" name="serviceID" value="${item.serviceID}"/>
                                        <input type="hidden" name="serviceDetailID" value="${item.serviceDetailID}"/>
                                    </td>
                                </form>
                                </tr>
                            </c:forEach>   
                            <c:forEach var="item2" items="${sessionScope.NOT_SER_DE_NORMAL_LIST}" varStatus="loop">
                                <tr>
                                <form action="MainController">
                                    <td>
                                        <a class="mina" href="MainController?action=priceList&serviceID=SV001&itemID=${pageScope.item2.serviceDetailID}">
                                            <input type="checkbox" />${item2.name}
                                        </a>
                                    </td>
                                </form>
                                </tr>
                            </c:forEach>
                            </tbody>
                        </table>
                    </div>
                    <!--                            xăm màu-->
                    <div class="tb">
                        <table class="tb2">
                            <thead>
                                <tr>
                                    <th>Dịch vụ</th>
                                    <th>Chi tiết</th>
                                    <th>Bảng giá</th>
                                </tr>
                            </thead>
                            <tbody>
                                <c:set var="sizeColor" value="${sessionScope.SERVICE_DETAIL_COLOR_LIST}"></c:set>
                                <c:if test="${empty (sizeColor)}">
                                    <tr>
                                        <td rowspan="${sessionScope.SER_DE_LIST_SIZE+1}"> 
                                            Xăm màu
                                        </td>
                                    </tr>
                                </c:if>
                                <c:forEach var="item" items="${sessionScope.SERVICE_DETAIL_COLOR_LIST}" varStatus="loop">
                                    <tr>
                                        <%
                                            if (!isTitled2) {

                                        %>
                                        <td  rowspan = "${sessionScope.SER_DE_LIST_SIZE}" > 
                                            Xăm màu
                                        </td>
                                        <%         isTitled2 = true;
                                            }
                                        %>                                        
                                <form action="MainController">
                                    <td>
                                        <a class="mina" href="MainController?action=delService&serviceID=SV002&itemID=${pageScope.item.serviceDetailID}">
                                            <input type="checkbox" checked/>
                                            ${item.serviceDetailName}
                                        </a>
                                    </td>
                                    <td class="btn-edit">
                                        <button class="input-btn" type="submit" name="action" value="editPriceList">Sửa bảng giá</button>
                                        <input type="hidden" name="serviceID" value="${item.serviceID}"/>
                                        <input type="hidden" name="serviceDetailID" value="${item.serviceDetailID}"/>
                                    </td>
                                </form>
                                </tr>
                            </c:forEach>   
                            <c:forEach var="item2" items="${sessionScope.NOT_SER_DE_COLOR_LIST}" varStatus="loop">
                                <tr>
                                <form action="MainController">
                                    <td>
                                        <a class="mina" href="MainController?action=priceList&serviceID=SV002&itemID=${pageScope.item2.serviceDetailID}">
                                            <input type="checkbox" />${item2.name}
                                        </a>
                                    </td>
                                </form>
                                </tr>
                            </c:forEach>
                            </tbody>
                        </table>
                    </div>
                </div>

            </div>
        </div>
        <div>
            <c:forEach var="service" items="${sessionScope.IMG_SERVICE_LIST}" varStatus="loop">
                <c:if test="${loop.index > 1 && loop.index < 3}">
                    <a href="#" onclick="popup_img()"><img src="${img.imgLink}"></a>
                    </c:if>
                </c:forEach>
            <table>

            </table>
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
            <c:forEach var="img" items="${sessionScope.IMG_SERVICE_LIST}" varStatus="loop">
                <img src="data:image/jpeg;base64,${img.imgLink}">
            </c:forEach>
            <a href="#" onclick="popup_img()">Đóng</a>
        </div>
    </div>
    <script>

        function drop() {
            var divs = document.getElementById("subnav-content-id");
            divs.classList.toggle("show");
        }
        function redirect() {
            location.href = 'MainController?action=priceList';
        }
        function popup_img() {
            var blur = document.getElementById('blur');
            blur.classList.toggle('active');
            var popup = document.getElementById('popup-img');
            popup.classList.toggle("active");
        }
    </script>
</body>
</html>