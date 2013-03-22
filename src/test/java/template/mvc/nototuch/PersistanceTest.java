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




public class PersistanceTest {
	
	
	
	
	
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
	public void testCreateMovie() {
		
		String title = "batman part 3";
		Movie m = new Movie();
      
		requestBean.createMovie(title ,id, "www.batman.com");
      
       Movie m2 = requestBean.findMovieById(id);
        
        assertEquals(title , m2.getTitle());
	}

	@Test
	@Transactional(isolation=Isolation.READ_UNCOMMITTED)
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
	
	@Test
	@Transactional
	public void getAllMovies(){
		requestBean.createMovie("some movie" ,99, "www.batman.com");
		List<Movie> movies  = requestBean.getAllMovies();
		
		
		
		for(Movie m : movies)
			System.out.println(m);
		
		assertNotSame(movies.size(),0);
	}
	
	
	@Test
	@Transactional
	public void getAllUsers(){
		requestBean.createUser("Tomek", "!@#$%");
		requestBean.createUser("Tomek2", "asdsa!@#$%");
		requestBean.createUser("Tomek3", "asdas!@#$%");
		
		List<User> users  = requestBean.getAllUsers();
		
		for(User u : users)
			System.out.println(u);
		assertTrue(users.size()>2);
		 
		
	}

	
	
}
