package com.example.camel.route;

import java.util.Arrays;

import org.apache.camel.Exchange;
import org.apache.camel.Processor;
import org.apache.camel.builder.RouteBuilder;
import org.apache.camel.model.dataformat.CsvDataFormat;
import org.springframework.stereotype.Component;


@Component
public class CSVRouteBuilder extends RouteBuilder {

	public static String SOURCE_INPUT_PATH = "file://D:/mywork/microservices/springboot-camel-application?fileName=fileadd.csv&noop=true";
	public static String SOURCE_OUTPUT_PATH = "file://D:/mywork/microservices/springboot-camel-application?fileName=fileadd.done";
	
	@Override
	public void configure() throws Exception {
		 try {
                    CsvDataFormat csvDataFormat = new CsvDataFormat();
    	            csvDataFormat.setHeaderDisabled("true");
    				 from(SOURCE_INPUT_PATH)
	    			.unmarshal()
			    	.csv()
			    	.process(new Processor() {
			    		@Override 
		 	   			public void process(Exchange exchange) throws Exception { 
		 	   				int sum = addValuesFromCSVFile(exchange); 
		 	   			   exchange.getIn().setBody(String.valueOf(sum));
	 	   			  } })
	  			  	.to(SOURCE_OUTPUT_PATH)
	    		    .end();     			  
    			
            } catch (Exception e) {
                e.printStackTrace();
            }
	   }
	

	private int addValuesFromCSVFile (Exchange exchange) {
	  return Arrays.stream(exchange.getIn().getBody(String.class)
				.replace("[[","")
				.replace("]]","")
				.trim().split(","))
				.mapToInt(Integer::parseInt)
				.sum();
	}
	
}
	
		

