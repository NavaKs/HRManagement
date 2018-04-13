package com.employee.management;

import static reactor.bus.selector.Selectors.$;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Import;

import com.employee.management.consumer.StatusNotificationConsumer;

import reactor.bus.EventBus;

@SpringBootApplication(scanBasePackages = { "com.employee.management" })
@Import(AppConfig.class)
public class HRApplication implements CommandLineRunner {

	// logging
	static final Logger logger = LogManager.getLogger(HRApplication.class.getName());

	// logging
	@Autowired
	private EventBus eventBus;

	@Autowired
	private StatusNotificationConsumer notificationConsumer;

	@Override
	public void run(String... args) throws Exception {
		eventBus.on($("notificationConsumer"), notificationConsumer);
	}

	public static void main(String[] args) {
		logger.info("entered application");
		SpringApplication.run(HRApplication.class, args);

	}

}
