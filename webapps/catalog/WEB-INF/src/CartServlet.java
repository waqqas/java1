import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.Map;

public class CartServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;
    private DatabaseManager db;
    private CartManager cartManager;

    @Override
    public void init() {
        db = new DatabaseManager();
        cartManager = new CartManager(db.getUserCarts());
    }

    @Override
    public void destroy() {
        db.writeUserCarts(cartManager.getUserCarts());
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        HttpSession session = request.getSession(false);
        if (session == null) {
            response.sendRedirect("/catalog/login.html");
            return;
        }

        String email = (String) session.getAttribute("email");
        if(email == null) {
            response.sendRedirect("/catalog/login.html");
            return;
        }

        Map<CartItem,Integer> cart = cartManager.getUserCart(email);

        PrintWriter out = response.getWriter();
        if(cart == null || cart.isEmpty()) {
            out.println("<html><head><title>My Cart</title><link rel=\"stylesheet\" href=\"styles.css\"></head><body>");
            out.println("<h1>Your Cart!</h1>");
            out.println("<div class=\"cart-summary\">");
            out.println("<h2>Your cart is empty!</h2>");
            out.println("<form><button class=\"cart-button\" type=\"submit\" formaction=\"/catalog/catalog.html\">Keep Shopping</button></form>");
            out.println("</div>");
            out.println("</body></html>");
            return;
        }
        else{
            out.println(CartSummaryHtmlGenerator.getCartSummaryPage(cart));
        }
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        HttpSession session = request.getSession(false);
        if (session == null) {
            response.sendRedirect("/catalog/login.html");
            return;
        }

        String email = (String) session.getAttribute("email");
        if(email == null) {
            session.invalidate();
            response.sendRedirect("/catalog/login.html");
            return;
        }

        // Get the imgAddress, itemName, and itemPrice parameters from request.
        String imgAddress = request.getParameter("imgAddress");
        String itemName = request.getParameter("itemName");
        int itemPrice = Integer.parseInt(request.getParameter("itemPrice"));

        // Create a CartItem object called cartItem using the all fields constructor with the parameters we got.
        CartItem cartItem = new CartItem(imgAddress, itemName, itemPrice);
        
        // Add cartItem to the user cart associated with the email attribute.
        cartManager.addToCart(email, cartItem);

        //Redirect the user back to /catalog/catalog.html.
        response.sendRedirect("/catalog/catalog.html");

    }


    
}
