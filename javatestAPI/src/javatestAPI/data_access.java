import java.util.HashMap;
import java.util.Map;


public class data_access {

	private Map<String,Person> personMap = new HashMap<>();
	private static data_access instance = new data_access();
	public static data_access getInstance() {
		return instance;
	}
	
	private data_access() {
		personMap.put("amal", new Person("amal","junior developer.",12000));
		personMap.put("vipin", new Person("vipin","associate developer.",15000));
		personMap.put("basil", new Person("basil","UX designer.",18000));
		personMap.put("natasha", new Person("natasha","junior developer.",12500));
		
	}
	public Person getPerson(String name) {
		return personMap.get(name);
	}
	
	public void putPerson(Person person) {
		personMap.put(person.getName(), person);	
	}
}
