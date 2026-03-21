/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.pixelcraft;
import java.awt.image.BufferedImage; 

/**
 *Mirrors the image along its vertical centre axis (left to right / right to left flip).
 * 
 * @author Alper Diker
 */
public class Fliphorizontal extends Converter {

    @Override
    protected BufferedImage process(BufferedImage image) {
        int width = image.getWidth();
        int height = image.getHeight();
        
        // Initialize the output image
        BufferedImage result = new BufferedImage(width, height, image.getType());

        for (int y = 0; y < height; y++) {
            for (int x = 0; x < width; x++) {
                
                // Get the original pixel
                int pixel = image.getRGB(x, y);

                // Calculate the mirror position on the X axis
                int mirroredX = (width - 1) - x;

                // Set the pixel at the new horizontal position
                result.setRGB(mirroredX, y, pixel);
            }
        }

        return result;
    }
}
