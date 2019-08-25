/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Model;

import java.sql.ResultSet;
import java.sql.Statement;
import static Model.DBConnection.conn;

/**
 *
 * @author Josh
 */
public class Query {
    private static String query;
    private static Statement statement;
    private static ResultSet result;
    
    public static void makeQuery(String q){
        query = q;
        
        try{
            //create statement object
            statement = conn.createStatement();
            
            //determine query execution
            if(query.toLowerCase().startsWith("select"))
                result = statement.executeQuery(query);
            
            if(query.toLowerCase().startsWith("delete") || query.toLowerCase().startsWith("insert") || query.toLowerCase().startsWith("update"))
                statement.executeUpdate(query);
            
        }catch(Exception e){
            System.out.println("ERROR: " + e.getMessage());
        }
    }
    
    public static ResultSet getResult(){
        return result;
    }
}
