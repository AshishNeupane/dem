package com.ann.dem.model;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;
import javax.persistence.*;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotNull;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.List;

@Entity
@Data
@Table(name = "users")
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @Column(unique = true)
    //@NotNull(message = "error.common.null")
    //@NotBlank(message = "error.common.empty")
    private String username;

    private String password;

    private boolean enabled;
    
    @Column(name = "account_non_locked")
    private Boolean accountNonLocked = true;
    
    @JsonProperty("first_name")
    private String firstName;
    
    @JsonProperty("last_name")
    private String lastName;
    
    @JsonProperty("date_of_birth")
    private Date dateOfBirth;
    
    @Email(message = "error.common.email")
    @JsonProperty("email_id")
    @Column(unique = true)
    private String emailId;


    @Column(columnDefinition = "boolean default false")
    private boolean deleted;

    @NotNull(message = "error.common.null")
    private boolean active;

    private String roles = "";

    private String permissions = "";

    public User(String username, String password, String roles, String permissions){
        this.username = username;
        this.password = password;
        this.roles = roles;
        this.permissions = permissions;
        this.active = true;
    }
    
    protected User(){}

    @JsonIgnore
    public List<String> getRoleList(){
        if(this.roles.length() > 0){
            return Arrays.asList(this.roles.split(","));
        }
        return new ArrayList<>();
    }

    @JsonIgnore
    public List<String> getPermissionList(){
        if(this.permissions.length() > 0){
            return Arrays.asList(this.permissions.split(","));
        }
        return new ArrayList<>();
    }

    @JsonIgnore
    public String getPassword() {
        return password;
    }

    @JsonProperty
    public void setPassword(String password) {
        this.password = password;
    }
}