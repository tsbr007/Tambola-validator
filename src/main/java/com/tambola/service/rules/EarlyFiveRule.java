package com.tambola.service.rules;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

import com.tambola.model.Claim;
import com.tambola.model.Ticket;

public class EarlyFiveRule implements GameClaimRule {

    @Override
    public boolean validate(Claim claim) {
        Ticket ticket = claim.getTicket();
        List<Integer> announced = claim.getNumbersAnnounced();
        List<Integer> ticketNumbers = ticket.getAllNumbers();

        Set<Integer> matched = new HashSet<>();

        for (int number : announced) {
            if (ticketNumbers.contains(number)) {
                matched.add(number);
                if (matched.size() == 5) {
                    return number == announced.get(announced.size() - 1);
                }
            }
        }

        return false;
    }
}
