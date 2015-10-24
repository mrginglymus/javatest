/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package works.bill.mavenproject.service;

import java.util.ArrayList;
import java.util.List;
import javax.annotation.PostConstruct;
import javax.faces.bean.ApplicationScoped;
import javax.faces.bean.ManagedBean;

import works.bill.mavenproject.domain.Title;

/**
 *
 * @author bill
 */
@ManagedBean(name = "titleService", eager=true)
@ApplicationScoped
public class TitleService {

    private List<Title> titles;
    
    @PostConstruct
    public void init() {
        titles = new ArrayList<Title>();
        titles.add(new Title(0, "mr", "Mr"));
        titles.add(new Title(1, "mrs", "Mrs"));
        titles.add(new Title(2, "prof", "Professor"));
    }
    
    public List<Title> getTitles() {
        return titles;
    }
    
}
