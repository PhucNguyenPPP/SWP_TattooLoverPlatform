<%-- 
    Document   : userProfile
    Created on : Oct 25, 2023, 12:00:41 AM
    Author     : ACER
--%>

<%@page import="DAO.CityDAO"%>
<%@page import="DTO.CityDTO"%>
<%@page import="java.util.List"%>
<%@page import="DAO.UserDAO"%>
<%@page import="DAO.ImgDAO"%>
<%@page import="DTO.UserDTO"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="en">
    <head>
        <meta charset="UTF-8" />
        <meta name="viewport" content="width=device-width, initial-scale=1.0" />
        <title>HOME</title>
        <link rel="stylesheet" href="Resource/css/userProfileStyle.css" />
        <link rel="preconnect" href="https://fonts.googleapis.com">
        <link rel="preconnect" href="https://fonts.gstatic.com" crossorigin>
        <link rel="stylesheet" type="text/css" href="//fonts.googleapis.com/css?family=Quicksand" />
        <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/6.4.2/css/all.min.css">
    </head>

    <body>
        <c:if test="${sessionScope.User == null}">
            <c:redirect url="HomeController">

            </c:redirect>
        </c:if>
        <%
            CityDAO cityDao = new CityDAO();
            List<CityDTO> cityList = cityDao.getCityList();
        %>
        <div class="container">
            <c:set var="us" value="${sessionScope.User}"/>
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
            <div class="profile">
                <div class="profile-left">
                    <div class="profile-left-title">Hồ sơ</div>
                    <div class="profile-left-avatar">
                        <img
                            class="profile-left-avatar-img"
                            src="${requestScope.useravatar}"
                            />
                    </div>
                </div>
                <div class="profile-right">
                    <div class="profile-right-title">Thông tin chi tiết</div>
                    <div class="profile-right-total-info">
                        <form action="MainController" method="POST">
                            <input type="hidden" name="action" value="edituserservlet">
                            <div class="profile-right-left">
                                <div class="profile-right-info">Tên tài khoản</div>
                                <!-- placeholder la data thiet khi ma co data base roi nha -->

                                <input
                                    class="input-info"
                                    type="text"
                                    name="username"
                                    placeholder="${us.userAccount}"
                                    value="${us.userAccount}"
                                    readonly=""
                                    />
                                <div class="profile-right-info">Ngày sinh</div>
                                <input
                                    class="input-info"
                                    name="birthday"
                                    type="date"
                                    placeholder="${us.birthday}"
                                    value="${us.birthday}"
                                    id="birthDate"
                                    />
                                <div class="profile-right-info">Email</div>
                                <input
                                    class="input-info"
                                    type="email"
                                    name="email"
                                    placeholder="${us.email}"
                                    value="${us.email}"
                                    />
                                <div class="profile-right-info">Giới tính</div>
                                <select class="input-info" name="gender">
                                    <option selected="" value="${us.gender}">
                                        <c:choose>
                                            <c:when test="${us.gender == 'Male'}">
                                                Nam
                                            </c:when>
                                            <c:when test="${us.gender == 'Female'}">
                                                Nữ
                                            </c:when>
                                            <c:when test="${us.gender == 'Other'}">
                                                Khác
                                            </c:when>
                                            <c:otherwise>

                                            </c:otherwise>
                                        </c:choose>
                                    </option>
                                    <c:choose>
                                        <c:when test="${us.gender == 'Male'}">
                                            <option value="Female">Nữ</option>
                                            <option value="Other">Other</option>
                                        </c:when>
                                        <c:when test="${us.gender == 'Female'}">
                                            <option value="Male">Nam</option>
                                            <option value="Other">Other</option>
                                        </c:when>
                                        <c:when test="${us.gender == 'Other'}">
                                            <option value="Male">Nam</option>
                                            <option value="Female">Nữ</option>
                                        </c:when>
                                        <c:otherwise>
                                            <option value="Nam">Nam</option>
                                            <option value="Nữ">Nữ</option>
                                            <option value="Other">Other</option>
                                        </c:otherwise>
                                    </c:choose>
                                </select>
                            </div>
                            <div class="profile-right-right">
                                <div class="profile-right-info">Họ tên</div>
                                <input
                                    value="${us.fullname}"
                                    class="input-info"
                                    type="text"
                                    name="fullname"
                                    placeholder="${us.fullname}"
                                    />

                                <div class="profile-right-info">Thành phố</div>
                                <select class="input-info" id="city" name="city" required>

                                    <%
                                        for (CityDTO city : cityList) {
                                    %>
                                    <option value="<%=city.getCityID()%>"><%=city.getCityName()%></option>
                                    <%
                                        }
                                    %>
                                </select>
                                <div class="profile-right-info">
                                    Quận
                                </div>
                                <select class="input-info" id="district" name="district" required>
                                    <option selected="" value="${us.district}">
                                        <c:choose>
                                            <c:when test="${us.city == 'CT001'}">
                                                <c:forEach var="item" items="${requestScope.HCMDistrictList}">
                                                    <c:if test="${item.districtID == us.district}">
                                                        <c:out value="${item.districtName}"/>  
                                                    </c:if>
                                                </c:forEach>
                                            </c:when>
                                            <c:otherwise>
                                                <c:forEach var="item" items="${requestScope.HNDistrictList}">
                                                    <c:if test="${item.districtID == us.district}">
                                                        <c:out value="${item.districtName}"/>  
                                                    </c:if>
                                                </c:forEach>
                                            </c:otherwise>
                                        </c:choose>
                                    </option>
                                </select>
                                <div class="profile-right-info">Số điện thoại</div>
                                <input
                                    class="input-info"
                                    type="tel"
                                    name="phoneNumber"
                                    placeholder="${us.phoneNumber}"
                                    value="${us.phoneNumber}" pattern="[0]{1}[0-9]{9}"
                                    />
                            </div>
                            <input  class="buttons-confirm" type="submit" value="CHỈNH SỬA THÔNG TIN">
                            <div class="system-msg">
                                ${requestScope.msg}
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
        </div>

        <script>
            function drop() {
                var divs = document.getElementById("subnav-content-id");
                divs.classList.toggle("show");
            }
        </script>
        <script src="app.js"></script>
        <script>
            function updateDistrictOption() {
                var selectedCity = document.getElementById("city");
                var selectDistrict = document.getElementById("district");
                var selectedCityId = selectedCity.value;
                var selectDistrictOptionsName = [];
                var selectDistrictOptionsId = [];
                switch (selectedCityId) {
                    case "CT001":
            <c:forEach var="item" items="${requestScope.HCMDistrictList}">
                        selectDistrictOptionsName.push("${item.districtName}");
                        selectDistrictOptionsId.push("${item.districtID}");
            </c:forEach>
                        break;
                    case "CT002":
            <c:forEach var="item" items="${requestScope.HNDistrictList}">
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
