/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Properties;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JTable;

/**
 *
 * @author Sandu
 */
public class DatabaseAdapter {
    private static Connection con = null;
    private static Statement stmt = null;
    private static final ResultSet rs = null;
    private static final Properties connectionProps = new Properties();
//    final private static String url = "jdbc:mysql://localhost/dharmapala_data?autoReconnect=true"; 
    final private static String url = "jdbc:derby:dharmapala_data;create=true"; 
    private static String user;
    private static String login_pwd;

    public DatabaseAdapter() {
        try {
            //STEP 2: Register JDBC driver
//            Class.forName("com.mysql.jdbc.Driver");
            Class.forName("org.apache.derby.jdbc.EmbeddedDriver");
        }catch (ClassNotFoundException ex) {
            Logger.getLogger(DatabaseAdapter.class.getName()).log(Level.SEVERE, null, ex);
        }
    }       
    
    public boolean login(String username, String password){
       
        try {
            user = username;
            login_pwd = password;
            connectionProps.put("user", user);
            connectionProps.put("password", login_pwd);
            con = DriverManager.getConnection(url, connectionProps);
            
//            Statement sta = con.createStatement(); 
//            int count = sta.executeUpdate(
//              "CREATE TABLE account (username VARCHAR(100), holder_name VARCHAR(1000),"
//              + " privilage VARCHAR(20), password VARCHAR(2000))");
//            
//            count = sta.executeUpdate(
//              "CREATE TABLE cost_table (class_name VARCHAR(15), total_available DOUBLE,"
//              + " return_amount DOUBLE, reason VARCHAR(2000), date DATE)");
            
            return true;
        } catch (SQLException ex) {
            Logger.getLogger(DatabaseAdapter.class.getName()).log(Level.SEVERE, null, ex);
            return false;
        }
    } 
    
    public String create_account(String username, String holder_name, String password){
        boolean check_account = check_account(username);
        if(check_account){
            HashAlgorithm algorithm = new HashAlgorithm();
            String generateStorngPasswordHash = algorithm.generateStorngPasswordHash(password);
            try {
                con.setAutoCommit(false); 
                String query = "INSERT INTO account (username, holder_name, privilege,password) VALUES (?,?, 'user',?)";
                PreparedStatement pre_stmt = con.prepareStatement(query);
                pre_stmt.setString(1, username);
                pre_stmt.setString(2, holder_name);
                pre_stmt.setString(3, generateStorngPasswordHash);
                int executeUpdate = pre_stmt.executeUpdate();
                con.commit();

                if(executeUpdate == 1){
                    return "success";
                }
                else{
                    return "Error in account creating. Please try again";
                }
            } catch (SQLException ex) {            
                Logger.getLogger(DatabaseAdapter.class.getName()).log(Level.SEVERE, null, ex);
                return "Error in account creating. Please try again";
            }
        }
        else{
            return "User Name is already in used. Try using another one";
        }
    }
    
    public boolean update_account(String current_username, String new_username, String password){
        
        HashAlgorithm algorithm = new HashAlgorithm();
        String generateStorngPasswordHash = algorithm.generateStorngPasswordHash(password);
        System.out.println(generateStorngPasswordHash);
//        String generateStorngPasswordHash = password;
        try {
            con.setAutoCommit(false); 
            String query = "";
            PreparedStatement pre_stmt = null;
            if(new_username != null && !new_username.equals("")){
                query = "UPDATE account SET username = ?, password = ? WHERE username = ?";
                pre_stmt = con.prepareStatement(query);  
                pre_stmt.setString(1, new_username); 
                pre_stmt.setString(2, generateStorngPasswordHash);
                pre_stmt.setString(3, current_username); 
                System.out.println("updated1");
            }
            else{
                query = "UPDATE account SET password = ? WHERE username = ?";
                pre_stmt = con.prepareStatement(query);  
                pre_stmt.setString(1, generateStorngPasswordHash);
                pre_stmt.setString(2, current_username); 
                System.out.println("updated2");
            }
                                 
            int executeUpdate = pre_stmt.executeUpdate();
            con.commit();
            
//            Statement sta = con.createStatement(); 
//            ResultSet results = sta.executeQuery("SELECT * FROM account");
//                
//                while(results.next()){
//                    System.out.println(executeUpdate+"  "+results.getObject("username")+" "+results.getObject("password"));
////                    System.out.println(algorithm.validatePassword("2", results.getObject("password").toString()));
//                }
            if(executeUpdate == 1){
                 System.out.println("updated ok");
                return true;
            }
            else{
                 System.out.println("updated wrong");
                return false;
            }
        } catch (SQLException ex) {            
            Logger.getLogger(DatabaseAdapter.class.getName()).log(Level.SEVERE, null, ex);
            return false;
        }
    }
    
    public boolean remove_account(String username){        
        
        try {
            con.setAutoCommit(false); 
            String query = "DELETE FROM account WHERE username = ?";
            PreparedStatement pre_stmt = con.prepareStatement(query);
            pre_stmt.setString(1, username); 
            int executeUpdate = pre_stmt.executeUpdate();
            con.commit();
            
            if(executeUpdate == 1){
                return true;
            }
            else{
                return false;
            }
        } catch (SQLException ex) {            
            Logger.getLogger(DatabaseAdapter.class.getName()).log(Level.SEVERE, null, ex);
            return false;
        }
    }
    
    private boolean check_account(String username){
        try {
            con.setAutoCommit(false); 
            String query = "SELECT * FROM account WHERE username = ?";
            PreparedStatement pre_stmt = con.prepareStatement(query , ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_UPDATABLE );
            pre_stmt.setString(1, username); 
            ResultSet resultSet = pre_stmt.executeQuery();
            con.commit();
            
            int size= 0;
            if (resultSet != null)   
            {  
              resultSet.beforeFirst();  
              resultSet.last();  
              size = resultSet.getRow();  
            }  
            
            if(size == 0){
                return true;
            }
            else{
                return false;
            }
        } catch (SQLException ex) {            
            Logger.getLogger(DatabaseAdapter.class.getName()).log(Level.SEVERE, null, ex);
            return false;
        }
    }
    
    public String login_into_account(String username, char [] password){
        String type = "";
        if(!check_account(username)){
            try {
                con.setAutoCommit(false); 
                String query = "SELECT privilege, password FROM account WHERE username = ?";
                PreparedStatement pre_stmt = con.prepareStatement(query);
                pre_stmt.setString(1, username); 
                ResultSet resultSet = pre_stmt.executeQuery();
                con.commit();

                String pw = "";
                while(resultSet.next()){
                    pw = resultSet.getString("password");
                    type = resultSet.getString("privilege");
                }
                
                if(validate_password(password, pw)){
                    return type;
                }
                else{
                    return "";
                }
            } catch (SQLException ex) {            
                Logger.getLogger(DatabaseAdapter.class.getName()).log(Level.SEVERE, null, ex);
                return "";
            }
        }
        else{
            return "";
        }
    }
    
    public boolean validate_password(char [] password, String stored_pw){
        String pw = new String(password);
         
        HashAlgorithm algorithm = new HashAlgorithm();
        return algorithm.validatePassword(pw, stored_pw);

    }
    
    public void create_table(String class_name){
                    
        String sql;
//        sql = "CREATE TABLE IF NOT EXISTS "+class_name+" ( "
//                + "id INT(5) NOT NULL ,"
//                + "index_no VARCHAR(10) NOT NULL,"
//                + "name VARCHAR(100) NOT NULL,"
//                + "receipt_no VARCHAR(10) NOT NULL,"
//                + "total_amount REAL NOT NULL"
//                + ");";
        
        sql = "CREATE TABLE IF NOT EXISTS "+class_name+" ( "
                + "id INT(5) NOT NULL ,"
                + "index_no VARCHAR(10) NOT NULL,"
                + "name VARCHAR(100) NOT NULL,"
                + "receipt_no VARCHAR(10) NOT NULL,"
                + "amount REAL NOT NULL,"
                + "public REAL NOT NULL,"
                + "class REAL NOT NULL,"
                + "sport REAL NOT NULL,"
                + "member REAL NOT NULL"
//                + "PRIMARY KEY(id)"
                + ");";
        try {
            stmt = con.createStatement();
            stmt.executeUpdate(sql);
        } catch (SQLException ex) {
            Logger.getLogger(DatabaseAdapter.class.getName()).log(Level.SEVERE, null, ex);
        }catch(Exception e){
            //Handle errors for Class.forName
            e.printStackTrace();
        }
    }
    
    public boolean store_data(JTable data, String class_name){
        boolean status = false;
        if(login("root", "")){
            create_table(class_name);
            insert_to_class_table(data, class_name);
            status = prepare_and_insert_to_cost_table_data(data,class_name); 
            close_connection();            
        }
        return status;
    }    
    
    public void insert_to_class_table(JTable data, String class_name){
        int rows = data.getRowCount();
        PreparedStatement pre_stmt = null;    
        for(int row = 0; row<rows ; row++){
            if(data.getValueAt(row, 0) != null){
                int id = (int) data.getValueAt(row, 0);
                String index = (String) data.getValueAt(row, 1);
                String name = (String) data.getValueAt(row, 2);
                String receipt_no = (String) data.getValueAt(row, 3);
                float amount = Float.parseFloat(data.getValueAt(row, 4).toString());               

                try{ 
                    con.setAutoCommit(false);  
                    String query = "INSERT INTO "+class_name+" (id, index_no, name, receipt_no, amount, public, class, sport, member)"
                           + " VALUES(?, ?, ?, ?, ?, ?, ?, ?, ?) " ;

                    pre_stmt = con.prepareStatement(query);
                    pre_stmt.setInt(1, id); 
                    pre_stmt.setString(2, index); 
                    pre_stmt.setString(3, name);
                    pre_stmt.setString(4, receipt_no); 
                    pre_stmt.setDouble(5, amount); 
                    
                    switch(data.getValueAt(row, 4).toString()){
                        case Utils.VALUE_2440:
                            pre_stmt.setDouble(6, 1437);
                            pre_stmt.setDouble(7, 600);
                            pre_stmt.setDouble(8, 100);
                            pre_stmt.setDouble(9, 303);
                            break;
                        case Utils.VALUE_2464:
                            pre_stmt.setDouble(6, 1461);
                            pre_stmt.setDouble(7, 600);
                            pre_stmt.setDouble(8, 100);
                            pre_stmt.setDouble(9, 303);
                            break;
                        case Utils.VALUE_700:
                            pre_stmt.setDouble(6, 0);
                            pre_stmt.setDouble(7, 600);
                            pre_stmt.setDouble(8, 100);
                            pre_stmt.setDouble(9, 0);
                            break;
                        case Utils.VALUE_1740:
                            pre_stmt.setDouble(6, 1437);
                            pre_stmt.setDouble(7, 0);
                            pre_stmt.setDouble(8, 0);
                            pre_stmt.setDouble(9, 303);
                            break;
                        case Utils.VALUE_1764:
                            pre_stmt.setDouble(6, 1461);
                            pre_stmt.setDouble(7, 0);
                            pre_stmt.setDouble(8, 0);
                            pre_stmt.setDouble(9, 303);
                            break;
                    }

                    pre_stmt.executeUpdate();
                    con.commit();
                } catch (SQLException ex) {
                    Logger.getLogger(DatabaseAdapter.class.getName()).log(Level.SEVERE, null, ex);
                }catch(Exception e){
                    //Handle errors for Class.forName
                    e.printStackTrace();
                }  
            }
            else{
                break;
            }              
        }    
    }
    
    private boolean prepare_and_insert_to_cost_table_data(JTable data, String class_name){

        double total_class = 0;
        int rows = data.getRowCount();  
        
        for(int row = 0; row<rows ; row++){
            if(data.getValueAt(row, 0) != null){
                String amount = data.getValueAt(row, 4).toString();
                
                switch(amount){
                    case Utils.VALUE_2440 :
                        total_class += 600;
                        break;
                    case Utils.VALUE_2464 :
                        total_class += 600;
                        break;  
                    case Utils.VALUE_700 :
                        total_class += 600;
                        break;
                }
            }
            else{
                break;
            }
        }  
        
        return insert_or_update_cost_table(class_name, total_class);        
    }
    
    public boolean insert_or_update_cost_table(String class_name, double amount){
        boolean status = false;
        try {
            
            ResultSet resultSet = retrive_cost_data(class_name);
            String name = "";
            while(resultSet.next()){
                name = resultSet.getString("class_name");
            }
             
            if(class_name.equals(name)){
                status = update_cost_table(class_name, amount,"");
            }
            else{
                status = insert_to_cost_table(class_name, amount);                
            }
                   
        } catch (SQLException ex) {
            Logger.getLogger(DatabaseAdapter.class.getName()).log(Level.SEVERE, null, ex);
            status = false;
        }
        return status;
    }
    
    public boolean insert_to_cost_table(String class_name, double total_available){
        
        try {
            con.setAutoCommit(false); 
            String query = "INSERT INTO cost_table (class_name, total_available, return_amount, reason, date)"
                    + " VALUES(?, ?, ?, ?, NOW()) " ;
            
            PreparedStatement pre_stmt = con.prepareStatement(query);
            pre_stmt.setString(1, class_name);
            pre_stmt.setDouble(2, total_available);
            pre_stmt.setDouble(3, 0);
            pre_stmt.setString(4, "");
            
            int executeUpdate = pre_stmt.executeUpdate();
            con.commit();
            
            if(executeUpdate == 1){
                return true;
            }
            else{
                return false;
            }
        } catch (SQLException ex) {
            Logger.getLogger(DatabaseAdapter.class.getName()).log(Level.SEVERE, null, ex);
            return false;
        }
    }
    
    public boolean update_cost_table(String class_name, double remain, String description){
        try {
            ResultSet resultSet = retrive_cost_data(class_name);            
            double total_available = 0;
            while(resultSet.next()){
                total_available = resultSet.getDouble("total_available");
            }
            
            if(total_available+remain >= 0){
                con.setAutoCommit(false); 
                String update_query = "";
                PreparedStatement pre_stmt = null;
                if(description != null && !description.equals("")){
                    update_query = "UPDATE cost_table SET total_available = ?, reason = ?, date = NOW()"
                        + " WHERE class_name = ?  " ;
                    pre_stmt = con.prepareStatement(update_query);
                    pre_stmt.setDouble(1, total_available+remain);
                    pre_stmt.setString(2, description);
                    pre_stmt.setString(3, class_name);
                }
                else{
                    update_query = "UPDATE cost_table SET total_available = ?, date = NOW()"
                        + " WHERE class_name = ?  " ;
                    pre_stmt = con.prepareStatement(update_query);
                    pre_stmt.setDouble(1, total_available+remain);
                    pre_stmt.setString(2, class_name);
                }
                
                
                int executeUpdate = pre_stmt.executeUpdate();
                con.commit();
                
                return executeUpdate == 1;
                
            }
            else{
                return false;
            }            
        } catch (SQLException ex) {
            Logger.getLogger(DatabaseAdapter.class.getName()).log(Level.SEVERE, null, ex);
            return false;
        }
    }
    
    public ResultSet retrive_class_data(String class_name){
        
//        if(login("root", "")){
            String query = "SELECT * FROM "+class_name;
            try {
                stmt = con.createStatement();
                ResultSet rs = stmt.executeQuery(query);

                return rs;
            } catch (SQLException ex) {            
                Logger.getLogger(DatabaseAdapter.class.getName()).log(Level.SEVERE, null, ex);
                return null;
            }
//            finally{
//            //finally block used to close resources
//                try{
//                   if(stmt!=null)
//                      stmt.close();
//                }catch(SQLException se){
//                }// do nothing
//                try{
//                   if(con!=null)
//                       con.setAutoCommit(true);
//                       con.close();
//                }catch(SQLException se){
//                   se.printStackTrace();
//                }//end finally try
//            }
//        }
//        else{
//            return null;
//        }
    }
    
    public ResultSet retrive_total_data(String class_name){
        String query = "SELECT SUM(amount) AS total_amount, "
                +"SUM(public) AS total_public, "
                +"SUM(class) AS total_class, "
                +"SUM(sport) AS total_sport, "
                +"SUM(member) AS total_member FROM "
                +class_name;
        try {
            stmt = con.createStatement();
            ResultSet rs = stmt.executeQuery(query);

            return rs;
        } catch (SQLException ex) {            
            Logger.getLogger(DatabaseAdapter.class.getName()).log(Level.SEVERE, null, ex);
            return null;
        }
//        finally{
//        //finally block used to close resources
//            try{
//               if(stmt!=null)
//                  stmt.close();
//            }catch(SQLException se){
//            }// do nothing
//            try{
//               if(con!=null)
//                   con.setAutoCommit(true);
//                   con.close();
//            }catch(SQLException se){
//               se.printStackTrace();
//            }//end finally try
//        }
    }
    
    public ResultSet retrive_student_data(String class_name, String index){
        String query = "SELECT * FROM "+class_name+" WHERE index_no = '"+index+"'";
        try {
            stmt = con.createStatement();
            ResultSet rs = stmt.executeQuery(query);

            return rs;
        } catch (SQLException ex) {            
            Logger.getLogger(DatabaseAdapter.class.getName()).log(Level.SEVERE, null, ex);
            return null;
        }
    }
    
    public ResultSet retrive_cost_data(String class_name){
        String query = "SELECT * FROM cost_table WHERE class_name = '"+class_name+"'";
        try {
            stmt = con.createStatement();
            ResultSet rs = stmt.executeQuery(query);

            return rs;
        } catch (SQLException ex) {            
            Logger.getLogger(DatabaseAdapter.class.getName()).log(Level.SEVERE, null, ex);
            return null;
        }
    }
    
    public boolean update_class_table(JTable data, String class_name){
        int rows = data.getRowCount();
        PreparedStatement pre_stmt = null;    
        for(int row = 0; row<rows ; row++){
            if(data.getValueAt(row, 0) != null){
                int id = (int) data.getValueAt(row, 0);
                String index = (String) data.getValueAt(row, 1);
                String name = (String) data.getValueAt(row, 2);
                String receipt_no = (String) data.getValueAt(row, 3);
                float amount = Float.parseFloat(data.getValueAt(row, 4).toString()); 
                float public_amount = Float.parseFloat(data.getValueAt(row, 5).toString()); 
                float class_amount = Float.parseFloat(data.getValueAt(row, 6).toString()); 
                float sport_amount = Float.parseFloat(data.getValueAt(row, 7).toString()); 
                float member_amount = Float.parseFloat(data.getValueAt(row, 8).toString()); 

                try{ 
                    con.setAutoCommit(false);  
                    String query = "UPDATE "+class_name+" SET receipt_no = ?, "
                            +"amount = ?, public = ?, class = ?, sport = ?, "
                            +"member = ? "
                            +"WHERE id =? and index_no = ? and name = ?" ;

                    pre_stmt = con.prepareStatement(query);
                    
                    pre_stmt.setString(1, receipt_no); 
                    pre_stmt.setDouble(2, amount); 
                    pre_stmt.setDouble(3, public_amount);
                    pre_stmt.setDouble(4, class_amount);
                    pre_stmt.setDouble(5, sport_amount);
                    pre_stmt.setDouble(6, member_amount);
                    pre_stmt.setInt(7, id); 
                    pre_stmt.setString(8, index); 
                    pre_stmt.setString(9, name);

                    int executeUpdate = pre_stmt.executeUpdate();
                    con.commit();
                } catch (SQLException ex) {
                    Logger.getLogger(DatabaseAdapter.class.getName()).log(Level.SEVERE, null, ex);
                }catch(Exception e){
                    //Handle errors for Class.forName
                    e.printStackTrace();
                }  
            }
            else{
                break;
            }          
        }
        return true;
    }
      
    public boolean delete_entry(String class_name, String id, String index, String receipt_no, double class_amount){
        try {
            ResultSet resultSet = retrive_cost_data(class_name);
            double total_available = 0;
            while(resultSet.next()){
                total_available = resultSet.getDouble("total_available");
            }
            
            if(total_available >= class_amount){
                con.setAutoCommit(false); 
                String update_query = "DELETE FROM "+class_name
                        +" WHERE id = ? AND index_no = ? AND receipt_no = ?" ;
                PreparedStatement pre_stmt = con.prepareStatement(update_query);

                pre_stmt.setString(1, id);
                pre_stmt.setString(2, index);
                pre_stmt.setString(3, receipt_no);
                int executeUpdate = pre_stmt.executeUpdate();
                con.commit();
                
                boolean update_cost_table = update_cost_table(class_name, (-1*class_amount),"");
                if(executeUpdate == 1 && update_cost_table){
                    return true;
                }
                else{
                    return false;
                }
            }
            else{
                return false;
            }
        } catch (SQLException ex) {
            Logger.getLogger(DatabaseAdapter.class.getName()).log(Level.SEVERE, null, ex);
            return false;
        }
    }
    
    public Object [] retrive_accounts_data(){

        Object [] data = new Object[2];
        ArrayList<String> account_list = new ArrayList<>();
        ArrayList<String> holder_list = new ArrayList<>();
        String query = "SELECT username, holder_name FROM account ";//WHERE 1";
        try {
            stmt = con.createStatement();
            ResultSet rs = stmt.executeQuery(query);
            
            
            while(rs.next()){
                account_list.add(rs.getString("username"));
                holder_list.add(rs.getString("holder_name"));
            }
            
        } catch (SQLException ex) {            
            Logger.getLogger(DatabaseAdapter.class.getName()).log(Level.SEVERE, null, ex);
        }
        data[0] = account_list;
        data[1] = holder_list;
        
        return data;
    }
    
    public void close_connection(){
       try{
           if(stmt!=null)
              stmt.close();
       }catch(SQLException se){
       }// do nothing
       try{
          if(con!=null)
              con.setAutoCommit(true);
              con.close();
       }catch(SQLException se){
          se.printStackTrace();
       }
   }
}
