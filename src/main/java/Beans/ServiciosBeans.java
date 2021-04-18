package Beans;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.Random;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Scope;
import org.springframework.core.env.Environment;
import org.springframework.web.context.annotation.RequestScope;

 
@Configuration
public class ServiciosBeans {

	@Autowired
	private Environment env;
	
	@Bean
	@RequestScope
	@Scope
	public String getSaludo() {
		
		 Calendar cal = Calendar.getInstance();
	 	    Date date=cal.getTime();
	 	  //  DateFormat dateFormatHMS = new SimpleDateFormat("HH:mm:ss");
	 	        DateFormat dateFormatHour = new SimpleDateFormat("HH");

	 	        String hora=dateFormatHour.format(date);
	 	        int hora2=Integer.parseInt(hora);
	 	       String saludo="";
	 	       
	 	       if(hora2<=12){
	 	       saludo= "dias";
	 	       }
	 	       if(hora2>=12){
	 	           saludo = "tardes";
	 	       }
	 	       if(hora2>=22){
	 	             saludo = "noches";
	 	        
	 	       }
		return saludo;
		
	}
	 private static int getRandomNumberInRange(int min, int max) {

			if (min >= max) {
				throw new IllegalArgumentException("max must be greater than min");
			}

			Random r = new Random();
			return r.nextInt((max - min) + 1) + min;
		}
	 int aleat=0;
	  
	   public int getAleat() {
		return aleat;
	}
	@Bean 
	   @RequestScope
	   @Scope("prototype")
	public String getTecnologiasVariadas() {
		  String pagina="";
		  aleat = getRandomNumberInRange(1, 4);
		        
		  pagina="inicio_"+ aleat;
		      
		  return pagina;
		    
  }
	   @Bean 
	   @RequestScope
	   @Scope
		public String getApiHostURL() {
		
		    String path = env.getProperty("api_host.url");

		    return path;
		}
	}

