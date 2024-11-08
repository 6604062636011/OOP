/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package projectfinal;
import java.awt.*;
import javax.swing.*;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author user
 */
public class Girl {
    public int x, y;
    public int count = 0;
    public boolean isJumping = false;
    public int velocity = -1;
    public int jumpHeight = 17;
    public ImageIcon[] gr = new ImageIcon[2];
    public int groundY = 500;
    private Platform pf;
    public int bounceS = -60;  

    Girl(Platform pf) {
        this.pf = pf;
        ImageIcon gir1 = new ImageIcon("src/pic/Charac1 (2).png");
        Image girImage1 = gir1.getImage().getScaledInstance(100, 100, Image.SCALE_SMOOTH);
        gr[0] = new ImageIcon(girImage1);

        ImageIcon gir2 = new ImageIcon("src/pic/Charac2 (2).png");
        Image girImage2 = gir2.getImage().getScaledInstance(100, 100, Image.SCALE_SMOOTH);
        gr[1] = new ImageIcon(girImage2);

        x = 0;
        y = groundY;
    }

    public void moveLeft() {
        if (x - 5 >= 0) { 
            x -= 5;
        }
    }

    public void moveRight() {
        if (x + 80 + 5 <= 1200) { 
            x += 5;
        }
    }

    public void jump() {
        if (!isJumping) {
            isJumping = true;
            velocity = -jumpHeight; 
        }
    }

    public void update() {
        if (isJumping) {
            y += velocity;  
            velocity += 1; 

            
            if (isOnPlatform()) {
                if (velocity > 0) { 
                    isJumping = false;
                    velocity = 0;
                    y = getPlatformY();
                }
            }

            
            if (y >= groundY) {
                y = groundY;
                isJumping = false;
                velocity = 0;
            }
        }
    }

    private boolean isOnPlatform() {
        for (int i = 0; i < pf.p.length; i++) {
            int platX = pf.x[i];
            int platY = pf.y[i];

            
            if (x + 80 > platX && x < platX + 200 && y + 100 <= platY && velocity >= 0) {
                return true;
            }
        }
        return false;
    }

    private int getPlatformY() {
        for (int i = 0; i < pf.p.length; i++) {
            int platX = pf.x[i];
            int platY = pf.y[i];
            if (x + 80 > platX && x < platX + 200 && y + 100 <= platY && velocity >= 0) {
                return platY - 100; 
            }
        }
        return groundY;
    }

    public Rectangle getBounds(){
        return new Rectangle(x, y, 80, 100);
    }
}
