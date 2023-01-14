
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;

/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */

/**
 *
 * @author Selahattin
 */
public class user {
    public String kullaniciadi;
    public String isim;
    public String soyisim;
    public String telefonno;
    public int wallet;

    public user(String kullaniciadi, String isim, String soyisim, String telefonno, int wallet) {
        this.kullaniciadi = kullaniciadi;
        this.isim = isim;
        this.soyisim = soyisim;
        this.telefonno = telefonno;
        this.wallet = wallet;
    }
    
    
    
    ArrayList<user> userInfo = new ArrayList<>();
    private static Connector db=DatabaseInstance.getDb();
    private String query="SELECT * FROM kullanici";
    
    public String getKullaniciadi() {
        return kullaniciadi;
    }

    public String getIsim() {
        return isim;
    }

    public String getSoyisim() {
        return soyisim;
    }

    public String getTelefonno() {
        return telefonno;
    }

    public int getWallet() {
        return wallet;
    }

    
    
    //Boş Constructor
    public user() {

    }
    
    public ArrayList<user> prepareuser() {
        try {
            db.preState=db.con.prepareStatement(query);
         
            ResultSet rs=db.preState.executeQuery();
            while(rs.next()){
                userInfo.add(new user(rs.getString("kullaniciadi"),rs.getString("isim"), rs.getString("soyisim"), rs.getString("telefonno"), rs.getInt("cuzdan")));
            }
        } catch (SQLException ex) {
            System.out.println(ex);
        }
        return userInfo;
    }
    
    public static void main(String[] args) {
        user u = new user();
        ArrayList<user> users = u.prepareuser();
        for (user user : users) {
            System.out.println(user.getKullaniciadi() + ": " + user.getIsim() + " " + user.getSoyisim() + " " + user.getTelefonno() + " " + user.getWallet());
        }
    }
    
}
