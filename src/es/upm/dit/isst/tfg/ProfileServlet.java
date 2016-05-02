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
		try {
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
		}catch (Exception e) {
			e.printStackTrace();
		}
				
		RequestDispatcher view = req.getRequestDispatcher("profile.jsp");
        view.forward(req, resp);
	}
	
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		String user, titulo, resumen, tutor;
//		try {
			titulo = (String) req.getParameter("titulo");
			resumen = (String) req.getParameter("resumen");
			tutor = (String) req.getParameter("tutor");

			System.out.println(titulo);
			System.out.println(tutor);
			System.out.println(resumen);
			
			if (titulo != null && resumen != null && tutor != null) {
				user = req.getUserPrincipal().getName().split("@")[0];
				TFGImpl tfgdao = TFGImpl.getInstance();
				tfgdao.create(user, titulo, resumen, tutor, "", "", 0);
				resp.sendRedirect("/tfgisst");
			}	
//		} catch (Exception e) {
//			e.printStackTrace();
//			resp.sendRedirect("/profile");
//		}
	}
}
