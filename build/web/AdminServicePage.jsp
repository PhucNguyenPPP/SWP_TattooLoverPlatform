<%-- 
    Document   : AdminServicePage
    Created on : Oct 25, 2023, 9:57:12 AM
    Author     : hieu09097248
--%>
<%@page import="DTO.UserDTO"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <meta name="viewport" content="width=device-width, initial-scale=1.0" />
        <title>DANH SÁCH DỊCH VỤ</title>
        <link rel="stylesheet" href="./Resource/css/serviceList.css" />
        <link rel="preconnect" href="https://fonts.googleapis.com" />
        <link rel="preconnect" href="https://fonts.gstatic.com" crossorigin />
        <link
            rel="stylesheet"
            type="text/css"
            href="//fonts.googleapis.com/css?family=Quicksand"
            />
        <link
            rel="stylesheet"
            href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/6.4.2/css/all.min.css"
            />
    </head>
    <body>
        <c:if test="${sessionScope.User == null || (sessionScope.User.roleId ne 'AD' && sessionScope.User.roleId ne 'SST')}">
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
                <h1 class="service-title">DANH SÁCH DỊCH VỤ</h1>
                <div class="search-bar">
                    <div class="search-bar-item">

                        <form action="MainController">
                            <button class="search-bar-btn" name="action" value="CreateService">Tạo dịch vụ</button>
                        </form>


                        <form action="MainController">
                            <input class="input-search-" type="text" placeholder="Tìm kiếm dịch vụ" name="searchService" value="${param.searchService}" />
                            <button class="search-icon-" type="submit" name="action" value="SearchService">
                                <i class="fa-solid fa-magnifying-glass"></i>
                            </button>
                        </form>


                        <!--                        <div class="search-bar-btn">
                                                    <select>
                                                        <option>Xếp theo: Tên dịch vụ</option>
                                                        <option>Xếp theo: Tên người tạo</option>
                                                        <option>Xếp theo: ID</option>
                                                    </select>
                                                </div>-->
                    </div>
                </div>
                <c:if test="${not empty requestScope.SERVICE_LIST}">
                    <table class="table-service">
                        <thead>
                        <td>ID</td>
                        <td>Tên dịch vụ</td>
                        <td>Người tạo</td>
                        <td>Xoá</td>
                        </thead>
                        <tbody>
                            <c:forEach items="${requestScope.SERVICE_LIST}" var="service">
                                <tr>
                                    <td>
                                        <input class="input input-id" type="text" name="serviceId" value="${service.getServiceID()}" readonly=""/>
                                    </td>

                                    <td>
                                        <input class="input" type="text" name="serviceName" value="${service.getName()}" readonly=""/>
                                    </td>

                                    <td>
                                        <input class="input" type="text" name="staffName" value="${service.getStaffID()}" readonly=""/>
                                    </td>
                                    <td>
                                        <form action="MainController">
                                            <input type="hidden" name="serviceID" value="${service.getServiceID()}">
                                            <button type="submit" name="action" value="DeleteServiceSystem" onclick="return confirm('Xác nhận xoá dịch vụ')" class="del-btn">
                                                <i class="fa-solid fa-trash"></i>
                                            </button>
                                        </form>
                                    </td>
                                </tr>
                            </c:forEach>
                        </tbody>
                    </table>
                </c:if>

                <!--                <div class="update">
                                    <button class="search-bar-btn">Xác nhận chỉnh sửa</button>
                                </div>-->
            </div>



            <div class="service-item">
                <h1 class="service-title">DANH SÁCH CHI TIẾT DỊCH VỤ</h1>
                <div class="search-bar">
                    <div class="search-bar-item">
                        <form action="MainController">
                            <button class="search-bar-btn" name="action" value="CreateServiceDetail">Tạo chi tiết</button>
                        </form>
                        <form action="MainController">
                            <input class="input-search-" type="text" placeholder="Tìm kiếm chi tiết dịch vụ" name="searchServiceDetail" value="${param.searchServiceDetail}" />
                            <button class="search-icon-" type="submit" name="action" value="SearchServiceDetail">
                                <i class="fa-solid fa-magnifying-glass"></i>
                            </button>
                        </form>
                        <!--                        <div class="search-bar-btn">
                                                    <select>
                                                        <option>Xếp theo: Tên chi tiết</option>
                                                        <option>Xếp theo: Tên người tạo</option>
                                                        <option>Xếp theo: ID</option>
                                                    </select>
                                                </div>-->
                    </div>
                </div>
                <c:if test="${not empty requestScope.SERVICE_LIST_DETAIL}">
                    <table class="table-service">
                        <thead>
                        <td>ID</td>
                        <td>Tên chi tiết</td>
                        <td>Người tạo</td>
                        <td>Xoá</td>
                        </thead>
                        <c:forEach items="${requestScope.SERVICE_LIST_DETAIL}" var="serviceDetail">
                            <tr>

                                <td>
                                    <input class="input input-id" type="text" name="serviceDetailId" value="${serviceDetail.getServiceDetailID()}" readonly=""/>
                                </td>

                                <td>
                                    <input class="input" type="text" name="serviceDetailName" value="${serviceDetail.getName()}" readonly=""/>
                                </td>

                                <td>
                                    <input class="input" type="text" name="staffName" value="${serviceDetail.getStaffID()}" readonly=""/>
                                </td>
                                <td>
                                    <form action="MainController">
                                        <input type="hidden" name="serviceDetailID" value="${serviceDetail.getServiceDetailID()}">
                                        <button type="submit" name="action" value="DeleteServiceDetailSystem" onclick="return confirm('Xác nhận xoá chi tiết dịch vụ')" class="del-btn">
                                            <i class="fa-solid fa-trash"></i>
                                        </button>
                                    </form>

                                </td>
                            </tr>
                        </c:forEach>
                    </table>
                </c:if>
                <!--                <div class="update">
                                    <button class="search-bar-btn">Xác nhận chỉnh sửa</button>
                                </div>-->
            </div>


            <div class="service-item">
                <h1 class="service-title">DANH SÁCH KÍCH THƯỚC</h1>
                <div class="search-bar">
                    <div class="search-bar-item">
                        <form action="MainController">
                            <button class="search-bar-btn" name="action" value="CreateServiceSize">Tạo kích thước</button>
                        </form>
                        <form action="MainController">
                            <input class="input-search-" type="text" placeholder="Tìm kiếm dịch vụ" name="searchServiceSize" value="${param.searchServiceSize}" />
                            <button class="search-icon-" type="submit" name="action" value="SearchServiceSize">
                                <i class="fa-solid fa-magnifying-glass"></i>
                            </button>
                        </form>
                        <!--                        <div class="search-bar-btn">
                                                    <select>
                                                        <option>Xếp theo: Tên người tạo</option>
                                                        <option>Xếp theo: ID</option>
                                                    </select>
                                                </div>-->
                    </div>
                </div>
                <c:if test="${not empty requestScope.SERVICE_SIZE}">
                    <table class="table-service">
                        <thead>
                        <td>ID</td>
                        <td>Kích thước</td>
                        <td>Người tạo</td>
                        <td>Xoá</td>
                        </thead>
                        <c:forEach items="${requestScope.SERVICE_SIZE}" var="serviceSize">
                            <tr>

                                <td>
                                    <input class="input input-id" type="text" name="serviceDetailId" value="${serviceSize.getServiceSizeID()}" readonly=""/>
                                </td>

                                <td>
                                    <input class="input" type="text" name="serviceDetailName" value="${serviceSize.getName()}" readonly=""/>
                                </td>

                                <td>
                                    <input class="input" type="text" name="staffName" value="${serviceSize.getStaffID()}" readonly=""/>
                                </td>
                                <td>
                                    <form action="MainController">
                                        <input type="hidden" name="serviceSizeID" value="${serviceSize.getServiceSizeID()}">
                                        <button type="submit" name="action" value="DeleteServiceSizeSystem" onclick="return confirm('Xác nhận xoá kích thước')" class="del-btn">
                                            <i class="fa-solid fa-trash"></i>
                                        </button>
                                    </form>
                                </td>
                            </tr>
                        </c:forEach>
                    </table>
                </c:if>
                <!--                <div class="update">
                                    <button class="search-bar-btn">Xác nhận chỉnh sửa</button>
                                </div>-->
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

