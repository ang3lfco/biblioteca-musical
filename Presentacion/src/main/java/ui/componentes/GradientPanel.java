/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package ui.componentes;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.GradientPaint;
import java.awt.Graphics;
import java.awt.Graphics2D;
import javax.swing.JPanel;

/**
 *
 * @author ang3lfco
 */
public class GradientPanel extends JPanel {
    private final Color barColor = new Color(0, 200, 200);
    private final Color gradientStart = new Color(0, 200, 200, 100);
    private final Color gradientEnd = new Color(0, 200, 200, 0);


    public GradientPanel() {
        setOpaque(false); 
        setPreferredSize(new Dimension(188, 30));
    }

    @Override
    protected void paintComponent(Graphics g) {
        Graphics2D g2 = (Graphics2D) g.create();
        int width = getWidth();
        int height = getHeight();

        int barWidth = 10;
        g2.setColor(barColor);
        g2.fillRect(0, 0, barWidth, height);

        GradientPaint gradient = new GradientPaint(
                barWidth, 0, gradientStart,
                width / 2, 0, gradientEnd
        );
        
        g2.setPaint(gradient);
        g2.fillRect(barWidth, 0, width - barWidth, height);
        g2.dispose();
        super.paintComponent(g);
    }
}
