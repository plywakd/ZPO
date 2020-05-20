/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package lab08;

public class Lab08 {

    public static void main(String[] args) {
        /*java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new NameForm().setVisible(true);
            }
        });*/
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new Calc().setVisible(true);
            }
        });
    }
    
}
