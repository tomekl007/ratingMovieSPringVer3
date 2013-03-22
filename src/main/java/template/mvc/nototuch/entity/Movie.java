package template.mvc.nototuch.entity;


import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.logging.Logger;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import static javax.persistence.CascadeType.ALL;
import javax.persistence.*;


/**
 *
 * @author Tomek
 */
@Entity

@Table(name = "RATING_MOVIE")
@NamedQuery(name = "findAllMovies", query = "SELECT m FROM Movie m "
+ "ORDER BY m.id")
//znajduje nie zdupilkowane/distinct/ movie, w ktorych user.id = arg
//@NamedQuery(name="findMoviesByUserId" query= "SELECT DISTINCT m from Movie m, IN(m.users) AS u WHERE u.id = : userId");
public class Movie implements java.io.Serializable {
    private static Logger logger = Logger.getLogger("rating.ejb.entity.Movie");
    private String title;
    private String url;
    @Id
    private Integer id;
   // @JoinTable(name = "RATING_MOVIE_USER", joinColumns = @JoinColumn(name = "MOVIE_ID", referencedColumnName = "ID")
 //  , inverseJoinColumns = @JoinColumn(name = "USER_ID", referencedColumnName = "ID")
//   )
    //@ManyToMany
  //  @JoinTable(name = "RATING_MOVIE_USER", joinColumns = @JoinColumn(name = "MOVIE_ID", referencedColumnName = "ID")
  //    , inverseJoinColumns = @JoinColumn(name = "USER_ID", referencedColumnName = "ID")
 //     )
    @ManyToMany
    private Collection<User> users;
   // private int rate;
   @OneToMany(cascade = ALL, mappedBy = "movie", fetch = FetchType.EAGER )
  // @ElementCollection(fetch=FetchType.EAGER)
   private Collection<Rate> rates;
    
    public Movie(){
        this.users = new ArrayList<User>();
        this.rates = new ArrayList<Rate>();
    }
    
    public Movie(String t, int id,String url ){
        this.title = t;
        this.id = id;
        this.url = url;
        this.users = new ArrayList<User>();
        this.rates = new ArrayList<Rate>();
    
     
    }
    
        public Integer getId() {
        return id;
    }

    public void setId(Integer movieId) {
        this.id = movieId;
    }
    
    public String getTitle(){
        return title;
    }
    
    public void setTitle(String t){
        this.title = t;
    }
    
    public String getUrl(){
        return url;
    }
    
    public void setUrl(String url){
        this.url = url;
    }
    
          public Collection<User> getUsers(){
        logger.info(users.isEmpty()? "users is empty :":  users.toString() );
        return users;
    }
    
    public void setUsers(Collection<User> users){
        this.users = users;
    }
    
     public void addUser(User u) {
         logger.info("addUser "+ u.toString());
        this.getUsers()
            .add(u);
    }

    public void dropUser(User u) {
        this.getUsers()
            .remove(u);
    }
    
  
    
    
     public Collection<Rate> getRates(){
       // logger.info("Movie.getallRates() rates :"+ rates.toString() );
        logger.info(rates.isEmpty()? " rates is empty" : rates.toString());
           return rates;
    }
    
    public void setRates(Collection<Rate> r){
        this.rates = r;
    }
    
    public void addRate(Rate r){
        logger.info("in MOvie.addRate() rate = "+ r.toString() );
        this.getRates().add(r);
        logger.info("getRates after add new rate" + this.getRates().toString());
    }
    
  
    
    @Override
    public String toString(){
        return this.title;
    }
    
}
