package com.tambola.service;

import com.tambola.model.Claim;
import com.tambola.service.rules.GameClaimRule;

public class ClaimValidator {

    public boolean validateClaim(Claim claim) {
        if (claim == null || claim.getTicket() == null || claim.getGameType() == null
                || claim.getNumbersAnnounced() == null || claim.getNumbersAnnounced().isEmpty()) {
            return false;
        }

        GameClaimRule rule = ClaimRuleFactory.getRule(claim.getGameType());
        return rule.validate(claim);
    }
}
