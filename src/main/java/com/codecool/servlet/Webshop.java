package com.codecool.servlet;

import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Iterator;

@WebServlet(name = "WebShopServlet", urlPatterns = {"/"}, loadOnStartup = 1)
public class Webshop extends HttpServlet {


    public void init() {
        ItemStore.listOfItems.add(new Item("Asus laptop",1500));
        ItemStore.listOfItems.add(new Item("Plazma tv",1200));
        ItemStore.listOfItems.add(new Item("retek",100));

    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException {
        PrintWriter out = response.getWriter();

        StringBuffer buffer = new StringBuffer();

        if (request.getParameterNames().hasMoreElements()) {
            String method = request.getParameter("method").equals("add") ? "added":"removed";
            buffer.append("You have "+ method+ "this item : " + request.getParameter("name"));
            if (request.getParameter("method").equals("add")) {
                ShoppingCart.addItem(new Item(request.getParameter("name"),Double.parseDouble(request.getParameter("price"))));
            }
            if (request.getParameter("method").equals("remove")) {
                Iterator<Item> iterator = ShoppingCart.cart.iterator();

                while (iterator.hasNext()) {
                    if (iterator.next().name.equals(request.getParameter("name"))) {
                        iterator.remove();
                        break;
                    }
                }
            }
        }

        String title = " items in our shop";

        out.println("<html>\n"+
                            "<head><title>" + title + "</head></title>\n"+
                            "<body>\n" +
                            "<h1 align = \"center\">" + title + "</h1>\n");

        out.println(buffer.toString() +
                "ul\n");

        for (Item item : ItemStore.listOfItems) {
            out.println("<li><b></b>: "+ item.id + " " + item.name + " " + item.price + " " +
                    "<form action=\"/\" method=\"GET\"><input type=\"hidden\" name=\"name\" value=\"" +
                    item.name + "\"><input type=\"hidden\" name=\"price\" value=\"" +
                    item.price + " \"><button type=\"submit\" name=\"method\" value=\"add\">ADD</button><button type=\"submit\" name=\"method\" value=\"remove\">REMOVE</button>" +
                    "</form>");
        }
        out.println(
                "<a href=\"/cart\">SHOPPING CART</a></body></html>"
        );
    }

}
