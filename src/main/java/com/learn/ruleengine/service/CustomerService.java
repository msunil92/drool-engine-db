package com.learn.ruleengine.service;

import com.learn.ruleengine.model.Customer;
import com.learn.ruleengine.model.RuleObject;
import org.kie.api.runtime.KieContainer;
import org.kie.api.runtime.KieSession;
import org.springframework.stereotype.Service;

/**
 * @author sunil
 * @project spring-boot-drools-db
 * @created 2021/07/28 1:15 PM
 */
@Service
public class CustomerService {



    public Customer getCustomerDrl() {

        KieContainer kieContainer = new RulesService().getKieContainer();
        KieSession ksession_drl = kieContainer.newKieSession();
        Customer customer = new Customer();
        customer.setBond(7);
        customer.setName("Rohit");
        customer.setType(Customer.CustomerType.GOLD);
        ksession_drl.insert(customer);
        ksession_drl.fireAllRules();

        return customer;
    }

    public Customer getCustomerDrlValidation() {

        KieContainer kieContainer = new RulesService().getKieContainer();
        KieSession ksession_drl = kieContainer.newKieSession();
        Customer customer = new Customer();
        RuleObject ruleObject = new RuleObject();
        customer.setBond(7);
        customer.setName("Rohit");
        customer.setType(Customer.CustomerType.GOLD);
        ksession_drl.insert(customer);
        ksession_drl.insert(ruleObject);
        ksession_drl.fireAllRules();

        System.out.println(customer);
        System.out.println(ruleObject);

        return customer;
    }
}
