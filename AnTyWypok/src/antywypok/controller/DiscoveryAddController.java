package antywypok.controller;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import antywypok.model.User;
import antywypok.service.DiscoverService;

/**
 * Servlet implementation class DiscoveryAddController
 */
@WebServlet("/add")
public class DiscoveryAddController extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		if(request.getUserPrincipal() != null){
			request.getRequestDispatcher("new_post.jsp").forward(request, response);
		} else {
			response.sendError(403);
		}
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	@Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        request.setCharacterEncoding("UTF-8");
        String name = request.getParameter("inputName");
        String description = request.getParameter("inputDescription");
        String url = request.getParameter("inputUrl");
        User authenticatedUser = (User) request.getSession().getAttribute("user");
        if(request.getUserPrincipal() != null) {
            DiscoverService discoverService = new DiscoverService();
            discoverService.addDiscovery(name, description, url, authenticatedUser);
            response.sendRedirect(request.getContextPath() + "/");
        } else {
            response.sendError(403);
        }
    }

}
