package es.upm.dit.isst.tfg;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import es.upm.dit.isst.dao.TFGDAO;
import es.upm.dit.isst.dao.TFGImpl;

public class ChooseTFGServlet extends HttpServlet {

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		String user, titulo, resumen, tutor;
		try {
			titulo = (String) req.getSession().getAttribute("titulo");
			resumen = (String) req.getSession().getAttribute("resumen");
			tutor = (String) req.getSession().getAttribute("tutor");
			
			if (!titulo.equals("") && !tutor.equals("")){
				TFGImpl tfgdao = TFGImpl.getInstance();
				tfgdao.create("user", titulo, resumen, tutor, "", "", 0);
			}
			
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			RequestDispatcher view = req.getRequestDispatcher("profile.jsp");
	        view.forward(req, resp);
		}
		
	}
}
