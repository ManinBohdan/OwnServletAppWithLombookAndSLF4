import lombok.extern.slf4j.Slf4j;

import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.NoSuchElementException;
@Slf4j

@WebServlet("/viewByIDServlet")
public class ViewByIDServlet extends HttpServlet {
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException {

        response.setContentType("text/html");
        PrintWriter out = response.getWriter();

        String sid = request.getParameter("id");
        int id = Integer.parseInt(sid);


        Dog dog = DogRepository.getDogById(id);

        try{
        out.print(dog);
    }
        catch (NoSuchElementException e) {
            log.info("This objects don't exist");
            //System.out.println("This object doesn't exist(((((");
        }
        finally {
            out.close();
        }
    }
}
