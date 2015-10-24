/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package works.bill.mavenproject;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import java.io.Serializable;
import javax.validation.constraints.Size;

/**
 *
 * @author bill
 */
@ManagedBean
@SessionScoped
public class MyBean implements Serializable {

    /**
     * Creates a new instance of MyBean
     */
    public MyBean() {
    }
    
    @Size(min=5, max=11)
    private String name;
    
    public String getName() {
        return name;
    }
    
    public void setName(String name) {
        this.name = name;
    }
    
    public String greet() {
        return String.format("Welcome, %s", name);
    }
    
}
