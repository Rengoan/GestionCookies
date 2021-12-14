package web;

import java.io.IOException;
import java.io.PrintWriter;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.*;

@WebServlet("/CookiesServlet")
public class CookiesServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest request,
            HttpServletResponse response) throws IOException {

        boolean nuevoUsuario = true;

        Cookie[] cookies = request.getCookies();

        /*Comprobamos si ya existe una cookie creada con anterioridad llamada
         visitanteRecurrente*/
        if (cookies != null) {
            for (Cookie c : cookies) {
                if (c.getName().equals("visitanteRecurrente")
                        && c.getValue().equals("si")) {
                    nuevoUsuario = false;
                    break;

                }
            }

        }
        String mensaje = null;
        if (nuevoUsuario) {
            Cookie visitanteRecurrente = new Cookie("visitanteRecurrente", "si");
            response.addCookie(visitanteRecurrente);
            mensaje = "Gracias por visitar nuestro sitio web por primera vez";
        }else{ //Pasa por aqui cuando el usuario es falso
            mensaje = "Gracias por visitarnos de nuevo";
        }
           
        response.setContentType("text/html;charset=UTF-8");
        PrintWriter out = response.getWriter();
        
        out.print("Mensaje: "+mensaje);
        out.close();
    }
}
