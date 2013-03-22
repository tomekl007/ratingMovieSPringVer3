package template.mvc.nototuch;

import java.util.List;

import javax.security.auth.login.LoginContext;



import org.springframework.context.support.GenericXmlApplicationContext;

import template.mvc.nototuch.entity.*;
import template.mvc.nototuch.web.MyLoginBean;
import template.mvc.nototuch.web.RatingManager;

public class MainClass {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
	
		GenericXmlApplicationContext ctx = new GenericXmlApplicationContext();
		ctx.load("root-context.xml");
		ctx.refresh();
		
		
		RequestBean requestBean = ctx.getBean("requestBean", RequestBean.class);
		List<Movie> lm = requestBean.getAllMovies(); 
		System.out.println(lm);
		
		requestBean.createMovie("Batman", 3, "www.ras.ap");
		requestBean.createUser("tomekll", "13113");
		requestBean.createUser("tomekll2", "1311312");
		

		
		List<Movie> lm2 = requestBean.getAllMovies(); 
		System.out.println(lm2);
		
		List<User> users = requestBean.getAllUsers();
		System.out.println(users);
		
		Movie m = requestBean.findMovieById(3);
	//	System.out.println(m + "  " + m.getRates());
		
		User u=requestBean.findUserById(1);
		//System.out.println("User  " + u + " watched: "  + u.getMovies() );
		
		//List<Movie> moviesForUser = (List<Movie>) requestBean.findMoviesForSpecyficUser(1);
		//System.out.println("-->>> "+ moviesForUser);
		
		
		Integer movieId = 3;
		int rate=5;
		//requestBean.addRate(rate, 1, movieId);
		
		//requestBean.changeRate(movieId,1 , 2);
	  // requestBean.createRate(rate, 1, m);
		requestBean.addRate(rate,1,  movieId);
		
		List<Rate> rates = requestBean.getRatesForSpecyficMovie(movieId);
		
		 for(Rate r : rates){
			 System.out.println("rates for specyfic movie are :  " + r.getRate());
			}
		 
		 RatingManager ratingManager= ctx.getBean("ratingManager", RatingManager.class);
		 
		 System.out.println(ratingManager.getMovies());
		 System.out.println(ratingManager.getUsers());
		 
		 MyLoginBean myLoginBean= ctx.getBean("myLoginBean", MyLoginBean.class);
		 
		 System.out.println(myLoginBean.getName());
	
		
	}

}
