/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tv.sonis.autotyper;

import java.awt.AWTException;
import java.awt.Color;
import java.awt.Robot;
import static java.awt.event.KeyEvent.*;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import static java.lang.System.exit;
import static tv.sonis.autotyper.AutoTyper.error;
import static tv.sonis.autotyper.AutoTyper.sleep;
import com.tulskiy.keymaster.common.HotKey;
import com.tulskiy.keymaster.common.HotKeyListener;
import com.tulskiy.keymaster.common.Provider;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.KeyStroke;

/**
 *
 * @author Leach
 */
public class AutoTyperApplication extends javax.swing.JFrame implements Runnable {

    private Robot robot;
    private Keyboard keyboard;
    private int wait = 250;
    private int repeat = 0;
    private char[] content;
    public static AutoTyperApplication ata;
    public static Thread typeprocess;
    public static boolean isOn = false;
    public static Provider provider = Provider.getCurrentProvider(false);
    public static HotKeyToggler stateF7 = new HotKeyToggler(KeyStroke.getKeyStroke(118, 0, true));
    public static HotKeyToggler stateF8 = new HotKeyToggler(KeyStroke.getKeyStroke(119, 0, true));

    /**
     * Creates new form AutoTyperApplication
     */
    public AutoTyperApplication() {
        initComponents();
        setTitle("AutoTyper Application by Skaviouz");
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jScrollPane1 = new javax.swing.JScrollPane();
        jTextArea1 = new javax.swing.JTextArea();
        jPanel1 = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();
        howManyLinesField = new javax.swing.JTextField();
        jLabel2 = new javax.swing.JLabel();
        waitTimeField = new javax.swing.JTextField();
        jLabel3 = new javax.swing.JLabel();
        repeatField = new javax.swing.JTextField();
        jButton1 = new javax.swing.JButton();
        jLabel4 = new javax.swing.JLabel();
        jLabel5 = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        jTextArea1.setColumns(20);
        jTextArea1.setRows(5);
        jScrollPane1.setViewportView(jTextArea1);

        jPanel1.setBorder(javax.swing.BorderFactory.createTitledBorder("Statistics"));

        jLabel1.setText("How many lines:");

        howManyLinesField.setText("1000");
        howManyLinesField.setEnabled(false);

        jLabel2.setText("Wait between(sec):");

        waitTimeField.setText("0.25");
        waitTimeField.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                waitTimeFieldKeyReleased(evt);
            }
        });

        jLabel3.setText("Repeat:");

        repeatField.setText("0");
        repeatField.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyTyped(java.awt.event.KeyEvent evt) {
                repeatFieldKeyTyped(evt);
            }
        });

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addComponent(jLabel1)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(howManyLinesField))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addComponent(jLabel2)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(waitTimeField, javax.swing.GroupLayout.DEFAULT_SIZE, 38, Short.MAX_VALUE))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addComponent(jLabel3)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(repeatField)))
                .addContainerGap())
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(howManyLinesField, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel1))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel2)
                    .addComponent(waitTimeField, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel3)
                    .addComponent(repeatField, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        jButton1.setText("Recount Text");

        jLabel4.setText("Press F7 to start/pause bot");

        jLabel5.setText("Press F8 to restart bot");

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jScrollPane1)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                        .addComponent(jLabel4)
                        .addGap(18, 18, 18)
                        .addComponent(jLabel5)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 209, Short.MAX_VALUE)
                        .addComponent(jButton1)))
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 244, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jButton1)
                    .addComponent(jLabel4)
                    .addComponent(jLabel5))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void waitTimeFieldKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_waitTimeFieldKeyReleased
        try {
            float f = Float.parseFloat(waitTimeField.getText()) * 1000;
            wait = (int) f;
            waitTimeField.setForeground(Color.black);
        } catch (NumberFormatException nfe) {
            waitTimeField.setForeground(Color.red);
        }
    }//GEN-LAST:event_waitTimeFieldKeyReleased

    private void repeatFieldKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_repeatFieldKeyTyped
        try {
            repeat = Integer.parseInt(repeatField.getText());
            repeatField.setForeground(Color.black);
        } catch (NumberFormatException nfe) {
            repeatField.setForeground(Color.red);
        }
    }//GEN-LAST:event_repeatFieldKeyTyped


    /**
     * @param args the command line arguments
     */
    public static void main(String args[]) {

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new AutoTyperApplication().setVisible(true);
            }
        });
        ata = new AutoTyperApplication("Location not needed");
        typeprocess = new Thread(ata);
    }

    public void run() {
        boolean neverEnd = true;
        while (neverEnd) {
            boolean running = false;
            stateF7.isOn = false;
            stateF8.isOn = false;
            if (stateF7.isOn == true) {
                jLabel4.setForeground(Color.red);
            } else {
                jLabel4.setForeground(Color.blue);
            }
            if (stateF8.isOn == true) {
                jLabel5.setForeground(Color.red);
            } else {
                jLabel5.setForeground(Color.blue);
            }
            /*
             while (running == true) {

             }
             System.out.println("Starting in 10...");
             if (provider != null) {
             System.out.println("Press F5 to pause or resume typing.");
             }
             sleep(10.0D);

             typer.Type();
             if (provider != null) {
             provider.reset();
             provider.stop();
             }
             System.out.println("Finished typing!");*/
        }
        System.exit(0);
    }

    public void Type() throws IllegalArgumentException {
        for (char character : content) {
            try {
                keyboard.type(character);
            } catch (Exception ex) {
            }
        }
        robot.keyPress(VK_ENTER);
        robot.keyRelease(VK_ENTER);
        keyboard.type("--Autotyped by Mudkip's Autotyper");
        robot.keyPress(VK_CONTROL);
        robot.keyRelease(VK_CONTROL);
        robot.keyPress(VK_ENTER);
        robot.keyRelease(VK_ENTER);
        robot.keyPress(VK_CONTROL);
        robot.keyRelease(VK_CONTROL);
        robot.keyPress(VK_RIGHT);
        robot.keyRelease(VK_RIGHT);
        robot.keyPress(VK_ENTER);
        robot.keyRelease(VK_ENTER);
    }

    public static void sleep(int time) {
        try {
            Thread.sleep((long) time * 1);
        } catch (InterruptedException e) {
        }
    }

    public AutoTyperApplication(String filename) {
        if (jTextArea1 == null) {
            content = new char[0];
        } else {
            content = new char[jTextArea1.getText().length()];
            content = jTextArea1.getText().toCharArray();
        }
        keyboard = new Keyboard(robot);
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JTextField howManyLinesField;
    private javax.swing.JButton jButton1;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JTextArea jTextArea1;
    private javax.swing.JTextField repeatField;
    private javax.swing.JTextField waitTimeField;
    // End of variables declaration//GEN-END:variables

}