<%-- 
    Document   : studioInformation
    Created on : Oct 12, 2023, 6:55:51 PM
    Author     : ASUS
--%>

<%@page import="DTO.UserDTO"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>THÔNG TIN TIỆM XĂM</title>
        <link rel="preconnect" href="https://fonts.googleapis.com">
        <link rel="preconnect" href="https://fonts.gstatic.com" crossorigin>
        <link rel="stylesheet" type="text/css" href="//fonts.googleapis.com/css?family=Quicksand" />
        <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/6.4.2/css/all.min.css">
        <link rel="stylesheet" href="Resource/css/studioInformationStyle.css"/>
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

            <h1 class="msg-error">${requestScope.MESSAGE_ERROR}</h1>
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
                            <p>${requestScope.STUDIO.address},${requestScope.STUDIO.district},${requestScope.STUDIO.city} </p>
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
                <h1>ĐÁNH GIÁ</h1>
                <div id="rateList">
                    <div id="list">
                        <c:forEach var="item" items="${requestScope.CMT_FEEDBACK_LIST}">
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
                    <button id="prev"> < </button>
                    <button id="next"> > </button>
                </div>

            </div>

            <div class="service">
                <h1>DỊCH VỤ</h1>
                <form action="MainController">
                    <table>
                        <thead>
                            <tr>
                                <td class="add-row"></td>
                                <td class="th-service">Dịch vụ</td>
                                <td class="th-detail">Chi tiết</td>
                                <td class="th-size">Kích thước</td>
                                <td class="th-price">Đơn giá</td>
                                <td class="th-amount">Số lượng</td>
                                <td class="th-total">Thành tiền</td>
                            </tr>

                        </thead>
                        <tbody id="table_body">
                            <c:forEach begin="1" end="4">
                                <tr>
                                    <td class="delete-row"></td>
                                    <td>
                                        <select id="select_service" class="tb-service select_service" name="serviceID">
                                            <option></option>
                                            <c:forEach var="service" items="${requestScope.SERVICE_LIST}" varStatus="counter">
                                                <option value="${service.serviceID}">${service.serviceName}</option>
                                            </c:forEach>
                                        </select>
                                    </td>
                                    <td>
                                        <select id="select_service_detail" class="tb-detail select_service_detail" name="serviceDetailID">
                                        </select>
                                    </td>
                                    <td class="tb-size">
                                        <select size="1" name="serviceSizeID">
                                            <option></option>
                                            <c:forEach var="sSize" items="${requestScope.SERVICE_SIZE_LIST}" varStatus="counter">
                                                <option value="${sSize.serviceSizeID}">${sSize.name}</option>
                                            </c:forEach>
                                        </select>
                                    </td>
                                    <td class="tb-price">
                                        <p></p>
                                    </td>
                                    <td class="tb-amount">
                                        <input type="number" name="amount" min="1" max="4">
                                    </td>
                                    <td class="tb-total">
                                        <p></p>
                                    </td>
                                </tr>

                            </c:forEach>
                        </tbody>
                    </table>
                    <p class="total">TỔNG TIỀN : 0</p>
                    <input type="hidden" name="studioID" value="${requestScope.STUDIO.id}"/>
                    <div class="btn">
                        <button class="btn-book" type="submit" name="action" value="BookService">
                            <i class="fa-regular fa-calendar-check"></i>
                            XÁC NHẬN
                        </button>
                        <button class="btn-back" type="button" onclick="history.back()">
                            <i class="fa-solid fa-arrow-rotate-left"></i>
                            QUAY VỀ
                        </button>
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
        <div id="popup-img">
            <c:forEach var="img" items="${requestScope.IMG_SERVICE_LIST}" varStatus="loop">
                <img src="data:image/jpeg;base64,${img.imgLink}">
            </c:forEach>
            <a href="#" onclick="popup_img()">Đóng</a>
        </div>

        <script>
            function drop() {
                var divs = document.getElementById("subnav-content-id");
                divs.classList.toggle("show");
            }

            function popup_img() {
                var blur = document.getElementById('blur');
                blur.classList.toggle('active');
                var popup = document.getElementById('popup-img');
                popup.classList.toggle("active");
            }

            var select_service = document.getElementsByClassName("select_service");
            var select_service_detail = document.getElementsByClassName("select_service_detail");

            function create_tr(table_id) {
                let table_body = document.getElementById(table_id);

                if (table_body.childElementCount < 4) {
                    var newTR = document.createElement("tr");
                    newTR.innerHTML = `<td class="delete-row"><i class="fa-solid fa-x" onclick="remove_tr(this)"></i></td>
                                <td>
                                    <select id="select_service" class="tb-service select_service" name="serviceID">
                                        <option></option>
            <c:forEach var="service" items="${requestScope.SERVICE_LIST}" varStatus="counter">
                                            <option value="${service.serviceID}">${service.serviceName}</option>
            </c:forEach>
                                         
                                    </select>
                                </td>
                                <td>
                                    <select id="select_service_detail" class="tb-detail select_service_detail" name="serviceDetailID">
                                    </select>
                                </td>
                                <td class="tb-size">
                                    <select size="1" name="serviceSizeID">
                                        <option></option>
            <c:forEach var="sSize" items="${requestScope.SERVICE_SIZE_LIST}" varStatus="counter">
                                            <option value="${sSize.serviceSizeID}">${sSize.name}</option>
            </c:forEach>
                                    </select>
                                </td>
                                <td class="tb-price">
                                    <p></p>
                                </td>
                                <td class="tb-amount">
                                    <input type="number" name="amount" min="1" max="4">
                                </td>
                                <td class="tb-total">
                                    <p></p>
                                </td>`;
                    table_body.appendChild(newTR);
                    select_service = document.getElementsByClassName("select_service");
                    select_service_detail = document.getElementsByClassName("select_service_detail");
                }
            }

            function remove_tr(This) {
                if (This.closest('tbody').childElementCount == 1) {
                    alert("Bạn không thể xóa dòng này");
                } else {
                    This.closest('tr').remove();
                }
            }

            var normal_name_list = [];
            var normal_id_list = [];
            <c:forEach var="item" items="${requestScope.SERVICE_DETAIL_NORMAL_LIST}">
            normal_name_list.push("${item.serviceDetailName}");
            normal_id_list.push("${item.serviceDetailID}");
            </c:forEach>

            var color_name_list = [];
            var color_id_list = [];
            <c:forEach var="item" items="${requestScope.SERVICE_DETAIL_COLOR_LIST}">
            color_name_list.push("${item.serviceDetailName}");
            color_id_list.push("${item.serviceDetailID}");
            </c:forEach>

            let service_detail_name_normal = normal_name_list;
            let service_detail_name_color = color_name_list;
            let service_detail_id_normal = normal_id_list;
            let service_detail_id_color = color_id_list;
//            let select_service = document.getElementById("select_service");
//            let select_service_detail = document.getElementById("select_service_detail");

            function addToService_detail(arr_name, arr_id, count) {
                for (let i = 0; i < arr_id.length; i++) {
                    let option = document.createElement("option");
                    option.value = arr_id[i];
                    option.text = arr_name[i];
                    select_service_detail[count].appendChild(option);
                }
            }


            select_service[0].onchange = function () {
                select_service_detail[0].innerHTML = "<option></option>";
                if (this.value == "SV001") {
                    addToService_detail(service_detail_name_normal, service_detail_id_normal, 0);
                }

                if (this.value == "SV002") {
                    addToService_detail(service_detail_name_color, service_detail_id_color, 0);
                }
            };

            select_service[1].onchange = function () {
                select_service_detail[1].innerHTML = "<option></option>";
                if (this.value == "SV001") {
                    addToService_detail(service_detail_name_normal, service_detail_id_normal, 1);
                }

                if (this.value == "SV002") {
                    addToService_detail(service_detail_name_color, service_detail_id_color, 1);
                }
            };

            select_service[2].onchange = function () {
                select_service_detail[2].innerHTML = "<option></option>";
                if (this.value == "SV001") {
                    addToService_detail(service_detail_name_normal, service_detail_id_normal, 2);
                }

                if (this.value == "SV002") {
                    addToService_detail(service_detail_name_color, service_detail_id_color, 2);
                }
            };

            select_service[3].onchange = function () {
                select_service_detail[3].innerHTML = "<option></option>";
                if (this.value == "SV001") {
                    addToService_detail(service_detail_name_normal, service_detail_id_normal, 3);
                }

                if (this.value == "SV002") {
                    addToService_detail(service_detail_name_color, service_detail_id_color, 3);
                }
            };

            document.getElementById('next').onclick = function () {
                const widthItem = document.querySelector('.item').offsetWidth;
                document.getElementById('rateList').scrollLeft += widthItem;
            };

            document.getElementById('prev').onclick = function () {
                const widthItem = document.querySelector('.item').offsetWidth;
                document.getElementById('rateList').scrollLeft -= widthItem;
            };
        </script>
    </body>
</html>
