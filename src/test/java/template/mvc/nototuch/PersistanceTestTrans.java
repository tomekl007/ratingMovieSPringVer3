package template.mvc.nototuch;



import static org.junit.Assert.*;

import java.util.List;

import template.mvc.nototuch.entity.*;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.springframework.context.support.GenericXmlApplicationContext;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.transaction.annotation.Isolation;




public class PersistanceTestTrans {
	
	
	
	
	
	RequestBean requestBean ;
	
	@Before
    public void initObjects() {
		GenericXmlApplicationContext ctx = new GenericXmlApplicationContext();
		ctx.load("root-context.xml");
		ctx.refresh();
		requestBean = ctx.getBean("requestBean", RequestBean.class);
		System.out.println("--> requestBean : " + requestBean);
    }
	
	int id = 10;
	
	@Test
	@Transactional
	public void testCreateUser() {
		String name = "nameUniqueToTest";
		requestBean.createUser(name, "surname");
		List<User> users = requestBean.getAllUsers();
		
		Integer count=0;
		for(User user : users){
			if(name.equals(user.getName()))
				count++;
		}
		
		assertNotSame(new Integer(0), count);
			
		
	}
	

	


	
	
}
