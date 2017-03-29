
package Rekisteri;
import jbcrypt.BCrypt;
public class Security {
    private int Workload = 12;
    
    
    public String hashPassword(String password_plaintext){
        String salt = BCrypt.gensalt(Workload);
        String hashed_password = BCrypt.hashpw(password_plaintext, salt);
        
        return hashed_password;
    }
    
    
}
