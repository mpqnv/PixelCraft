/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.pixelcraft;
import java.awt.image.BufferedImage;
import java.awt.Color; 
/**
 * Applies a warm sepia tone effect to the image, giving it an aged,
 * brownish appearance similar to old photographs.
 *
 * The transformation uses the standard sepia tone matrix:
 * 
 *   outR = clamp(0.393*R + 0.769*G + 0.189*B)
 *   outG = clamp(0.349*R + 0.686*G + 0.168*B)
 *   outB = clamp(0.272*R + 0.534*G + 0.131*B)
 * 
 * The alpha channel is preserved unchanged.
 *
 * Implementation strategy: iterative (nested for loops over every pixel).
 * @author Alper Diker
 */
public class Sepia extends Converter {
    /**
     * Applies a sepia tone filter to the given image.
     *
     * @param image the source image
     * @return a new sepia-toned image of the same dimensions
     */
     @Override
    protected BufferedImage process(BufferedImage image) {
        int width = image.getWidth();
        int height = image.getHeight();

        // Create the new image to return
        BufferedImage sepiaImage = new BufferedImage(width, height, image.getType());

        // Go through every pixel in the image
        for (int y = 0; y < height; y++) {
            for (int x = 0; x < width; x++) {
                int rgb = image.getRGB(x, y);
                Color color = new Color(rgb, true);

                // Get individual color channels
                int red = color.getRed();
                int green = color.getGreen();
                int blue = color.getBlue();

                // Apply the sepia transformation formulas
                int newRed = (int)(0.393 * red + 0.769 * green + 0.189 * blue);
                int newGreen = (int)(0.349 * red + 0.686 * green + 0.168 * blue);
                int newBlue = (int)(0.272 * red + 0.534 * green + 0.131 * blue);

                // Check for overflow (must stay between 0 and 255)
                if (newRed > 255) newRed = 255;
                if (newGreen > 255) newGreen = 255;
                if (newBlue > 255) newBlue = 255;
                if (newRed < 0) newRed = 0;
                if (newGreen < 0) newGreen = 0;
                if (newBlue < 0) newBlue = 0;

                // Set the new color to the pixel
                Color sepiaColor = new Color(newRed, newGreen, newBlue, color.getAlpha());
                sepiaImage.setRGB(x, y, sepiaColor.getRGB());
            }
        }

        return sepiaImage;
    }
}
