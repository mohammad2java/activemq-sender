package com.amir.activemqdemosender;

import javax.jms.ConnectionFactory;

import org.apache.activemq.ActiveMQConnectionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.jms.core.JmsTemplate;

@SpringBootApplication
public class ActivemqDemoSenderApplication  implements CommandLineRunner{

	@Autowired
	private JmsTemplate jmsTemplate;
	
	public static void main(String[] args) {
		SpringApplication.run(ActivemqDemoSenderApplication.class, args).close();;
	}

	@Bean
	public ConnectionFactory connectionFactory() {
		//note active support 5(tcp,amqp,stomp,mqtt,ws) protocols to connect the broker
			/*Listening for connections at: tcp://localhost:61616?maximumConnections=1000&wireFormat.maxFrameSize=104857600
			Listening for connections at: amqp://localhost:5672?maximumConnections=1000&wireFormat.maxFrameSize=104857600
			Listening for connections at: stomp://localhost:61613?maximumConnections=1000&wireFormat.maxFrameSize=104857600
			Listening for connections at: mqtt://localhost:1883?maximumConnections=1000&wireFormat.maxFrameSize=104857600
			Listening for connections at ws://localhost:61614?maximumConnections=1000&wireFormat.maxFrameSize=104857600
	*/	
		// ActiveMQConnectionFactory is factory class for tcp protocol..each protocol have their own factory class.
		ActiveMQConnectionFactory factory  =  new ActiveMQConnectionFactory("tcp://localhost:61616");
		factory.setUserName("admin");
		factory.setPassword("admin");
		return factory;
	}
	
	

	@Override
	public void run(String... args) throws Exception {
	System.out.println("message sending started..");
	
	jmsTemplate.convertAndSend("myQueue", "This is first message from sender code.");
	
	System.out.println("message sending finished..");
		
	}

}
