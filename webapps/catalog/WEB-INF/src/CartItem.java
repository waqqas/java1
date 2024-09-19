import java.io.Serializable;

public class CartItem implements Serializable{
    private static final long serialVersionUID = 1L;

    private String imgAddress;
    private String name;
    private int price;

    public CartItem(String imgAddress, String name, int price){
        this.imgAddress = imgAddress;
        this.name = name;
        this.price = price;
    }

    public String getImgAddress(){
        return imgAddress;
    }

    public String getName(){
        return name;
    }

    public int getPrice(){
        return price;
    }

    public void setImgAddress(String imgAddress){
        this.imgAddress = imgAddress;
    }

    public void setName(String name){
        this.name = name;
    }

    public void setPrice(int price){
        this.price = price;
    }

    public boolean equals(Object other){
        if(this == other){
            return true;
        }

        if(other == null || getClass() != other.getClass()){
            return false;
        }
        CartItem otherItem = (CartItem) other;

        return name.equals(otherItem.getName());
    }

    public String toString(){
        return "CartItem: " + name + " " + price;
    }

    public int hashCode(){
        return name.hashCode();
    }
}

