package com.Lab2;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class TicketServiceImpl implements TicketService {

    private final TicketRepository ticketRepository;

    @Autowired
    public TicketServiceImpl(TicketRepository ticketRepository) {
        this.ticketRepository = ticketRepository;
    }

    @Override
    public List<Ticket> getAllTickets() {
        return ticketRepository.findAll();
    }

    @Override
    public Optional<Ticket> getTicketById(Long id) {
        return ticketRepository.findById(id);
    }

    @Override
    public Ticket saveTicket(Ticket ticket) {
        return ticketRepository.save(ticket);
    }

    @Override
    public void deleteTicket(Long id) { ticketRepository.deleteById(id); }

    @Override
    public boolean ticketExists(String seatNumber, double price, Long sessionId) {
        return ticketRepository.existsBySeatNumberAndPriceAndSessionIdAndIdNot(
                seatNumber, price, sessionId, -1L);
    }

    @Override
    public boolean ticketExistsExceptCurrent(String seatNumber, double price, Long sessionId, Long currentId) {
        return ticketRepository.existsBySeatNumberAndPriceAndSessionIdAndIdNot(
                seatNumber, price, sessionId, currentId);
    }
}
