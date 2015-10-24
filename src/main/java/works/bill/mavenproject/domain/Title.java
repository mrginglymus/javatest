/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package works.bill.mavenproject.domain;

/**
 *
 * @author bill
 */
public class Title {
    
    private int id;
    private String displayName;
    private String name;
    
    public Title() {}
    
    public Title(int id, String displayName, String name) {
        this.id = id;
        this.displayName = displayName;
        this.name = name;
    }
    
    public int getId() {
        return id;
    }
    
    public void setId(int id) {
        this.id = id;
    }
    
    public String getDisplayName() {
        return displayName;
    }
    
    public void setDisplayName(String displyaName) {
        this.displayName = displayName;
    }
    
    public String getName() {
        return name;    
    }
    
    public void setName(String name) {
        this.name = name;
    }
    
    @Override
    public String toString() {
        return name;
    }
}
