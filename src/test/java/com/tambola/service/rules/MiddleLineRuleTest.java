package com.tambola.service.rules;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import org.junit.jupiter.api.Test;

import com.tambola.model.Claim;
import com.tambola.model.GameType;
import com.tambola.model.Ticket;

class MiddleLineRuleTest {

    @Test
    void testMiddleLineValidClaim() {
        int[][] grid = {
            {1, 2, 3, -1, -1, -1, -1, -1, -1},
            {4, 5, 6, 7, 8, 9, -1, -1, -1},
            {10, 11, 12, -1, -1, -1, -1, -1, -1}
        };
        Ticket ticket = new Ticket(grid);
        List<Integer> announced = List.of(4, 5, 6, 7, 8, 9);
        Claim claim = new Claim(ticket, announced, GameType.MIDDLE_LINE);

        MiddleLineRule rule = new MiddleLineRule();
        assertTrue(rule.validate(claim));
    }

    @Test
    void testMiddleLineInvalidClaim() {
        int[][] grid = {
            {1, 2, 3, -1, -1, -1, -1, -1, -1},
            {4, 5, 6, 7, 8, 9, -1, -1, -1},
            {10, 11, 12, -1, -1, -1, -1, -1, -1}
        };
        Ticket ticket = new Ticket(grid);
        List<Integer> announced = List.of(4, 5, 6, 7, 8); // Missing 9
        Claim claim = new Claim(ticket, announced, GameType.MIDDLE_LINE);

        MiddleLineRule rule = new MiddleLineRule();
        assertFalse(rule.validate(claim));
    }
}
