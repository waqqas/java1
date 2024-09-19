import java.io.Serializable;

public class User implements Serializable{
    private static final long serialVersionUID  = 1L;

    private String email;
    private String password;
    private String firstName;
    private String lastName;

    public User(String email, String password, String firstName, String lastName){
        this.email = email;
        this.password = password;
        this.firstName = firstName;
        this.lastName = lastName;
    }

    public String getEmail(){
        return email;
    }
    public String getPassword(){
        return password;
    }
    public String getFirstName(){
        return firstName;
    }
    public String getLastName(){
        return lastName;
    }

    public void setEmail(String email){
        this.email = email;
    }
    public void setPassword(String password){
        this.password = password;
    }
    public void setFirstName(String firstName){
        this.firstName = firstName;
    }
    public void setLastName(String lastName){
        this.lastName = lastName;
    }

    public boolean equals(Object other){
        if(this == other){
            return true;
        }

        if(other == null || getClass() != other.getClass()){
            return false;
        }
        User otherUser = (User) other;

        return email.equals(otherUser.getEmail());
    }

    public String toString(){
        return "User: " + email + " " + firstName + " " + lastName;
    }

    public int hashCode(){
        return email.hashCode();
    }
}


