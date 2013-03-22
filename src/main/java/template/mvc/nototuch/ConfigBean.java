/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package template.mvc.nototuch;

import java.util.logging.Logger;
import javax.annotation.PostConstruct;


/**
 *
 * @author Tomek
 */
public class ConfigBean {
   
	
    private RequestBean request;
    
   public void setRequest(RequestBean request) {
		this.request = request;
	}

/*
@PostConstruct
    public void createData() {
       request.createMovie("Batman", 4,"resources/images/batman_rise.jpg");
       request.createMovie("Piraci z Karaibów", 5, "resources/images/piractes_of_carraiben.jpg");
       request.createMovie("Spider-Man", 6, "resources/images/spider_man.jpg");
       
       request.createUser("Tomek", "1111");
       request.createUser("Miecio",  "1234");
     
         
   }*/
}
