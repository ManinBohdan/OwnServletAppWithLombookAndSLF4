import lombok.extern.slf4j.Slf4j;

import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;
import java.util.NoSuchElementException;

@Slf4j
@WebServlet("/viewAllServlet")
public class ViewAllServlet extends HttpServlet{
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException {
        response.setContentType("text/html");
        PrintWriter out = response.getWriter();

        List <Dog> dogs = DogRepository.getAllDogs();
        try{
            log.info("The client got list of all dogs we have");
        out.print(dogs);
        }
        catch (NoSuchElementException e) {
            log.info("This objects don't exist");
//            System.out.println("This objects don't exist");
        }
        out.close();
    }
}
