/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package template.mvc.nototuch;

import java.util.Collection;
import java.util.List;
import java.util.logging.Logger;


import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import template.mvc.nototuch.entity.*;

import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;



/**
 *
 * @author Tomek
 */

@Transactional(propagation=Propagation.REQUIRED)
public class RequestBean {
     private static Logger logger = Logger.getLogger("rating.ejb.RequestBean");
    @PersistenceContext
    private EntityManager em;
    
    public void createMovie(String title, int movieId, String url){
        try{
            Movie m1 = new Movie(title, movieId, url);
           logger.info("Created movie " + title + "-" + movieId);
           
         
           em.persist(m1);
          em.flush();
           
            
          
            logger.info("Persisted movie " + title + "-" + movieId + " with id : " + m1.getId()); 
           // return m1;
        }catch(Exception ex) {
            throw new RuntimeException(ex.getMessage());
        }
    
    }
    
    public List<Movie> getAllMovies() {
    	System.out.println("EntityMenager : "+ (em == null ? "null" : em) );
        List<Movie> movies = (List<Movie>) em.createNamedQuery("findAllMovies")
                                          .getResultList();
        logger.info(movies.isEmpty()? "movies is empty":movies.toString());
        return movies;
    }
    
    public List<User> getAllUsers(){
        List<User> users = (List<User>) em.createNamedQuery("findAllUsers").getResultList();
        logger.info(users.isEmpty()? "users is empty":users.toString());
        return users;
    }
    
    public List<Rate> getAllRates(){
        List<Rate> rates = (List<Rate>) em.createNamedQuery("findAllRates").getResultList();
        logger.info(rates.isEmpty()? "rates are empty":rates.toString());
        return rates;
    }
    
    public void createUser(String name, String password){
       try{
            User u = new User(name, password);
            logger.info("Created user " + name + " id " + u.getId() );
            em.persist(u);
          
            logger.info("Persisted user " + name + " id " + u.getId() ); 
           // return u;
        }catch(Exception ex) {
        	  throw new RuntimeException(ex.getMessage());
        }
    }
    
    //musi wraca idDO rate i po tym szukam,
    public Rate createRate(int rate, int userid, Movie currentMovie ){
        try{
            Rate r = new Rate();
            r.setRate(rate);
            r.setIdOfRatingUser(userid);
            r.setMovie(currentMovie);
            r.setId(1);
          //  logger.info("Created rate " + rate + " userid " + r.getIdOfRatingUser() + "id " + r.getId() 
          //          + " for movie : " + r.getMovie().getTitle());
            em.persist(r);
           
            //-------------
            RateKey rkey = new RateKey();
            rkey.setMovie(currentMovie.getId());
            rkey.setId(r.getId());

            Rate rate2  = em.find(Rate.class, rkey);
         //-------------------
            logger.info("Persisted rate " + rate + " userid " + r.getIdOfRatingUser() + "id" 
                    + r.getId()+ " for movie : " + r.getMovie().getTitle() ); 
            return r;
        }catch(Exception ex) {
        	System.out.println(ex.getCause());
        	  throw new RuntimeException(ex.getMessage());
        }
    }
    
    public Rate findRateById(int rateId){
         try {
            Rate r = em.find(Rate.class, rateId);
            logger.info("Found rateId : " + r.getId());
            return r;
            
        } catch (Exception ex) {
            logger.warning(
                    "Couldn't find rate by rateId " + rateId
                    + ".");
            throw new RuntimeException(ex.getMessage());
        }
        
    }
    
    public Movie findMovieById(int movieId){
        try {
            Movie movie = em.find(Movie.class, movieId);
            logger.info("Found movie ID " + movieId);
            return movie;
            
        } catch (Exception ex) {
            logger.warning(
                    "Couldn't find movie by movieID " + movieId
                    + ".");
            throw new RuntimeException(ex.getMessage());
        }
        
    }
    
   /* public Collection<Movie> findMoviesForSpecyficUser(int userId){
    	try {
            User user = em.find(User.class, userId);
            logger.info("Found user  :" + user.getName() +" by ID : " + user.getId() );
            return user.getMovies();
        }catch (Exception ex) {
            logger.warning(
                    "Couldn't find user by userID " + userId
                    + ".");
            throw new RuntimeException(ex.getMessage());
        }
    	
    	
    }*/
    
    
    
    
    public User findUserById(int userId){
        try {
            User user = em.find(User.class, userId);
            logger.info("Found user  :" + user.getName() +" by ID : " + user.getId() );
            return user;
        }catch (Exception ex) {
            logger.warning(
                    "Couldn't find user by userID " + userId
                    + ".");
            throw new RuntimeException(ex.getMessage());
        }
            
    }
    
   // public List<Rate> getRatesForSpecyficMovieId(int currentMovieId ){
    //     return (List<Rate>) findMovieById(currentMovieId).getRates();
   // }
   @SuppressWarnings("unchecked")
public List<Rate> getRatesForSpecyficMovie(int movieId) {
        try {
            logger.info("i`am in getRatesForSpecyficMOvie()");
            return em.createNamedQuery("findRateByMovieId")
                     .setParameter("movieId", movieId)
                     .getResultList();
        } catch (Exception ex) {
            logger.warning("Couldn't find rates for specyfic movie id : "  + movieId +  " " + ex.getCause());
            throw new RuntimeException(ex.getMessage());
        }
    }
   
   public void changeRate(Integer movieId, int rateId , int newRate){
       logger.info("requestBean.changeRate rateId :" + rateId + " new rate : " + newRate);
       
            RateKey rkey = new RateKey();
            rkey.setMovie(movieId);
            rkey.setId(rateId);

            Rate rate  = em.find(Rate.class, rkey);
            rate.setRate(newRate);

         logger.info("after requestBean.changeRate new Rate :" + rate.getRate() );
          return;
    }
   
   
    public void addRate(int rate, int idOfLoginUser, Integer movieId) {
        logger.info("in addRate");
        try {
            Movie movie = em.find(Movie.class, movieId);
            logger.info("Found movie ID " + movieId + "  title : "  + movie.getTitle());

            Rate r = new Rate(rate, idOfLoginUser,movie);
           // r.setId(1);
          //  RateKey rk = new RateKey();
         //   rk.setMovie(movieId);
           // rk.setId(1);
            
           // r.setId(0);
            em.persist(r);
            movie.addRate(r);
            User u = em.find(User.class, idOfLoginUser);
            movie.addUser(u);
            u.addMovie(movie);
            logger.info("succesfully add Rate " + r + "to movie " + movie);
        } catch (Exception ex) {
            logger.warning(
                    "Couldn't add " + rate + " to movie ID " + movieId
                    + "."  + ex.getCause());
            throw new RuntimeException(ex.getMessage());
        }
    }

    
    
}
