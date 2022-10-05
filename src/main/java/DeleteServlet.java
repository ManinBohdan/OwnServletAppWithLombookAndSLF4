import lombok.extern.slf4j.Slf4j;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
@Slf4j

@WebServlet("/deleteServlet")
public class DeleteServlet extends HttpServlet {

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        String sid = request.getParameter("id");
        // Refactored
        try {int id = Integer.parseInt(sid);
            DogRepository.delete(id);
        }
        catch (NumberFormatException nfe) {
            log.warn("Id can't be anything else except an int");
        }
        // Refactored
        response.sendRedirect("viewServlet");
    }
}
