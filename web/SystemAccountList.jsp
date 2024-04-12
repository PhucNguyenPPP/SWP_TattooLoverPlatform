<%-- 
    Document   : SystemAccountList
    Created on : Oct 28, 2023, 9:00:46 PM
    Author     : ASUS
--%>


<%@page import="DTO.UserDTO"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <meta charset="UTF-8" />
        <meta name="viewport" content="width=device-width, initial-scale=1.0" />
        <title>DANH SÁCH TÀI KHOẢN</title>
        <link rel="stylesheet" href="Resource/css/stuAccountListStyle.css" />
        <link rel="preconnect" href="https://fonts.googleapis.com">
        <link rel="preconnect" href="https://fonts.gstatic.com" crossorigin>
        <link rel="stylesheet" type="text/css" href="//fonts.googleapis.com/css?family=Quicksand" />
        <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/6.4.2/css/all.min.css">
    </head>
    <body>
        <c:if test="${sessionScope.User == null || (sessionScope.User.roleId ne 'AD' && sessionScope.User.roleId ne 'SST')}">
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
                    if (us != null && us.getRoleId().equals("AD")) {
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


            <div class="service-item">

                <h1 class="service-title">DANH SÁCH TÀI KHOẢN</h1>
                <div class="search-bar">
                    <div class="search-bar-item"> 
                        <a href="MainController?action=signup">
                            <button class="search-bar-btn">Đăng ký nhân viên</button>
                        </a>

                        <form action="MainController">
                            <input class="input-search-" type="text" placeholder="Tìm kiếm tài khoản" name="search" value="${param.search}" />

                            <button class="search-icon-" type="submit" name="action" value="SearchAccountSystem">
                                <i class="fa-solid fa-magnifying-glass"></i>
                            </button>
                        </form>    
                        <!--                        <div class="search-bar-btn">
                                                    <select>
                                                        <option>Xếp theo: Tên đăng nhập</option>
                                                        <option>Xếp theo: Họ và tên</option>
                                                        <option>Xếp theo: ID</option>
                                                    </select>
                                                </div>-->
                    </div>
                </div>
                <c:if test="${not empty requestScope.ACCOUNT_LIST}">
                    <table class="table-service">
                        <thead>
                        <th>ID</th>
                        <th>Tên đăng nhập</th>
                        <th>Họ và tên</th>
                        <th>Ngày sinh</th>
                        <th>Giới tính</th>
                        <th>Vai trò</th>
                        <th>Số điện thoại</th>
                        <th>Quận</th>
                        <th>Thành phố</th>
                        <th>Xoá</th>
                        </thead>
                        <tbody>
                            <c:forEach items="${requestScope.ACCOUNT_LIST}" var="account">
                                <tr>
                                    <td>
                                        <input class="input input-id" type="text" name="userID" value="${account.id}" readonly=""/>
                                    </td>

                                    <td>
                                        <input class="input" type="text" name="userAccount" value="${account.userAccount}" readonly=""/>
                                    </td>

                                    <td>
                                        <input class="input" type="text" name="fullName" value="${account.fullname}" readonly=""/>
                                    </td>

                                    <td>
                                        <input class="input" type="text" name="birthday" value="${account.birthday}" readonly=""/>
                                    </td>


                                    <c:choose>
                                        <c:when test="${account.gender == 'Male'}">
                                            <td>
                                                <input class="input" type="text" name="gender" value="Nam" readonly=""/>
                                            </td>
                                        </c:when>
                                        <c:when test="${account.gender == 'Female'}">
                                            <td>
                                                <input class="input" type="text" name="gender" value="Nữ" readonly=""/>
                                            </td>
                                        </c:when>
                                        <c:otherwise>
                                            <td>
                                                <input class="input" type="text" name="gender" value="Khác" readonly=""/>
                                            </td>
                                        </c:otherwise>
                                    </c:choose>

                                    <td>
                                        <input class="input" type="text" name="roleId" value="${account.roleId}" readonly=""/>
                                    </td>

                                    <td>
                                        <input class="input" type="text" name="phone" value="${account.phoneNumber}" readonly=""/>
                                    </td>         

                                    <td>
                                        <input class="input" type="text" name="district" value="${account.district}" readonly=""/>
                                    </td>

                                    <td>
                                        <input class="input" type="text" name="city" value="${account.city}" readonly=""/>
                                    </td>

                                    <td>
                                        <c:choose>
                                            <c:when test="${account.roleId ne 'AD' && account.roleId ne 'SM'}">
                                                <form action="MainController" method="POST">
                                                    <input type="hidden" name="action" value="DeleteUser">
                                                    <input type="hidden" name="userId" value="${account.id}">
                                                    <input type="hidden" name="roleId" value="${account.roleId}">
                                                    <button type="submit" onclick="return confirm('Xác nhận xoá tài khoản')">
                                                        <i class="fa-solid fa-trash"></i>
                                                    </button>
                                                </form>
                                            </c:when>
                                            <c:when test="${account.roleId ne 'AD' && account.roleId eq 'SM'}">
                                                <form action="MainController" method="POST">
                                                    <input type="hidden" name="action" value="DeleteUserManager">
                                                    <input type="hidden" name="userId" value="${account.id}">
                                                    <input type="hidden" name="roleId" value="${account.roleId}">
                                                    <button type="submit" onclick="return confirm('Xác nhận xoá tài khoản')">
                                                        <i class="fa-solid fa-trash"></i>
                                                    </button>
                                                </form>
                                            </c:when>
                                        </c:choose>
                                    </td>
                                </tr>
                            </tbody>
                        </c:forEach>
                    </table>
                </c:if>

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
