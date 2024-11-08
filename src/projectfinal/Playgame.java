/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package projectfinal;


import java.awt.*;
import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
/**
 *
 * @author user
 */
public class Playgame extends JFrame implements ActionListener{
    Home home = new Home();
    State1 state1 = new State1();
    
    
    
    
    public Playgame (){
        this.setSize(1200,700);
        this.add(home);
        home.jbtStart.addActionListener(this);
        home.jbtExit.addActionListener(this);
    }
    
    
    public void actionPerformed(ActionEvent e){
        if(e.getSource()==home.jbtStart){
            this.setLocationRelativeTo(null);
            this.remove(home);
            this.setSize(1200,700);
            this.add(state1);
            this.revalidate();
            this.repaint();
            state1.requestFocusInWindow();
            
            
            
        }else if(e.getSource()== home.jbtExit){
            System.exit(0);
        }
    }
    
    
    
    public static void main(String[] args) {
        JFrame f= new Playgame();
        f.setTitle("APPLE GURL");
        f.setSize(1200,700);
        f.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        f.setLocationRelativeTo(null);
        f.setVisible(true);
    }
}
