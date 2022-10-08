import lombok.extern.slf4j.Slf4j;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
@Slf4j


@WebServlet("/saveServlet")
public class SaveServlet extends HttpServlet {

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

     response.setContentType("text/plain");
        response.setCharacterEncoding("UTF-8");

    PrintWriter out = response.getWriter();

    String name = request.getParameter("name");
    String breed = request.getParameter("breed");
    int food = Integer.parseInt(request.getParameter("food"));
    String SicksAndIssues = request.getParameter("sicksandissues");

    var dog = new Dog();

        dog.setName(name);
        dog.setBreed(breed);
        dog.setSicksandissues(SicksAndIssues);
        dog.setFood(food);

    out.println(dog);
    out.println(DogRepository.getConnection());

    int status = DogRepository.save(dog);
    out.println(status);

        if (status > 0) {
            log.info("Record saved successfully!");
        out.print("Record saved successfully!");
    } else {
            log.info("Sorry! unable to update record");
        out.println("Sorry! unable to save record");
    }
        out.close();
}
}
