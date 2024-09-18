import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

public class HomeServlet extends HttpServlet {
    private static final long serialVersionUID = 100L;  // Used for serialization since `HttpServlet` implements `Serializable`. 
    public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        PrintWriter out = response.getWriter();
        out.println("<html>");
        out.println("<head><title>My Icecream Shop!</title></head>");
        out.println("<body>");
        out.println("<h1>Welcome To My Ice Cream Parlor</h1>");
        out.println("<a href='/icecream-app/form.html'>Place An Order!</a>"); // link to form
        out.println("</body></html>");
      }

    //   public void doPost(HttpServletRequest request, HttpServletResponse response) throws  ServletException, IOException {
    //     String cupOrCone = request.getParameter("cupOrCone");
    //     String size = request.getParameter("size");
    //     String flavor = request.getParameter("flavor");
              
    //     PrintWriter out = response.getWriter();   // Get the response PrintWriter
    //     out.println("<html>");
    //     out.println("<head><title>My Icecream Shop!</title></head>");
    //     out.println("<body>");
    //     out.println(String.format("<h1>Here is your %s %s ice cream in a %s. Enjoy!</h1>", size, flavor, cupOrCone));
    //     out.println("</body></html>");
    //   }

    public void doPost(HttpServletRequest request, HttpServletResponse response) throws  ServletException, IOException {
        String cupOrCone = request.getParameter("cupOrCone");
        String size = request.getParameter("size");
        String flavor = request.getParameter("flavor");
        HttpSession session = request.getSession(true);  // Get or create session object
      
        String prevSize = (String)session.getAttribute("size");  // Get previously saved size value
        String prevFlavor = (String)session.getAttribute("flavor");  // Get previously saved flavor value
        String prevCupOrCone = (String)session.getAttribute("cupOrCone");  // Get previously saved cupOrCone value
      
        session.setAttribute("size", size);  // Update size option
        session.setAttribute("flavor", flavor);  // Update flavor option
        session.setAttribute("cupOrCone", cupOrCone);  // Update cupOrCone option

        PrintWriter out = response.getWriter();   // Get the response PrintWriter
        out.println("<html>");
        out.println("<head><title>My Icecream Shop!</title></head>");
        out.println("<body>");
        out.println(String.format("<h1>Here is your %s %s ice cream in a %s. Enjoy!</h1>", size, flavor, cupOrCone));
        out.println("</body></html>");        
      }    
      
      
  }