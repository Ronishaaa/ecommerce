package controller;

import java.io.IOException;
import java.sql.SQLException;

import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import appConstant.ViewPages;
import service.ProductDao;

/**
 * Servlet implementation class CustomerLogin
 */
@WebServlet(asyncSupported = true, urlPatterns = { "/Login" })
public class CustomerLogin extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private ProductDao dao; 
	
	
	@Override
	public void init(ServletConfig config) throws ServletException {
		// TODO Auto-generated method stub
		super.init(config);
		dao = new ProductDao();
	}
    /**
     * @see HttpServlet#HttpServlet()
     */
    public CustomerLogin() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		request.getRequestDispatcher(ViewPages.LOGIN_PAGE).forward(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub

		
		String loginForm = request.getParameter("submit");
		if (loginForm != null) {

			String username = request.getParameter("username");
			String password = request.getParameter("password");
			try {
				if (username.equals("admin") && password.equals("admin123")) {
					HttpSession session = request.getSession();
					session.setAttribute("username", username);
					session.setAttribute("role", "admin");
					session.setMaxInactiveInterval(5 * 60);
					response.sendRedirect(request.getContextPath()+"/admin");
				}

				else {
					boolean isSuccess = dao.studentLogin(username, password);
					if (isSuccess) {
						HttpSession session = request.getSession();
						session.setAttribute("username", username);
						session.setAttribute("role", "user");
						session.setMaxInactiveInterval(5 * 60);
						response.sendRedirect(request.getContextPath()+"/profile");
					} else {
						request.setAttribute("error", "invalid username or password");
						doGet(request, response);
					}
				}
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}

		} else {

			doGet(request, response);
		}

	}
	}


