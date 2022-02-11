/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Interfaz;

import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.Icon;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JOptionPane;

/**
 *
 * @author HP
 */


public class JFLaberinto extends javax.swing.JFrame {

    int n=8;
    JButton[][] laberinto;
    int tipo=0;
    boolean banderaInicio=false;
    boolean banderaFin=false;
    int[][] val=new int[n][n];
    ImageIcon raton = new ImageIcon(getClass().getResource("/Imagenes/ratonP.png"));
    ImageIcon queso = new ImageIcon(getClass().getResource("/Imagenes/quesoP.png"));
    public JFLaberinto() {
        initComponents();
        setMatriz();
        this.setResizable(false);
        
    }
    
    public void setMatriz(){
        int x=10,y=10;
        laberinto=new JButton[n][n];
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                laberinto[i][j]=new JButton();
                
                laberinto[i][j].setBackground(Color.white);
                laberinto[i][j].setBounds(x, y, 50, 50);
                val[i][j]=0;
                
                ButtonController bt = new ButtonController();
                laberinto[i][j].addActionListener(bt);
                
                jPLaberinto.add(laberinto[i][j]);
                x+=47;
            }
            x=10;
            y+=47;
        }
        
       
    }
     private class ButtonController implements ActionListener{

        @Override
        public void actionPerformed(ActionEvent e) {
            for (int i = 0; i < n; i++) {
                for (int j = 0; j < n; j++) {
                    if (e.getSource().equals(laberinto[i][j])) {
                         
                        switch(tipo){
                            case 0://blanco
                                if(val[i][j]==1){
                                    banderaInicio=false;
                                }
                                if(val[i][j]==2){
                                    banderaFin=false;
                                }
                                laberinto[i][j].setIcon(null);
                                laberinto[i][j].setBackground(Color.white);
                                val[i][j]=tipo;
                                System.out.println(tipo);
                                break;
                            case 1://inicio
                                if(val[i][j]==2){
                                    banderaFin=false;
                                }
                                if (banderaInicio) {
                                    JOptionPane.showMessageDialog(null,"Ya existe un inicio");  
                                }else{
                                    laberinto[i][j].setIcon(raton);
                                    laberinto[i][j].setBackground(Color.white);
                                    
                                    val[i][j]=tipo;
                                     System.out.println(tipo);
                                    banderaInicio=true;
                                }
                                
                                break;
                            case 2://final
                                if(val[i][j]==1){
                                    banderaInicio=false;
                                }
                                if (banderaFin) {
                                    JOptionPane.showMessageDialog(null,"Ya existe un fin");  
                                }else{
                                    laberinto[i][j].setIcon(queso);
                                    laberinto[i][j].setBackground(Color.white);
                                    val[i][j]=tipo;
                                    System.out.println(tipo);
                                    banderaFin=true;
                                }
                                break;
                            
                            case 5:
                                if(val[i][j]==1){
                                    banderaInicio=false;
                                }
                                if(val[i][j]==2){
                                    banderaFin=false;
                                }
                                laberinto[i][j].setIcon(null);
                                laberinto[i][j].setBackground(Color.black);
                                val[i][j]=tipo;
                                System.out.println(tipo);
                                break;
                        }
                        
                    }
                }
            }
        }
            
     }
    

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel1 = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();
        jPLaberinto = new javax.swing.JPanel();
        jBInicio = new javax.swing.JButton();
        jBMuro = new javax.swing.JButton();
        jFin = new javax.swing.JButton();
        jBBlanco = new javax.swing.JButton();
        jBCamino = new javax.swing.JButton();
        jBVaciar = new javax.swing.JButton();
        jButton1 = new javax.swing.JButton();
        jLabel2 = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        jPanel1.setBackground(new java.awt.Color(255, 153, 51));

        jLabel1.setFont(new java.awt.Font("Elephant", 0, 36)); // NOI18N
        jLabel1.setText("Laberinto");

        jPLaberinto.setBackground(new java.awt.Color(255, 255, 255));

        javax.swing.GroupLayout jPLaberintoLayout = new javax.swing.GroupLayout(jPLaberinto);
        jPLaberinto.setLayout(jPLaberintoLayout);
        jPLaberintoLayout.setHorizontalGroup(
            jPLaberintoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 402, Short.MAX_VALUE)
        );
        jPLaberintoLayout.setVerticalGroup(
            jPLaberintoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 401, Short.MAX_VALUE)
        );

        jBInicio.setBackground(new java.awt.Color(255, 255, 255));
        jBInicio.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Imagenes/ratonP.png"))); // NOI18N
        jBInicio.setText("Inicio");
        jBInicio.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jBInicioActionPerformed(evt);
            }
        });

        jBMuro.setBackground(new java.awt.Color(255, 255, 255));
        jBMuro.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Imagenes/muro.png"))); // NOI18N
        jBMuro.setText("Muro");
        jBMuro.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jBMuroActionPerformed(evt);
            }
        });

        jFin.setBackground(new java.awt.Color(255, 255, 255));
        jFin.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Imagenes/quesoP.png"))); // NOI18N
        jFin.setText("Fin");
        jFin.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jFinActionPerformed(evt);
            }
        });

        jBBlanco.setBackground(new java.awt.Color(255, 255, 255));
        jBBlanco.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Imagenes/borrar.png"))); // NOI18N
        jBBlanco.setText("Borrar");
        jBBlanco.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jBBlancoActionPerformed(evt);
            }
        });

        jBCamino.setBackground(new java.awt.Color(255, 255, 255));
        jBCamino.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Imagenes/foco.png"))); // NOI18N
        jBCamino.setText("Buscar Camino");

        jBVaciar.setBackground(new java.awt.Color(255, 255, 255));
        jBVaciar.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Imagenes/basuraP.png"))); // NOI18N
        jBVaciar.setText("Vaciar");
        jBVaciar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jBVaciarActionPerformed(evt);
            }
        });

        jButton1.setBackground(new java.awt.Color(255, 255, 255));
        jButton1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Imagenes/salida.png"))); // NOI18N
        jButton1.setText("Salir");
        jButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton1ActionPerformed(evt);
            }
        });

        jLabel2.setText("Agregar:");

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGap(214, 214, 214)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addComponent(jPLaberinto, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addGroup(jPanel1Layout.createSequentialGroup()
                                        .addGap(81, 81, 81)
                                        .addComponent(jBCamino))
                                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                            .addComponent(jBVaciar, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 117, javax.swing.GroupLayout.PREFERRED_SIZE)
                                            .addComponent(jButton1, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 117, javax.swing.GroupLayout.PREFERRED_SIZE)))))
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(jLabel2)
                                    .addGroup(jPanel1Layout.createSequentialGroup()
                                        .addComponent(jBInicio)
                                        .addGap(18, 18, 18)
                                        .addComponent(jBMuro)
                                        .addGap(18, 18, 18)
                                        .addComponent(jFin)))
                                .addGap(14, 14, 14)
                                .addComponent(jBBlanco))))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGap(331, 331, 331)
                        .addComponent(jLabel1)))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGap(41, 41, 41)
                .addComponent(jLabel1)
                .addGap(5, 5, 5)
                .addComponent(jLabel2)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jBInicio)
                    .addComponent(jBMuro)
                    .addComponent(jBBlanco)
                    .addComponent(jFin))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 42, Short.MAX_VALUE)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addComponent(jBCamino)
                        .addGap(18, 18, 18)
                        .addComponent(jBVaciar)
                        .addGap(18, 18, 18)
                        .addComponent(jButton1)
                        .addGap(24, 24, 24))
                    .addComponent(jPLaberinto, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(72, 72, 72))
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, Short.MAX_VALUE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void jBInicioActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jBInicioActionPerformed
        tipo=1;
        
        
    }//GEN-LAST:event_jBInicioActionPerformed

    private void jBMuroActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jBMuroActionPerformed
        tipo=5;
    }//GEN-LAST:event_jBMuroActionPerformed

    private void jFinActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jFinActionPerformed
        tipo=2;
        
    }//GEN-LAST:event_jFinActionPerformed

    private void jBBlancoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jBBlancoActionPerformed
        tipo=0;
    }//GEN-LAST:event_jBBlancoActionPerformed

    private void jBVaciarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jBVaciarActionPerformed
        //int c=0;
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
            
                laberinto[i][j].setBackground(Color.white);
                val[i][j]=0;
                //c++;
                //System.out.println(c);
            }
        }
    }//GEN-LAST:event_jBVaciarActionPerformed

    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1ActionPerformed
        System.exit(EXIT_ON_CLOSE);
    }//GEN-LAST:event_jButton1ActionPerformed

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
            java.util.logging.Logger.getLogger(JFLaberinto.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(JFLaberinto.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(JFLaberinto.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(JFLaberinto.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new JFLaberinto().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton jBBlanco;
    private javax.swing.JButton jBCamino;
    private javax.swing.JButton jBInicio;
    private javax.swing.JButton jBMuro;
    private javax.swing.JButton jBVaciar;
    private javax.swing.JButton jButton1;
    private javax.swing.JButton jFin;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JPanel jPLaberinto;
    private javax.swing.JPanel jPanel1;
    // End of variables declaration//GEN-END:variables
}
