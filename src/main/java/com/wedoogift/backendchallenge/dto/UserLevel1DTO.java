package com.wedoogift.backendchallenge.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;

@EqualsAndHashCode(callSuper = true)
@Data
public class UserLevel1DTO extends UserDTO{


    private int balance;

    public UserLevel1DTO(long id) {
        super(id);
    }

    public UserLevel1DTO(long id, int balance) {
        super(id);
        this.balance = balance;
    }
}
