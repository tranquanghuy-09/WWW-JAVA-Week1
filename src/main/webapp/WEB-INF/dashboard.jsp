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
<% String activeMenu1 = (String) session.getAttribute("activeMenu1"); %>
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
            <a class="nav-link" data-toggle="tab" href="#menu2">Quản lý Role</a>
        </li>
        <li class="nav-item">
            <a class="nav-link" data-toggle="tab" href="#menu2">Quản lý Log</a>
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
            <p>${accountLogin}</p>
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
                    <th>AccountID</th>
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
            <h3>Menu 2</h3>
            <p>Sed ut perspiciatis unde omnis iste natus error sit voluptatem accusantium doloremque laudantium,
                totam rem aperiam.</p>
        </div>
    </div>
</div>

<!-- The Modal -->
<div class="modal" id="myModal">
    <div class="modal-dialog">
        <div class="modal-content">

            <!-- Modal Header -->
            <div class="modal-header">
                <h4 class="modal-title">Create Account</h4>
                <button type="button" class="close" data-dismiss="modal">&times;</button>
            </div>

            <!-- Modal body -->
            <div class="modal-body">
                <form action="/action_page.php">
                    <div class="form-group">
                        <label for="accountId">Account Id:</label>
                        <input type="text" class="form-control" id="accountId" placeholder="" name="accountId">
                    </div>
                    <div class="form-group">
                        <label for="fullName">Full name:</label>
                        <input type="text" class="form-control" id="fullName" placeholder="" name="fullName">
                    </div>
                    <div class="form-group">
                        <label for="email">Email:</label>
                        <input type="email" class="form-control" id="email" placeholder="" name="email">
                    </div>
                    <div class="form-group">
                        <label for="pwd">Password:</label>
                        <input type="password" class="form-control" id="pwd" placeholder="" name="pswd">
                    </div>
                    <div class="form-group">
                        <label for="phone">Phone:</label>
                        <input type="text" class="form-control" id="phone" placeholder="" name="phone">
                    </div>
                    <div class="form-group form-check">
                        <label class="form-check-label">
                            <input class="form-check-input" type="checkbox" name="status" required> Status.
                        </label>
                    </div>

                </form>
            </div>

            <!-- Modal footer -->
            <div class="modal-footer">
                <button type="submit" class="btn btn-primary">Submit</button>
                <!-- <button type="button" class="btn btn-danger" data-dismiss="modal">Close</button> -->
            </div>

        </div>
    </div>
</div>
<script>
    var temp = '<%= accountLogin %>';
    console.log(temp);
</script>
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