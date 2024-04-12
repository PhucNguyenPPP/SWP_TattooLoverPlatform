<%@page import="DTO.StudioServiceDTO"%>
<%@page import="java.util.List"%>
<%@page import="DTO.ServiceSizeDTO"%>
<%@page import="DTO.UserDTO"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="en">
    <head>
        <meta charset="UTF-8" />
        <meta name="viewport" content="width=device-width, initial-scale=1.0" />
        <title>CHỈNH SỬA BẢNG GIÁ</title>
        <link rel="preconnect" href="https://fonts.googleapis.com">
        <link rel="preconnect" href="https://fonts.gstatic.com" crossorigin>
        <link rel="stylesheet" type="text/css" href="//fonts.googleapis.com/css?family=Quicksand" />
        <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/6.4.2/css/all.min.css">
        <link rel="stylesheet" href="Resource/css/priceListStyle.css"/>
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
                    String itemName = (String) request.getAttribute("serviceDetailName");
                    String serName = (String) request.getAttribute("serviceName");
                    List<StudioServiceDTO> list = (List<StudioServiceDTO>) request.getAttribute("EDIT_STU_SER_LIST");
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

            <h1><%=serName%> - <%=itemName%></h1>
            <h3>Vui lòng không bỏ trống bất kỳ đơn giá nào</h3>
            <!-- nhớ catch exception bị trống -->
            <table>
                <thead>
                <th>Kích thước</th>
                <th>Đơn giá (VNĐ)</th>
                </thead>
                <%
                    if (list != null) {
                %>
                <form action="MainController">


                    <%
                        List<ServiceSizeDTO> sizelist = (List<ServiceSizeDTO>) session.getAttribute("SIZE_LIST");
                        for (ServiceSizeDTO size : sizelist) {
                            boolean status = false;
                            for (StudioServiceDTO item : list) {
                                if (size.getServiceSizeID().equals(item.getServiceSizeID())) {
                                    status = true;

                    %>
                    <tr>
                        <td>
                            <%=item.getServiceSizeName()%>

                        </td>
                        <td>
                            <input type="text" name="<%=item.getServiceSizeID()%>" required="" placeholder="Ví dụ: 300000" value="<%=item.getPrice()%>"/>
                        </td>
                    </tr>
                    <%
                                break;

                            }
                        }
                        if (!status) {
                    %>
                    <tr>
                        <td>
                            <%=size.getName()%>

                        </td>
                        <td>
                            <input type="text" name="<%=size.getServiceSizeID()%>" required="" placeholder="Ví dụ: 300000"/>
                        </td>
                    </tr>
                    <%
                            }
                        }

                    %>
            </table>
            <div class="btn">
                <button type="submit" name="action" value="editPriceConfirm" class="confirm">Xác nhận</button>
            </div>
        </form>
        <%            }
        %>


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
