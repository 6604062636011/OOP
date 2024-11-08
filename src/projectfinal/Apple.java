/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package projectfinal;
import java.awt.*;
import javax.swing.*;

/**
 *
 * @author user
 */
public class Apple {
    public int x,y;
    public boolean collected =false;
    private Image appleIm;
    
    public Apple(int x,int y){
        this.x=x;
        this.y=y;
        
        ImageIcon appleIcon = new ImageIcon("src/pic/apple.png");
        this.appleIm = appleIcon.getImage().getScaledInstance(50, 50, Image.SCALE_SMOOTH);
    }
    
    
    public void draw(Graphics g, JPanel panel){
        if(!collected){
            g.drawImage(appleIm, x, y, panel);
        }
    }
    
    public Rectangle getBounds(){
        return new Rectangle(x,y,35,35);
    }
}
