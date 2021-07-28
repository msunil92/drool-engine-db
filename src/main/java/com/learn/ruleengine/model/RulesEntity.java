package com.learn.ruleengine.model;

import lombok.*;
import javax.persistence.*;
import java.io.Serializable;

/**
 * @author sunil
 * @project spring-boot-drools-db
 * @created 2021/07/28 12:45 PM
 */

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class RulesEntity implements Serializable {

	private static final long serialVersionUID = 1L;
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	@Column(nullable = false, unique = true)
	private String ruleKey;
	@Column(nullable = false)
	private String content;

}