/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Properties;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Sandu
 */
public class CreateInitialTables {
    private static Connection con = null;
    private static Statement stmt = null;
    private static final ResultSet rs = null;
    private static final Properties connectionProps = new Properties();
//    final private static String url = "jdbc:mysql://localhost/dharmapala_data?autoReconnect=true"; 
    final private static String url = "jdbc:derby:dharmapala_data;create=true"; 
    private static String user;
    private static String login_pwd;
    
    public boolean login(String username, String password){
       
        try {
            
            user = username;
            login_pwd = password;
            connectionProps.put("user", user);
            connectionProps.put("password", login_pwd);
            con = DriverManager.getConnection(url, connectionProps);
            
            Statement sta = con.createStatement(); 
            int count = sta.executeUpdate(
              "CREATE TABLE account (username VARCHAR(100), holder_name VARCHAR(1000),"
              + " privilege VARCHAR(20), password VARCHAR(2000))");
            
            count = sta.executeUpdate(
              "CREATE TABLE cost_table (class_name VARCHAR(15), total_available DOUBLE,"
              + " return_amount DOUBLE, reason VARCHAR(2000), date DATE)");
            
            count = sta.executeUpdate(
                  "INSERT INTO account VALUES ( 'admin', 'tharindu', 'admin', '0')");
                System.out.println("insert.");
            
           
            ResultSet results = sta.executeQuery("SELECT * FROM account");
                
                while(results.next()){
                    System.out.println( results.getObject("username")+" "+results.getObject("password"));
//                    System.out.println(algorithm.validatePassword("2", results.getObject("password").toString()));
                }
            return true;
        } catch (SQLException ex) {
            Logger.getLogger(DatabaseAdapter.class.getName()).log(Level.SEVERE, null, ex);
            return false;
        }
    } 
}
