

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.json.JSONObject;

public class PS extends HttpsServlet{
	
	@override 
	public void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException{
		
		String requestUrl = request.getRequestURI();
		String name = requestUrl.substring("/people/".length());
		
		Person person = data_access.getInstance().getPerson(name);
		if(person !=null) {
			String json="{\n";
			json += "\"name\":"+JSONObject.quote(person.getName())+ ",\n";
			json += "\"position\":"+JSONObject.quote(person.getPosition())+ ",\n";
			json += "\"salary\":"+person.getSalary()+ ",\n";
			response.getOutputStream().println("{}");
		
		}
		else {
			response.getOutputStream().println("{}");
		}
	}
	@override
	public void doPost(HttpServletRequest request , HttpServletResponse.response) throws IOException, Servlet Exception{
		String name= request.getParameter("name");
		String name= request.getParameter("position");
		int salary= Integer.parseInt(request.getParameter("salary"));
		
		data_access.getInstance().putPerson(new Person(name , position, salary ));
	}
}