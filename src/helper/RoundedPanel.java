/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package helper;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.RenderingHints;
import javax.swing.JPanel;

/**
 *
 * @author macbook
 */



public class RoundedPanel extends JPanel {




    private int cornerRadius = 20;

    private Color backgroundColor = Color.WHITE;




    public RoundedPanel() {

        setOpaque(false);

    }




    public RoundedPanel(int radius, Color bgColor) {

        cornerRadius = radius;

        backgroundColor = bgColor;

        setOpaque(false);

    }




    @Override

    protected void paintComponent(Graphics g) {

        Graphics2D g2 = (Graphics2D) g.create();




        g2.setRenderingHint(

                RenderingHints.KEY_ANTIALIASING,

                RenderingHints.VALUE_ANTIALIAS_ON);




        g2.setColor(backgroundColor);

        g2.fillRoundRect(

                0,

                0,

                getWidth(),

                getHeight(),

                cornerRadius,

                cornerRadius);




        g2.dispose();




        super.paintComponent(g);

    }

}
