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
public class gradient extends JPanel {

    public gradient() {
        setOpaque(false);
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);

        Graphics2D g2 = (Graphics2D) g.create();

        g2.setRenderingHint(RenderingHints.KEY_RENDERING, RenderingHints.VALUE_RENDER_QUALITY);
        g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);

        int width = getWidth();
        int height = getHeight();

        float[] dist = {0.0f, 0.3f, 0.6f, 1.0f};

        Color[] colors = {
            new Color(140, 60, 255),
            new Color(100, 80, 255),
            new Color(60, 140, 220),
            new Color(0, 200, 255)
        };

        LinearGradientPaint gradient = new LinearGradientPaint(
                0, 0,
                width, height,
                dist,
                colors
        );

        g2.setPaint(gradient);
        g2.fillRect(0, 0, width, height);

        g2.dispose();
    }
}