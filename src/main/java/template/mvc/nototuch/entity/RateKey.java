/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package template.mvc.nototuch.entity;

/**
 *
 * @author Tomek
 */
public class RateKey implements java.io.Serializable{
	
    private Integer movie;
	 public Integer id;
    
    public RateKey(){
    	// movie = 0;
    	// Integer id=0;//rateId
    }
    
    public RateKey(
        int movie,
        int id) {
        this.setMovie(movie);
        this.setId(id);
    }

    @Override
    public int hashCode() {
        return (((this.getMovie() == null) ? 0 : this.getMovie()
                                                     .hashCode())
        ^ ((int) this.getId()));
    }
    
    @Override
    public boolean equals(Object otherOb) {
        if (this == otherOb) {
            return true;
        }

        if (!(otherOb instanceof Rate)) {
            return false;
        }

        Rate other = (Rate) otherOb;

        return (((this.getMovie() == null) ? (other.getMovie() == null)
                                           : this.getMovie()
                                                 .equals(other.getMovie()))
        && (this.getId() == other.getId()));
    }
    
    @Override
    public String toString() {
        return "" + getMovie() + "-" + getId();
    }
    
    public void setId(int id) { //setRateId
        this.id = id;
    }
    
     public int getId(){ //getRateId
        return id;
    }

     public void setMovie(Integer movie) {
        this.movie = movie;
    }
    
     public Integer getMovie(){
        return movie;
    }
}
