/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.pixelcraft;
import java.awt.image.BufferedImage; 

/**
 *Converts a color image to grayscale by replacing erach pixel's red, green, and blue channels with their artihmetic average,
 * while preserving the original alpha (transparency) channel/ 
 * 
 * Formula: gray = (R + G + B) / 3
 * 
 * Strategy: Iterative (nested for loops over every pixel).
 * @author Alper Diker
 */
public class Grayscale extends Converter {

    @Override
    protected BufferedImage process(BufferedImage image) {
        int width = image.getWidth();
        int height = image.getHeight();
        
        // Create a blank image to store our grayscale version
        BufferedImage result = new BufferedImage(width, height, BufferedImage.TYPE_INT_ARGB);

        // Loop through every single pixel row by row
        for (int y = 0; y < height; y++) {
            for (int x = 0; x < width; x++) {
                
                // Pull out the integer value of the pixel at this (x, y) coordinate
                int pixel = image.getRGB(x, y);

                // Use bit shifting to grab the A, R, G, and B values individually.
                // We use & 255 to make sure we only get the last 8 bits for each color.
                int a = (pixel >> 24) & 255;
                int r = (pixel >> 16) & 255;
                int g = (pixel >> 8) & 255;
                int b = pixel & 255;

                // Calculate the average of the three colors to get the gray value
                int avg = (r + g + b) / 3;

                // Now we put them back into a single 32-bit integer.
                int grayPixel = (a << 24) | (avg << 16) | (avg << 8) | avg;

                // Write the new grayscale pixel to our result image
                result.setRGB(x, y, grayPixel);
            }
        }
        
        // Return the finished image back to the Converter
        return result;
    }
}
