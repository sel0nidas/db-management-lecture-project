import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */

/**
 *
 * @author Selahattin
 */
public class Connector {
    private String admin_ad="postgres";
    private String admin_pass="1234";
    private String db_name="database_project";
    private String host="localhost";
    int port=5432;
    Connection con=null;
    Statement state=null;
    PreparedStatement preState=null;
    
    public Connector(){
        
        String url="jdbc:postgresql://"+host+":"+port+"/"+db_name+"?useUnicode=true&characterEncoding=utf8";
        
        try{
            Class.forName("org.postgresql.Driver");
            System.out.println("Driver Basarili");
        } catch (ClassNotFoundException ex) {
            System.out.println("Driver Bulunamadi...");
        }
        
        try {
            con=DriverManager.getConnection(url,admin_ad,admin_pass);
            System.out.println("Baglanti Basarili...");
        } catch (SQLException ex) {
            System.out.println(ex);
            System.out.println("Veri Tabanina Baglanti Basarisiz...");
        }
        
    }
 /*
       
public static void main(String[] args) {
        
        Connector db=new Connector();

}
    */
}
