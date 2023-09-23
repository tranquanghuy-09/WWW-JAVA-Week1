<%--
  Created by IntelliJ IDEA.
  User: tranquanghuyit09
  Date: 9/23/2023
  Time: 1:19 AM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%--
  Created by IntelliJ IDEA.
  User: tranquanghuyit09
  Date: 9/21/2023
  Time: 6:27 PM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>

<%@ page import="vn.edu.iuh.fit.entities.Account" %>
<%@ page import="vn.edu.iuh.fit.entities.Role" %>
<%@ page import="java.util.List" %>
<% String activeMenu1 = (String) session.getAttribute("activeMenu1"); %>
<% Account accountLogin = (Account) session.getAttribute("accountLogin");%>
<% Role role = (Role) session.getAttribute("role");%>
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
            <a class="nav-link ${activeMenu1}" data-toggle="tab" href="#menu1" id="menu1Tab">Thông tin Account</a>
        </li>
        <li class="nav-item">
            <a class="nav-link" data-toggle="tab" href="#menu2">Thông tin Role</a>
        </li>
        <li class="nav-item">
            <form action="ControlServlet?action=logout" method="post">
                <button type="submit" class="nav-link">Đăng xuất</button>
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
            <div class="container">
                <form action="ControlServlet?action=updateAccount_User" method="POST" class="col-6 mx-auto py-4 px-4 border">
                    <h2 class="text-center mb-3">Thông tin Account</h2>
                    <div class="form-group">
                        <label for="accountId">Account Id:</label>
                        <input type="text" class="form-control" id="accountId" placeholder="" name="accountId" readonly  value="<%= accountLogin.getId() %>">
                    </div>
                    <div class="form-group">
                        <label for="fullName">Full name:</label>
                        <input type="text" class="form-control" id="fullName" placeholder="" name="fullName" value="<%= accountLogin.getFullName() %>">
                    </div>
                    <div class="form-group">
                        <label for="email">Email:</label>
                        <input type="email" class="form-control" id="email" placeholder="" name="email" value="<%= accountLogin.getEmail() %>">
                    </div>
                    <div class="form-group">
                        <label for="pwd">Password:</label>
                        <input type="text" class="form-control" id="pwd" placeholder="" name="pwd" value="<%= accountLogin.getPassword()%>">
                    </div>
                    <div class="form-group">
                        <label for="phone">Phone:</label>
                        <input type="text" class="form-control" id="phone" placeholder="" name="phone" value="<%= accountLogin.getPhone() %>">
                    </div>
                    <div class="text-center">
                        <button type="submit" class="btn btn-primary ">Update</button>
                        <button type="button" class="btn btn-danger ml-4">Close</button>
                    </div>
                </form>
            </div>
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
                </tr>
                </thead>
                <tbody>

                <tr>
                    <td contenteditable="true"><%= role.getId()%></td>
                    <td contenteditable="true"><%= role.getName() %></td>
                    <td contenteditable="true"><%= role.getDescription()%></td>
                    <td contenteditable="true"><%= role.getStatus() %></td>
                </tr>


                </tbody>
            </table>
        </div>
    </div>
</div>

</body>

</html>