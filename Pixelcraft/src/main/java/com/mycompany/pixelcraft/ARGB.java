/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.pixelcraft;

/**
 * *Represents a pixel's colour using the ARGB colour model.
 * Each channel (alpha, red, green, blue) is stored as an integer in the range [0, 250]/
 * Provides conversion to and from the package integer format used by BufferedImage.
 * @author Koosha Shamdani
 */
public class ARGB {
    
    public int alpha, red, green, blue; 
    
    /**
     * Constructs an ARGB instance by unpacking a signle packed integer pixel value
     * 
     * @param pixel the packed ARGB integer (as returned by BufferedImage.getRGB)
     */
    
    public ARGB (int pixel){
        this.alpha = (pixel >> 24) & 0xff; 
        this.red   = (pixel >> 16) & 0xff; 
        this.green = (pixel >> 8)  & 0xff; 
        this.blue  = pixel         & 0xff;      
    }
    
    /**
     * Constructs an ARGB instance from individial channel values.
     * 
     * @param a alpha channel (0 is fully transparent and 255 is fully opeque)
     * @param r red channel (0-255)
     * @param g green channel (0 - 255)
     * @param b blue channel (0 - 255)
     */
    
    public ARGB(int a, int r, int g, int b){
        this.alpha = a; 
        this.red   = r; 
        this.green = g; 
        this.blue  = b; 
    }
    
    /**
     * Packs the four channels back into a single integer that can be used for BufferedImage.setRGB.
     * 
     * @return the packed ARGB integer.
     */
    
    public int toInt(){
        return (this.alpha << 24) | (this.red << 16) | (this.green << 8) | this.blue; 
    }
    
    /**
     * Clamps a value to the valid pixel channel range [0, 255].
     * 
     * @param value the raw value to clamp
     * @return the channel value
     */
    public static int clamp(int value){
        return Math.max(0, Math.min(255, value)); 
    }
}