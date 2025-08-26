package com.tambola;

import java.util.Arrays;
import java.util.List;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import com.tambola.model.Claim;
import com.tambola.model.GameType;
import com.tambola.model.Ticket;
import com.tambola.service.ClaimValidator;

@SpringBootApplication
public class TambolaClaimValidatorApplication implements CommandLineRunner {

    public static void main(String[] args) {
        SpringApplication.run(TambolaClaimValidatorApplication.class, args);
    }

    @Override
    public void run(String... args) {
        int[][] grid = {
            {4, 16, -1, -1, 48, -1, 63, 76, -1},
            {7, -1, 23, 38, -1, 52, -1, -1, 80},
            {9, -1, 25, -1, -1, 56, 64, -1, 83}
        };

        // Make sure these 5 numbers exist in the ticket grid
        List<Integer> announced = Arrays.asList(90, 4, 76, 7, 9, 83);
        // Matching ticket numbers: 4, 76, 7, 9, 16 — fifth match is 16

        Claim claim = new Claim(new Ticket(grid), announced, GameType.CORNER_CLAIM);
        ClaimValidator validator = new ClaimValidator();

        boolean result = validator.validateClaim(claim);
        System.out.println("Result: " + (result ? "  Accepted" : " Rejected"));
    }

}
