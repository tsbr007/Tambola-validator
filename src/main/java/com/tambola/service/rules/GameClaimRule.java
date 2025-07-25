package com.tambola.service.rules;

import com.tambola.model.Claim;

public interface GameClaimRule {

    boolean validate(Claim claim);
}
