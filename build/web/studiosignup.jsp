<%-- 
    Document   : studiosignup
    Created on : Oct 22, 2023, 5:53:53 PM
    Author     : ACER
--%>

<<<<<<< HEAD
<%@page import="DAO.DistrictDAO"%>
=======
>>>>>>> 46860fd9c8195799510ba7b99c8b013849670722
<%@page import="DTO.UserDTO"%>
<%@page import="java.util.List"%>
<%@page import="DTO.CityDTO"%>
<%@page import="DAO.CityDAO"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html lang="en">
    <head>
        <meta charset="UTF-8">
        <meta name="viewport" content="width=device-width, initial-scale=1.0">
        <title>ĐĂNG KÍ TIỆM XĂM</title>
        <link rel="stylesheet" href="Resource/css/studioSignUpStyle.css">
        <link rel="preconnect" href="https://fonts.googleapis.com">
        <link rel="preconnect" href="https://fonts.gstatic.com" crossorigin>
        <link rel="stylesheet" type="text/css" href="//fonts.googleapis.com/css?family=Quicksand" />
        <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/6.4.2/css/all.min.css">
    </head>
    <body>
<<<<<<< HEAD
        <%
            session.setAttribute("cityList", CityDAO.getCityListStatic());
            session.setAttribute("HCMDistrictList", DistrictDAO.getDistrictListByCityID("CT001"));
            session.setAttribute("HNDistrictList", DistrictDAO.getDistrictListByCityID("CT002"));
        %>
=======
>>>>>>> 46860fd9c8195799510ba7b99c8b013849670722
        <div class="container">
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
                        }
                    %>
                </div>
                <%
                    List<CityDTO> cityList = CityDAO.getCityListStatic();
                %>
                <div class="container2" style="height: max-content;padding-bottom: 10px">
                    <div class="signUp">
                        <h2>ĐĂNG KÝ TIỆM XĂM </h2>
                        <form action="MainController" method="POST" enctype="multipart/form-data">
                            <input type="hidden" name="roleid" value="SM">
                            <div class="Username">
                                <label for="username">Tên đăng nhập</label>
                                <input type="text" id="username" name="username" required>
                            </div>
                            <p class="msg-error">${requestScope.usernameError}</p>
                            <div class="Password">
                                <label for="password">Mật khẩu</label>
                                <input type="password" id="password" name="password" required>
                            </div>
                            <div class="Confirm">
                                <label for="confirmedpassword">Xác nhận mật khẩu</label>
                                <input type="password" id="confirmedpassword" name="confirmedpassword" required>
                            </div>
                            <p class="msg-error">${requestScope.confirmPasswordError}</p>
                            <div class="studioName">
                                <label for="fullname">Họ và Tên</label>
                                <input type="text" id="fullname" name="fullname" required="">
                            </div>
                            <div class="studioName">
                                <label for="studioName">Tên studio</label>
                                <input type="text" id="studioName" name="studioName" required>
                            </div>
                            <div class="phoneNumber">
                                <label for="phone">Số điện thoại</label>
                                <input type="tel" id="phone" name="phone" required pattern="[0-9]{10}">
                            </div>
                            <p class="msg-error">${requestScope.phoneNumberError}</p>
                            <div class="Email">
                                <label for="email">Email</label>
                                <input type="email" id="email" name="email">
                            </div>
<<<<<<< HEAD
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
=======
>>>>>>> 46860fd9c8195799510ba7b99c8b013849670722
                            <p class="msg-error">${requestScope.emailError}</p>
                            <div class="City">
                                <label for="city">Thành phố</label>
                                <select id="city" name="city" required>
                                    <%
                                        for (CityDTO city : cityList) {
                                    %>
                                    <option value="<%=city.getCityID()%>"><%=city.getCityName()%></option>
                                    <%
                                        }
                                    %>
                                </select>
                            </div>
                            <div class="District">
                                <label for="district">Quận</label>
                                <select id="district" name="district" required>
                                </select>
                            </div>
                            <div class="studioName">
                                <label for="address">Địa chỉ cụ thể</label>
                                <input type="text" id="address" name="address" required>
                            </div>
                            <div class="studioName">
                                <label>Giờ mở cửa(Giờ:phút)</label>
                                <select class="hourstart" name="hourstart">
                                    <c:forEach var="hour" begin="0" end="23">
                                        <option value="${hour}">${hour}</option>
                                    </c:forEach>   
                                </select>

                                <select class="minutestart" name="minutestart">
                                    <option value="00">00</option>
                                    <option value="00">05</option>
                                    <c:forEach var="minute" begin="10" end="55" step="5">
                                        <c:set var="minuteString" value="${minute < 10 ? '0' + minute : minute}"/>
                                        <option value="${minuteString}">${minuteString}</option>
                                    </c:forEach>
                                </select>

                            </div>
                            <div class="studioName">
                                <label>Giờ đóng cửa(Giờ:phút)</label>
                                <select class="hourend" name="hourend">
                                    <c:forEach var="hour" begin="0" end="23">
                                        <option value="${hour}">${hour}</option>
                                    </c:forEach>   
                                </select>

                                <select class="minuteend" name="minuteend">
                                    <option value="00">00</option>
                                    <option value="00">05</option>
                                    <c:forEach var="minute" begin="10" end="55" step="5">
                                        <c:set var="minuteString" value="${minute < 10 ? '0' + minute : minute}"/>
                                        <option value="${minuteString}">${minuteString}</option>
                                    </c:forEach>
                                </select>

                            </div>
                            <p class="msg-error">${requestScope.timeError}</p>

                            <div class="studioName">
                                <label for="timePerSlot">Thời gian mỗi ca</label>
                                <input type="number" class="timePerSlot" name="timePerSlot" min="1" required=""> 
                            </div>
                            <div class="avatar">
                                <label for="avatar">Ảnh đại diện</label>
                                <input type="file" name="avatar" accept=".png, .jpg, .jpeg" required="">
                            </div>
                            <div class="LOGIN">
                                <input type="hidden" name="action" value="studiosignupservlet">
                                <input type="submit" value="ĐĂNG KÍ">
                                <button type="button" onclick="history.back()">HỦY</button>
                            </div>
                        </form>
                    </div>
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
<<<<<<< HEAD
                
                 const currentDate = new Date().toISOString().split('T')[0];
                document.getElementById('birthDate').setAttribute('max', currentDate);
=======
>>>>>>> 46860fd9c8195799510ba7b99c8b013849670722
            </script>
    </body>
</html>

