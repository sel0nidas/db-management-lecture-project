import java.awt.Color;
import java.security.NoSuchAlgorithmException;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Date;
import java.util.logging.Level;
import java.util.logging.Logger;

public class loginPage extends javax.swing.JFrame {
    
    public static String kullaniciadi;
    
    public loginPage() {
        initComponents();
    }
    
    private static Connector db = DatabaseInstance.getDb();
    
    private String query;

    
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        user_name = new javax.swing.JTextField();
        jLabel1 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        user_loginBTN = new javax.swing.JButton();
        user_pass = new javax.swing.JPasswordField();
        info_message = new javax.swing.JLabel();
        jLabel6 = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setTitle("Kullanici Girisi");
        setPreferredSize(new java.awt.Dimension(1280, 720));
        setResizable(false);
        setSize(new java.awt.Dimension(1280, 720));

        jLabel1.setText("Email");

        jLabel2.setText("Şifre");

        user_loginBTN.setText("Giriş Yap");
        user_loginBTN.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                user_loginBTNActionPerformed(evt);
            }
        });

        info_message.setText(" ");

        jLabel6.setFont(new java.awt.Font("Segoe UI", 1, 24)); // NOI18N
        jLabel6.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel6.setText("GİRİŞ YAP");

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 450, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(jLabel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(jLabel2, javax.swing.GroupLayout.PREFERRED_SIZE, 70, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(18, 18, 18)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(user_name)
                            .addComponent(user_pass)
                            .addComponent(user_loginBTN, javax.swing.GroupLayout.PREFERRED_SIZE, 180, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(62, 62, 62)
                        .addComponent(info_message, javax.swing.GroupLayout.PREFERRED_SIZE, 364, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(61, 61, 61))
                    .addComponent(jLabel6, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addGap(20, 20, 20))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap(100, Short.MAX_VALUE)
                .addComponent(jLabel6, javax.swing.GroupLayout.PREFERRED_SIZE, 96, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(info_message)
                        .addGap(249, 249, 249))
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(user_name, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel1))
                        .addGap(34, 34, 34)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(user_pass, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel2))
                        .addGap(18, 18, 18)
                        .addComponent(user_loginBTN, javax.swing.GroupLayout.PREFERRED_SIZE, 29, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(281, 281, 281))))
        );

        pack();
        setLocationRelativeTo(null);
    }// </editor-fold>//GEN-END:initComponents
    
    /* Giriş Yap Butonuna Tıklandığında yapılacak işlemler */
    private void user_loginBTNActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_user_loginBTNActionPerformed
        
        try {
            
            
            /* Mevcut online kullanıcıların offline'a döndürülmesi için yapılan SQL sorgusu */
            
            /*
            query = "UPDATE accounts SET account_online = 0 where account_online = 1";
            db.preState=db.con.prepareStatement(query);
            db.preState.executeUpdate();
            */
            
            String name=user_name.getText();
            
            /* Şifrenin text field'dan alınıp MD5 formatıyla hash'lenmesi */
            String pass=new String(user_pass.getPassword());
            /*
            try {
                pass = md5Hash.toHexString(md5Hash.getSHA(pass));
            } catch (NoSuchAlgorithmException ex) {
                Logger.getLogger(loginPage.class.getName()).log(Level.SEVERE, null, ex);
            }
            */
            
            /* Kullanıcının veritabanında yer alıp almadığını kontrol eden SQL sorgusu */
            query = "SELECT * FROM kullanici where kullaniciadi = ? and sifre =  ?";
            db.preState=db.con.prepareStatement(query);
            db.preState.setString(1, name);
            db.preState.setString(2, pass);
            ResultSet rs=db.preState.executeQuery();
            System.out.println(rs);
            if(rs != null && rs.next()){
                info_message.setForeground(new Color(37, 146, 67));
                info_message.setText("Kullanıcı girisi basarili!");
                
                kullaniciadi = rs.getString("kullaniciadi");
                
                Date date = new Date();    
                java.sql.Date sDate = new java.sql.Date(date.getTime());
                
                //EmployeeManager.onLogin(account_emp_id);
                
                /* Kullanıcının veritabanındaki accounts tablosundaki id'si aracılığıyla en son giriş yaptığı zaman güncellenir */
                /*
                query = "UPDATE accounts SET account_online = 1 where account_emp_id = ?";
                db.preState=db.con.prepareStatement(query);
                db.preState.setInt(1, account_emp_id);
                db.preState.executeUpdate();
                */
                
            } else {
                
                info_message.setText("Kullanici girisi basarisiz!");
                info_message.setForeground(new Color(255, 0, 0));
                return;
            }
            
            /*  
            Başarılı Girişi Ana Menüye Yönlendirme
            */
            mainMenu main=new mainMenu();
            setVisible(false);
            main.addWindowListener(new java.awt.event.WindowAdapter() {
            @Override
            public void windowClosed(java.awt.event.WindowEvent evt) {
                System.exit(0);
            }
            });
            main.mainInject();
            
        } catch (SQLException ex) {
            
            info_message.setText("Kullanici girisi basarisiz!");
            info_message.setForeground(new Color(255, 0, 0));
            Logger.getLogger(loginPage.class.getName()).log(Level.SEVERE, null, ex);
        }
        
    }//GEN-LAST:event_user_loginBTNActionPerformed
    
    
    /**
     * @param args the command line arguments
     */
   

public static void main(String[] args) {
    new loginPage().setVisible(true);
    
}

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JLabel info_message;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JButton user_loginBTN;
    private javax.swing.JTextField user_name;
    private javax.swing.JPasswordField user_pass;
    // End of variables declaration//GEN-END:variables
}
