

import java.io.IOException;

import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import dao.UsersDAO;
import dto.Users;

/**
 * Servlet implementation class Index
 */
@WebServlet("/")
public class DaoTest extends HttpServlet {
	private static final long serialVersionUID = 1L;
	  
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String view = "WEB-INF/views/index.jsp";
		RequestDispatcher dispatch = request.getRequestDispatcher(view);
		dispatch.forward(request, response);
	}
	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// エンコーディング設定
		request.setCharacterEncoding("utf-8");
		
		// selectするidを取得
		int userId = Integer.parseInt(request.getParameter("id"));
		
		UsersDAO usersDao = new UsersDAO();
		Users user = usersDao.selectById(userId);

		request.setAttribute("user", user);
		
		String view = "WEB-INF/views/index.jsp";
		RequestDispatcher dispatch = request.getRequestDispatcher(view);
		dispatch.forward(request, response);
	}
}
