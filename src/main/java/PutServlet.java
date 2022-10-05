import lombok.extern.slf4j.Slf4j;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
@Slf4j

@WebServlet("/putServlet")
public class PutServlet extends HttpServlet {

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        response.setContentType("text/html");
        PrintWriter out = response.getWriter();

        String sid = request.getParameter("id");
        int id = Integer.parseInt(sid);

        String name = request.getParameter("name");
        String breed = request.getParameter("breed");

        var dog = new Dog();
        dog.setId(id);
        dog.setName(name);
        dog.setBreed(breed);
        dog.setOwner(request.getParameter("owner"));

        int status = DogRepository.update(dog);

        if (status > 0) {
            response.sendRedirect("viewServlet");
        } else {
            log.info("Sorry! unable to update record");
            out.println("Sorry! unable to update record");
        }
        out.close();
    }
}
