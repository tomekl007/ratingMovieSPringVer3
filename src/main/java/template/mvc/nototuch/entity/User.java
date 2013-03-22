/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package template.mvc.nototuch.entity;

import java.util.ArrayList;
import java.util.Collection;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.NamedQuery;
import javax.persistence.Table;

/**
 *
 * @author Tomek
 */
@Entity
@Table(name = "RATING_USER")
@NamedQuery(name = "findAllUsers", query = "SELECT u FROM User u "
+ "ORDER BY u.id")


public class User implements java.io.Serializable{
   
    @ManyToMany(mappedBy = "users")
    private Collection<Movie> movies;
    private String name;
    private String password;
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int id;
    
    
    public User(){
        // movies = new ArrayList<Movie>();
    }
    
  //  public User(String n){
   //     this.id = this.getNextId();
  //      this.name = n;
     //   movies = new ArrayList<Movie>();
  //  }
    
    public User(String n, String p){
       
        this.name = n;
        this.password  = p;
     //   movies = new ArrayList<Movie>();
        
    }
    
        public int getId() {
        return id;
    }

    public void setId(int Id) {
        this.id = Id;
    }
    
    public String getName(){
        return name;
    }
    
    public void setName(String n){
        this.name = n;
    }
    
   public String getPassword(){
        return password;
    }
    
    public void setPassword(String prd){
        this.password = prd;
    }
        public Collection<Movie> getMovies() {
        return movies;
    }
            
    public void setMovies(Collection<Movie> m) {
        this.movies = m;
    }
    
  
     
      public void addMovie(Movie m) {
        this.getMovies()
            .add(m);
    }

    public void dropMovie(Movie m) {
        this.getMovies()
            .remove(m);
    }
    
   @Override
   public String toString(){
        return name;
       
   }
}
