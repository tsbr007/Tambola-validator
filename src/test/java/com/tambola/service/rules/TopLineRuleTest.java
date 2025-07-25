package com.tambola.service.rules;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import org.junit.jupiter.api.Test;

import com.tambola.model.Claim;
import com.tambola.model.GameType;
import com.tambola.model.Ticket;

class TopLineRuleTest {

    @Test
    void testTopLineValidClaim() {
        int[][] grid = {
            {4, 16, -1, -1, 48, -1, 63, 76, -1},
            {7, -1, 23, 38, -1, 52, -1, -1, 80},
            {9, -1, 25, -1, -1, 56, 64, -1, 83}
        };
        Ticket ticket = new Ticket(grid);
        List<Integer> announced = List.of(90, 4, 63, 16, 48, 76);
        Claim claim = new Claim(ticket, announced, GameType.TOP_LINE);

        TopLineRule rule = new TopLineRule();
        assertTrue(rule.validate(claim));
    }

    @Test
    void testTopLineInvalidDueToLateClaim() {
        int[][] grid = {
            {4, 16, -1, -1, 48, -1, 63, 76, -1},
            {7, -1, 23, 38, -1, 52, -1, -1, 80},
            {9, -1, 25, -1, -1, 56, 64, -1, 83}
        };
        Ticket ticket = new Ticket(grid);
        List<Integer> announced = List.of(90, 4, 63, 16, 48, 76, 12); // 12 is not in top row
        Claim claim = new Claim(ticket, announced, GameType.TOP_LINE);

        TopLineRule rule = new TopLineRule();
        assertFalse(rule.validate(claim));
    }
}
