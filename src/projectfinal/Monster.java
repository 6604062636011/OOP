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
public class Monster {
    public int x,y;
    private int j=0;
    private final ImageIcon[] monsterIcon;
    private final int speed=5;
    
    public Monster(int x,int y){
        this.x=x;
        this.y=y;
        monsterIcon = new ImageIcon[3];
        monsterIcon[0] = new ImageIcon("src/pic/monster2 (1).png");
        monsterIcon[1] = new ImageIcon("src/pic/monster1 (1).png");
        monsterIcon[2] = new ImageIcon("src/pic/monster3 (1).png");
        
        for(int i=0;i<monsterIcon.length;i++){
            Image monsIm = monsterIcon[i].getImage().getScaledInstance(50, 50, Image.SCALE_SMOOTH);
            monsterIcon[i] = new ImageIcon(monsIm);
        }
    }
    public void animate(){
        j =(j+1)%(monsterIcon.length*speed);
    }
    public void drawMons(Graphics g, JPanel panel){
        g.drawImage(monsterIcon[j/speed].getImage(), x, y, panel);
    }
    public Rectangle getBounds(){
        return new Rectangle(x,y,30,30);
    }
}
