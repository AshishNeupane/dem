package com.ann.dem.model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonProperty;

import lombok.Data;

@Entity
@Data
@Table(name = "role_permission")
public class RolePermission {
	@Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
	
	@JsonProperty("role_id")
	private Long roleId;
	
	@JsonProperty("permission_id")
	private Long permissionId;
	
	@JsonProperty("url")
	private String url;

	@JsonProperty("is_allowed")
	private Boolean isAllowed;
	
	@JsonProperty("method_type")
	private String methodType;
}
