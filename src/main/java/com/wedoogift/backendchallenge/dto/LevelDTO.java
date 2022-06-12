package com.wedoogift.backendchallenge.dto;

import lombok.Data;

import java.util.List;

@Data
public class LevelDTO {

    private List<CompanyDTO> companies;
    private List<UserDTO> users;
    private List<DistributionDTO> distributions;
}
