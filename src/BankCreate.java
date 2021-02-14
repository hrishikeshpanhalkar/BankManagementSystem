
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;


public class BankCreate {
    public static void main(String args[]){  
        Statement stmt=null;
        Connection con=null;
        try{
            Class.forName("com.mysql.jdbc.Driver");
            System.out.println("Connecting to the database....");
            con=DriverManager.getConnection("jdbc:mysql://localhost:3306/Bank", "root", "root");
            System.out.println("Connected database successfully");
            System.out.println("Creating table in given database");
            stmt=con.createStatement();
            String sql="create table bank" +
                    "(acc INTEGER not NULL AUTO_INCREMENT ," +                    
                    "name VARCHAR(255) not null," +                    
                    "password VARCHAR(255) not null," +
                    "acc_type VARCHAR(255) not null," + 
                    "dob varchar(255) not null," +
                    "gender VARCHAR(255) not null," +
                    "address VARCHAR(255) not null," +
                    "contact LONG not null," +
                    "balance INTEGER not null," +                 
                    "PRIMARY KEY(acc))";
            
            stmt.executeUpdate(sql);
            System.out.println("Created table in given database...."); 
            con.close();
                     
        }catch(SQLException se){
        se.printStackTrace();
        }catch(Exception e){
            e.printStackTrace();
        }finally{
            try{
                if(stmt!=null)
                con.close();
               }catch(SQLException se){
                   se.printStackTrace();
               }
        }System.out.println("GoodBye!");
        }
    }

