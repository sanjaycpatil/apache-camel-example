package com.example.camel.config;

import org.apache.camel.CamelContext;
import org.apache.camel.builder.RouteBuilder;
import org.apache.camel.impl.DefaultCamelContext;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import com.example.camel.route.CSVRouteBuilder;


@Component
public class CSVConfig extends RouteBuilder {

	private Logger logger = LoggerFactory.getLogger(CSVConfig.class);
	

	@Override
	public void configure() throws Exception {
		try {
		     CamelContext context = new DefaultCamelContext();
		     CSVRouteBuilder route = new CSVRouteBuilder();
		     route.addRoutesToCamelContext(context);
		     context.start();
		     Thread.sleep(5000);
		     context.stop();
		    } catch (Exception exe) {
		      logger.info(exe.getMessage());
		      exe.printStackTrace();
		    }
		  }
		
	}


