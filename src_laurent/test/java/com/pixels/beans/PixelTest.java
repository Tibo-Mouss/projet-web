package com.pixels.beans;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.awt.Color;

import org.apache.commons.lang3.SerializationUtils;

public class PixelTest {

    private static Pixel pixel;

    @BeforeAll
    public static void setUp() throws Exception {
        pixel = new Pixel(10, new Color(255, 255, 255), "pixel de test");
    }

    @Test
    public void beanIsSerializable() {
        final byte[] serializedPixel = SerializationUtils.serialize(pixel);
        final Pixel deserializedPixel = (Pixel) SerializationUtils.deserialize(serializedPixel);
        assertEquals(pixel, deserializedPixel);
    }

}
