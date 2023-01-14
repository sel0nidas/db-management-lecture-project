
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */

/**
 *
 * @author Selahattin
 */
public class estate {
    private int estateID;
    private int type;
    private int price;
    private int m2;
    private String adres;
    
        
    ArrayList<estate> estateInfo = new ArrayList<>();
    private static Connector db=DatabaseInstance.getDb();
    private String query;//"SELECT * FROM kullanici"

    public estate(int estateID, int type, int price, int m2, String adres) {
        this.estateID = estateID;
        this.type = type;
        this.price = price;
        this.m2 = m2;
        this.adres = adres;
    }

    public int getEstateID() {
        return estateID;
    }

    public int getType() {
        return type;
    }

    public int getPrice() {
        return price;
    }

    public int getM2() {
        return m2;
    }

    public String getAdres() {
        return adres;
    }

    @Override
    public String toString() {
        return "estate{" + "estateID=" + estateID + ", type=" + type + ", price=" + price + ", m2=" + m2 + ", adres=" + adres + ", estateInfo=" + estateInfo + ", query=" + query + '}';
    }
   
    
    

    public estate() {
    }
    
    public ArrayList<estate> prepareEstate() {
        query = "SELECT * FROM tasinmaz";
        try {
            db.preState=db.con.prepareStatement(query);
         
            ResultSet rs=db.preState.executeQuery();
            while(rs.next()){
                estateInfo.add(new estate(rs.getInt("tid"),rs.getInt("tur"), rs.getInt("fiyat"),rs.getInt("metrekare"), rs.getString("adres")));
            }
        } catch (SQLException ex) {
            System.out.println(ex);
        }
        return estateInfo;
    }
    
    public static void main(String[] args) {
        estate e = new estate();
        ArrayList<estate> estates = e.prepareEstate();
        for (estate est : estates) {
            System.out.println(est.toString());
        }
    }
    
}
