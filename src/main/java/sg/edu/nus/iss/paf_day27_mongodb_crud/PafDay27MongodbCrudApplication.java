package sg.edu.nus.iss.paf_day27_mongodb_crud;

import org.bson.Document;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import jakarta.json.Json;
import jakarta.json.JsonObject;
import sg.edu.nus.iss.paf_day27_mongodb_crud.repositories.AirbnbRepository;
import sg.edu.nus.iss.paf_day27_mongodb_crud.repositories.TaskRepository;

@SpringBootApplication
public class PafDay27MongodbCrudApplication implements CommandLineRunner{

	@Autowired
	private TaskRepository taskRepo;

	@Autowired
	private AirbnbRepository airbnbRepo;

	public static void main(String[] args) {
		SpringApplication.run(PafDay27MongodbCrudApplication.class, args);
	}

	@Override
	public void run(String... args) throws Exception {
		
		// JsonObject task = Json.createObjectBuilder()
		// 	.add("task","Watch JW4")
		// 	.add("priority",5)
		// 	.add("dueDate", "2023-04-01")
		// 	.build();

		// Document result = taskRepo.insertTask(task);
		// System.out.println(" >>>> inserted result : " + result);

		// // taskRepo.findWithoutTask();
		// // taskRepo.deleteActivitiesWithoutTask();
		// // taskRepo.updateActivity();

		// airbnbRepo.addTextIndex();
		// airbnbRepo.findByTextSearch();

	}

}
