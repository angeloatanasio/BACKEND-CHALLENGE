package com.wedoogift.backendchallenge.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class DistributionDTO {

    private long id;
    private int amount;
    private String start_date;
    private String end_date;
    private long company_id;
    private long user_id;

}
