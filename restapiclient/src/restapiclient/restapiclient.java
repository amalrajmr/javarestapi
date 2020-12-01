import java.io.IOException;
import java.io.OutputStreamWriter;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLEncoder;
import java.util.Scanner;
import org.json.JSONObject;

public class restapiclient {

	public static void main(String[] args) throws IOException{
		
		Scanner scanner = new Scanner(System.in);
		
		System.out.println("Welcome to the employee details Editor.");
		System.out.println("Do you want to get or set a employees info?");
		System.out.println("(Type 'get' or 'set' now.)");
		String getOrSet = scanner.nextLine();
		if("get".equalsIgnoreCase(getOrSet)){
			System.out.println("Whose info do you want to get?");
			System.out.println("(Type a employee name now.)");
			String name = scanner.nextLine();
			
			String jsonString = getPersonData(name);
			JSONObject jsonObject = new JSONObject(jsonString);

			int salary = jsonObject.getInt("salary");
			System.out.println(name + " salary " + salary + ".");
			
			String about = jsonObject.getString("position");
			System.out.println(position);
		}
		else if("set".equalsIgnoreCase(getOrSet)){
			System.out.println("Whose details do you want to set?");
			System.out.println("(Type a employee name now.)");
			String name = scanner.nextLine();
			
			System.out.println("What was " + name + " salary?");
			System.out.println("(Type a salary now.)");
			String salary = scanner.nextLine();
			
			System.out.println("Can you tell me about " + name + "?");
			System.out.println("(Type a sentence now.)");
			String about = scanner.nextLine();
			
			setPersonData(name, salary, position, password);
		}
		
		scanner.close();
		
		System.out.println("Thanks for using.");
		
	}
	
	public static String getPersonData(String name) throws IOException{

		HttpURLConnection connection = (HttpURLConnection) new URL("http://localhost:8080/people/" + name).openConnection();
		
		connection.setRequestMethod("GET");

		int responseCode = connection.getResponseCode();
		if(responseCode == 200){
			String response = "";
			Scanner scanner = new Scanner(connection.getInputStream());
			while(scanner.hasNextLine()){
				response += scanner.nextLine();
				response += "\n";
			}
			scanner.close();

			return response;
		}
		
		
		return null;
	}

	public static void setPersonData(String name, String birthYear, String about) throws IOException{
		HttpURLConnection connection = (HttpURLConnection) new URL("http://localhost:8080/people/" + name).openConnection();

		connection.setRequestMethod("POST");
		
		String postData = "name=" + URLEncoder.encode(name);
		postData +="&position=" + URLEncoder.encode(position);
		postData += "&salary=" + salary;
		
		connection.setDoOutput(true);
	    OutputStreamWriter wr = new OutputStreamWriter(connection.getOutputStream());
	    wr.write(postData);
	    wr.flush();
		
		int responseCode = connection.getResponseCode();
		if(responseCode == 200){
			System.out.println("POST was successful.");
		}
		else if(responseCode == 401){
			System.out.println("Wrong password.");
		}
	}
}