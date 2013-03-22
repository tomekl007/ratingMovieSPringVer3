package template.mvc.nototuch;

import static org.junit.Assert.*;

import java.util.List;

import template.mvc.nototuch.entity.*;

import org.junit.Before;
import org.junit.Test;
import org.springframework.context.support.GenericXmlApplicationContext;
import org.springframework.transaction.annotation.Transactional;

public class RatePersistanceTest {
	
	RequestBean requestBean ;

	
	
	@Before
    public void initObjects() {
		GenericXmlApplicationContext ctx = new GenericXmlApplicationContext();
		ctx.load("root-context.xml");
		ctx.refresh();
		requestBean = ctx.getBean("requestBean", RequestBean.class);
		System.out.println("--> requestBean : " + requestBean);
    }
	
	@Test
	@Transactional
	public void testAddRate() {
		
		
		Integer movieId = 99;
		
		requestBean.createMovie("batmam" ,movieId, "www.batman.com");
		requestBean.createUser("Tomek2", "asdsa!@#$%");
		
		int rate=9;
		requestBean.addRate(rate, 1, movieId);
		List<Rate> rates = requestBean.getRatesForSpecyficMovie(movieId);
		int count = 0;
		  for(Rate r : rates){
			  if(r.getRate()==rate)
				  count++;
			}
			  
		  assertNotSame(0, count);
		
		
		
	}

}
