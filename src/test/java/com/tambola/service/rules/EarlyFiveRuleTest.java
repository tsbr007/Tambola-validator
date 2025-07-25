package com.tambola.service.rules;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import org.junit.jupiter.api.Test;

import com.tambola.model.Claim;
import com.tambola.model.GameType;
import com.tambola.model.Ticket;

class EarlyFiveRuleTest {

    @Test
    void testEarlyFiveAccepted() {
        int[][] grid = {
            {1, 2, 3, 4, 5, -1, -1, -1, -1},
            {-1, -1, -1, -1, -1, -1, -1, -1, -1},
            {-1, -1, -1, -1, -1, -1, -1, -1, -1}
        };
        Ticket ticket = new Ticket(grid);
        List<Integer> announced = List.of(1, 2, 3, 4, 5);
        Claim claim = new Claim(ticket, announced, GameType.EARLY_FIVE);

        EarlyFiveRule rule = new EarlyFiveRule();
        assertTrue(rule.validate(claim));
    }

    @Test
    void testEarlyFiveInvalidLessThanFive() {
        int[][] grid = {
            {1, 2, 3, 4, 5, -1, -1, -1, -1},
            {-1, -1, -1, -1, -1, -1, -1, -1, -1},
            {-1, -1, -1, -1, -1, -1, -1, -1, -1}
        };
        Ticket ticket = new Ticket(grid);
        List<Integer> announced = List.of(1, 2, 3);
        Claim claim = new Claim(ticket, announced, GameType.EARLY_FIVE);

        EarlyFiveRule rule = new EarlyFiveRule();
        assertFalse(rule.validate(claim));
    }
}
