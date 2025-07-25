package com.tambola.service.rules;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

import com.tambola.model.Claim;
import com.tambola.model.Ticket;

public class FullHouseRule implements GameClaimRule {

    @Override
    public boolean validate(Claim claim) {
        Ticket ticket = claim.getTicket();
        List<Integer> announced = claim.getNumbersAnnounced();
        List<Integer> allNumbers = ticket.getAllNumbers();
        Set<Integer> announcedSet = new HashSet<>(announced);
        if (!announcedSet.containsAll(allNumbers)) {
            return false;
        }

        int lastNumber = announced.get(announced.size() - 1);
        for (int i = announced.size() - 1; i >= 0; i--) {
            if (allNumbers.contains(announced.get(i))) {
                return announced.get(i) == lastNumber;
            }
        }
        return false;
    }
}
