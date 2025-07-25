package com.tambola.model;

import java.util.List;

public class Claim {

    private final Ticket ticket;
    private final List<Integer> numbersAnnounced;
    private final GameType gameType;

    public Claim(Ticket ticket, List<Integer> numbersAnnounced, GameType gameType) {
        this.ticket = ticket;
        this.numbersAnnounced = numbersAnnounced;
        this.gameType = gameType;
    }

    public Ticket getTicket() {
        return ticket;
    }

    public List<Integer> getNumbersAnnounced() {
        return numbersAnnounced;
    }

    public GameType getGameType() {
        return gameType;
    }
}
