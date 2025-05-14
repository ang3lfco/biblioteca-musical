/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package ui.componentes;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import javax.swing.plaf.basic.BasicComboBoxUI;
import java.awt.*;
/**
 *
 * @author ang3lfco
 */
public class RoundedComboBox<E> extends JComboBox<E> {

    public RoundedComboBox(E[] items) {
        super(items);
        setOpaque(false);
        setBackground(new Color(18, 25, 44));
        setForeground(Color.WHITE);
        setUI(new RoundedComboBoxUI());
        setBorder(new EmptyBorder(5, 10, 5, 10));
        setFocusable(false);
        
        setRenderer(new DefaultListCellRenderer() {
            @Override
            public Component getListCellRendererComponent(JList<?> list, Object value, int index,
                                                          boolean isSelected, boolean cellHasFocus) {
                JLabel label = (JLabel) super.getListCellRendererComponent(list, value, index, isSelected, cellHasFocus);
                label.setOpaque(true);
                label.setFont(new Font("Segoe UI", Font.PLAIN, 13));
                label.setBackground(isSelected ? new Color(30, 38, 58) : new Color(18, 25, 44));
                label.setForeground(Color.WHITE);
                label.setBorder(new EmptyBorder(5, 10, 5, 10));
                return label;
            }
        });
    }

    @Override
    protected void paintComponent(Graphics g) {
        if (!isOpaque()) {
            int width = getWidth();
            int height = getHeight();
            Graphics2D g2 = (Graphics2D) g.create();
            g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
            g2.setColor(getBackground());
            g2.fillRoundRect(0, 0, width, height, 20, 20);
            g2.dispose();
        }
        super.paintComponent(g);
    }

    private static class RoundedComboBoxUI extends BasicComboBoxUI {
        @Override
        protected JButton createArrowButton() {
            JButton button = new JButton("▼");
            button.setBorder(BorderFactory.createEmptyBorder());
            button.setContentAreaFilled(false);
            button.setFocusPainted(false);
            button.setOpaque(false);
            button.setForeground(Color.WHITE);
            return button;
        }

        @Override
        public void paintCurrentValueBackground(Graphics g, Rectangle bounds, boolean hasFocus) {
        }
    }
}