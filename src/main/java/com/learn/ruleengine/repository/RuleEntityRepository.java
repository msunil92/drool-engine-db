package com.learn.ruleengine.repository;

import com.learn.ruleengine.model.RulesEntity;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * @author sunil
 * @project spring-boot-drools-db
 * @created 2021/07/28 12:45 PM
 */


public interface RuleEntityRepository extends JpaRepository<RulesEntity, Long> {

}