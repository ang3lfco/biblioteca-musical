/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package ui.componentes;

import javax.swing.*;
import java.awt.*;

/**
 *
 * @author ang3lfco
 */
public class CustomRoundedTextField extends JPanel {
    private JTextField textField;
    private JLabel iconLabel;
    private String placeholder;
    private Color backgroundColor = new Color(18, 25, 44);
    private Color textColor = Color.WHITE;

    public CustomRoundedTextField(String placeholder, String iconPath) {
        this.placeholder = placeholder;
        setLayout(new BorderLayout());
        setOpaque(false);
//        setPreferredSize(new Dimension(330, 40));
//        setBackground(new Color(18,25,44));

        textField = new JTextField();
        textField.setHorizontalAlignment(JTextField.CENTER);
        textField.setBorder(BorderFactory.createEmptyBorder(5, -23, 5, 10));
        textField.setFont(new Font("SansSerif", Font.PLAIN, 14));
        textField.setOpaque(false);
        textField.setBackground(new Color(0,0,0,0));
        textField.setForeground(textColor);

        textField.setText(placeholder);
        textField.setForeground(textColor);
        textField.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusGained(java.awt.event.FocusEvent e) {
                if (textField.getText().equals(placeholder)) {
                    textField.setText("");
                    textField.setForeground(textColor);
                }
            }

            public void focusLost(java.awt.event.FocusEvent e) {
                if (textField.getText().isEmpty()) {
                    textField.setText(placeholder);
                    textField.setForeground(textColor);
                }
            }
        });

        iconLabel = new JLabel(new ImageIcon(getClass().getResource(iconPath)));
        iconLabel.setBorder(BorderFactory.createEmptyBorder(0, 10, 0, 10));

        add(iconLabel, BorderLayout.WEST);
        add(textField, BorderLayout.CENTER);
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        Graphics2D g2d = (Graphics2D) g;
        g2d.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);

        g2d.setColor(new Color(0, 0, 0, 30));
        g2d.fillRoundRect(5, 5, getWidth() - 10, getHeight() - 10, 25, 25);

        g2d.setColor(backgroundColor);
        g2d.fillRoundRect(0, 0, getWidth(), getHeight(), 25, 25);
    }

    public JTextField getTextField() {
        return textField;
    }

    public String getText() {
        return textField.getText();
    }

    public void setText(String text) {
        textField.setText(text);
    }
    
    public void setBackgroundColor(Color backgroundColor) {
        this.backgroundColor = backgroundColor;
        repaint();
    }
    
    public void setTextColor(Color textColor) {
        this.textColor = textColor;
        textField.setForeground(textColor);
        repaint();
    }
}