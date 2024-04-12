<%-- 
    Document   : signup
    Created on : Oct 19, 2023, 11:27:25 AM
    Author     : ACER
--%>

<<<<<<< HEAD
<%@page import="DAO.DistrictDAO"%>
=======
>>>>>>> 46860fd9c8195799510ba7b99c8b013849670722
<%@page import="DAO.CityDAO"%>
<%@page import="java.util.List"%>
<%@page import="DTO.CityDTO"%>
<%@page import="DTO.UserDTO"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<!DOCTYPE html>
<html lang="en">

    <head>
        <meta charset="UTF-8">
        <meta name="viewport" content="width=device-width, initial-scale=1.0">
        <title>ĐĂNG KÍ</title>
        <link rel="stylesheet" href="Resource/css/signupStyle.css">
        <link rel="preconnect" href="https://fonts.googleapis.com">
        <link rel="preconnect" href="https://fonts.gstatic.com" crossorigin>
        <link rel="stylesheet" type="text/css" href="//fonts.googleapis.com/css?family=Quicksand" />
        <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/6.4.2/css/all.min.css">

    </head>

    <body>
        <% List<CityDTO> cityList = CityDAO.getCityListStatic();
<<<<<<< HEAD
            session.setAttribute("cityList", CityDAO.getCityListStatic());
            session.setAttribute("HCMDistrictList", DistrictDAO.getDistrictListByCityID("CT001"));
            session.setAttribute("HNDistrictList", DistrictDAO.getDistrictListByCityID("CT002"));
=======
>>>>>>> 46860fd9c8195799510ba7b99c8b013849670722
        %>
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
                } else if (us != null && us.getRoleId().equals("AD")) {
                %>
                <a class="btn-notification" href="MainController?action=Notification"><i class="fa-solid fa-bell"></i> Thông báo</a>
                <a class="btn-signupstudio-af" href="#">Quản lý nhân viên</a>
                <div class="avatar-item">
                    <img onclick="drop()" class="avatar-img"
                         src="https://th.bing.com/th/id/OIP.7Z0skq54kBfcHRPjlsvATgHaG_?pid=ImgDet&rs=1">
                    <div id="subnav-content-id" class="subnav-content">
                        <a href="MainController?action=userprofile"><i class="fa-solid fa-user"></i>Tài khoản của tôi</a>
                        <a href="MainController?action=logoutservlet"><i class="fa-solid fa-right-from-bracket"></i>Đăng xuất</a>
                    </div>
                </div>
                <%
                    }
                %>

            </div>

            <c:set var="User" value="${sessionScope.User}"/>

            <div class="container2">
                <div class="signUp">
                    <form action="MainController" method="POST">
                        <c:choose>
                            <c:when test="${User.roleId == 'AD'}">
                                <h2>ĐĂNG KÝ TÀI KHOẢN NHÂN VIÊN HỆ THốNG</h2>
                                <input type="hidden" name="roleid" value="SST">
                            </c:when>
                            <c:when test="${User.roleId == 'SM'}">
                                <h2>ĐĂNG KÝ TÀI KHOẢN NHÂN VIÊN, THỢ XĂM</h2>
                                <div class="select-role-class">
                                    <label class="select-role-label">Vai trò</label>
                                    <select class="select-role" name="roleid">
                                        <option value="SS">Nhân viên</option>
                                        <option value="SA">Thợ xăm</option>
                                    </select>
                                </div>
                            </c:when>
                            <c:otherwise>
                                <h2>ĐĂNG KÝ TÀI KHOẢN</h2>
                                <input type="hidden" name="roleid" value="TL">
                            </c:otherwise>
                        </c:choose>
                        <input type="hidden" name="action" value="signupservlet">
                        <div class="Username">
                            <label for="username">Tên đăng nhập</label>
                            <input type="text" id="username" name="username" required="" />
                        </div>
                        <p class="msg-error">${requestScope.ERROR_USERNAME}</p>
                        <div class="Password">
                            <label for="password">Mật khẩu</label>
                            <input type="password" id="password" name="password"
                                   required="" />
                        </div>
                        <div class="Confirm">
                            <label for="password">Xác nhận mật khẩu</label>
                            <input type="password" id="password" name="confirmedpassword"
                                   required="" />
                        </div>
                        <p class="msg-error">${requestScope.ERROR_PASSWORD}</p>
                        <div class="fullName">
                            <label for="fullName">Họ và tên</label>
                            <input type="text" id="fullName" name="fullName" required="" />
                        </div>
                        <div class="birthDate">
                            <label for="birthDate">Ngày sinh</label>
                            <input type="date" id="birthDate" name="birthDate"
                                   required="" />
                        </div>
                        <div class="Gender">
                            <label for="gender">Giới tính</label>
                            <select id="gender" name="gender" required>
                                <option value="Male">Nam</option>
                                <option value="Female">Nữ</option>
                                <option value="Other">Khác</option>
                            </select>
                        </div>

                        <div class="phoneNumber">
                            <label for="phone">Số điện thoại</label>
                            <input type="tel" id="phone" name="phoneNumber" required=""
                                   pattern="[0]{1}[0-9]{9}" />
                        </div>
                        <p class="msg-error">${requestScope.ERROR_PHONE}</p>
                        <div class="Email">
                            <label for="email">Email<label>
                                    <input type="email" id="email" name="email" />
                                    </div>
                                    <p class="msg-error">${requestScope.ERROR_EMAIL}</p>

                                    <div class="City">
                                        <label for="city">Thành phố</label>
                                        <select id="city" name="city" required>
                                            <% for (CityDTO city : cityList) {%>
                                            <option value="<%=city.getCityID()%>">
                                                <%=city.getCityName()%>
                                            </option>
                                            <% }%>
                                        </select>
                                    </div>


                                    <div class="District">
                                        <label for="district">Quận</label>
                                        <select id="district" name="district" required>
                                        </select>
                                    </div>

                                    <c:choose>
                                        <c:when test="${User.roleId == 'AD'}">
                                            <!--                        <input type="hidden" name="roleid" value="SST">-->
                                        </c:when>
                                        <c:when test="${User.roleId == 'SM'}">
                                            <!--                        <select name="roleid">
            <option value="SS">Nhân viên</option>
            <option value="SA">Thợ xăm</option>
        </select>-->
                                        </c:when>
                                        <c:otherwise>
                                            <!--                        <input type="hidden" name="roleid" value="TL">-->
                                        </c:otherwise>
                                    </c:choose>


                                    <div class="LOGIN">
                                        <input type="submit" value="ĐĂNG KÝ">
                                        <button type="button" onclick="history.back()">HỦY</button>
                                    </div>

                                    </form>
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

                                        function updateDistrictOption() {
                                            var selectedCity = document.getElementById("city");
                                            var selectDistrict = document.getElementById("district");
                                            var selectedCityId = selectedCity.value;
                                            var selectDistrictOptionsName = [];
                                            var selectDistrictOptionsId = [];
                                            switch (selectedCityId) {
                                                case "CT001":
                                        <c:forEach var="item" items="${sessionScope.HCMDistrictList}">
                                                    selectDistrictOptionsName.push("${item.districtName}");
                                                    selectDistrictOptionsId.push("${item.districtID}");
                                        </c:forEach>
                                                    break;
                                                case "CT002":
                                        <c:forEach var="item" items="${sessionScope.HNDistrictList}">
                                                    selectDistrictOptionsName.push("${item.districtName}");
                                                    selectDistrictOptionsId.push("${item.districtID}");
                                        </c:forEach>
                                                    break;
                                                default:
                                                    selectDistrictOptionsName = [];
                                                    selectDistrictOptionsId = [];
                                                    break;
                                            }
                                            selectDistrict.innerHTML = '';
                                            for (let i = 0; i < selectDistrictOptionsName.length; i++) {
                                                let option = document.createElement("option");
                                                option.text = selectDistrictOptionsName[i];
                                                option.value = selectDistrictOptionsId[i];
                                                selectDistrict.appendChild(option);
                                            }
                                        }
                                        var selectedCity = document.getElementById("city");
                                        selectedCity.addEventListener("change", updateDistrictOption);
                                        updateDistrictOption();

                                        // Get the current date in the format "YYYY-MM-DD"
                                        const currentDate = new Date().toISOString().split('T')[0];

                                        // Set the max attribute to the current date
                                        document.getElementById('birthDate').setAttribute('max', currentDate);
                                    </script>
                                    </body>

                                    </html>
