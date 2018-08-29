package jogo;

import java.io.Serializable;

/**
 *
 * @author crisley
 */
public class Usuario implements Serializable{

    private String user;
    private String pass;

    public String getUser() {
        return user;
    }

    public void setUser(String user) {
        this.user = user;
    }

    public String getPass() {
        return pass;
    }

    public void setPass(String pass) {
        this.pass = pass;
    }

}
