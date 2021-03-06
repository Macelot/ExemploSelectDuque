/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.exemploselectduque;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.InputStreamReader;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;

/**
 *
 * @author marce
 */
public class NewJFrame extends javax.swing.JFrame {
    
    Conexao conexao;
    Connection c;
    /**
     * Creates new form NewJFrame
     */
    public NewJFrame() {
        initComponents();
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jButton1 = new javax.swing.JButton();
        jButton2 = new javax.swing.JButton();
        jScrollPane1 = new javax.swing.JScrollPane();
        jTable1 = new javax.swing.JTable();
        jTextField1 = new javax.swing.JTextField();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        jButton1.setText("Criar Base");
        jButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton1ActionPerformed(evt);
            }
        });

        jButton2.setText("Mostra Paises");
        jButton2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton2ActionPerformed(evt);
            }
        });

        jTable1.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null}
            },
            new String [] {
                "Title 1", "Title 2", "Title 3", "Title 4"
            }
        ));
        jScrollPane1.setViewportView(jTable1);

        jTextField1.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                jTextField1KeyReleased(evt);
            }
        });

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(jButton1)
                        .addGap(18, 18, 18)
                        .addComponent(jButton2)
                        .addGap(18, 18, 18)
                        .addComponent(jTextField1))
                    .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 375, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(53, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(15, 15, 15)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jButton1)
                    .addComponent(jButton2)
                    .addComponent(jTextField1, javax.swing.GroupLayout.PREFERRED_SIZE, 34, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(63, 63, 63)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 247, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(55, Short.MAX_VALUE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1ActionPerformed
        // TODO add your handling code here:
        conexao = new Conexao();

        c=conexao.conecta();
        if(c!=null){
            JOptionPane.showMessageDialog(this, "Sucesso");
            File arq=new File("Endereco.sql");
            try {
               FileInputStream fis = new FileInputStream(arq);
               InputStreamReader isr = new InputStreamReader(fis, "UTF-8");
               BufferedReader bf = new BufferedReader(isr);
               String linha;
               linha=bf.readLine();
               StringBuffer sb=new StringBuffer();
               while(linha!=null){
                   if( (!linha.startsWith("--"))&& (!linha.startsWith("/*")) ){
                       //System.out.println(linha);
                       sb.append(linha);
                   }
                   linha=bf.readLine();
                   
               }
               String[] intrucoes;
               intrucoes = sb.toString().split(";");
               System.out.println("Temos "+intrucoes.length);
               Statement stmt; 
               stmt = c.createStatement();
               for (int i = 0; i < intrucoes.length; i++) {
                    if(!intrucoes[i].trim().equals("")){
                        stmt.executeUpdate(intrucoes[i]+";");
                        //System.out.println("->"+intrucoes[i]);
                    }
               }
               JOptionPane.showMessageDialog(this, "Base Criada");
               
            } catch (Exception e) {
                e.printStackTrace();
                
            }
            
        }else{
            JOptionPane.showMessageDialog(this, "Deu Erro");
        }
    }//GEN-LAST:event_jButton1ActionPerformed

    private void jButton2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton2ActionPerformed
        // TODO add your handling code here:
        conexao = new Conexao();
        c=conexao.conecta();
        String comando;
        comando = "SELECT * FROM framework.pais_tela WHERE descricao like '%"+jTextField1.getText()+"%' OR codigoBacen like '%"+jTextField1.getText()+"%'";
        try {
            Statement stmt;
            stmt = c.createStatement();
            ResultSet rs;
            rs = stmt.executeQuery(comando);
            Object[] col;
            Object[][] lin;
            col = new Object[2];
            lin=new Object[246][2];
            int cont=0;
            while(rs.next()){
                lin[cont][0]=rs.getString("descricao");
                lin[cont][1]=rs.getString("codigoBacen");
                cont++;
            }
            DefaultTableModel df;
            df = new DefaultTableModel(lin, col);
            jTable1.setModel(df);
          
        } catch (Exception e) {
            e.printStackTrace();
        }
    }//GEN-LAST:event_jButton2ActionPerformed

    private void jTextField1KeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_jTextField1KeyReleased
        // TODO add your handling code here:
        conexao = new Conexao();
        c=conexao.conecta();
        String comando;
        comando = "SELECT * FROM framework.pais_tela WHERE descricao like '%"+jTextField1.getText()+"%' OR codigoBacen like '%"+jTextField1.getText()+"%'";
        try {
            Statement stmt;
            stmt = c.createStatement();
            ResultSet rs;
            rs = stmt.executeQuery(comando);
            Object[] col;
            Object[][] lin;
            col = new Object[2];
            lin=new Object[246][2];
            int cont=0;
            while(rs.next()){
                lin[cont][0]=rs.getString("descricao");
                lin[cont][1]=rs.getString("codigoBacen");
                cont++;
            }
            DefaultTableModel df;
            df = new DefaultTableModel(lin, col);
            jTable1.setModel(df);
          
        } catch (Exception e) {
            e.printStackTrace();
        }
    }//GEN-LAST:event_jTextField1KeyReleased

    /**
     * @param args the command line arguments
     */
    public static void main(String args[]) {
        /* Set the Nimbus look and feel */
        //<editor-fold defaultstate="collapsed" desc=" Look and feel setting code (optional) ">
        /* If Nimbus (introduced in Java SE 6) is not available, stay with the default look and feel.
         * For details see http://download.oracle.com/javase/tutorial/uiswing/lookandfeel/plaf.html 
         */
        try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException ex) {
            java.util.logging.Logger.getLogger(NewJFrame.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(NewJFrame.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(NewJFrame.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(NewJFrame.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new NewJFrame().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton jButton1;
    private javax.swing.JButton jButton2;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JTable jTable1;
    private javax.swing.JTextField jTextField1;
    // End of variables declaration//GEN-END:variables
}
