import java.util.logging.Logger;

import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.security.InvalidParameterException;
import java.io.IOException;

public class AccessServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;
    private static final Logger logger = Logger.getLogger(AccessServlet.class.getName());

    private UserManager userManager;
    private DatabaseManager db;
    

    @Override
    public void init() {
        db = new DatabaseManager();
        userManager = new UserManager(db.getUsers());

    }

    @Override
    public void  destroy() {
        db.writeUsers(userManager.getUsers());
    }

    private void loginAction(HttpServletRequest request, HttpServletResponse response) throws IOException{
   
        String email = request.getParameter("email");
        String password = request.getParameter("password");
        HttpSession session = request.getSession(false);
        if (session != null) {
            String sessionEmail = (String) session.getAttribute("email");
            if (sessionEmail == null) {
                session.invalidate();
                response.sendRedirect("/catalog/login.html");
                return;
            }
            if (sessionEmail.equals(email)) {
                response.sendRedirect("/catalog/catalog.html");
                return;
            }
            session.invalidate();
        }
        try {
            User userToLogin = userManager.loginUser(new User(email, password, null, null));
            session = request.getSession();
            session.setAttribute("email", userToLogin.getEmail());
            response.sendRedirect("/catalog/catalog.html");
        } catch (InvalidParameterException e) {
            logger.warning("Invalid parameter exception: " + e.getMessage());
            response.sendError(HttpServletResponse.SC_BAD_REQUEST,  e.getMessage());
        } catch (IllegalStateException e) {
            logger.warning("Illegal state exception: " + e.getMessage());
            response.sendError(HttpServletResponse.SC_BAD_REQUEST,  e.getMessage());
        } catch (IllegalArgumentException e) {
            logger.warning("Illegal argument exception: " + e.getMessage());
            response.sendError(HttpServletResponse.SC_BAD_REQUEST,  e.getMessage());
        }
    }

    public void registerAction(HttpServletRequest request, HttpServletResponse response) throws IOException{
        /*
         * In registerAction(), let’s first read the email, firstName, lastName, and password parameters from request. Let’s verify that the user is not already signed in by checking but not creating their HttpSession. If the session exists we’ll invalidate it and send an error to the user telling them to try registering again.
         */
        String email = request.getParameter("email");
        String password = request.getParameter("password");
        String firstName = request.getParameter("firstName");
        String lastName = request.getParameter("lastName");
        
        HttpSession session = request.getSession(false);
        if (session != null) {
            session.invalidate();
        }

        User userToRegister = new User(email, password, firstName, lastName);
        try {
            userManager.registerUser(userToRegister);
            response.sendRedirect("/catalog/login.html");
        } catch (InvalidParameterException e) {
            logger.warning("Invalid parameter exception: " + e.getMessage());
            response.sendError(HttpServletResponse.SC_BAD_REQUEST,  e.getMessage());
        } catch (IllegalStateException e) {
            logger.warning("Illegal state exception: " + e.getMessage());
            response.sendError(HttpServletResponse.SC_BAD_REQUEST,  e.getMessage());
        }
    }

    public void logoutAction(HttpServletRequest request, HttpServletResponse response) throws IOException{
        HttpSession session = request.getSession(false);
        if (session != null) {
            session.invalidate();
        }
        response.sendRedirect("/catalog/login.html");
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws IOException {
        String action = request.getParameter("action");
        if (action == null) {
            response.sendError(HttpServletResponse.SC_BAD_REQUEST, "Action parameter is missing");
            return;
        }
        switch (action) {
            case "login":
                loginAction(request, response);
                break;
            case "register":
                registerAction(request, response);
                break;
            case "logout":
                logoutAction(request, response);
                break;
            default:
                response.sendError(HttpServletResponse.SC_BAD_REQUEST, "Invalid action parameter");
        }
    }

}
