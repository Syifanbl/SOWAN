/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.sowan.swn.swing;


import javax.swing.*;
import java.awt.*;

public class gradient3 extends JPanel {

    private int cornerRadius = 30;

    public gradient3() {
        setOpaque(false);
        setBackground(new Color(255, 255, 255)); // warna default (putih)
    }

    public gradient3(int radius) {
        this.cornerRadius = radius;
        setOpaque(false);
        setBackground(new Color(255, 255, 255));
    }

    public void setCornerRadius(int radius) {
        this.cornerRadius = radius;
        repaint();
    }

    @Override
    protected void paintComponent(Graphics g) {
        Graphics2D g2 = (Graphics2D) g.create();

        // biar halus
        g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);

        // isi warna panel
        g2.setColor(getBackground());
        g2.fillRoundRect(0, 0, getWidth(), getHeight(), cornerRadius, cornerRadius);

        g2.dispose();

        super.paintComponent(g);
    }
}