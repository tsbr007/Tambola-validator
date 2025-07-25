package com.tambola.service.rules;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import org.junit.jupiter.api.Test;

import com.tambola.model.Claim;
import com.tambola.model.GameType;
import com.tambola.model.Ticket;

class FullHouseRuleTest {

    @Test
    void testFullHouseValidClaim() {
        int[][] grid = {
            {1, 2, -1, -1, 3, -1, -1, 4, 5},
            {6, 7, 8, -1, -1, -1, 9, -1, 10},
            {11, 12, -1, 13, 14, 15, -1, -1, -1}
        };
        Ticket ticket = new Ticket(grid);
        List<Integer> announced = List.of(1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 11, 12, 13, 14, 15);
        Claim claim = new Claim(ticket, announced, GameType.FULL_HOUSE);

        FullHouseRule rule = new FullHouseRule();
        assertTrue(rule.validate(claim));
    }

    @Test
    void testFullHouseInvalidClaim() {
        int[][] grid = {
            {1, 2, 3, -1, -1, -1, -1, -1, -1},
            {4, 5, 6, -1, -1, -1, -1, -1, -1},
            {7, 8, 9, -1, -1, -1, -1, -1, -1}
        };
        Ticket ticket = new Ticket(grid);
        List<Integer> announced = List.of(1, 2, 3, 4, 5, 6); // Missing 7,8,9
        Claim claim = new Claim(ticket, announced, GameType.FULL_HOUSE);

        FullHouseRule rule = new FullHouseRule();
        assertFalse(rule.validate(claim));
    }
}
