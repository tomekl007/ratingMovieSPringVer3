/*
 * Copyright 2012 Oracle and/or its affiliates.
 * All rights reserved.  You may not modify, use,
 * reproduce, or distribute this software except in
 * compliance with  the terms of the License at:
 * http://developers.sun.com/license/berkeley_license.html
 */


package template.mvc.nototuch.web;

import java.util.Iterator;
import java.util.logging.Logger;
import javax.ejb.EJB;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ApplicationScoped;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.RequestScoped;
import javax.faces.bean.SessionScoped;
import javax.faces.context.FacesContext;
import javax.inject.Inject;

import template.mvc.nototuch.RequestBean;
import template.mvc.nototuch.entity.User;




//The managed bean, src/java/compositecomponentlogin/MyLoginBean.java, defines a
//method called login, which retrieves the values of the username and password.
@ManagedBean
@SessionScoped
public class MyLoginBean {
    private static final Logger logger = Logger.getLogger(
                "rating.web.MyLogiBean");
    private String name;
    private String password;
    private User loginUser;
    private Boolean renderCreateUser = false;
    
     @EJB
    private RequestBean request;
     
    // @ManagedProperty(value="#{RatingManager}")
    // private RatingManager rm;
     
  //   @Inject
  //   private RatingManager rm;

    /** Creates a new instance of MyLoginBean */
    public MyLoginBean() {
    }

    public MyLoginBean(
        String name,
        String password, User lu) {
        this.name = name;
        this.password = password;
        this.loginUser = lu;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String newValue) {
        password = newValue;
    }

    public String getName() {
        return name;
    }

    public void setName(String newValue) {
        name = newValue;
    }
    
    public void setLoginUser(User u){
        this.loginUser = u;
    }

    public User getLoginUser(){
        return loginUser;
    }
    
     public void setRenderCreateUser(Boolean r){
        this.renderCreateUser = r;
    }

    public Boolean getRenderCreateUser(){
        return renderCreateUser;
    }
    
    
            
    public String login() {
        
     for( Iterator it =  request.getAllUsers().iterator(); it.hasNext();){
         User user = (User) it.next();
         if(getName().equals(user.getName())){
             if(getPassword().equals(user.getPassword())){
                 logger.info("i found user " +  user.getName() + " with pass : " + user.getPassword()  );
                 String msg = "You Have succesfully logged in ";
                 FacesMessage facesMsg = new FacesMessage(msg, msg);
                 FacesContext.getCurrentInstance()
                        .addMessage(null, facesMsg);
                 this.loginUser = user;
              //   rm.setCurrentUser(user);
                 
                 return "index";
         }else{
            String msg = "Failure. You typed incorrect password, try again! ";
            FacesMessage facesMsg = new FacesMessage(
                        FacesMessage.SEVERITY_ERROR,
                        msg,
                        msg);
            FacesContext.getCurrentInstance()
                        .addMessage(null, facesMsg);

            return "login";
                 
           }
       }if(!it.hasNext()){
             this.setRenderCreateUser(true);
            String msg = "There is no user in such name ";
            FacesMessage facesMsg = new FacesMessage(
                        FacesMessage.SEVERITY_ERROR,
                        msg,
                        msg);
            FacesContext.getCurrentInstance()
                        .addMessage(null, facesMsg);
            return null;
         }
     
     } 
       
   //pozniewz funkjca zada zeby cos bylo zwracane : --- do nieczego nie potrzebne       
   return "login";  
    
}

    public String reset(){
        setName("");
        setPassword("");
        String msg = "You pressed Reset, so all field are clear ";
        FacesMessage facesMsg = new FacesMessage(msg,msg);
        FacesContext.getCurrentInstance()
                        .addMessage(null, facesMsg);
        return "login";
    }
    
    

}
