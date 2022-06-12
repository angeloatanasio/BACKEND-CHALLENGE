package com.wedoogift.backendchallenge.dto;

import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@EqualsAndHashCode(callSuper = true)
public class DistributionLevelDTO extends DistributionDTO {

    private long wallet_id;

    public DistributionLevelDTO(long id, int amount, String start_date, String end_date, long company_id, long user_id) {
        super(id, amount, start_date, end_date, company_id, user_id);
    }

}
