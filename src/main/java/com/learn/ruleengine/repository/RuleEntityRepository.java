package com.learn.ruleengine.repository;

import com.learn.ruleengine.model.RulesEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface RuleEntityRepository extends JpaRepository<RulesEntity, Long> {

}