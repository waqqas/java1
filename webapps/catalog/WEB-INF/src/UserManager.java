import java.security.InvalidParameterException;
import java.util.HashMap;
import java.util.Map;

public class UserManager {
    private Map<String, User> users;

    UserManager(User[] users){
        this.users = new HashMap<String, User>();
        for(User user : users){
            this.users.put(user.getEmail(), user);
        }
    }

    UserManager(){
        this.users = new HashMap<String, User>();
    }

    public Map<String,User> getUsers(){
        return users;
    }

    public void registerUser(User userToRegister) throws InvalidParameterException, IllegalStateException{
        if(userToRegister == null || 
        (userToRegister.getEmail() == null || userToRegister.getEmail().isEmpty())
         || (userToRegister.getPassword() == null || userToRegister.getPassword().isEmpty())
         || (userToRegister.getFirstName() == null || userToRegister.getFirstName().isEmpty())
         || (userToRegister.getLastName() == null || userToRegister.getLastName().isEmpty())){
            throw new InvalidParameterException("User cannot be null");
        }
        

        // Throw an IllegalStateException if userToRegister is already registered.
        if(users.containsKey(userToRegister.getEmail())){
            throw new IllegalStateException("User already registered");
        }
        users.put(userToRegister.getEmail(), userToRegister);
    }

    public void unregisterUser(String email) throws InvalidParameterException{
        if(email == null || email.isEmpty()){
            throw new InvalidParameterException("Email cannot be null");
        }
        users.remove(email);
    }

    public User loginUser(User userToLogin) throws InvalidParameterException, IllegalStateException, IllegalArgumentException{
        if(userToLogin == null || 
        (userToLogin.getEmail() == null || userToLogin.getEmail().isEmpty())
         || (userToLogin.getPassword() == null || userToLogin.getPassword().isEmpty())){
            throw new InvalidParameterException("User cannot be null");
        }

        // Throw an IllegalArgumentException if userToLogin is not registered.
        if(!users.containsKey(userToLogin.getEmail())){
            throw new IllegalArgumentException("User not registered");
        }

        // Throw an IllegalStateException if the password is incorrect.
        if(!users.get(userToLogin.getEmail()).getPassword().equals(userToLogin.getPassword())){
            throw new IllegalStateException("Incorrect password");
        }
        return users.get(userToLogin.getEmail());
    }

        
}
