package io.pivotal.amqpsample;

import org.springframework.amqp.core.AmqpTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@SpringBootApplication
@RestController
public class AmqpSampleApplication {

	@Autowired
	private AmqpTemplate amqpTemplate;

	@RequestMapping("/")
	public String home() {
		return "amqpTemplate=" + amqpTemplate;
	}

	public static void main(String[] args) {
		SpringApplication.run(AmqpSampleApplication.class, args);
	}
}
