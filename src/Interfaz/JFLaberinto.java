/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Interfaz;

import Negocio.Animacion;
import Negocio.Laberinto;

import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.animation.Animation;
import javax.swing.Icon;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JOptionPane;

/**
 * Una clase para representar una interfaz gráfica que corresponde
 * a un juego interactivo en donde el usuario puede construir un laberinto
 * a voluntad, de forma que al distribuir sus elementos (ratón (inicio),queso(llegada),
 * muros (celdas inalcanzables) y celdas vacías), el usuario cuenta con la funcionalidad 
 * de encontrar el mejor camino posible (más óptimo). 
 * @version 1.0, 28/02/22
 * @author Leines Eduardo, Montalvo Emilio, Matute Israel GR11
 */


public class JFLaberinto extends javax.swing.JFrame {

	//VARIABLES
    int n=8;                        // Dimensión Filas-Columnas 
    JButton[][] laberinto;          // Matriz de botónes para la construcción de celdas del laberinto
    int tipo=0;                     // Entero de control del tipo de celda del laberinto
    boolean banderaInicio=false;    // Booleano de Control de Inicio
    boolean banderaFin=false;       // Booleano de Control de Final
    int[][] val=new int[n][n];      // Matriz de valores asociados a cada cela del laberinto
    ImageIcon raton = new ImageIcon(getClass().getResource("/Imagenes/ratonP.png")); // Icono correspondiente al ratón
    ImageIcon queso = new ImageIcon(getClass().getResource("/Imagenes/quesoP.png")); // Icono correspondiente al queso
    int iniciox=-1;					// Almacena la coordenada X inicial del ratón
    int inicioy=-1;					// Almacena la coordenada Y inicial del ratón
    int finx=-1;					// Almacena la coordenada X final del ratón
    int finy=-1;					// Almacena la coordenada Y final del ratón
    Animacion h;					// Generación de la Animación de la interfaz
   
    //MÉTODOS PÚBLICOS

    /*
    * Constructor del Laberinto que incializa sus componentes, setea la Matriz y 
    * establece la ventana como no redimensionable, posiciona la ventana
	* y oculta por el momento la ventana jBReiniciar.
    */
   
   
    public JFLaberinto() {
        initComponents();
        setMatriz();
        this.setResizable(false);
        this.setLocationRelativeTo(null);
        this.jBReiniciar.setVisible(false);
    } //Fin del constructor
    
	/**
    * Método que setea la matriz con la dimensión n declarada en un inicio, 
    * cada celda de la matriz (botón) es inicializada, se le otorgan propiedades
    * como color y tamaño y además, se inicializa la matriz de valores asociados
    * con una valor de 0 (estado: vacio= como incial). 
    */
	
    public void setMatriz(){
        int x=10,y=10;
        laberinto=new JButton[n][n];
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                laberinto[i][j]=new JButton();
                
                laberinto[i][j].setBackground(Color.white);
                laberinto[i][j].setBounds(x, y, 48, 48);
                val[i][j]=0;
                
                ButtonController bt = new ButtonController();
                laberinto[i][j].addActionListener(bt);
                
                jPLaberinto.add(laberinto[i][j]);
                x+=47;
            }
            x=10;
            y+=47;
        }   
    }//Cierre del Mëtodo
	
	/**
    * Método sobrecargado que  establece el icono y color de fondo 
	* según el estado de cada celda.
	* Estado 0 -> Color: Blanco¨
	* Estado 1 -> Color: Blanco (Pto de Partida: Ratón)
	* Estado 2 -> Color: Blanco (Pto de Llegada: Queso)
	* Estado 3 -> Color: Amarillo (Recorrido)
	* Estado 5 -> Color: Negro (Muro/Celda Inalcanzable)
¨   * @param matriz
    */
    
    private void setMatriz(int[][] matriz){
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                switch(matriz[i][j]){
                    case 0:
                        laberinto[i][j].setIcon(null);
                        laberinto[i][j].setBackground(Color.white);
                        break;
                    case 1:
                        laberinto[i][j].setIcon(raton);
                        laberinto[i][j].setBackground(Color.white);
                        break;
                    case 2:
                        laberinto[i][j].setIcon(queso);
                        laberinto[i][j].setBackground(Color.white);
                        break;
                    case 3:
                        laberinto[i][j].setIcon(null);
                        laberinto[i][j].setBackground(Color.yellow);
                        break;
                    case 5:
                        laberinto[i][j].setIcon(null);
                        laberinto[i][j].setBackground(Color.black);
                        break;
                }
            }
        }
    }//Cierre del Mëtodo
	
	//CLASE PRIVADA
    /**
    * Una clase privada que implementa métodos con las acciones a tomar a partir
    * de las acciones que el usuario realize sobre la interfaz. Para esto es 
    * necesario la implementación del Interface Action Listener. 
    */
	
     private class ButtonController implements ActionListener{

        @Override
		/**
        * Método que establece las acciones que cada botón causa a partir de su interacción 
        * con el usuario. 
        * @param e
        */
		
        public void actionPerformed(ActionEvent e) {
            for (int i = 0; i < n; i++) {
                for (int j = 0; j < n; j++) {
                    if (e.getSource().equals(laberinto[i][j])) {
                        //Se busca verificar el tipo y estado actual de la celda escogida por el usuario.  
                        switch(tipo){
                            case 0://blanco - celda vacia
                                if(val[i][j]==1){
                                    banderaInicio=false;
                                }
                                if(val[i][j]==2){
                                    banderaFin=false;
                                }
                                laberinto[i][j].setIcon(null);
                                laberinto[i][j].setBackground(Color.white);
                                val[i][j]=tipo;
                                //System.out.println(tipo);
                                break;
                            case 1://inicio - ratón
                                
                                if (banderaInicio) {
                                    JOptionPane.showMessageDialog(null,"Ya existe un inicio");  
                                }else{
                                    if(val[i][j]==1){
                                    banderaInicio=false;
                                    }
                                    if(val[i][j]==2){
                                    banderaFin=false;
                                    }
                                    laberinto[i][j].setIcon(raton);
                                    laberinto[i][j].setBackground(Color.white);
                                    
                                    val[i][j]=tipo;
                                    iniciox=i;
                                    inicioy=j;
                                     //System.out.println(tipo);
                                    banderaInicio=true;
                                }
                                
                                break;
                            case 2://final - queso
                                
                                if (banderaFin) {
                                    JOptionPane.showMessageDialog(null,"Ya existe un fin");  
                                }else{
                                    if(val[i][j]==1){
                                    banderaInicio=false;
                                    }
                                    if(val[i][j]==2){
                                    banderaFin=false;
                                    }
                                    laberinto[i][j].setIcon(queso);
                                    laberinto[i][j].setBackground(Color.white);
                                    val[i][j]=tipo;
                                    finx=i;
                                    finy=j;
                                    //System.out.println(tipo);
                                    banderaFin=true;
                                }
                                break;
                            
                            case 5://Muro - celda inalcanzble 
                                if(val[i][j]==1){
                                    banderaInicio=false;
                                }
                                if(val[i][j]==2){
                                    banderaFin=false;
                                }
                                laberinto[i][j].setIcon(null);
                                laberinto[i][j].setBackground(Color.black);
                                val[i][j]=tipo;
                                //System.out.println(tipo);
                                break;
                        }
                        
                    }
                }
            }
        }//Cierre del Método
            
     }//Cierre de la Clase 
    

    /**
     * Este método al ser llamado por el constructor para inicilizar los
     * componentes de la interfaz gráfica. 
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
        jBSalir = new javax.swing.JButton();
        jLabel2 = new javax.swing.JLabel();
        jBReiniciar = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setTitle("Laberinto");

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
        jBCamino.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jBCaminoActionPerformed(evt);
            }
        });

        jBVaciar.setBackground(new java.awt.Color(255, 255, 255));
        jBVaciar.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Imagenes/basuraP.png"))); // NOI18N
        jBVaciar.setText("Vaciar");
        jBVaciar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jBVaciarActionPerformed(evt);
            }
        });

        jBSalir.setBackground(new java.awt.Color(255, 255, 255));
        jBSalir.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Imagenes/salida.png"))); // NOI18N
        jBSalir.setText("Salir");
        jBSalir.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jBSalirActionPerformed(evt);
            }
        });

        jLabel2.setText("Agregar:");

        jBReiniciar.setBackground(new java.awt.Color(255, 255, 255));
        jBReiniciar.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Imagenes/atras-en-el-tiempo.png"))); // NOI18N
        jBReiniciar.setText("Reiniciar");
        jBReiniciar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jBReiniciarActionPerformed(evt);
            }
        });

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
                                            .addComponent(jBSalir, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 117, javax.swing.GroupLayout.PREFERRED_SIZE)
                                            .addComponent(jBReiniciar, javax.swing.GroupLayout.Alignment.TRAILING)))))
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
                        .addComponent(jBReiniciar)
                        .addGap(26, 26, 26)
                        .addComponent(jBCamino)
                        .addGap(18, 18, 18)
                        .addComponent(jBVaciar)
                        .addGap(18, 18, 18)
                        .addComponent(jBSalir)
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
            .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents
	//FIN DE MÉTODO

    /**
    * Método que setea el tipo celda como inicio / punto de paritda del ratón 
    * @param evt
    */
    private void jBInicioActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jBInicioActionPerformed
        tipo=1;
        
        
    }//GEN-LAST:event_jBInicioActionPerformed
    //FIN DE MÉTODO
	
    /**
    * Método que setea el tipo celda como muro / celda inalcanzable
    * @param evt
    */	
    private void jBMuroActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jBMuroActionPerformed
        tipo=5;
    }//GEN-LAST:event_jBMuroActionPerformed
	//FIN DE MÉTODO
	
    /**
    * Método que setea el tipo celda de llegada/ posición del queso
    * @param evt
    */	
    private void jFinActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jFinActionPerformed
        tipo=2;
        
    }//GEN-LAST:event_jFinActionPerformed
	//FIN DE MÉTODO
	
	/**
    * Método que setea el tipo celda como vacia /disponible 
    * @param evt
    */
    private void jBBlancoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jBBlancoActionPerformed
        tipo=0;
    }//GEN-LAST:event_jBBlancoActionPerformed
    //FIN DE MÉTODO
	
    /**
    * Método que vacia el tablero y reestablece el valor inicial de todas las celdas
    * @param evt
    */	
    private void jBVaciarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jBVaciarActionPerformed
        //int c=0;
        banderaFin=false;
        banderaInicio=false;
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
            
                laberinto[i][j].setBackground(Color.white);
                laberinto[i][j].setIcon(null);
                val[i][j]=0;
                //c++;
                //System.out.println(c);
            }
        }
    }//GEN-LAST:event_jBVaciarActionPerformed
	//FIN DE MÉTODO
	
	/**
    * Método para cerrar la ventana/finalizar el programa
    * @param evt
    */
    private void jBSalirActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jBSalirActionPerformed
        System.exit(EXIT_ON_CLOSE);
    }//GEN-LAST:event_jBSalirActionPerformed
	//FIN DE MÉTODO 
	
	/**
    * Método para imprimir el camino más óptimo a tomar
	* haciendo uso de la animación definida en un inicio. 
    * @param evt
    */
	
    private void jBCaminoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jBCaminoActionPerformed

    if(banderaFin & banderaInicio){  //Si el camino fue completado
       
       int[][]matriz=new int[n][n];
       //int[][]aux=new int[n][n];
       for (int x=0; x<val.length; x++) {
            for (int y=0; y<val[0].length; y++) {
                matriz[x][y]=val[x][y];
                //aux[x][y]=val[x][y];
            }
       }
       
       //Laberinto laux=new Laberinto(aux);
      
       Laberinto l=new Laberinto(matriz);
       
         h=new Animacion(laberinto, n, raton, queso, l, iniciox, inicioy);
         h.start();
        
         
         
       jBCamino.setEnabled(false);
       jBVaciar.setEnabled(false);
       jBInicio.setEnabled(false);
       jBSalir.setEnabled(false);
       jBMuro.setEnabled(false);
       jFin.setEnabled(false);
       jBBlanco.setEnabled(false);
       jBReiniciar.setVisible(true);
       
         
       
     }else{ //Warning
         JOptionPane.showMessageDialog(null,"Debe colocar el fin e inicio");
     }
    }//GEN-LAST:event_jBCaminoActionPerformed
	//FIN DE METODO
	
	/**
    * Método para reiniciar el programa y limpiar la interfaz 
    * @param evt
    */

    private void jBReiniciarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jBReiniciarActionPerformed
        h.stop();
        setMatriz(val);
        jBCamino.setEnabled(true);
       jBVaciar.setEnabled(true);
       jBInicio.setEnabled(true);
       jBSalir.setEnabled(true);
       jBMuro.setEnabled(true);
       jFin.setEnabled(true);
       jBBlanco.setEnabled(true);
       jBReiniciar.setVisible(false);
    }//GEN-LAST:event_jBReiniciarActionPerformed
	//FIN DE METODO

     /**
     * El main() del programa desde donde se corre el programa 
     * @param args the command line arguments
     */
	
    public static void main(String args[]) {
		//Nimbus es una apariencia multiplataforma de JAVA. 
		//Nimnbus usa grafica de vectores 2D para la contrucción de la interfaz de usuario
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
			//Método que muestra la ventana de la interfaz dando inicio al programa
            public void run() {
                new JFLaberinto().setVisible(true);
            }
        });
    }
    /*
    public boolean hayCamino(int[][]m,int finx,int finy){
        
        //muros arriba
        for (int i = 0; i < m.length; i++) {
            
        }
        
        
        
        return false;
    }
   
   */

	//VARIABLES PRIVADAS
    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton jBBlanco;
    private javax.swing.JButton jBCamino;
    private javax.swing.JButton jBInicio;
    private javax.swing.JButton jBMuro;
    private javax.swing.JButton jBReiniciar;
    private javax.swing.JButton jBSalir;
    private javax.swing.JButton jBVaciar;
    private javax.swing.JButton jFin;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JPanel jPLaberinto;
    private javax.swing.JPanel jPanel1;
    // End of variables declaration//GEN-END:variables
}
