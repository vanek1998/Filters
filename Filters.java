package com.company;

import java.awt.*;
import java.awt.image.BufferedImage;
import java.util.Arrays;

public class Filters {

    public static void Original(BufferedImage imgBefore, BufferedImage imgAfter) {
        try {
            for (int i = 0; i < imgBefore.getWidth(); i++) {
                for (int j = 0; j < imgBefore.getHeight(); j++) {
                    Color color = new Color(imgBefore.getRGB(i, j));

                    int blue = color.getBlue();
                    int red = color.getRed();
                    int green = color.getGreen();

                    Color newColor = new Color(red, green, blue);

                    imgAfter.setRGB(i, j, newColor.getRGB());
                }
            }
        }
        catch (Exception e){

        }
    }


    public static void GreyScale(BufferedImage imgBefore, BufferedImage imgAfter) {
        try {
            for (int i = 0; i < imgBefore.getWidth(); i++) {
                for (int j = 0; j < imgBefore.getHeight(); j++) {
                    Color color = new Color(imgBefore.getRGB(i, j));

                    int blue = color.getBlue();
                    int red = color.getRed();
                    int green = color.getGreen();

                    int grey = (int) (red * 0.3 + green * 0.59 + blue * 0.11);

                    Color newColor = new Color(grey, grey, grey);

                    imgAfter.setRGB(i, j, newColor.getRGB());
                }
            }
        }
        catch (Exception e){

        }
    }

    public static void Negativ(BufferedImage imgBefore, BufferedImage imgAfter){
        try {
            for (int i = 0; i < imgBefore.getWidth(); i++) {
                for (int j = 0; j < imgBefore.getHeight(); j++) {
                    Color color = new Color(imgBefore.getRGB(i, j));

                    int blue = 255 - color.getBlue();
                    int red = 255 - color.getRed();
                    int green = 255 - color.getGreen();

                    Color newColor = new Color(red, green, blue);

                    imgAfter.setRGB(i, j, newColor.getRGB());
                }
            }
        }
        catch (Exception e){

        }
    }

    public static void Blur(BufferedImage imgBefore, BufferedImage imgAfter) {
        double[][] matr = {{0.000789,0.006581,0.013347,0.006581,0.000789},
                {0.006581,0.054901,0.111345,0.054901,0.006581},
                {0.013347,0.111345,0.225821,0.111345,0.013347},
                {0.006581,0.054901,0.111345,0.054901,0.006581},
                {0.000789,0.006581,0.013347,0.006581,0.000789}};
        int div = 1;
        try {
            for (int i = 0; i < imgBefore.getWidth(); i++) {
                for (int j = 0; j < imgBefore.getHeight(); j++) {
                    double newRed = 0;
                    double newGreen = 0;
                    double newBlue = 0;

                    for (int r = 0; r < matr.length; r++){
                        for (int c = 0; c < matr[r].length; c++){
                            try{
                                Color color = new Color(imgBefore.getRGB(i - 1 + r, j - 1 + c));
                                newRed += color.getRed() * matr[r][c];
                                newGreen += color.getGreen() * matr[r][c];
                                newBlue += color.getBlue() * matr[r][c];
                            }
                            catch(Exception e){

                            }
                        }
                    }

                    Color newColor = new Color((int)(newRed / div), (int)(newGreen / div), (int)(newBlue / div));

                    imgAfter.setRGB(i, j, newColor.getRGB());
                }
            }
        }
        catch (Exception e){

        }
    }

    public static void Clarity(BufferedImage imgBefore, BufferedImage imgAfter) {
        double[][] matr = {{-1,-1,-1},
                {-1,9,-1},
                {-1,-1,-1}};
        int div = 1;
        try {
            for (int i = 1; i < imgBefore.getWidth() - 1; i++) {
                for (int j = 1; j < imgBefore.getHeight() - 1; j++) {
                    double newRed = 0;
                    double newGreen = 0;
                    double newBlue = 0;

                    for (int r = 0; r < matr.length; r++){
                        for (int c = 0; c < matr[r].length; c++){
                            try{
                                Color color = new Color(imgBefore.getRGB(i - 1 + r, j - 1 + c));
                                newRed += color.getRed() * matr[r][c];
                                newGreen += color.getGreen() * matr[r][c];
                                newBlue += color.getBlue() * matr[r][c];
                            }
                            catch(Exception e){

                            }
                        }
                    }

                    if (newRed > 255)
                        newRed = 255;
                    if (newGreen > 255)
                        newGreen = 255;
                    if (newBlue > 255)
                        newBlue = 255;
                    if (newRed < 0)
                        newRed = 0;
                    if (newGreen < 0)
                        newGreen = 0;
                    if (newBlue < 0)
                        newBlue = 0;

                    Color newColor = new Color((int)(newRed / div), (int)(newGreen / div), (int)(newBlue / div));

                    imgAfter.setRGB(i, j, newColor.getRGB());
                }
            }
        }
        catch (Exception e){

        }
    }

    public static void Median(BufferedImage imgBefore, BufferedImage imgAfter) {
        int matrSize = 25;
        try {
            for (int i = 0; i < imgBefore.getWidth(); i++) {
                for (int j = 0; j < imgBefore.getHeight(); j++) {
                    int[] matrRed = new int[matrSize];
                    int[] matrGreen = new int[matrSize];
                    int[] matrBlue = new int[matrSize];

                    for (int r = 0; r < (int)Math.sqrt(matrSize); r++){
                        for (int c = 0; c < (int)Math.sqrt(matrSize); c++){
                            try{
                                Color color = new Color(imgBefore.getRGB(i - 1 + r, j - 1 + c));
                                matrRed[r*(int)Math.sqrt(matrSize)+c] = color.getRed();
                                matrGreen[r*(int)Math.sqrt(matrSize)+c] += color.getGreen();
                                matrBlue[r*(int)Math.sqrt(matrSize)+c] += color.getBlue();
                            }
                            catch(Exception e){

                            }
                        }
                    }

                    Arrays.sort(matrRed);
                    Arrays.sort(matrGreen);
                    Arrays.sort(matrBlue);

                    Color newColor = new Color(matrRed[(matrSize - 1) / 2], matrGreen[(matrSize - 1) / 2], matrBlue[(matrSize - 1) / 2]);

                    imgAfter.setRGB(i, j, newColor.getRGB());
                }
            }
        }
        catch (Exception e){

        }
    }

    public static void Erosion(BufferedImage imgBefore, BufferedImage imgAfter) {
        int[][] matr = {{0,0,1,0,0},
                {0,1,1,1,0},
                {1,1,1,1,1},
                {0,1,1,1,0},
                {0,0,1,0,0}};
        Color[] colors = new Color[25];

        try {
            for (int i = 1; i < imgBefore.getWidth() - 1; i++) {
                for (int j = 1; j < imgBefore.getHeight() - 1; j++) {
                    int newRed, newGreen, newBlue;

                    for (int r = 0; r < matr.length; r++){
                        for (int c = 0; c < matr[r].length; c++){
                            try{
                                Color color = new Color(imgBefore.getRGB(i - 1 + r, j - 1 + c));
                                newRed = color.getRed() * matr[r][c];
                                newGreen = color.getGreen() * matr[r][c];
                                newBlue = color.getBlue() * matr[r][c];
                                colors[r * matr[r].length + c] = new Color(newRed, newGreen, newBlue);
                            }
                            catch(Exception e){

                            }
                        }
                    }

                    SortColors(colors);

                    imgAfter.setRGB(i, j, colors[12].getRGB());
                }
            }
        }
        catch (Exception e){

        }
    }

    public static void Saturation(BufferedImage imgBefore, BufferedImage imgAfter) {
        int[][] matr = {{0,0,1,0,0},
                {0,1,1,1,0},
                {1,1,1,1,1},
                {0,1,1,1,0},
                {0,0,1,0,0}};
        Color[] colors = new Color[25];

        try {
            for (int i = 1; i < imgBefore.getWidth() - 1; i++) {
                for (int j = 1; j < imgBefore.getHeight() - 1; j++) {
                    int newRed, newGreen, newBlue;

                    for (int r = 0; r < matr.length; r++){
                        for (int c = 0; c < matr[r].length; c++){
                            try{
                                Color color = new Color(imgBefore.getRGB(i - 1 + r, j - 1 + c));
                                newRed = color.getRed() * matr[r][c];
                                newGreen = color.getGreen() * matr[r][c];
                                newBlue = color.getBlue() * matr[r][c];
                                colors[r * matr[r].length + c] = new Color(newRed, newGreen, newBlue);
                            }
                            catch(Exception e){

                            }
                        }
                    }

                    Color britestColor = new Color(0,0,0);
                    for (int k = 0; k < colors.length; k++)
                        if (colors[k].getBlue() + colors[k].getRed() + colors[k].getGreen() > britestColor.getBlue() + britestColor.getRed() + britestColor.getGreen())
                            britestColor = new Color(colors[k].getRed(), colors[k].getGreen(), colors[k].getBlue());

                    imgAfter.setRGB(i, j, britestColor.getRGB());
                }
            }
        }
        catch (Exception e){

        }
    }

    public static void SortColors(Color[] colors){
        for (int i = 0; i < colors.length; i++)
            for (int j = i; j < colors.length; j++){
                int fBright = colors[i].getBlue() + colors[i].getRed() + colors[i].getGreen();
                int sBright = colors[j].getBlue() + colors[j].getRed() + colors[j].getGreen();
                try {
                    if (sBright < fBright) {
                        Color newColor = new Color(colors[i].getRed(), colors[i].getGreen(), colors[i].getBlue());
                        colors[i] = new Color(colors[j].getRed(), colors[j].getGreen(), colors[j].getBlue());
                        colors[j] = newColor;
                    }
                }
                catch(Exception e){

                }

            }
    }
}