/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.exemploselectduque;

import java.sql.Connection;
import java.sql.DriverManager;

/**
 *
 * @author marce
 */
public class Conexao {
    
    public Connection conecta(){
        Connection c=null;
        String url,user,pass;
        url="jdbc:mysql://localhost:3307/test";
        user="root";
        pass="usbw";
        try {
            c=DriverManager.getConnection(url, user, pass);
        } catch (Exception e) {
            e.printStackTrace();
        }
        
        return c;
    }
    
}
