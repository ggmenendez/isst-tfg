package es.upm.dit.isst.tfg;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.*;

import com.google.appengine.api.users.*;

import es.upm.dit.isst.dao.TFGDAO;
import es.upm.dit.isst.dao.TFGImpl;
import es.upm.dit.isst.model.TFG;
import es.upm.dit.isst.model.User;

@SuppressWarnings("serial")
public class TfgISSTServlet extends HttpServlet {
	public void doGet(HttpServletRequest req, HttpServletResponse resp) throws IOException, ServletException {
		
		TFGDAO daoTFG = TFGImpl.getInstance();
		
		daoTFG.create("test", "El buen titulardo", "Érase una vez en un lugar de la Mancha de cuyo nombre no quiero acordarme", "tutoratti", "Yourself", "/home/pablo/tfg", 0);
		
//		for (TFG tfg : daoTFG.readAll()) {
//			resp.getWriter().println(tfg);
//		}
	    
	    toLogin(req, resp);
	}
	
	private void toLogin (HttpServletRequest req, HttpServletResponse resp) throws IOException, ServletException {
		UserService userService = UserServiceFactory.getUserService();
        String url = userService.createLoginURL(req.getRequestURI());
        String urlLinktext = "Login";
        User user = new User("", 0) ;
        resp.setContentType("text/html");            
        if (req.getUserPrincipal() != null){
        	user.setUsername(req.getUserPrincipal().getName().split("@")[0]);
            url = userService.createLogoutURL(req.getRequestURI());
            urlLinktext = "Logout";
        }

        
        req.getSession().setAttribute("user", user);
        req.getSession().setAttribute("url", url);
        req.getSession().setAttribute("urlLinktext", urlLinktext);
        
        RequestDispatcher view = req.getRequestDispatcher("index.jsp");
        view.forward(req, resp);
	}
}
