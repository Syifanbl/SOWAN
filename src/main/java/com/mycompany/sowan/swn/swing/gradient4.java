/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.sowan.swn.swing;

import javax.swing.*;
import java.awt.*;


/**
 *
 * @author syifa
 */
public class gradient4 extends JPanel {
    private int cornerRadius = 30;
    
    public gradient4(int radius) {
        this.cornerRadius = radius;
        setBackground(new Color(255, 255, 255));
        setOpaque(false);
    }
    

    public gradient4() {
        setBackground(new Color(255, 255, 255));
        setOpaque(false);
    }
    
    public void setCornerRadius(int radius) {
        this.cornerRadius = radius;
        repaint();
    }

    @Override
    protected void paintComponent(Graphics g) {
    super.paintComponent(g);

    Graphics2D g2 = (Graphics2D) g.create();
    g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);

    int w = getWidth();
    int h = getHeight();
    int r = cornerRadius; // Gunakan radius yang Anda inginkan (misal: 30)

    // Siapkan Gradien seperti sebelumnya
    float[] dist = {0.0f, 0.3f, 0.6f, 1.0f};
    Color[] colors = {
        new Color(140, 60, 255),
        new Color(100, 80, 255),
        new Color(60, 140, 220),
        new Color(0, 200, 255)
    };
    LinearGradientPaint gradient = new LinearGradientPaint(0, 0, w, h, dist, colors);
    g2.setPaint(gradient);

    // --- MULAI MEMBUAT PATH KUSTOM (Sesuai Diagram) ---
    java.awt.geom.Path2D path = new java.awt.geom.Path2D.Double();
    
    // 1. Move to start (top-left)
    path.moveTo(0, 0); 
    
    // 2. Line to bottom-left
    path.lineTo(0, h); 
    
    // 3. Line to bottom-right
    path.lineTo(w, h); 
    
    // 4. Line up to start of curve
    path.lineTo(w, r); 
    
    // 5. Draw the curve for top-right corner
    path.curveTo(w, r, w, 0, w - r, 0); 
    
    // 6. Close the path back to top-left
    path.closePath(); 

    // --- SELESAI MEMBUAT PATH, SEKARANG ISI DENGAN GRADIENT ---
    g2.fill(path);

    g2.dispose();
}
}