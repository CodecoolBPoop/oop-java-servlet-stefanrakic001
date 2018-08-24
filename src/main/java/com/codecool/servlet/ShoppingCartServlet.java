package com.codecool.servlet;

import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

@WebServlet(name="ShoppingCartServlet", urlPatterns = {"/cart"},loadOnStartup = 2)
public class ShoppingCartServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException{
        PrintWriter out = response.getWriter();

        StringBuffer htmlCode = new StringBuffer();
        if (ShoppingCart.cart.isEmpty()) {
            htmlCode.append("<h3>There is no item in you cart</h3>");
        }else {
            int result = 0;
            for (Item item : ShoppingCart.cart) {
                htmlCode.append("<h3>" + item.id + " " + item.name + " " + item.price + "</h3>");
                result+=item.price;
            }
            htmlCode.append("<h2>" + result + " Ft </h2>");
        }


        out.println("<html>\n" + "<head><title>SHOPPING CART</title></head>\n" + "<body>\n" + htmlCode +"<br><a href=\"/\">BACK TO ITEMS</a>" + "<body></html>");
    }

}
