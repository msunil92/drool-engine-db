package com.learn.ruleengine.controller;

import com.learn.ruleengine.model.Customer;
import com.learn.ruleengine.service.CustomerService;
import com.learn.ruleengine.service.RulesService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;

/**
 * @author sunil
 * @project spring-boot-drools-db
 * @created 2021/07/28 12:45 PM
 */
@RequestMapping("/api")
@RestController
public class Endpoints {

    @Autowired
    private RulesService rulesService;

    @Autowired
    private CustomerService customerService;

    @GetMapping(value = "customer_drl" , produces = MediaType.APPLICATION_JSON_VALUE)
    public Customer getcustomer_drl() {
        long startTime = System.currentTimeMillis();
        Customer customer = customerService.getCustomerDrl();
        System.out.println(customer);
        System.out.println(customer.getOffers());
        long endTime = System.currentTimeMillis();
        return customer;
    }

    @GetMapping(value = "customer_drl_validation" , produces = MediaType.APPLICATION_JSON_VALUE)
    public Customer getcustomer_drl_validation() {
        long startTime = System.currentTimeMillis();
        Customer customer = customerService.getCustomerDrlValidation();
        System.out.println(customer);
        System.out.println(customer.getOffers());
        long endTime = System.currentTimeMillis();
        return customer;
    }

    @GetMapping("/save")
    public String save() throws Exception{

        rulesService.save();
        return "saved";

    }

    @PostMapping(value = "/save_rule")
    public String saverules(@RequestBody String rulesContents) throws Exception{
        rulesService.saverules(rulesContents);
        return "saved rules";
    }

    @GetMapping(value = "/delete/{id}")
    public String deleteRule(@PathVariable("id") Long id) {
        rulesService.deleteRule(id);
        return "Deleted successfully :: " + id;
    }


    @GetMapping("/reload")
    public String reload() throws IOException {
        rulesService.reload();
        return "ok";
    }

}
