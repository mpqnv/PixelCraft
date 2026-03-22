/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.pixelcraft;
import java.awt.image.BufferedImage; 

/**
 * Produces a negative of the image by inverting each colour channel:
 * {@code channel' = 255 - channel}. The alpha channel is preserved.
 *
 * This achieves an effect similar to a photographic negative, which meaning dark areas
 * become light and vice versa, while colours are replaced by their complements.
 *
 * Implementation strategy: purely recursive, no loops used anywhere.
 * Rows are traversed via {@link #processRow}, which recurses on {@code y + 1}
 * until all rows are processed. Within each row, pixels are traversed via
 * {@link #processPixel}, which recurses on {@code x + 1} until the row ends.
 * This two-level approach keeps the maximum stack depth at
 * {@code max(width, height)} rather than {@code width × height}, preventing
 * stack overflow on real world images.
 * @author Alper Diker
 */
public class Invert extends Converter {
    /**
     * Applies the invert (negative) filter to the given image.
     *
     * @param image the source image
     * @return a new inverted image of the same dimensions
     */
    @Override
    protected BufferedImage process(BufferedImage image) {
        int width = image.getWidth();
        int height = image.getHeight();

        // Create a blank output image with the same dimensions and type as the source
        BufferedImage result = new BufferedImage(width, height, image.getType());

        // Begin recursive traversal from the first row
        processRow(image, result, 0, width, height);
        return result;
    }

    /**
     * Recursively processes one row at a time in top to bottom order.
     * Delegates per pixel work to {@link #processPixel}, then recurses
     * to the next row. Terminates when {@code y} reaches {@code height}.
     *
     * @param src    the source image (read only)
     * @param dst    the destination image (written to)
     * @param y      the current row index
     * @param width  image width
     * @param height image height
     */
    private void processRow(BufferedImage src, BufferedImage dst, int y, int width, int height) {
        // Base case
        if (y >= height) return;

        // Process every pixel in this row, starting from the leftmost column
        processPixel(src, dst, 0, y, width, height);

        // Move on to the next row
        processRow(src, dst, y + 1, width, height);
    }

    /**
     * Recursively processes one pixel at a time across a single row,
     * left to right. Inverts the red, green, and blue channels of each
     * pixel while preserving alpha. Terminates when {@code x} reaches
     * {@code width}.
     *
     * @param src    the source image (read only)
     * @param dst    the destination image (written to)
     * @param x      the current column index
     * @param y      the current row index
     * @param width  image width
     * @param height image height
     */
    private void processPixel(BufferedImage src, BufferedImage dst, int x, int y, int width, int height) {
        // Base case
        if (x >= width) return;

        // Unpack the packed ARGB integer into individual channels
        int rgb = src.getRGB(x, y);
        int a = (rgb >> 24) & 255;
        int r = (rgb >> 16) & 255;
        int g = (rgb >> 8)  & 255;
        int b =  rgb        & 255;

        // Invert each colour channel by subtracting from 255; leave alpha unchanged
        dst.setRGB(x, y, (a << 24) | ((255 - r) << 16) | ((255 - g) << 8) | (255 - b));

        // Move on to the next pixel in this row
        processPixel(src, dst, x + 1, y, width, height);
    }
}