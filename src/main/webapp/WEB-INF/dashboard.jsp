<%--
  Created by IntelliJ IDEA.
  User: tranquanghuyit09
  Date: 9/21/2023
  Time: 6:27 PM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>

<%@ page import="vn.edu.iuh.fit.entities.Account" %>
<%@ page import="java.util.List" %>
<%@ page import="vn.edu.iuh.fit.entities.Role" %>
<%@ page import="vn.edu.iuh.fit.entities.Log" %>
<% String activeMenu1 = (String) session.getAttribute("activeMenu1"); %>
<% String activeMenu2 = (String) session.getAttribute("activeMenu2"); %>
<% Account accountLogin = (Account) session.getAttribute("accountLogin");%>
<% String roleName = (String) session.getAttribute("roleName"); %>

<!DOCTYPE html>
<html lang="en">

<head>
    <title>Bootstrap Example</title>
    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/bootstrap@4.6.2/dist/css/bootstrap.min.css">
    <script src="https://cdn.jsdelivr.net/npm/jquery@3.6.4/dist/jquery.slim.min.js"></script>
    <script src="https://cdn.jsdelivr.net/npm/popper.js@1.16.1/dist/umd/popper.min.js"></script>
    <script src="https://cdn.jsdelivr.net/npm/bootstrap@4.6.2/dist/js/bootstrap.bundle.min.js"></script>
    <!-- Thêm thư viện DataTables từ CDN -->
    <link rel="stylesheet" href="https://cdn.datatables.net/1.11.5/css/jquery.dataTables.min.css">
    <script src="https://cdn.datatables.net/1.11.5/js/jquery.dataTables.min.js"></script>
    <script src="https://code.jquery.com/jquery-3.6.0.min.js"></script>

</head>

<body>
<br>

<div class="container">
    <!-- Nav tabs -->
    <ul class="nav nav-tabs" role="tablist">
        <li class="nav-item">
            <a class="nav-link ${activeHome}" data-toggle="tab" href="#home">Home</a>
        </li>
        <li class="nav-item">
            <a class="nav-link ${activeMenu1}" data-toggle="tab" href="#menu1" id="menu1Tab">Quản lý Account</a>
        </li>
        <li class="nav-item">
            <a class="nav-link ${activeMenu2}" data-toggle="tab" href="#menu2" id="menu2Tab">Quản lý Role</a>
        </li>
        <li class="nav-item">
            <a class="nav-link" data-toggle="tab" href="#menu3">Quản lý Log</a>
        </li>
        <li class="nav-item">
            <form action="ControlServlet?action=logout" method="post">
                <button type="submit" class="nav-link">Đăng Xuất</button>
            </form>
        </li>
    </ul>

    <!-- Tab panes -->
    <div class="tab-content">
        <div id="home" class="container tab-pane active"><br>
            <h3>Xin chào ${sessionScope.roleName} <%= accountLogin.getFullName() %></h3>
<%--            <p>${accountLogin}</p>--%>
        </div>
        <div id="menu1" class="container tab-pane fade"><br>
            <div class="">
                <a href="ControlServlet?action=addAccount">
                    <button id="creatbtn" class="btn btn-primary mb-3" type="">Create Account</button>
                </a>

            </div>
            <table class="table table-bordered">
                <thead>
                <tr>
                    <th>Account ID</th>
                    <th>FullName</th>
                    <th>Email</th>
                    <th >Password</th>
                    <th>Phone</th>
                    <th >Status</th>
                    <th>Function</th>
                </tr>
                </thead>
                <tbody>

                <% for (Account a : (List<Account>) session.getAttribute("accountList")) { %>
                <tr>
                    <td contenteditable="true"><%= a.getId()%></td>
                    <td contenteditable="true"><%= a.getFullName() %></td>
                    <td contenteditable="true"><%= a.getEmail()%></td>
                    <td contenteditable="true"><%= a.getPassword() %></td>
                    <td contenteditable="true"><%= a.getPhone() %></td>
                    <td contenteditable="true"><%= a.getStatus() %></td>
                    <td class="text-center d-flex align-items-center">
                        <a href="ControlServlet?action=updateAccount&id=<%= a.getId()%>">
                            <button class="btn btn-info">Update</button>
                        </a>
                        <form action="ControlServlet?action=deleteAccount&id=<%= a.getId() %>" method="post">
                            <button type="submit" class="btn btn-danger ml-2">Delete</button>
                        </form>
                    </td>
                </tr>
                <% } %>


                </tbody>
            </table>
        </div>

        <div id="menu2" class="container tab-pane fade"><br>
            <div class="">
                <a href="ControlServlet?action=addRole">
                    <button id="creatRole" class="btn btn-primary mb-3" type="">Create Role</button>
                </a>

            </div>
            <table class="table table-bordered">
                <thead>
                <tr>
                    <th>Role ID</th>
                    <th>Role Name</th>
                    <th>Description</th>
                    <th>Status</th>
                    <th>Function</th>
                </tr>
                </thead>
                <tbody>

                <% for (Role role : (List<Role>) session.getAttribute("roleList")) { %>
                <tr>
                    <td contenteditable="true"><%= role.getId()%></td>
                    <td contenteditable="true"><%= role.getName() %></td>
                    <td contenteditable="true"><%= role.getDescription()%></td>
                    <td contenteditable="true"><%= role.getStatus() %></td>
                    <td class="text-center d-flex align-items-center">
                        <a href="ControlServlet?action=updateRole&id=<%= role.getId()%>">
                            <button class="btn btn-info">Update</button>
                        </a>
                        <form action="ControlServlet?action=deleteRole&id=<%= role.getId() %>" method="post">
                            <button type="submit" class="btn btn-danger ml-2">Delete</button>
                        </form>
                    </td>
                </tr>
                <% } %>


                </tbody>
            </table>
        </div>

        <div id="menu3" class="container tab-pane fade"><br>
            <table class="table table-bordered">
                <thead>
                <tr>
                    <th>STT</th>
                    <th>Account ID</th>
                    <th>Login Time</th>
                    <th>Logout Time</th>
                    <th>Notes</th>
                </tr>
                </thead>
                <tbody>

                <% for (Log log : (List<Log>) session.getAttribute("logList")) { %>
                <tr>
                    <td contenteditable="true"><%= log.getId()%></td>
                    <td contenteditable="true"><%= log.getAccountId()%></td>
                    <td contenteditable="true"><%= log.getLoginTime()%></td>
                    <td contenteditable="true"><%= log.getLogoutTime()%></td>
                    <td contenteditable="true"><%= log.getNotes()%></td>
                </tr>
                <% } %>


                </tbody>
            </table>
        </div>
    </div>
</div>

<script>
    document.addEventListener('DOMContentLoaded', function() {
        // Kích hoạt tab "Quản lý Account" ngay khi trang được tải
        var activeMenu1Value = '<%= activeMenu1 %>';
        if(activeMenu1Value == "active"){
            var menu1Tab = document.getElementById('menu1Tab');
            menu1Tab.click();
            console.log(activeMenu1Value);
            // Hiển thị nội dung của tab "Quản lý Account" và ẩn các tab khác
            var tabContent = document.querySelectorAll('.tab-pane');
            tabContent.forEach(function(tab) {
                if (tab.id === 'menu1') {
                    tab.classList.add('show', 'active');
                } else {
                    tab.classList.remove('show', 'active');
                }
            });
        }else
            console.log("Ko chayj if");
        var activeMenu2Value = '<%= activeMenu2 %>';
        if(activeMenu2Value == "active"){
            var menu2Tab = document.getElementById('menu2Tab');
            menu2Tab.click();
            console.log(activeMenu2Value);
            // Hiển thị nội dung của tab "Quản lý Account" và ẩn các tab khác
            var tabContent = document.querySelectorAll('.tab-pane');
            tabContent.forEach(function(tab) {
                if (tab.id === 'menu2') {
                    tab.classList.add('show', 'active');
                } else {
                    tab.classList.remove('show', 'active');
                }
            });
        }else
            console.log("Ko chay if");
    });
</script>
<script>
    $(document).ready(function () {
        $('#editableTable').DataTable();
    });
</script>
<script>

    function redirectToManagerAccount() {
        window.location.href = "ControlServlet?action=managerAccount";
    }
    function showResultAddAccount(){
        alert(${resultAdd});
    }
</script>

</body>

</html>