package com.class8.eduAdmin.controller;


import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import com.class8.eduAdmin.shiro.CaptchaFormAuthenticationFilter;
import javax.imageio.ImageIO;
import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.util.Random;

/**
 *	图片验证码
 */
@Controller
public class CaptachaController {
	
    private int width = 100;
    private int height = 38;
    private int codeCount = 4;
    private int xx = 24;
    private int fontHeight = 24;
    private int codeY = 28;
    char[] codeSequence = { 'A', 'B', 'C', 'D', 'E', 'F', 'G', 'H', 'I', 'J',
            'K', 'L', 'M', 'N', 'O', 'P', 'Q', 'R', 'S', 'T', 'U', 'V', 'W',
            'X', 'Y', 'Z', '0', '1', '2', '3', '4', '5', '6', '7', '8', '9' };
    
    @RequestMapping("/captacha")
    public void captacha(HttpServletRequest req, HttpServletResponse resp)
            throws IOException {

        BufferedImage buffImg = new BufferedImage(width, height,BufferedImage.TYPE_INT_RGB);
        Graphics gd = buffImg.getGraphics();
        Random random = new Random();
        gd.setColor(getRandColor(200,250));
        gd.fillRect(0, 0, width, height);

        Font font = new Font("Times New Roman", Font.PLAIN, fontHeight);
        gd.setFont(font);
        gd.setColor(new Color(221, 221, 221));
        gd.drawRect(0, 0, width - 1, height - 1);

        gd.setColor(Color.BLACK);
        for (int i = 0; i < 5; i++) {
            int x = random.nextInt(width);
            int y = random.nextInt(height);
            int xl = random.nextInt(12);
            int yl = random.nextInt(12);
            gd.drawLine(x, y, x + xl, y + yl);
        }

        StringBuffer randomCode = new StringBuffer();
        int red = 0, green = 0, blue = 0;

        for (int i = 0; i < codeCount; i++) {
            String code = String.valueOf(codeSequence[random.nextInt(36)]);
            red = random.nextInt(255);
            green = 0;
            blue = random.nextInt(255);
            gd.setColor(new Color(red, green, blue));
            gd.drawString(code, i  * xx+5, codeY);
            randomCode.append(code);
        }
        HttpSession session = req.getSession();
        session.setAttribute(CaptchaFormAuthenticationFilter.DEFAULT_CAPTACHA_PARAM, randomCode.toString());

        resp.setHeader("Pragma", "no-cache");
        resp.setHeader("Cache-Control", "no-cache");
        resp.setDateHeader("Expires", 0);
        resp.setContentType("image/jpeg");

        ServletOutputStream sos = resp.getOutputStream();
        ImageIO.write(buffImg, "jpeg", sos);
        sos.close();
    }
    
    private Color getRandColor(int fc,int bc) {     
        Random random = new Random();     
        if(fc>255) fc=255;     
        if(bc>255) bc=255;     
        int r=fc+random.nextInt(bc-fc);     
        int g=fc+random.nextInt(bc-fc);     
        int b=fc+random.nextInt(bc-fc);     
        return new Color(r,g,b);     
    }

}
