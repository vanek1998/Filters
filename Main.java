package com.company;

import javax.imageio.ImageIO;
import java.io.File;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import java.awt.image.BufferedImage;

public class Main {

    public static BufferedImage imgBefore;
    public static BufferedImage imgAfter;
    JPanel centerPanel = new JPanel();

    public static void main(String[] args) {
        Main main = new Main();
        main.go();
    }

    public static void OpenIMG(String name){
        try {
            imgBefore = ImageIO.read(new File(name));
            imgAfter = ImageIO.read(new File(name));
        }
        catch (Exception e){

        }
    }

    public void go(){
        JFrame frame = new JFrame();
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        String str = "Безымянный.jpg";

        OpenIMG(str);
        MyDrawP MyImgBefore = new MyDrawP(imgBefore);
        MyDrawP MyImgAfter = new MyDrawP(imgAfter);

        centerPanel.setLayout(new BoxLayout(centerPanel, BoxLayout.X_AXIS));
        centerPanel.add(MyImgBefore);
        centerPanel.add(MyImgAfter);

        JButton original = new JButton("Оригинал");
        original.addActionListener(new Original());
        JButton greyScale = new JButton("Оттенки серого");
        greyScale.addActionListener(new GreyScale());
        JButton negativ = new JButton("Негатив");
        negativ.addActionListener(new Negativ());
        JButton blur = new JButton("Размытие");
        blur.addActionListener(new Blur());
        JButton clarity = new JButton("Резкость");
        clarity.addActionListener(new Clarity());
        JButton median = new JButton("Медианный фильтр");
        median.addActionListener(new Median());
        JButton erosion = new JButton("Эрозия");
        erosion.addActionListener(new Erosion());
        JButton saturation = new JButton("Наращивание");
        saturation.addActionListener(new Saturation());


        JPanel westPanel = new JPanel();
        westPanel.setLayout(new BoxLayout(westPanel, BoxLayout.Y_AXIS));
        westPanel.add(original);
        westPanel.add(greyScale);
        westPanel.add(negativ);
        westPanel.add(blur);
        westPanel.add(clarity);
        westPanel.add(median);
        westPanel.add(erosion);
        westPanel.add(saturation);

        frame.getContentPane().add(BorderLayout.WEST, westPanel);
        frame.getContentPane().add(BorderLayout.CENTER, centerPanel);
        frame.setSize(800, 370);
        frame.setVisible(true);
    }

    public class MyDrawP extends JPanel{
        public BufferedImage img;

        public MyDrawP(BufferedImage img){
            this.img = img;
        }

        public void paintComponent(Graphics g){
            g.drawImage(img, 10,10,300,300, null);
        }
    }

    class Original implements ActionListener{
        public void actionPerformed(ActionEvent e){
            Filters.Original(imgBefore, imgAfter);
            centerPanel.repaint();
        }
    }

    class GreyScale implements ActionListener{
        public void actionPerformed(ActionEvent e){
            Filters.GreyScale(imgBefore, imgAfter);
            centerPanel.repaint();
        }
    }

    class Negativ implements ActionListener{
        public void actionPerformed(ActionEvent e){
            Filters.Negativ(imgBefore, imgAfter);
            centerPanel.repaint();
        }
    }

    class Blur implements ActionListener{
        public void actionPerformed(ActionEvent e){
            Filters.Blur(imgBefore, imgAfter);
            centerPanel.repaint();
        }
    }

    class Clarity implements ActionListener{
        public void actionPerformed(ActionEvent e){
            Filters.Clarity(imgBefore, imgAfter);
            centerPanel.repaint();
        }
    }

    class Median implements ActionListener{
        public void actionPerformed(ActionEvent e){
            Filters.Median(imgBefore, imgAfter);
            centerPanel.repaint();
        }
    }

    class Erosion implements ActionListener{
        public void actionPerformed(ActionEvent e){
            Filters.Erosion(imgBefore, imgAfter);
            centerPanel.repaint();
        }
    }

    class Saturation implements ActionListener{
        public void actionPerformed(ActionEvent e){
            Filters.Saturation(imgBefore, imgAfter);
            centerPanel.repaint();
        }
    }
}