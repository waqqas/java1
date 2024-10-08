import java.util.HashMap;
import java.util.Map;

public class CartManager {
    private Map<String, Map<CartItem, Integer>> userCarts;

    public CartManager() {
        this.userCarts = new HashMap<String, Map<CartItem, Integer>>();
    }

    public CartManager(Map<String, Map<CartItem, Integer>> userCarts) {
        this.userCarts = userCarts;
    }

    public Map<String, Map<CartItem, Integer>> getUserCarts() {
        return userCarts;
    }

    public Map<CartItem, Integer> getUserCart(String email) {
        return userCarts.get(email);
    }

    public void addToCart(String email, CartItem item) {
        Map<CartItem, Integer> cart;
        if (userCarts.containsKey(email)) {
            cart = userCarts.get(email);
            if (cart.containsKey(item)) {
                cart.put(item, cart.get(item) + 1);
            } else {
                cart.put(item, 1);
            }
        } else {
            cart = new HashMap<CartItem, Integer>();
            cart.put(item, 1);
        }
        userCarts.put(email, cart);

    }
}