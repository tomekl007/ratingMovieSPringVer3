/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package template.mvc.nototuch.web;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;
import java.util.List;
import java.util.logging.Logger;
import javax.ejb.EJB;
import javax.ejb.EJBException;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.SessionScoped;
import javax.faces.component.UIParameter;
import javax.faces.event.ActionEvent;

import org.hibernate.Hibernate;
import org.springframework.beans.factory.annotation.Autowired;

import template.mvc.nototuch.RequestBean;
import template.mvc.nototuch.entity.*;


/**
 *
 * @author Tomek
 */
@ManagedBean
@SessionScoped
public class RatingManager {
    private static final Logger logger = Logger.getLogger(
                "rating.web.RatingManager");
    final private int maximum = 10;
    final private int minimum = 0;
    private List<Movie> movies;
    private List<User> users;
    private List<Rate> rates;
    
    
    //i injected MyLoginBean, to rating manager
    @Autowired
    // @ManagedProperty(value="#{myLoginBean}")
     private MyLoginBean mlb;
     
  //  private List<Rate> ratesForCurrentMovie;
    
    private List <User> usersForCurrentMovie;
    
    private Integer currentRatingMovie;
    private String currentName;
    private int currentMovieRate;
    private int tempIdForUser;
   // private User currentUser;
    
    private String nameOfLoginUser;
    
    private String newUserName;
    private String newUserPassword;
    
    private List<Rate> ratesForCurrentMovieId;
    private Boolean preConfirmAddUser = false; 
     @EJB
    private RequestBean request;
    private Rate rateToChange;
     
     
      public void setPreConfirmAddUser(Boolean preConfirm){
         this.preConfirmAddUser = preConfirm;
     }
      
     public Boolean getPreConfirmAddUser(){
         return preConfirmAddUser;
     }
     
    
     
     public void preConfirm(){
         this.preConfirmAddUser = true;
     }
     
      public void setNewUserName(String name){
         this.newUserName = name;
     }
      
     public String getNewUserName(){
         return  newUserName;
     }
     
     public void setNewUserPassword(String password){
         this.newUserPassword = password;
     }
     
     
      public String getNewUserPassword(){
         return  newUserPassword;
     }
     
    
     
    public MyLoginBean getMlb(){
       return mlb;
    } 
    public void setMlb(MyLoginBean mlb){
        logger.info("setMLB : " + mlb.getName() + mlb.getPassword() + mlb.getLoginUser());
        this.mlb = mlb;
        
    }
    
    public String getNameOfLoginUser(){
    	
    	System.out.println("-----> in get neame of login USer = " + 
    	(mlb.getLoginUser().getName())==null?"null":mlb.getName());
         this.nameOfLoginUser = mlb.getLoginUser().getName();
         logger.info(nameOfLoginUser.isEmpty() ? (" nameOfLoginUser is empty" ): nameOfLoginUser);
        return nameOfLoginUser;
    }
    
    
     public List<Movie> getMovies() {
    System.out.println(mlb.getName());
  //  Hibernate.initialize();
    movies = request.getAllMovies();
        return movies;
     }
     
      public void setMovies(List<Movie> m ){
         this.movies = m;
     
     }
     
     public List<User> getUsers(){
        users = request.getAllUsers();
        return users;
        
     }
     
     public void setUsers(List<User> u){
         this.users = u;
     }
     
     public List<Rate> getRates(){
         rates = request.getAllRates();
         return rates;
     }
     
     public void setRates(List<Rate> r){
         this.rates = r;
     }
   
      
    // public List<Rate> getRatesForCurrentMovie(){
    //     ratesForCurrentMovie = request.getRatesForSpecyficMovieId(currentRatingMovie);
    //     logger.info(ratesForCurrentMovie.isEmpty()?"getRatesForCurrentMovie returns empty":ratesForCurrentMovie.toString() );
    //     return rates;
   //  }
     
 //    public void setRatesForCurrentMovie(List<Rate> r){
  //       this.ratesForCurrentMovie = r;
  //   }
     
   /*  public User getCurrentUser(){
   logger.info(currentUser.getName().isEmpty() ? "getCurrentUser return null User" : currentUser.toString());

         return currentUser;
     }
     
     public void setCurrentUser(User u){
         logger.info("setCurrentUser to : " + u.toString());
         this.currentUser = u;
     }*/
    
     public int getCurrentRatingMovie(){
         return currentRatingMovie;
     }
     
     public void setCurrentRatingMovie(int curr){
       this.currentRatingMovie = curr;
     }
     
    public String getCurrentName(){
        return currentName;
    }
    public void setCurrentName(String n){
        this.currentName = n;
    }
    
    public String getNameOfCurrenMovie(){
        return request.findMovieById(this.currentRatingMovie).getTitle();
    }
    public void setCurrentMovieRate(int rate){
        this.currentMovieRate = rate;
    }
    public int getCurrentMovieRate(){
    return currentMovieRate;
    }
    
    //-------- to jest do poprawienia, i id Rate
    public void submitRate(){
        //problem jest z createUser bez podania id
    //request.createUser(currentName, tempIdForUser);
    ///---
    //User u = request.findUserById(tempIdForUser);
    if(didUserRateThisMovie() ){
        logger.info(mlb.getLoginUser().getName() + " alredy rate this movie ");
        changeRate();
        return;
    }
    request.addRate(currentMovieRate, mlb.getLoginUser().getId(),(this.currentRatingMovie)); 
    //Rate r = request.createRate(currentMovieRate,mlb.getLoginUser().getId(),request.findMovieById(this.currentRatingMovie));
    //Rate r = request.findRateById(rateId);
   // request.findMovieById(this.currentRatingMovie).
     //       addRate(r);
    
   //request.findMovieById(this.currentRatingMovie).addUser(mlb.getLoginUser());
   // mlb.getLoginUser().addMovie(request.findMovieById(this.currentRatingMovie));
    logger.info("submitRate : user.name = " + mlb.getLoginUser().getName() + " rate = " + 
               currentMovieRate + "for movie =  "  + request.findMovieById(this.currentRatingMovie).getTitle());
      
    }
    
     public void changeRate(){
        //logger.info("in changeRate rateToChange for Movie :" + rateToChange.getMovie()
         //           +" new rate " + this.currentMovieRate);
        //this.rateToChange.setRate(this.currentMovieRate);
        //logger.info("after setRate, currentMovieRate : " + this.rateToChange.getRate());
        //return;
       
        logger.info("in changeRate rateToChange for this Movie -" 
                 +" new rate :" + this.currentMovieRate );
        
        request.changeRate(this.currentRatingMovie, this.rateToChange.getId(), this.currentMovieRate);
      /*  List <Rate> rates = this.getRatesForCurrentMovieId();
        
        for (Iterator it = rates.iterator(); it.hasNext();) {
            Rate rate = (Rate) it.next();
            logger.info("rate.idOfRatingUser = " + rate.getIdOfRatingUser());
            if(rate.getIdOfRatingUser() == mlb.getLoginUser().getId() ){   
                rate.setRate(this.currentMovieRate);
                logger.info("in changeRate rate id : "+ rate.getId() +" after setRate :" + rate.getRate());
                return;
            }
        }*/
        
    }
    
    public Boolean didUserRateThisMovie(){
        logger.info("in didUserRateThisMovie ");
        List <Rate> rates = this.getRatesForCurrentMovieId();
        
        for (Iterator it = rates.iterator(); it.hasNext();) {
            Rate rate = (Rate) it.next();
            logger.info("rate.idOfRatingUser = " + rate.getIdOfRatingUser());
            if(rate.getIdOfRatingUser() == mlb.getLoginUser().getId() )
            {   this.rateToChange = rate;
                return true; }
        }return false;
        
        
    }
    
    public String findCurrentMovieUrl(){
        return request.findMovieById(this.currentRatingMovie).getUrl();
    }
    
    public int  getTempIdForUser(){
        return tempIdForUser;
    } 
    public void setTempIdForUser(int id){
        this.tempIdForUser = id;
    }
    
    public int getMinimum(){
        return minimum;
    }
    
    public int getMaximum(){
        return maximum;
    }
    
  /*  public List<Rate> getRates(){
    // Collection <Rate> rates2 = request.findMovieById(this.currentRatingMovie).getAllRates();
  //   logger.info("getRates() before cast to List, rates:" + rates.toString());
        
     Movie m = request.findMovieById(this.currentRatingMovie);
     rates = (List<Rate>) m.getRates();
     logger.info("getRates() after cast to List, rates:" + rates.toString());
     return rates;
    }*/
    
    public List <User> getUsersForCurrentMovie(){
      Movie m =  request.findMovieById(this.currentRatingMovie);
      usersForCurrentMovie = (List<User>) m.getUsers();
      logger.info("getUsersForCurrentMOvie : "+ usersForCurrentMovie.toString());
     return usersForCurrentMovie;
    }
    
    public String findUserNameById(int id){
       return request.findUserById(id).getName();
    }
    
    public List<Rate> getRatesForCurrentMovieId() {
        try {
            this.ratesForCurrentMovieId = request.getRatesForSpecyficMovie(this.currentRatingMovie);
            logger.info("ratesForCurrentMovieId contains : "+ ratesForCurrentMovieId.toString() );
            return ratesForCurrentMovieId;
        } catch (Exception e) {
            logger.info("getRatesForCurrentMovieId found nothing");
            return null;
        }
    }
    
    public Double computeAvarageRateForSpecyficMovie(Movie m){
        List<Rate> rates = request.getRatesForSpecyficMovie(m.getId() );
        double sum = 0.0;
        double counter = 0.0;
         
        for (Iterator it = rates.iterator(); it.hasNext();) {
            Rate rate = (Rate) it.next();
            sum += rate.getRate();
            counter++;
        }
        if(counter == 0){
            return 0.0;
        }
        
        Double average = sum / counter;
        logger.info("computeAverage() : " + average);
        return average;
    
    }
    
    
    public String createNewUser(){
        request.createUser(this.newUserName, this.newUserPassword);
        return "login";
    }
    
    public List<Movie> getMoviesForCurrentUserId(){
    	System.out.println(mlb == null ? "null" : "-------->mlb get Login user ");
        return (List<Movie>) request.findUserById(this.mlb.getLoginUser().getId()).getMovies();
    }
    
    public int findRateForUserMovieId(Movie m ){
        List<Rate> ratesForThisMovie = request.getRatesForSpecyficMovie(m.getId());
        
        for (Iterator it = ratesForThisMovie.iterator(); it.hasNext();) {
            Rate rate = (Rate) it.next();
            if(rate.getIdOfRatingUser() == (this.mlb.getLoginUser().getId()) ){
                return rate.getRate();
            }
            
       }
      return 0;
    }
    
}
