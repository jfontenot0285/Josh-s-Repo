/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Model;

/**
 *
 * @author Josh
 */
public class User {
    private int userId, active;
    private String userName, password;
    
    public User(int userId){
        this.userId = userId;
    }

    public int getUserId() {
        return userId;
    }

    public int getActive() {
        return active;
    }

    public String getUserName() {
        return userName;
    }

    public String getPassword() {
        return password;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

    public void setActive(int active) {
        this.active = active;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public void setPassword(String password) {
        this.password = password;
    }
    
    
}
