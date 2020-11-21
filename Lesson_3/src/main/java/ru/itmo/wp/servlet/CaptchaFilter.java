package ru.itmo.wp.servlet;

import ru.itmo.wp.util.ImageUtils;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.io.PrintWriter;
import java.net.URLDecoder;
import java.nio.charset.StandardCharsets;
import java.util.Base64;
import java.util.Random;
import java.util.zip.GZIPOutputStream;

public class CaptchaFilter extends HttpFilter {

    public static final String TRUE = "true";

    @Override
    protected void doFilter(HttpServletRequest request, HttpServletResponse response, FilterChain chain) throws IOException, ServletException {
        if ("GET".equals(request.getMethod())) {
            HttpSession session = request.getSession();
            String expected = (String) session.getAttribute("expectedCaptcha");
            if (expected != null) {
                String foundCaptcha = request.getParameter("captcha");
                if (expected.equals("true")) {
                    chain.doFilter(request, response);
                } else if (foundCaptcha.equals(expected)) {
                    session.setAttribute("expectedCaptcha", TRUE);
                    chain.doFilter(request, response);
                } else {
                    sendCaptcha(request, response);
                }
            } else {
                sendCaptcha(request, response);
            }

        } else {
            chain.doFilter(request, response);
        }
    }

    private void rsendCaptcha(HttpServletRequest request, HttpServletResponse response, String expected) {
        doCaptcha(request, response, expected);

    }

    private void sendCaptcha(HttpServletRequest request, HttpServletResponse response) {
        Random x = new Random();
        int cap = x.nextInt(900) + 100;
        doCaptcha(request, response, Integer.toString(cap));
    }

    private void doCaptcha(HttpServletRequest request, HttpServletResponse response, String cap) {
        request.getSession().setAttribute("expectedCaptcha", cap);
        byte[] img = Base64.getEncoder().encode(ImageUtils.toPng(cap));
        try (PrintWriter writer = response.getWriter()) {
            String sImg = new String(img, StandardCharsets.UTF_8);
            writer.print("<!DOCTYPE html>\n" +
                    "<head>\n" +
                    "    <meta charset=\"UTF-8\">\n" +
                    "    <title>Captcha</title>\n" +
                    "</head>\n" +
                    "<body/>" +
                    "<img src=\"data:image/png;base64," + sImg + "\"></img>\n" +
                    "<form>\n" +
                    "<input type=\"text\" name=\"captcha\">\n" +
                    "<input type=\"submit\" name=\"submit\">\n" +
                    "</form>\n" +
                    "<p> Write right captcha </p>\n" +
                    "</body>\n" +
                    "</html>");
            writer.flush();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
