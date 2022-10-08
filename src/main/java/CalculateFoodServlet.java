import lombok.extern.slf4j.Slf4j;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

@Slf4j



@WebServlet("/CalculateFoodServlet")
public class CalculateFoodServlet extends HttpServlet {

    protected void doGet (HttpServletRequest request, HttpServletResponse response) throws IOException {
        response.setContentType("text/html");
        PrintWriter out = response.getWriter();

        int id = Integer.parseInt(request.getParameter("id"));

        var dog = DogRepository.getDogById(id);
      out.print("Dog needs a" + dog.getFood() + "for 1 day");
      out.print("Dog needs a" + (dog.getFood() * 30) + "for month");


    }

}
