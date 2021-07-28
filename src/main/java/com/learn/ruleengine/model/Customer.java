package com.learn.ruleengine.model;

import lombok.*;

/**
 * @author sunil
 * @project drool-rule-engine
 * @created 2021/07/24 12:50 PM
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
@Setter
@Getter
public class Customer {

    private CustomerType type;
    private String name;
    private int bond;
    private int offers;
    private String status;

    public enum CustomerType {
        GOLD, SILVER, PLATINUM;
    }
}