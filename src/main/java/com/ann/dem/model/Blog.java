package com.ann.dem.model;

import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

import com.fasterxml.jackson.annotation.JsonProperty;

import lombok.Data;

@Data
@Entity
@Table(name = "blog")
public class Blog {

		@Id
	    @GeneratedValue(strategy = GenerationType.IDENTITY)
	    private Long id;

	    @NotNull(message = "error.common.null")
	    @NotBlank(message = "error.common.blank")
	    private String title;
	    
	    @JsonProperty("body")
	    @Column(columnDefinition = "VARCHAR(1200)")
	    private String body;
}
