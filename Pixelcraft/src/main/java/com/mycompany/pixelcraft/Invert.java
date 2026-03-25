/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.pixelcraft;

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
public class Invert extends RecursiveConverter {
 /**
     * Transforms a single pixel by inverting each RGB channel using
     * {@code channel' = 255 - channel}. The alpha channel is extracted
     * and repacked unchanged to preserve transparency.
     *
     * @param pixel the source pixel as a packed ARGB {@code int}
     * @return a new packed ARGB {@code int} with all RGB channels inverted
     */
    @Override
    protected int transformPixel(int pixel) {
    //Create a new ARGB object
    ARGB p = new ARGB(pixel);
    
    //Return the final value
    return new ARGB(
        p.alpha,
        255 - p.red,
        255 - p.green,
        255 - p.blue
    ).toInt();
    }
}