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
public class PastDateException extends Exception {
    public PastDateException(String message){
        super(message);
    }
}
