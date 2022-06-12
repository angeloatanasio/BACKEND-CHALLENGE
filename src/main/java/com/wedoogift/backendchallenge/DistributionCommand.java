package com.wedoogift.backendchallenge;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;

@Getter
public class DistributionCommand {

    private String levelNumber;
    private int distributionAmount;
    private long userId;
    private String typeWallet;

    @JsonCreator
    public DistributionCommand(@JsonProperty("levelNumber") String levelNumber,
                               @JsonProperty("distributionAmount") int distributionAmount,
                               @JsonProperty("userId")long userId,
                               @JsonProperty("typeWallet")String typeWallet) {
        this.levelNumber = levelNumber;
        this.distributionAmount = distributionAmount;
        this.userId = userId;
        this.typeWallet = typeWallet;
    }
}
