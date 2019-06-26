package com.laby.laby10.controller;

import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.RequestMapping;
import org.json.JSONObject;
import javax.servlet.http.HttpServletRequest;
import java.io.IOException;

@RestController
public class MainController {
    ImageProcessorController imageProcessorController = new ImageProcessorController();

    @RequestMapping(method = RequestMethod.POST, value = "/image")
    public String setImage(HttpServletRequest requestEntity) throws IOException {
        Integer code = imageProcessorController.setImage(requestEntity.getInputStream());
        return code.toString();
    }

    @RequestMapping(method = RequestMethod.DELETE, value = "/image/{id}")
    public String deleteImage(@PathVariable int id) throws IOException {
        imageProcessorController.deleteImage(id);
        return "Image deleted";
    }
    @RequestMapping(method = RequestMethod.GET, value = "/image/{id}/size")
    public String getImageSize(@PathVariable int id) throws notFoundExp {
        JSONObject imageSize = imageProcessorController.getImageSize(id);
        return "Image size: " + imageSize;
    }

    @RequestMapping(method = RequestMethod.GET, value = "/image/{id}/histogram")
    public String getHistogramImage(@PathVariable int id) throws IOException {
        try {
            JSONObject histogramImage = imageProcessorController.getHistogramImage(id);
            return "Image histogram: " + histogramImage;
        } catch (IOException e) {
            return "Error";
        }
    }
    @RequestMapping(value = "/image/{id}/crop/{x}/{y}/{w}/{h}", method = RequestMethod.GET, produces = MediaType.IMAGE_PNG_VALUE)
    public byte[] getGrayImage(@PathVariable int id, @PathVariable int x,@PathVariable int y,  @PathVariable int w, @PathVariable int h) throws Exception {
        return imageProcessorController.getSubImage(id,x,y,w,h);
    }
   @RequestMapping(value = "/image/{id}/greyscale",method = RequestMethod.GET, produces = MediaType.IMAGE_PNG_VALUE)
    public byte[] getGreyScale(@PathVariable int id) throws IOException {
       return imageProcessorController.getGreyScale(id);
    }

    @RequestMapping(value = "/image/{id}/scale/{percent}",method = RequestMethod.GET, produces = MediaType.IMAGE_PNG_VALUE)
    public byte[] getScaledImage(@PathVariable int id,@PathVariable int percent) throws IOException {
        return imageProcessorController.getScaledImage(id, percent);
    }

    @RequestMapping(value ="/image/{id}/blur/{radius}",method = RequestMethod.GET,produces = MediaType.IMAGE_PNG_VALUE)
     public  byte[] getBlurredImage(@PathVariable int id,@PathVariable int radius) throws notFoundExp, IOException{
    return imageProcessorController.getBlurredImage(id, radius);
    }
}

