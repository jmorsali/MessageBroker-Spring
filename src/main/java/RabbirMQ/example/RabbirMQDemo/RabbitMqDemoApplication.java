package RabbirMQ.example.RabbirMQDemo;

import RabbirMQ.example.RabbirMQDemo.Component.DailyReportGenerator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class RabbitMqDemoApplication {

	private final DailyReportGenerator backgroundWorker;

	public RabbitMqDemoApplication(DailyReportGenerator backgroundWorker) {
		this.backgroundWorker = backgroundWorker;
	}

	public static void main(String[] args) {
		SpringApplication.run(RabbitMqDemoApplication.class, args);
	}

	public void run(String... args) {
		backgroundWorker.doAsyncTask();

	}
}
