/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package works.bill.mavenproject.view;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import java.io.Serializable;
import java.util.List;
import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedProperty;
import javax.validation.constraints.Size;

import works.bill.mavenproject.service.TitleService;
import works.bill.mavenproject.domain.Title;

/**
 *
 * @author bill
 */
@ManagedBean
@SessionScoped
public class NameView implements Serializable {

    
    @Size(min=5, max=11)
    private String name;
    private Title title;
    private List<Title> titles;
    
    @ManagedProperty("#{titleService}")
    private TitleService titleService;
    
    @PostConstruct
    public void init() {
        titles = titleService.getTitles();
    }
    
    public String getName() {
        return name;
    }
    
    public void setName(String name) {
        this.name = name;
    }
    
    public Title getTitle() {
        return title;
    }
    
    public void setTitle(Title title) {
        this.title = title;
    }
    
    public List<Title> getTitles() {
        return titles;
    }
    
    public void setTitleService(TitleService titleService) {
        this.titleService = titleService;
    }
    
    public String fullName() {
        return String.format("%s %s", title, name);
    }
    
    public String greet() {
        return String.format("Welcome, %s", fullName());
    }
    
}
