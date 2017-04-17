package antywypok.controller;

import java.io.IOException;
import java.util.Comparator;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import antywypok.model.Discovery;
import antywypok.service.DiscoverService;

/**
 * Servlet implementation class HomeController
 */
@WebServlet("")
public class HomeController extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		saveDiscoveriesInRequest(request);
		request.getRequestDispatcher("index.jsp").forward(request, response);
	}

	private void saveDiscoveriesInRequest(HttpServletRequest request){
		DiscoverService discoverySer = new DiscoverService();
		List<Discovery> allDiscoveries = discoverySer.getAllDiscoveries((d1, d2) -> (d2.getUpVote() - d2.getDownVote()) - (d1.getUpVote() - d1.getDownVote()));
		// Wed³ug daty
		//List<Discovery> allDiscoveries = discoveryService.getAllDiscoveries((d1, d2) -> d2.getTimestamp().compareTo(d1.getTimestamp()));
		request.setAttribute("discoveries", allDiscoveries);
	}


}
