/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.pixelcraft;

/**
 *Applies a warm sepia tone effect to the image, giving it an aged, brownish
 * appearance similar to old photographs
 * 
 * Strategy: iterative (nested for loops over every pixel. 
 * @author Alper Diker
 */
import java.awt.image.BufferedImage;
import java.awt.Color; 
public class Sepia extends Converter {

    @Override
    protected BufferedImage process(BufferedImage image) {
        int width = image.getWidth();
        int height = image.getHeight();

        BufferedImage sepiaImage = new BufferedImage(width, height, image.getType());

        for (int y = 0; y < height; y++) {
            for (int x = 0; x < width; x++) {
                int rgb = image.getRGB(x, y);
                Color color = new Color(rgb);

                int red = color.getRed();
                int green = color.getGreen();
                int blue = color.getBlue();

                
                int newRed = (int)(0.393 * red + 0.769 * green + 0.189 * blue);
                int newGreen = (int)(0.349 * red + 0.686 * green + 0.168 * blue);
                int newBlue = (int)(0.272 * red + 0.534 * green + 0.131 * blue);

                
                if (newRed > 255) newRed = 255;
                if (newGreen > 255) newGreen = 255;
                if (newBlue > 255) newBlue = 255;
                if (newRed < 0) newRed = 0;
                if (newGreen < 0) newGreen = 0;
                if (newBlue < 0) newBlue = 0;

                Color sepiaColor = new Color(newRed, newGreen, newBlue);
                sepiaImage.setRGB(x, y, sepiaColor.getRGB());
            }
        }

        return sepiaImage;
    }
}
