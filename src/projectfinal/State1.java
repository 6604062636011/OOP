/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package projectfinal;

import java.awt.*;
import java.awt.event.*;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import javax.swing.*;
/**
 *
 * @author user
 */
import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.util.ArrayList;

public class State1 extends JPanel {
    private final ImageIcon bgstate1 = new ImageIcon("src/pic/bgState1.png");
    private final Image bg = bgstate1.getImage().getScaledInstance(1200, 700, Image.SCALE_SMOOTH);
    private final ImageIcon bgIcon = new ImageIcon(bg);
    private final ImageIcon gameOverIcon = new ImageIcon("src/pic/Gameover.png");
    private final ImageIcon completeIcon = new ImageIcon("src/pic/Complete.png");

    private boolean gameOver = false;
    private boolean gameComplete = false;
    private boolean moveLeft = false;
    private boolean moveRight = false;

    Home hg = new Home();
    Platform pf = new Platform();
    Girl m = new Girl(pf);
    ArrayList<Apple> apples = new ArrayList<>();
    private int fin = 0;
    ArrayList<Monster> monsters = new ArrayList<>();
    int live = 5;
    private ImageIcon hIcon;

    
    Thread actor = new Thread(() -> {
        while (true) {
            try {
                Thread.sleep(10);
            } catch (Exception e) {
                e.printStackTrace();
            }
            m.update();
            repaint();
        }
    });

    public State1() {
        this.setFocusable(true);
        this.setLayout(null);

        // Initialize apples and monsters
        apples.add(new Apple(300, 550));
        apples.add(new Apple(170, 170));
        apples.add(new Apple(640, 430));
        apples.add(new Apple(680, 430));
        apples.add(new Apple(400, 310));
        apples.add(new Apple(460, 310));
        apples.add(new Apple(50, 350));
        apples.add(new Apple(980, 470));
        apples.add(new Apple(1030, 470));

        monsters.add(new Monster(170, 140));
        monsters.add(new Monster(750, 430));
        monsters.add(new Monster(1080, 470));

        ImageIcon h = new ImageIcon("src/pic/Heart.png");
        Image hImage = h.getImage().getScaledInstance(100, 100, Image.SCALE_SMOOTH);
        hIcon = new ImageIcon(hImage);

        Thread monsLoop = new Thread(() -> {
            while (true) {
                for (Monster mon : monsters) {
                    mon.animate();
                }
                checkMonsCollision();
                repaint();
                try {
                    Thread.sleep(100);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        });
        monsLoop.start();

        // Apple collection and update loop
        Thread AppleLoop = new Thread(() -> {
            while (true) {
                try {
                    Thread.sleep(3);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                checkAppleCollision();
                repaint();
            }
        });
        AppleLoop.start();

        // Character blinking effect (if required)
        Thread blink = new Thread(() -> {
            while (true) {
                try {
                    Thread.sleep(200);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                m.count = (m.count + 1) % 2;
                repaint();
            }
        });
        blink.start();

        // Character movement control via keypress
        Thread actor = new Thread(() -> {
            while (true) {
                try {
                    Thread.sleep(20);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                if (moveLeft) {
                    m.moveLeft();
                }
                if (moveRight) {
                    m.moveRight();
                }
                m.update();
                repaint();
            }
        });

        this.addKeyListener(new KeyAdapter() {
            public void keyPressed(KeyEvent e) {
                int a = e.getKeyCode();
                if (a == KeyEvent.VK_LEFT) {
                    moveLeft = true;
                } else if (a == KeyEvent.VK_RIGHT) {
                    moveRight = true;
                } else if (a == KeyEvent.VK_UP || a == KeyEvent.VK_SPACE) {
                    m.jump();
                }
            }

            public void keyReleased(KeyEvent e) {
                int a = e.getKeyCode();
                if (a == KeyEvent.VK_LEFT) {
                    moveLeft = false;
                } else if (a == KeyEvent.VK_RIGHT) {
                    moveRight = false;
                }
            }
        });
        actor.start();

        // MouseListener to restart the game or show complete screen
        addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                if (gameOver && isClickOnGameOver(e.getX(), e.getY())) {
                    resetGame();
                } else if (gameComplete && isClickOnComplete(e.getX(), e.getY())) {
                    System.exit(0);
                }
            }
        });
    }

    public void checkAppleCollision() {
        for (Apple apple : apples) {
            if (!apple.collected && m.getBounds().intersects(apple.getBounds())) {
                apple.collected = true;
                fin++;
                if (fin == apples.size() && live > 0) {
                    gameComplete = true;
                    repaint();
                }
            }
        }
    }

    public void checkMonsCollision() {
        for (Monster mon : monsters) {
            if (m.getBounds().intersects(mon.getBounds())) {
                live--;
                
                if (live <= 0) {
                    gameOver = true;
                    repaint();
                }
                break;
            }
        }
    }

    private boolean isClickOnGameOver(int x, int y) {
        return x >= 400 && x <= 800 && y >= 200 && y <= 400;
    }

    private boolean isClickOnComplete(int x, int y) {
        return x >= 400 && x <= 800 && y >= 200 && y <= 400;
    }

    private void resetGame() {
        live = 5;
        fin = 0;
        gameOver = false;
        gameComplete = false;
        m.x = 0;
        m.y = m.groundY;
        for (Apple apple : apples) {
            apple.collected = false;
        }
        repaint();
    }

    public void paintComponent(Graphics g) {
        super.paintComponent(g);
        g.drawImage(bgIcon.getImage(), 0, 0, 1200, 700, this);
        g.drawImage(m.gr[m.count].getImage(), m.x, m.y, 80, 100, this);

        // Draw platforms
        for (int i = 0; i < pf.p.length; i++) {
            g.drawImage(pf.p[i].getImage(), pf.x[i], pf.y[i], 200, 30, this);
        }

        // Draw apples
        for (Apple apple : apples) {
            apple.draw(g, this);
        }

        g.setColor(Color.white);
        g.drawString("Apple: " + fin, 50, 50);

        // Draw monsters
        for (Monster mon : monsters) {
            mon.drawMons(g, this);
        }

        // Draw hearts
        for (int i = 0; i < live; i++) {
            g.drawImage(hIcon.getImage(), 10 + (i * 40), 10, 30, 30, this);
        }

        // Draw game over or complete screen
        if (gameOver) {
            g.drawImage(gameOverIcon.getImage(), 400, 200, 400, 200, this);
        } else if (gameComplete) {
            g.drawImage(completeIcon.getImage(), 400, 200, 400, 200, this);
        }
    }
}
