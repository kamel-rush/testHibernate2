package com.test.controller;

import com.test.models.CustomersEntity;
import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;
import org.hibernate.criterion.Restrictions;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import javax.validation.Valid;
import java.util.ArrayList;
import java.util.Map;

@Controller
public class HomeController {

    @RequestMapping("/")

    public String helloWorld()
    {
       return "index";

    }

    public ArrayList<CustomersEntity> getAllCustomers()
    {
        Configuration cfg = new Configuration().configure("hibernate.cfg.xml");


        SessionFactory sessionFact = cfg.buildSessionFactory();

        Session selectCustomers = sessionFact.openSession();

        selectCustomers.beginTransaction();

        Criteria c = selectCustomers.createCriteria(CustomersEntity.class);

        ArrayList<CustomersEntity> customerList=(ArrayList<CustomersEntity>)c.list();

        return  customerList;


    }


    @RequestMapping("newCustomer")

    public String newCustomer() {

    return "newCustomer";
    }


    @RequestMapping("welcome")

    public ModelAndView helloWorld2()
    {

ArrayList<CustomersEntity> customerList = getAllCustomers();


        return new
                ModelAndView("welcome2","cList",customerList);

    }

    @RequestMapping("addCustomer")
    public String addCustomer(@Valid @ModelAttribute("customerForm") CustomersEntity temp,
                                    BindingResult result, Model model)
    {

        if (result.hasErrors())
        {  model.addAttribute("errors",((FieldError) result.getAllErrors().get(0)).getCode());
            return "newCustomer";
        }

        Configuration cfg = new Configuration().configure("hibernate.cfg.xml");

        SessionFactory fact = cfg.buildSessionFactory();

        Session customers = fact.openSession();
        customers.beginTransaction();

        customers.save(temp);// delete the object from the list

        customers.getTransaction().commit();// delete the row from the database

        ArrayList<CustomersEntity> customerList = getAllCustomers();

        model.addAttribute("cList",customerList);
        return "welcome2";

    }



    @RequestMapping("delete")
    public ModelAndView deleteCustomer(@RequestParam("id") String id)
    {
        // temp will store info for the object that we want to delete
        CustomersEntity temp = new CustomersEntity();
        temp.setCustomerId(id);


        Configuration cfg = new Configuration().configure("hibernate.cfg.xml");

        SessionFactory fact = cfg.buildSessionFactory();

        Session customers = fact.openSession();
        customers.beginTransaction();

        customers.delete(temp);// delete the object from the list

        customers.getTransaction().commit();// delete the row from the database

        ArrayList<CustomersEntity> customerList = getAllCustomers();

        return new
                ModelAndView("welcome2","cList",customerList);

    }


    @RequestMapping("searchByCity")
    public ModelAndView searchByCity(@RequestParam("city") String cityName)
    {

        Configuration cfg = new Configuration().configure("hibernate.cfg.xml");


        SessionFactory sessionFact = cfg.buildSessionFactory();

        Session selectCustomers = sessionFact.openSession();

        selectCustomers.beginTransaction();

        Criteria c = selectCustomers.createCriteria(CustomersEntity.class);

        c.add(Restrictions.like("city","%"+cityName+"%"));

ArrayList<CustomersEntity> customerList =
        (ArrayList<CustomersEntity>) c.list();


        return new
                ModelAndView("welcome2","cList",customerList);




    }

}
