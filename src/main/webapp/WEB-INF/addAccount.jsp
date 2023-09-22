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

    <form action="ControlServlet?action=addAccount" method="POST" class="col-6 mx-auto mt-5 py-4 px-4 border">
        <h2 class="text-center mb-3">New Account</h2>
        <div class="form-group">
            <label for="accountId">Account Id:</label>
            <input type="text" class="form-control" id="accountId" placeholder="" name="accountId" required>
        </div>
        <div class="form-group">
            <label for="fullName">Full name:</label>
            <input type="text" class="form-control" id="fullName" placeholder="" name="fullName" required>
        </div>
        <div class="form-group">
            <label for="email">Email:</label>
            <input type="email" class="form-control" id="email" placeholder="" name="email">
        </div>
        <div class="form-group">
            <label for="pwd">Password:</label>
            <input type="password" class="form-control" id="pwd" placeholder="" name="pwd" required>
        </div>
        <div class="form-group">
            <label for="phone">Phone:</label>
            <input type="text" class="form-control" id="phone" placeholder="" name="phone">
        </div>
        <div class="form-group form-check">
            <label class="form-check-label">
                <input class="form-check-input" type="checkbox" name="status"> Status.
            </label>
        </div>
        <div class="text-center">
            <button type="submit" class="btn btn-primary ">Submit</button>
            <button type="button" class="btn btn-danger ml-4">Close</button>
        </div>
    </form>
    <% Boolean resultAdd = (Boolean) session.getAttribute("resultAdd"); %>
    <% if (resultAdd != null && resultAdd) { %>
    <script>
        alert("Thêm tài khoản thành công!");
    </script>
    <% } %>

</div>

</body>
</html>
