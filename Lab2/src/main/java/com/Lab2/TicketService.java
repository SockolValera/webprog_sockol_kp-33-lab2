package com.Lab2;

import java.util.List;
import java.util.Optional;

public interface TicketService {
    List<Ticket> getAllTickets();
    Optional<Ticket> getTicketById(Long id);
    Ticket saveTicket(Ticket ticket);
    void deleteTicket(Long id);
    boolean ticketExists(String seatNumber, double price, Long sessionId);
    boolean ticketExistsExceptCurrent(String seatNumber, double price, Long sessionId, Long currentId);
}
