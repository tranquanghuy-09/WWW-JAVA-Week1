package vn.edu.iuh.fit.controllers;


import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import org.jetbrains.annotations.NotNull;
import vn.edu.iuh.fit.entities.Account;
import vn.edu.iuh.fit.entities.GrantAccess;
import vn.edu.iuh.fit.services.AccountService;
import vn.edu.iuh.fit.services.GrantAccessService;

import java.io.IOException;
import java.util.List;
import java.util.Optional;

@WebServlet("/ControlServlet")
public class ControllerServlet extends HttpServlet {
    private static final AccountService accountService = new AccountService();
    private static final GrantAccessService grantAccessService = new GrantAccessService();

    protected void doGet(@NotNull HttpServletRequest request, HttpServletResponse response)
            throws IOException, ServletException {
        // Xử lý yêu cầu GET
        String action = request.getParameter("action");
        switch (action) {
            case "dashboard":
                HttpSession httpSession = request.getSession(true);
//                httpSession.invalidate();
//                httpSession = request.getSession(true);

                List<Account> accountList = accountService.getAll();
                System.out.println(accountList);
                httpSession.setAttribute("accountList",accountList);
                httpSession.setAttribute("activeHome","active");
                httpSession.setAttribute("activeMenu1","");
                httpSession.setAttribute("accountLogin", httpSession.getAttribute("accountLogin"));
                httpSession.setAttribute("roleName", httpSession.getAttribute("roleName"));
                RequestDispatcher requestDispatcher = request.getRequestDispatcher("/WEB-INF/dashboard.jsp");
                requestDispatcher.forward(request,response);

                break;
            case "dashboard_user":
                HttpSession httpSession11 = request.getSession(true);
                httpSession11.setAttribute("accountLogin", httpSession11.getAttribute("accountLogin"));
                httpSession11.setAttribute("roleName", httpSession11.getAttribute("roleName"));
                RequestDispatcher requestDispatcher11 = request.getRequestDispatcher("/WEB-INF/dashboard_user.jsp");
                requestDispatcher11.forward(request,response);

                break;
            case "managerAccount":
                List<Account> accountList2 = accountService.getAll();
                HttpSession httpSession2 = request.getSession(true);
                httpSession2.invalidate();

                httpSession2 = request.getSession(true);
                httpSession2.setAttribute("accountList",accountList2);
                httpSession2.setAttribute("activeHome","");
                httpSession2.setAttribute("activeMenu1","active");
                RequestDispatcher requestDispatcher2 = request.getRequestDispatcher("/WEB-INF/dashboard.jsp");
                requestDispatcher2.forward(request,response);
                break;
            case "addAccount":
                RequestDispatcher requestDispatcher3 = request.getRequestDispatcher("/WEB-INF/addAccount.jsp");
                requestDispatcher3.forward(request,response);
                break;
            case "updateAccount":
                HttpSession httpSession3 = request.getSession(true);

                String id = request.getParameter("id");
                System.out.println(id);
                Optional<Account> optionalAccount = accountService.findAccount(id);
                if(optionalAccount.isPresent()){
                    Account account2 = optionalAccount.get();
                    httpSession3.setAttribute("accountUpDate",account2);
                    System.out.println(account2);
                    RequestDispatcher requestDispatcher4 = request.getRequestDispatcher("/WEB-INF/updateAccount.jsp");
                    requestDispatcher4.forward(request,response);
                }else{
//                    System.out.println("khong tim thay account");
                }
                break;
        }
    }
    private void logoutAccount(@NotNull HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
        HttpSession session = request.getSession(true);
        session.invalidate();
        response.sendRedirect("index.jsp");
    }


    protected void doPost(@NotNull HttpServletRequest request, HttpServletResponse response)
            throws IOException, ServletException {
        String action = request.getParameter("action");
        switch (action) {
            case "login":
                loginAccount(request, response);
            break;
            case "logout":
                logoutAccount(request, response);
                break;
            case "addAccount":
                String accountId = request.getParameter("accountId");
                String fullName = request.getParameter("fullName");
                String email = request.getParameter("email");
                String password2 = request.getParameter("pwd");
                String phone = request.getParameter("phone");
                String status = request.getParameter("status");
                byte temp;
                if (status != null) {
                    temp = 1;
                } else {
                    temp = 0;
                }

                Account account1 = new Account(accountId, fullName, password2, email, phone, temp);
                System.out.println(account1);
                boolean b = accountService.addAccount(account1);
                HttpSession httpSession3 = request.getSession(true);
                httpSession3.invalidate();

                httpSession3 = request.getSession(true);
                httpSession3.setAttribute("resultAdd", b);
                response.sendRedirect("ControlServlet?action=managerAccount");
                break;
            case "updateAccount":
                String accountId2 = request.getParameter("accountId");
                String fullName2 = request.getParameter("fullName");
                String email2 = request.getParameter("email");
                String pass = request.getParameter("pwd");
                String phone2 = request.getParameter("phone");
                String status2 = request.getParameter("status");
                byte temp2;
                if (status2 != null) {
                    temp2 = 1;
                } else {
                    temp2 = 0;
                }

                Account account2 = new Account(accountId2, fullName2, pass, email2, phone2, temp2);
                System.out.println(account2);
                accountService.updateAccount(account2);
                response.sendRedirect("ControlServlet?action=managerAccount");

                break;
            case "deleteAccount":
                System.out.println("run");
                deleteAccount(request,response);
                break;
            case "updateAccount_User":
                updateAccountUser(request,response);
                break;
        }
    }

    private void deleteAccount(@NotNull HttpServletRequest request, HttpServletResponse response) throws IOException{
        String id = request.getParameter("id");
        Optional<Boolean> b = accountService.deleteAccountById(id);
        System.out.println("b");
        if(b.isPresent()){
            response.sendRedirect("ControlServlet?action=managerAccount");
        }
    }

    private void loginAccount(@NotNull HttpServletRequest request, HttpServletResponse response) throws IOException{
        String userName = request.getParameter("username");
        String password = request.getParameter("password");
        Optional<Account> optionalAccount = accountService.checkLogin(userName, password);
        if (optionalAccount.isEmpty()) {
            HttpSession session = request.getSession(true);
            session.setAttribute("errorLogin", "Wrong username or password");
            session.setAttribute("username", "");
            session.setAttribute("password", "");
            response.sendRedirect("index.jsp");

        }else{
            Account account = optionalAccount.get();
            Optional<GrantAccess> opGrantAccess = grantAccessService.findGrantAccessByAccountId(account.getId());
            GrantAccess grantAccess = opGrantAccess.get();
            String roleName = grantAccess.getRole().getName();
            if(roleName.compareToIgnoreCase("administrator") == 0){
                HttpSession httpSession = request.getSession(true);
                httpSession.invalidate();

                httpSession = request.getSession(true);
                httpSession.setAttribute("accountLogin", account);
                httpSession.setAttribute("roleName", roleName);

                response.sendRedirect("ControlServlet?action=dashboard");
            }else{
                HttpSession httpSession = request.getSession(true);
                httpSession.invalidate();

                httpSession = request.getSession(true);
                httpSession.setAttribute("accountLogin", account);
                httpSession.setAttribute("roleName", roleName);

                response.sendRedirect("ControlServlet?action=dashboard_user");
            }


        }
    }

    private void updateAccountUser(@NotNull HttpServletRequest request, HttpServletResponse response) throws IOException{
        String accountId = request.getParameter("accountId");
        String fullName = request.getParameter("fullName");
        String email = request.getParameter("email");
        String pass = request.getParameter("pwd");
        String phone = request.getParameter("phone");
        byte status= 1;

        Account account = new Account(accountId, fullName, pass, email, phone, status);
        System.out.println(account);
        accountService.updateAccount(account);
        response.sendRedirect("ControlServlet?action=dashboard_user");
    }

}


