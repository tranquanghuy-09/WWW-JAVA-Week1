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
                httpSession.invalidate();
                httpSession = request.getSession(true);

                List<Account> accountList = accountService.getAll();
                System.out.println(accountList);
                httpSession.setAttribute("accountList",accountList);
                httpSession.setAttribute("activeHome","active");
                httpSession.setAttribute("activeMenu1","");
                RequestDispatcher requestDispatcher = request.getRequestDispatcher("/WEB-INF/dashboard.jsp");
                requestDispatcher.forward(request,response);

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



    protected void doPost(@NotNull HttpServletRequest request, HttpServletResponse response)
            throws IOException {
        String action = request.getParameter("action");
        switch (action) {
            case "login":
                String userName = request.getParameter("username");
                String password = request.getParameter("password");
                Optional<Account> account = accountService.checkLogin(userName, password);
                System.out.println(account);
                if (account.isEmpty()) {
                    HttpSession session = request.getSession(true);
                    session.setAttribute("errorLogin", "Wrong username or password");
                    session.setAttribute("username", "");
                    session.setAttribute("password", "");
                    response.sendRedirect("index.jsp");

                }else{
                    HttpSession httpSession = request.getSession(true);
                    httpSession.invalidate();

                    httpSession = request.getSession(true);
                    httpSession.setAttribute("account", account.toString());
                    response.sendRedirect("ControlServlet?action=dashboard");

                }
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

}


