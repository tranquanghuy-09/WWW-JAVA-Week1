<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<!DOCTYPE html>
<html>
<head>
    <title>Login</title>
    <link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/bootstrap@4.6.2/dist/css/bootstrap.min.css">
</head>
<body>
<div class="container">
    <div class="d-flex justify-content-center align-items-center" style="height: 100vh;">
        <form class="col-5 bg-light px-5 py-5" action="ControlServlet?action=login" method="post">
            <h2 class="text-center">Login</h2>
            <div class="form-group">
                <p name="errorLogin" class="text-danger">${errorLogin}</p>
                <label for="exampleInputEmail1">User Name</label>
                <input type="text" class="form-control" id="exampleInputEmail1" name="username"
                       placeholder="Enter user name">
            </div>
            <div class="form-group mt-4">
                <label for="exampleInputPassword1">Password</label>
                <input type="password" class="form-control" id="exampleInputPassword1" placeholder="Enter password" name="password">
            </div>
            <div class="text-center mt-4">
                <button type="submit" class="btn btn-primary">Login</button>
            </div>

        </form>
    </div>
</div>
</body>
</html>