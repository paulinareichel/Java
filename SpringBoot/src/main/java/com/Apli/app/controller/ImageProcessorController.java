package com.laby.laby10.controller;

import org.json.JSONException;
import org.json.JSONObject;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.ResponseStatus;

import javax.imageio.ImageIO;
import javax.servlet.ServletInputStream;
import java.awt.*;
import java.awt.geom.AffineTransform;
import java.awt.image.*;
import java.io.BufferedInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.HashMap;
import java.util.Map;

@ResponseStatus(HttpStatus.NOT_FOUND)
class notFoundExp extends RuntimeException {
    public notFoundExp(String exception) {
        super(exception);
    }
}

public class ImageProcessorController {
    private Map<Integer, BufferedImage> image = new HashMap<>();
    private int counter = 0;

    int setImage(ServletInputStream inputStream) throws IOException {
        InputStream imageStream = new BufferedInputStream(inputStream);
        BufferedImage bufferedImage = ImageIO.read(imageStream);
//        BufferedImage bi = new BufferedImage(bufferedImage.getWidth(), bufferedImage.getHeight(), bufferedImage.TYPE_BYTE_BINARY);
//        bi.createGraphics().drawImage(bufferedImage, 0, 0, null);
//        image.put(counter,bi);
        image.put(counter, bufferedImage);
        counter++;

        return counter - 1;
    }

    void deleteImage(int id) throws notFoundExp {
        if (!image.containsKey(id) || image.get(id) == null) {
            throw new notFoundExp("Red alert! Image not found");
        }

        image.put(id, null);
    }

    JSONObject getImageSize(int id) throws notFoundExp {
        if (!image.containsKey(id) || image.get(id) == null) throw new notFoundExp("Red alert! Image not found");
        JSONObject sizeInfo = new JSONObject();

        try {
            sizeInfo.put("Height", image.get(id).getHeight());
            sizeInfo.put("Width", image.get(id).getWidth());
        } catch (JSONException e) {
        }

        return sizeInfo;
    }

    JSONObject getHistogramImage(int id) throws IOException {
        if (!image.containsKey(id) || image.get(id) == null) {
            throw new notFoundExp("Red alert! Image not found");
        }

        JSONObject histogramInfo = new JSONObject();
        int black = 0, white = 0;
        int R = 0;
        int G = 0;
        int B = 0;

        try {
            for (int i = 0; i < image.get(id).getWidth(); i++) {
                for (int j = 0; j < image.get(id).getHeight(); j++) {
                    int p = image.get(id).getRGB(i, j);
                    if (image.get(id).getRGB(i, j) == -1) white++;
                    if (image.get(id).getRGB(i, j) == ((p >> 24) & 0xff)) R++;
                    if (image.get(id).getRGB(i, j) == 150) G++;
                    if (image.get(id).getRGB(i, j) == 200) B++;

                    else {
                        black++;
                    }
                }
            }

            histogramInfo.put("Black", black);
            histogramInfo.put("White", white);
            histogramInfo.put("R", R);
            histogramInfo.put("G", G);
            histogramInfo.put("B", B);
        } catch (JSONException e) {
        }

        return histogramInfo;
    }


    byte[] getSubImage(int id, int x, int y, int w, int h) throws IOException {
        if (!image.containsKey(id) || image.get(id) == null) {
            throw new notFoundExp("Red alert! Image not found");
        }

        if (x + w > image.get(id).getWidth() || y + h > image.get(id).getHeight()) {
            throw new IOException("Out of boundaries");
        }

        ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
        ImageIO.write(image.get(id).getSubimage(x, y, w, h), "jpg", byteArrayOutputStream);
        byteArrayOutputStream.flush();

        byte[] bytesImage = byteArrayOutputStream.toByteArray();
        byteArrayOutputStream.close();

        return bytesImage;
    }

    byte[] getGreyScale(int id) throws notFoundExp, IOException {
        if (!image.containsKey(id) || image.get(id) == null) throw new notFoundExp("Image not found");
        BufferedImage newImage = deepCopy(image.get(id));
        for (int y = 0; y < image.get(id).getHeight(); y++)
            for (int x = 0; x < image.get(id).getWidth(); x++) {
                int p = image.get(id).getRGB(x, y);

                int a = (p >> 24) & 0xff;
                int r = (p >> 16) & 0xff;
                int g = (p >> 8) & 0xff;
                int b = p & 0xff;

                int avg = (r + g + b) / 3;

                p = (a << 24) | (avg << 16) | (avg << 8) | avg;

                newImage.setRGB(x, y, p);
            }


        ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
        ImageIO.write(newImage, "jpg", byteArrayOutputStream);
        byteArrayOutputStream.flush();
        byte[] bytesImage = byteArrayOutputStream.toByteArray();
        byteArrayOutputStream.close();

        return bytesImage;
    }
    byte[] getScaledImage(int id, int percent) throws notFoundExp, IOException {
        if(!image.containsKey(id) || image.get(id) == null) throw new notFoundExp(" Image not found");
        int Height = image.get(id).getHeight();
        int Width = image.get(id).getWidth();
        AffineTransform at = AffineTransform.getScaleInstance(percent/100, percent/100);
        BufferedImage newImage = deepCopy(image.get(id));
        AffineTransformOp scaleOp = new AffineTransformOp(at, AffineTransformOp.TYPE_BICUBIC);
        newImage = scaleOp.filter(newImage, null);
        ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
        ImageIO.write( newImage, "jpg", byteArrayOutputStream );
        byteArrayOutputStream.flush();

        byte[] bytesImage = byteArrayOutputStream.toByteArray();
        byteArrayOutputStream.close();

        return bytesImage;

    }
    byte[] getBlurredImage(int id, int radius) throws notFoundExp, IOException {
        if (!image.containsKey(id) || image.get(id) == null) throw new notFoundExp("Image not found");

        BufferedImageOp blurfilter = getGaussianBlurFilter(radius);
        BufferedImage newImage = deepCopy(image.get(id));
        newImage = blurfilter.filter(newImage, null);

        ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
        ImageIO.write(newImage, "jpg", byteArrayOutputStream);
        byteArrayOutputStream.flush();

        byte[] bytesImage = byteArrayOutputStream.toByteArray();
        byteArrayOutputStream.close();

        return bytesImage;

    }

    public static ConvolveOp getGaussianBlurFilter(int radius) {
        if (radius < 1) {
            throw new IllegalArgumentException("Radius must be >= 1");
        }

        int size = radius * 2 + 1;
        float[] data = new float[size];

        float sigma = radius / 3.0f;
        float twoSigmaSquare = 2.0f * sigma * sigma;
        float sigmaRoot = (float) Math.sqrt(twoSigmaSquare * Math.PI);
        float total = 0.0f;

        for (int i = -radius; i <= radius; i++) {
            float distance = i * i;
            int index = i + radius;
            data[index] = (float) Math.exp(-distance / twoSigmaSquare) / sigmaRoot;
            total += data[index];
        }

        for (int i = 0; i < data.length; i++) {
            data[i] /= total;
        }

        Kernel kernel = null;
        kernel = new Kernel(size, 1, data);
        return new ConvolveOp(kernel, ConvolveOp.EDGE_NO_OP, null);
    }

    public static final BufferedImage deepCopy(BufferedImage image) {
        BufferedImage clone = new BufferedImage(image.getWidth(),
                image.getHeight(), image.getType());
        Graphics2D g2d = clone.createGraphics();
        g2d.drawImage(image, 0, 0, null);
        g2d.dispose();
        return clone;
    }

}
