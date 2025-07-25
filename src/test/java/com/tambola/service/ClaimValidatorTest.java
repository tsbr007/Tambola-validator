package com.tambola.service;

import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import com.tambola.model.Claim;
import com.tambola.model.GameType;
import com.tambola.model.Ticket;

class ClaimValidatorTest {

    private ClaimValidator validator;
    private int[][] grid;

    @BeforeEach
    void setUp() {
        validator = new ClaimValidator();
        grid = new int[][]{
            {4, 16, -1, -1, 48, -1, 63, 76, -1},
            {7, -1, 23, 38, -1, 52, -1, -1, 80},
            {9, -1, 25, -1, -1, 56, 64, -1, 83}
        };
    }

    @Test
    void testTopLineAccepted() {
        List<Integer> announced = Arrays.asList(90, 4, 46, 63, 89, 16, 76, 48);
        Claim claim = new Claim(new Ticket(grid), announced, GameType.TOP_LINE);
        assertTrue(validator.validateClaim(claim));
    }

    @Test
    void testTopLineRejectedLateClaim() {
        List<Integer> announced = Arrays.asList(90, 4, 46, 63, 89, 16, 76, 48, 12);
        Claim claim = new Claim(new Ticket(grid), announced, GameType.TOP_LINE);
        assertFalse(validator.validateClaim(claim));
    }

    @Test
    void testMiddleLineAccepted() {
        List<Integer> announced = Arrays.asList(7, 23, 38, 52, 80);
        Claim claim = new Claim(new Ticket(grid), announced, GameType.MIDDLE_LINE);
        assertTrue(validator.validateClaim(claim));
    }

    @Test
    void testBottomLineRejectedMissingOneNumber() {
        List<Integer> announced = Arrays.asList(9, 25, 56, 64); // 83 is missing
        Claim claim = new Claim(new Ticket(grid), announced, GameType.BOTTOM_LINE);
        assertFalse(validator.validateClaim(claim));
    }

    @Test
    void testFullHouseAccepted() {
        List<Integer> announced = Arrays.asList(
                4, 16, 48, 63, 76, 7, 23, 38, 52, 80,
                9, 25, 56, 64, 83
        );
        Claim claim = new Claim(new Ticket(grid), announced, GameType.FULL_HOUSE);
        assertTrue(validator.validateClaim(claim));
    }

    @Test
    void testFullHouseRejectedNotAllNumbers() {
        List<Integer> announced = Arrays.asList(
                4, 16, 48, 63, 76, 7, 23, 38, 52, 80,
                9, 25, 56, 64 // 83 missing
        );
        Claim claim = new Claim(new Ticket(grid), announced, GameType.FULL_HOUSE);
        assertFalse(validator.validateClaim(claim));
    }

    @Test
    void testEarlyFiveAccepted() {
        List<Integer> announced = Arrays.asList(4, 16, 48, 63, 76);
        Claim claim = new Claim(new Ticket(grid), announced, GameType.EARLY_FIVE);
        assertTrue(validator.validateClaim(claim));
    }

    @Test
    void testEarlyFiveRejectedNotEnoughNumbers() {
        List<Integer> announced = Arrays.asList(4, 16, 48);
        Claim claim = new Claim(new Ticket(grid), announced, GameType.EARLY_FIVE);
        assertFalse(validator.validateClaim(claim));
    }

    @Test
    void testInvalidGameType() {
        List<Integer> announced = Arrays.asList(4, 16, 48, 63, 76);
        Claim claim = new Claim(new Ticket(grid), announced, null);
        assertFalse(validator.validateClaim(claim));
    }

    @Test
    void testClaimWithNullTicketShouldBeRejected() {
        List<Integer> announced = List.of(4, 16, 63, 48, 76);
        Claim claim = new Claim(null, announced, GameType.TOP_LINE);
        assertFalse(validator.validateClaim(claim));
    }

    @Test
    void testClaimWithEmptyAnnouncedNumbers() {
        Claim claim = new Claim(new Ticket(new int[3][9]), List.of(), GameType.TOP_LINE);
        assertFalse(validator.validateClaim(claim));
    }

    @Test
    void testClaimWithNullAnnouncedNumbers() {
        Claim claim = new Claim(new Ticket(new int[3][9]), null, GameType.TOP_LINE);
        assertFalse(validator.validateClaim(claim));
    }

    @Test
    void testEarlyFiveWithNonTicketNumbers() {
        int[][] grid = {
            {1, 2, 3, 4, 5, -1, -1, -1, -1},
            {-1, -1, -1, -1, -1, -1, -1, -1, -1},
            {-1, -1, -1, -1, -1, -1, -1, -1, -1}
        };
        Ticket ticket = new Ticket(grid);

        // Only 2 ticket numbers, rest are noise
        List<Integer> announced = List.of(1, 99, 100, 101, 102, 103);

        Claim claim = new Claim(ticket, announced, GameType.EARLY_FIVE);
        assertFalse(validator.validateClaim(claim));
    }
}
