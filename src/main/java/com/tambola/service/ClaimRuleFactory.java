package com.tambola.service;

import com.tambola.model.GameType;
import com.tambola.service.rules.BottomLineRule;
import com.tambola.service.rules.EarlyFiveRule;
import com.tambola.service.rules.FullHouseRule;
import com.tambola.service.rules.GameClaimRule;
import com.tambola.service.rules.MiddleLineRule;
import com.tambola.service.rules.TopLineRule;

public class ClaimRuleFactory {

    private ClaimRuleFactory() {

    }

    public static GameClaimRule getRule(GameType gameType) {
        if (gameType == null) {
            throw new IllegalArgumentException("Invalid game type");
        }

        return switch (gameType) {
            case TOP_LINE ->
                new TopLineRule();
            case MIDDLE_LINE ->
                new MiddleLineRule();
            case BOTTOM_LINE ->
                new BottomLineRule();
            case FULL_HOUSE ->
                new FullHouseRule();
            case EARLY_FIVE ->
                new EarlyFiveRule();
        };
    }
}
