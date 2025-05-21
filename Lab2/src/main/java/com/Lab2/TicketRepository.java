package com.Lab2;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface TicketRepository extends JpaRepository<Ticket, Long> {
    boolean existsBySeatNumberAndPriceAndSessionIdAndIdNot(String seatNumber, double price, Long sessionId, Long id);
}


