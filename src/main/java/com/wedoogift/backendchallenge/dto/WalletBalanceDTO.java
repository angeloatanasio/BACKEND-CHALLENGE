package com.wedoogift.backendchallenge.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class WalletBalanceDTO{

    private long wallet_id;
    private int amount;

}
