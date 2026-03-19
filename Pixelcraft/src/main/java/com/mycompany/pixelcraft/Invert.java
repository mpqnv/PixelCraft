/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.pixelcraft;
import java.awt.image.BufferedImage; 

/**
 *Produces a negative of the image by inverting each color channel.
 * @author Alper Diker
 */
public class Invert extends Converter {

    @Override
    protected BufferedImage process(BufferedImage image) {
        // Get the dimensions of the original image
        int width = image.getWidth();
        int height = image.getHeight();

        // Create a new image to store the inverted result
        BufferedImage result = new BufferedImage(width, height, image.getType());

        // Loop through every pixel in the image (row by row)
        for (int y = 0; y < height; y++) {
            for (int x = 0; x < width; x++) {

                // Get the RGB value of the current pixel
                int rgb = image.getRGB(x, y);

                // Extract alpha, red, green, and blue values using bit shifting
                int a = (rgb >> 24) & 255; // transparency
                int r = (rgb >> 16) & 255; // red
                int g = (rgb >> 8) & 255;  // green
                int b = rgb & 255;         // blue

                // Invert each color channel
                int invR = 255 - r;
                int invG = 255 - g;
                int invB = 255 - b;

                // Combine the inverted values back into a single RGB integer
                int invertedRgb = (a << 24) | (invR << 16) | (invG << 8) | invB;

                // Set the new pixel in the result image
                result.setRGB(x, y, invertedRgb);
            }
        }

        // Return the final inverted image
        return result;
    }
}