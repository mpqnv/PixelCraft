/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.pixelcraft;
import java.awt.image.BufferedImage; 

/**
 * Converts a colour image to grayscale by replacing each pixel's red, green,
 * and blue channels with their arithmetic average, while preserving the original
 * alpha (transparency) channel.
 *
 * Formula: gray = (R + G + B) / 3
 *
 * Implementation strategy: iterative (nested for loops over every pixel).
 * @author Alper Diker
 */
public class Grayscale extends Converter {
    /**
     * Converts the given image to grayscale.
     *
     * @param image the source image
     * @return a new grayscale image of the same dimensions
     */
    @Override
    protected BufferedImage process(BufferedImage image) {
        //Declare the width and height of the image
        int width = image.getWidth();
        int height = image.getHeight();
        
        //Create a new bufferedimage object
        BufferedImage result = new BufferedImage(width, height, BufferedImage.TYPE_INT_ARGB);

        //Itirate through the entire image 
        for (int y = 0; y < height; y++) {
            for (int x = 0; x < width; x++) {
                //Create an ARGB object
                ARGB p = new ARGB(image.getRGB(x, y));

                int avg = (p.red + p.green + p.blue) / 3;
                
                result.setRGB(x, y, new ARGB(p.alpha, avg, avg, avg).toInt());
            }
        }
        //Return the final image
        return result;
    }
}
