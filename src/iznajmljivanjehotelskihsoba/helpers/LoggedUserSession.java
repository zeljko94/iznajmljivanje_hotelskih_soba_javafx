
package iznajmljivanjehotelskihsoba.helpers;

import iznajmljivanjehotelskihsoba.models.User;


public class LoggedUserSession {
    public static User user;
    public static User getUser(){ return user; }
    public static void setUser(User u){ user = u; }
}
