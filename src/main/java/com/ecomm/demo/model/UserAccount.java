package com.ecomm.demo.model;

import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Data
@Document(value = "userAccount")
public class UserAccount {
    @Id
    public String id;

    public String username;

    public String password;

    public String name;

    //Todo: Add address and contact details

    public UserAccount(String username, String password, String name){
        this.username = username;
        this.password = password;
        this.name = name;
    }
}
