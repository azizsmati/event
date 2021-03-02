/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Tools;

import com.mysql.jdbc.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author ZIZOU
 */
public class Myconnexion {
    public String url="jdbc:mysql://localhost:3306/gestionevenement";
    public String login="root";
    public String pwd="";
    public Connection conn;

public Myconnexion()
{
        try {
          conn= (Connection) DriverManager.getConnection(url,login,pwd);
                      System.out.println("connexion etablie");

        } catch (SQLException ex) {
         System.out.println("Erreur de connexion");
         System.out.println(ex.getMessage());
        }
}
}