import java.io.IOException;
import java.sql.SQLException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import appConstant.ViewPages;
import service.ProductDao;

@WebServlet(asyncSupported = true, urlPatterns = { "/Login" })
public class CustomerLogin extends HttpServlet {
    private static final long serialVersionUID = 1L;
    private ProductDao dao;

    @Override
    public void init() throws ServletException {
        super.init();
        dao = new ProductDao();
    }

    public CustomerLogin() {
        super();
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        request.getRequestDispatcher(ViewPages.LOGIN_PAGE).forward(request, response);
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String loginForm = request.getParameter("submit");
        if (loginForm != null) {
            String username = request.getParameter("username");
            String password = request.getParameter("password");
            try {
                if (username.equals("admin") && password.equals("admin123")) {
                    HttpSession session = request.getSession(true); // Create session if not exists
                    session.setAttribute("username", username);
                    session.setAttribute("role", "admin");
                    session.setMaxInactiveInterval(30 * 60); // 30 minutes in seconds

                    // Store user info in cookies
                    Cookie usernameCookie = new Cookie("username", username);
                    Cookie roleCookie = new Cookie("role", "admin");
                    usernameCookie.setMaxAge(30 * 60); // 30 minutes in seconds
                    roleCookie.setMaxAge(30 * 60); // 30 minutes in seconds
                    response.addCookie(usernameCookie);
                    response.addCookie(roleCookie);

                    response.sendRedirect(request.getContextPath() + "/admin");
                } else {
                    boolean isSuccess = dao.studentLogin(username, password);
                    if (isSuccess) {
                        HttpSession session = request.getSession(true); // Create session if not exists
                        session.setAttribute("username", username);
                        session.setAttribute("role", "user");
                        session.setMaxInactiveInterval(30 * 60); // 30 minutes in seconds

                        // Store user info in cookies
                        Cookie usernameCookie = new Cookie("username", username);
                        Cookie roleCookie = new Cookie("role", "user");
                        usernameCookie.setMaxAge(30 * 60); // 30 minutes in seconds
                        roleCookie.setMaxAge(30 * 60); // 30 minutes in seconds
                        response.addCookie(usernameCookie);
                        response.addCookie(roleCookie);

                        response.sendRedirect(request.getContextPath() + "/profile");
                    } else {
                        request.setAttribute("error", "invalid username or password");
                        doGet(request, response);
                    }
                }
            } catch (SQLException e) {
                e.printStackTrace();
            }
        } else {
            doGet(request, response);
        }
    }
}
