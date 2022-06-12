package com.wedoogift.backendchallenge.dto;

import lombok.*;


@Data
@NoArgsConstructor
public class UserDTO {

    private long id;

    public UserDTO(long id) {
        this.id = id;
    }

}
