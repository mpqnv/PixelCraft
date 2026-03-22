/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.pixelcraft;
import java.awt.image.BufferedImage;

/**
 * Increases the brightness of the image by adding a fixed offset to each
 * colour channel (red, green, and blue). Values are clamped to [0, 255] so
 * that no channel overflows. The alpha channel is left unchanged.
 *
 * A positive {@value #DELTA} brightens the image; a negative value would darken it.
 *
 * Implementation strategy: purely recursive, no loops used anywhere.
 * Rows are traversed via {@link #processRow}, which recurses on {@code y + 1}
 * until all rows are processed. Within each row, pixels are traversed via
 * {@link #processPixel}, which recurses on {@code x + 1} until the row ends.
 * This two level approach keeps the maximum stack depth at
 * {@code max(width, height)} rather than {@code width × height}, preventing
 * stack overflow on real world images.
 *
 * @author Koosha Shamdani
 */
public class Brightness extends Converter {

    /** The amount added to each colour channel per pixel. */
    private static final int DELTA = 50;

    /**
     * Brightens the image by {@value #DELTA} units per colour channel.
     *
     * @param image the source image
     * @return a new brightened image of the same dimensions
     */
    @Override
    protected BufferedImage process(BufferedImage image) {
        int width = image.getWidth();
        int height = image.getHeight();

        // Create a blank output image with the same dimensions as the source
        BufferedImage result = new BufferedImage(width, height, BufferedImage.TYPE_INT_ARGB);

        // Begin recursive traversal from the first row
        processRow(image, result, 0, width, height);
        return result;
    }

    /**
     * Recursively processes one row at a time in top to bottom order.
     * Delegates per pixel work to {@link #processPixel}, then recurses
     * to the next row. Terminates when {@code y} reaches {@code height}.
     *
     * @param src    the source image (read-only)
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
     * left to right. Adds {@value #DELTA} to each colour channel, clamping
     * results to [0, 255] via {@link ARGB#clamp(int)}. The alpha channel is
     * preserved unchanged. Terminates when {@code x} reaches {@code width}.
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

        // Unpack the pixel and add DELTA to each colour channel, clamping to [0, 255]
        ARGB p = new ARGB(src.getRGB(x, y));
        ARGB bright = new ARGB(
            p.alpha,
            ARGB.clamp(p.red   + DELTA),
            ARGB.clamp(p.green + DELTA),
            ARGB.clamp(p.blue  + DELTA)
        );
        dst.setRGB(x, y, bright.toInt());

        // Move on to the next pixel in this row
        processPixel(src, dst, x + 1, y, width, height);
    }
}
