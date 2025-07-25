package com.tambola.service.rules;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import org.junit.jupiter.api.Test;

import com.tambola.model.Claim;
import com.tambola.model.GameType;
import com.tambola.model.Ticket;

class BottomLineRuleTest {

    @Test
    void testBottomLineValidClaim() {
        int[][] grid = {
            {-1, -1, -1, -1, -1, -1, -1, -1, -1},
            {-1, -1, -1, -1, -1, -1, -1, -1, -1},
            {10, 20, 30, -1, -1, -1, -1, -1, -1}
        };
        Ticket ticket = new Ticket(grid);
        List<Integer> announced = List.of(1, 2, 10, 20, 30);
        Claim claim = new Claim(ticket, announced, GameType.BOTTOM_LINE);

        BottomLineRule rule = new BottomLineRule();
        assertTrue(rule.validate(claim));
    }

    @Test
    void testBottomLineInvalidClaim() {
        int[][] grid = {
            {-1, -1, -1, -1, -1, -1, -1, -1, -1},
            {-1, -1, -1, -1, -1, -1, -1, -1, -1},
            {10, 20, 30, -1, -1, -1, -1, -1, -1}
        };
        Ticket ticket = new Ticket(grid);
        List<Integer> announced = List.of(10, 20); // Missing 30
        Claim claim = new Claim(ticket, announced, GameType.BOTTOM_LINE);

        BottomLineRule rule = new BottomLineRule();
        assertFalse(rule.validate(claim));
    }

}
