package com.tambola.service;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;
import org.junit.jupiter.api.Test;

import com.tambola.model.GameType;
import com.tambola.service.rules.BottomLineRule;
import com.tambola.service.rules.EarlyFiveRule;
import com.tambola.service.rules.FullHouseRule;
import com.tambola.service.rules.GameClaimRule;
import com.tambola.service.rules.MiddleLineRule;
import com.tambola.service.rules.TopLineRule;

class ClaimRuleFactoryTest {

    @Test
    void shouldReturnTopLineRule() {
        GameClaimRule rule = ClaimRuleFactory.getRule(GameType.TOP_LINE);
        assertNotNull(rule);
        assertTrue(rule instanceof TopLineRule);
    }

    @Test
    void shouldReturnMiddleLineRule() {
        GameClaimRule rule = ClaimRuleFactory.getRule(GameType.MIDDLE_LINE);
        assertNotNull(rule);
        assertTrue(rule instanceof MiddleLineRule);
    }

    @Test
    void shouldReturnBottomLineRule() {
        GameClaimRule rule = ClaimRuleFactory.getRule(GameType.BOTTOM_LINE);
        assertNotNull(rule);
        assertTrue(rule instanceof BottomLineRule);
    }

    @Test
    void shouldReturnFullHouseRule() {
        GameClaimRule rule = ClaimRuleFactory.getRule(GameType.FULL_HOUSE);
        assertNotNull(rule);
        assertTrue(rule instanceof FullHouseRule);
    }

    @Test
    void shouldReturnEarlyFiveRule() {
        GameClaimRule rule = ClaimRuleFactory.getRule(GameType.EARLY_FIVE);
        assertNotNull(rule);
        assertTrue(rule instanceof EarlyFiveRule);
    }

    @Test
    void shouldThrowExceptionForUnsupportedGameType() {
        Exception exception = assertThrows(IllegalArgumentException.class, () -> {
            ClaimRuleFactory.getRule(null);
        });
        assertEquals("Invalid game type", exception.getMessage());
    }
}
