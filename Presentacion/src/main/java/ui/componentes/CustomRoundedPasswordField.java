/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package ui.componentes;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.RenderingHints;
import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;

/**
 *
 * @author ang3lfco
 */
public class CustomRoundedPasswordField extends JPanel {
    private JPasswordField passwordField;
    private JLabel iconLabel;
    private Color backgroundColor = new Color(18, 25, 44);
    private Color textColor = Color.WHITE;
    private String placeholder;

    public CustomRoundedPasswordField(String placeholder, String iconPath) {
        this.placeholder = placeholder;
        setLayout(new BorderLayout());
        setOpaque(false);

        passwordField = new JPasswordField();
        passwordField.setEchoChar((char) 0);
        passwordField.setHorizontalAlignment(JTextField.CENTER);
        passwordField.setBorder(BorderFactory.createEmptyBorder(5, -23, 5, 10));
        passwordField.setFont(new Font("SansSerif", Font.PLAIN, 14));
        passwordField.setOpaque(false);
        passwordField.setBackground(new Color(0, 0, 0, 0));
        passwordField.setForeground(textColor);
        passwordField.setText(placeholder);

        passwordField.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusGained(java.awt.event.FocusEvent e) {
                if (String.valueOf(passwordField.getPassword()).equals(placeholder)) {
                    passwordField.setText("");
                    passwordField.setEchoChar('•');
                }
            }

            public void focusLost(java.awt.event.FocusEvent e) {
                if (String.valueOf(passwordField.getPassword()).isEmpty()) {
                    passwordField.setEchoChar((char) 0);
                    passwordField.setText(placeholder);
                }
            }
        });

        iconLabel = new JLabel(new ImageIcon(getClass().getResource(iconPath)));
        iconLabel.setBorder(BorderFactory.createEmptyBorder(0, 10, 0, 10));

        add(iconLabel, BorderLayout.WEST);
        add(passwordField, BorderLayout.CENTER);
    }

    public String getText() {
        return String.valueOf(passwordField.getPassword());
    }

    public void setText(String text) {
        passwordField.setText(text);
    }

    public void setBackgroundColor(Color backgroundColor) {
        this.backgroundColor = backgroundColor;
        repaint();
    }

    public void setTextColor(Color textColor) {
        this.textColor = textColor;
        passwordField.setForeground(textColor);
        repaint();
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
}
