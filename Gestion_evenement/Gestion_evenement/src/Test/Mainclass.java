/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Test;

import Entitties.Evenement.Evenement;
import Services.EvenementCrud;
import Tools.Myconnexion;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;


/**
 *
 * @author ZIZOU
 */
public class Mainclass {
    public static void main(String[] args) throws ParseException {
        Myconnexion mc = new Myconnexion();
        EvenementCrud ecd = new EvenementCrud();
        String s ="02/02/2020";
        String s2 = "07/02/2020";
        SimpleDateFormat t= new SimpleDateFormat("dd/mm/yyyy");
        Date date_debut = t.parse(s);        
        Date date_fin = t.parse(s2);
        //System.out.println(date_debut);
        Evenement e1=new Evenement("aziz",date_debut,date_fin,"tennis","competition","grombalia");
        ecd.addEvent(e1);
    }
 
}
