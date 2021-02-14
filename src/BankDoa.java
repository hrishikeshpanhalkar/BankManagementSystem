

import java.sql.*;
import javax.swing.JOptionPane;


class BankDoa {
    Connection con=null;
    static int add(int saccno, String sname, String spassword, int sbalance) {
        int status=0;
        try{
            Class.forName("com.mysql.jdbc.Driver");
            Connection con=DriverManager.getConnection("jdbc:mysql://localhost:3306/Bank","root","root");
            Statement st=con.createStatement();
            ResultSet res = st.executeQuery("select * from bank where acc="+saccno+" AND name='"+sname+"' AND password='"+spassword+"'");
            if(!res.isBeforeFirst()){
                JOptionPane.showMessageDialog(null,"Please enter proper account info.!");
            }else{
                PreparedStatement ps=con.prepareStatement("update bank set balance=balance+"+sbalance+" where acc=? AND name=? AND password=?");
                ps.setInt(1, saccno);
                ps.setString(2, sname );
                ps.setString(3, spassword);
                status=ps.executeUpdate();           
                ps.close();
            }
        }catch(Exception e){
             System.out.println("Error:" +e.getMessage());
        }
        return status;
            }

    static int withdraw(int saccno, String sname, String spassword, int sbalance) {
        int status=0;
        try{
            Class.forName("com.mysql.jdbc.Driver");
            Connection con=DriverManager.getConnection("jdbc:mysql://localhost:3306/Bank","root","root");
            Statement st=con.createStatement();
            ResultSet res = st.executeQuery("select * from bank where acc="+saccno+" AND name='"+sname+"' AND password='"+spassword+"'");
            if(!res.isBeforeFirst()){
                JOptionPane.showMessageDialog(null,"Please enter proper account info.!");
            }else{
                PreparedStatement as=con.prepareStatement("select balance from bank where acc="+saccno+"");
                ResultSet rs=as.executeQuery();
                while(rs.next()){
                    int balance=rs.getInt("balance");            
                    if(balance > sbalance){                            
                        PreparedStatement ps=con.prepareStatement("update bank set balance=balance-"+sbalance+" where acc=? AND name=? AND password=?");
                        ps.setInt(1, saccno);
                        ps.setString(2, sname );
                        ps.setString(3, spassword);
                        status=ps.executeUpdate();           
                        ps.close();
                    }else{
                        JOptionPane.showMessageDialog(null, "Withdraw Amount is more than balance");
                    }
                }
            }  
        }catch(Exception e){
             System.out.println("Error:" +e.getMessage());
        }
        return status;
    }
    
    
    
    static int save(String name, String password, String accounttype, String dob, String gender, String address, long contact, int balance) {
        int status=0;
        try{
            Class.forName("com.mysql.jdbc.Driver");
            Connection con=DriverManager.getConnection("jdbc:mysql://localhost:3306/Bank","root","root");
            PreparedStatement ps=con.prepareStatement("insert into bank(name,password,acc_type,dob,gender,address,contact,balance) values(?,?,?,?,?,?,?,?)");
            //ps.setInt(1, acc);
            ps.setString(1, name);
            ps.setString(2, password);
            ps.setString(3, accounttype );
            ps.setString(4, dob);
            ps.setString(5, gender);
            ps.setString(6, address);
            ps.setLong(7, contact);
            ps.setInt(8, balance);
            status=ps.executeUpdate();
            
            ps.close();
            
        }catch(Exception e){
             System.out.println("Error:" +e.getMessage());
        }
        return status;
    }

    static boolean login(String sname, String spassword) {
        boolean status=false;
        try{
            Class.forName("com.mysql.jdbc.Driver");
            Connection con=DriverManager.getConnection("jdbc:mysql://localhost:3306/Bank","root","root");
            PreparedStatement ps=con.prepareStatement("select name,password from bank ");
            
            ResultSet rs=ps.executeQuery();
            while(rs.next())
            {
                String name=rs.getString("name");
                String password=rs.getString("password");
                if(sname.equals(name) && spassword.equals(password) ){
                    status=true;
                }                
            }        
            rs.close();
        }catch(Exception e){
            System.out.println("Error:" +e.getMessage());
        }        
        return status;
    }
    
}
