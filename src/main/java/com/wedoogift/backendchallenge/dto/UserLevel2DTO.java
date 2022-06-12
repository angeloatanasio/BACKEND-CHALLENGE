package com.wedoogift.backendchallenge.dto;

import lombok.Data;
import lombok.EqualsAndHashCode;

import java.util.ArrayList;
import java.util.List;

@Data
@EqualsAndHashCode(callSuper = true)
public class UserLevel2DTO extends UserDTO {

    private List<WalletBalanceDTO> balance = new ArrayList<>();

    public UserLevel2DTO(long id) {
        super(id);
    }
}
