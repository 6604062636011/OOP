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
import javax.swing.*;
import java.awt.*;

import javax.swing.*;
import java.awt.*;

public class Platform {
    public int[] x = {900, 600, 400, 150, 0}; 
    public int[] y = {520, 480, 360, 250, 400}; 
    public ImageIcon[] p = new ImageIcon[5]; 

    Platform() {
        for (int i = 0; i < p.length; i++) {
            String path = "src/pic/Plat0" + i + ".png";
            ImageIcon pl = new ImageIcon(path);

            if (pl.getImageLoadStatus() != MediaTracker.COMPLETE) {
                System.out.println("Image load failed: " + path);
            }

            Image plIm = pl.getImage().getScaledInstance(200, 30, Image.SCALE_SMOOTH);
            p[i] = new ImageIcon(plIm);
        }
    }
}
