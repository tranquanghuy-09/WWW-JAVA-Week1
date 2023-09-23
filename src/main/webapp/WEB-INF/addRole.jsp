<%--
  Created by IntelliJ IDEA.
  User: tranquanghuyit09
  Date: 9/22/2023
  Time: 4:00 PM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html lang="en">
<head>
    <title>Add Account</title>
    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/bootstrap@4.6.2/dist/css/bootstrap.min.css">
    <script src="https://cdn.jsdelivr.net/npm/jquery@3.6.4/dist/jquery.slim.min.js"></script>
    <script src="https://cdn.jsdelivr.net/npm/popper.js@1.16.1/dist/umd/popper.min.js"></script>
    <script src="https://cdn.jsdelivr.net/npm/bootstrap@4.6.2/dist/js/bootstrap.bundle.min.js"></script>
</head>
<body>

<div class="container">

    <form action="ControlServlet?action=addRole" method="POST" class="col-6 mx-auto mt-5 py-4 px-4 border">
        <h2 class="text-center mb-3">New Role</h2>
        <div class="form-group">
            <label for="roleId">Role Id:</label>
            <input type="text" class="form-control" id="roleId" placeholder="" name="roleId" required>
        </div>
        <div class="form-group">
            <label for="roleName">Role name:</label>
            <input type="text" class="form-control" id="roleName" placeholder="" name="roleName" required>
        </div>
        <div class="form-group">
            <label for="description">Description:</label>
            <input type="text" class="form-control" id="description" placeholder="" name="description">
        </div>
        <div class="form-group form-check">
            <label class="form-check-label">
                <input class="form-check-input" type="checkbox" name="status">Status
            </label>
        </div>
        <div class="text-center">
            <button type="submit" class="btn btn-primary">Submit</button>
            <button type="button" class="btn btn-danger ml-4">Close</button>
        </div>
    </form>
    <% Boolean resultAdd = (Boolean) session.getAttribute("resultAdd"); %>
    <% if (resultAdd != null && resultAdd) { %>
    <script>
        // alert("Thêm tài khoản thành công!");
    </script>
    <% } %>

</div>

</body>
</html>
