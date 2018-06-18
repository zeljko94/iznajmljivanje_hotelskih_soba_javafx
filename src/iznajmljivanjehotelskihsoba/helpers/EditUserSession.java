/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package iznajmljivanjehotelskihsoba.helpers;

import iznajmljivanjehotelskihsoba.models.User;

/**
 *
 * @author owner
 */
public class EditUserSession {
    public static User user;
    public static User getUser(){ return user; }
    public static void setUser(User u){ user = u; }
}
