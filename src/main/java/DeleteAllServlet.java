import lombok.extern.slf4j.Slf4j;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.SQLException;

@Slf4j

@WebServlet("/deleteAllServlet")
public class DeleteAllServlet extends HttpServlet {

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        // Refactored
        try {
            DogRepository.deleteAll();
            log.info("Deleting ID");
        }
         catch (SQLException e) {
            log.error(e.getMessage(),e);
            throw new RuntimeException(e);
        }
        // Refactored
        log.info("Show All obj");
        //response.sendRedirect("viewAllServlet");
    }
}
