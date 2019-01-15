package org.etriks.security.registration;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.rmi.NoSuchObjectException;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by pliu on 7/3/15.
 */


public class CaptchaService {

    private static CaptchaService captchaService=new CaptchaService();

    private Map<String,NumCaptcha> captchas;

    private CaptchaService(){captchas=new HashMap<String, NumCaptcha>();}

    public static CaptchaService getInstance(){
        return captchaService;
    }

    public NumCaptcha creatCaptcha(String id){
        NumCaptcha captcha=new NumCaptcha(id);
        captchas.put(id,captcha);
        return captcha;
    }

    public boolean validateResponseForID(String id,String answer){

        if(captchas.containsKey(id)){
            NumCaptcha captcha = captchas.get(id);
            int ans=Integer.parseInt(answer);
            if (captcha.getAnswer()==ans){
                captchas.remove(id);
                return true;
            }
            else {
                captchas.remove(id);
                return false;}
        }
        else return false;
    }

    public BufferedImage getCaptchaImageForID(String captchaID) throws NoSuchObjectException {

        if(!captchas.containsKey(captchaID)){
            throw new NoSuchObjectException("The capchaID you enter doese not exist");
        }
        else{
        NumCaptcha cap=captchas.get(captchaID);
        String message=cap.getArg1()+"+"+cap.getArg2()+"=?";
        BufferedImage img = new BufferedImage(1, 1, BufferedImage.TYPE_INT_ARGB);
        Graphics2D g2d = img.createGraphics();
        Font font = new Font("Arial", Font.PLAIN, 48);
        g2d.setFont(font);
        FontMetrics fm = g2d.getFontMetrics();
        int width = fm.stringWidth(message);
        int height = fm.getHeight();
        g2d.dispose();

        img = new BufferedImage(width, height, BufferedImage.TYPE_INT_ARGB);
        g2d = img.createGraphics();
        g2d.setRenderingHint(RenderingHints.KEY_ALPHA_INTERPOLATION, RenderingHints.VALUE_ALPHA_INTERPOLATION_QUALITY);
        g2d.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
        g2d.setRenderingHint(RenderingHints.KEY_COLOR_RENDERING, RenderingHints.VALUE_COLOR_RENDER_QUALITY);
        g2d.setRenderingHint(RenderingHints.KEY_DITHERING, RenderingHints.VALUE_DITHER_ENABLE);
        g2d.setRenderingHint(RenderingHints.KEY_FRACTIONALMETRICS, RenderingHints.VALUE_FRACTIONALMETRICS_ON);
        g2d.setRenderingHint(RenderingHints.KEY_INTERPOLATION, RenderingHints.VALUE_INTERPOLATION_BILINEAR);
        g2d.setRenderingHint(RenderingHints.KEY_RENDERING, RenderingHints.VALUE_RENDER_QUALITY);
        g2d.setRenderingHint(RenderingHints.KEY_STROKE_CONTROL, RenderingHints.VALUE_STROKE_PURE);
        g2d.setFont(font);
        fm = g2d.getFontMetrics();
        g2d.setColor(Color.BLACK);
        g2d.drawString(message, 0, fm.getAscent());
        g2d.dispose();
        return img;}
    }
    public void saveCaptchaImage(String captchaID,String location){
        BufferedImage img= null;
        try {
            img = this.getCaptchaImageForID(captchaID);
        } catch (NoSuchObjectException e) {
            e.printStackTrace();
        }
        try {
            ImageIO.write(img, "png", new File(location));
        } catch (IOException ex) {
            ex.printStackTrace();
        }
    }
}
