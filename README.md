# drool-engine-db-main

#### MYSQL DB:
    
    select * from rules_entity;
    
    drop table rules_entity;
    
    
    show CREATE TABLE rules_entity;
    
    CREATE TABLE `rules_entity` (
    `id` bigint(20) NOT NULL AUTO_INCREMENT,
    `content` text NOT NULL,
    `rule_key` varchar(255) NOT NULL,
    PRIMARY KEY (`id`)
    )  ;



#### POSTGRESS DB:
    
    select * from rules_entity;
    
    drop table rules_entity;
    
    CREATE TABLE rules_entity (
    id BIGINT GENERATED BY DEFAULT AS IDENTITY,
    content text ,
    rule_key VARCHAR(255),
    PRIMARY KEY (id)
    );


#### API's:
    
    GET: http://localhost:8080/api/reload
    
    GET: http://localhost:8080/api/customer_drl
    
    GET: http://localhost:8080/api/customer_drl_validation

    GET: http://localhost:8080/api/delete/{id}

    POST: http://localhost:8080/api/save_rule
        request is rules in string format

#### Rule Engine:
    
    import com.learn.ruleengine.model.Customer;
    import com.learn.ruleengine.model.RuleObject;

    rule "Bond check"
    when
    customerObj: Customer(bond == 7)
    then
    customerObj.setOffers(1000);
    customerObj.setStatus("SpiderMan");
    end

    rule "Bond check with Validation"
    when
    customerObj: Customer(bond == 7)
    ruleObject: RuleObject()
    then
    customerObj.setOffers(1000);
    customerObj.setStatus("Super duper");
    ruleObject.setValid(true);
    end'
