How to send/publish message to ActiveMQ
---------------------------------------
step-1
------
create spring boot with following maven dependency <br>
1-spring-boot-starter-activemq <br>
2-jackson-databind <br>

Example:
-------		
		<dependency>
			<groupId>org.springframework.boot</groupId>
			<artifactId>spring-boot-starter-activemq</artifactId>
		</dependency>
       <dependency>
            <groupId>com.fasterxml.jackson.core</groupId>
            <artifactId>jackson-databind</artifactId>
       </dependency>
       
       
       
step-2
---------

create following bean and Inject JmsTemplate bean<br>
1-ConnectionFactory

Example of beans:
-----------------

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
	
	
Injecting JmsTemplate bean
----------------------------

	@Autowired
	private JmsTemplate jmsTemplate;	
	
	
calling jsmTemplate method using CommadLineRunner
-------------------------------------------------

	@Override
	public void run(String... args) throws Exception {
		System.out.println("message sending started..");
		jmsTemplate.convertAndSend("myQueue", "This is first message from sender code.");
		System.out.println("message sending finished..");	
	}	