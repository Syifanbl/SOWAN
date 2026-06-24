/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.sowan.swn.swing;



import javax.swing.*;
import java.awt.*;

public class gradient2 extends JPanel {
    
    public gradient2() {
        setOpaque(false);
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);

        Graphics2D g2 = (Graphics2D) g.create();

        // biar halus
        g2.setRenderingHint(RenderingHints.KEY_RENDERING, RenderingHints.VALUE_RENDER_QUALITY);
        g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);

        int width = getWidth();
        int height = getHeight();

        // distribusi warna (smooth)
        float[] dist = {0.0f, 0.3f, 0.6f, 1.0f};

        // warna gradient biru soft (sesuai gambar)
        Color[] colors = {
            new Color(233, 244, 252),  // atas (putih kebiruan)
            new Color(210, 230, 245),  // transisi halus
            new Color(161, 214, 255),  // biru soft
            new Color(130, 180, 220)   // bawah (biru lebih dalam)
        };

        // gradient vertikal (atas → bawah)
        LinearGradientPaint gradient = new LinearGradientPaint(
                0, 0,
                0, height,
                dist,
                colors
        );

        g2.setPaint(gradient);
        g2.fillRect(0, 0, width, height);

        g2.dispose();
    }
}