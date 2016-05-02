package es.upm.dit.isst.tfg;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import es.upm.dit.isst.dao.TFGDAO;
import es.upm.dit.isst.dao.TFGImpl;
import es.upm.dit.isst.model.TFG;

public class ProfileServlet extends HttpServlet {
	
	boolean hasTFG = false;

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		String user = null;
		user = req.getParameter("name");
		
		TFGDAO tfgDAO = TFGImpl.getInstance();
		TFG tfg = null;
		List<TFG> tfgs = null;
		tfg = tfgDAO.readByAutor(user);
		hasTFG = (tfg != null);
		req.getSession().setAttribute("hasTFG", hasTFG);
		if (hasTFG)
			req.getSession().setAttribute("tfg", tfg);
		else {
			tfgs = tfgDAO.readByTutor(user);
			if (tfgs != null)
				req.getSession().setAttribute("tfgs", new ArrayList<TFG>(tfgs));
		}
		
		RequestDispatcher view = req.getRequestDispatcher("profile.jsp");
        view.forward(req, resp);
	}
}
