package com.Lab2;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@Controller
@RequestMapping("/tickets")
public class TicketController {

    private final TicketService ticketService;
    private final SessionRepository sessionRepository;
    private final CinemaRepository cinemaRepository;

    @Autowired
    public TicketController(TicketService ticketService,
                            SessionRepository sessionRepository,
                            CinemaRepository cinemaRepository) {
        this.ticketService = ticketService;
        this.sessionRepository = sessionRepository;
        this.cinemaRepository = cinemaRepository;
    }

    // Показати всі квитки
    @GetMapping
    public String listTickets(Model model) {
        List<Ticket> tickets = ticketService.getAllTickets();
        model.addAttribute("tickets", tickets);
        model.addAttribute("cinemas", cinemaRepository.findAll());
        model.addAttribute("cinemas", cinemaRepository.findAll());
        return "ticket-list";
    }


    // Форма створення нового квитка
    @GetMapping("/new")
    public String showCreateForm(Model model) {
        model.addAttribute("ticket", new Ticket());
        model.addAttribute("sessions", sessionRepository.findAll());
        model.addAttribute("cinemas", cinemaRepository.findAll());
        return "ticket-form";
    }

    // Обробка створення квитка
    @PostMapping
    public String saveOrUpdateTicket(@ModelAttribute("ticket") Ticket ticket,
                                     BindingResult result,
                                     Model model) {

        Long sessionId = ticket.getSession().getId();
        double price = ticket.getPrice();
        String seatNumber = ticket.getSeatNumber();

        boolean isDuplicate = (ticket.getId() == null)
                ? ticketService.ticketExists(seatNumber, price, sessionId)
                : ticketService.ticketExistsExceptCurrent(seatNumber, price, sessionId, ticket.getId());

        if (isDuplicate) {
            result.rejectValue("seatNumber", "duplicate", "A ticket with this seat, price and session already exists");
        }

        if (price < 0) {
            result.rejectValue("price", "negative", "Price must be non-negative");
        }

        try {
            int seatNum = Integer.parseInt(seatNumber);
            if (seatNum < 1 || seatNum > 50) {
                result.rejectValue("seatNumber", "range", "Seat number must be between 1 and 50");
            }
        } catch (NumberFormatException e) {
            result.rejectValue("seatNumber", "format", "Seat must be a number");
        }

        if (result.hasErrors()) {
            model.addAttribute("sessions", sessionRepository.findAll());
            model.addAttribute("cinemas", cinemaRepository.findAll());
            return "ticket-form";
        }

        if (ticket.getId() != null) {
            Ticket existing = ticketService.getTicketById(ticket.getId()).orElse(null);
            if (existing != null) {
                existing.setSeatNumber(ticket.getSeatNumber());
                existing.setPrice(ticket.getPrice());
                existing.setSession(ticket.getSession());
                ticketService.saveTicket(existing);
                return "redirect:/tickets";
            }
        }

        ticketService.saveTicket(ticket);
        return "redirect:/tickets";
    }

    // Перегляд і редагування квитка
    @GetMapping("/{id}")
    public String showEditForm(@PathVariable Long id, Model model) {
        Optional<Ticket> optionalTicket = ticketService.getTicketById(id);
        if (optionalTicket.isPresent()) {
            model.addAttribute("ticket", optionalTicket.get());
            model.addAttribute("sessions", sessionRepository.findAll());
            model.addAttribute("cinemas", cinemaRepository.findAll());

            return "ticket-form";
        } else {
            return "redirect:/tickets";
        }
    }

    // Видалення квитка
    @GetMapping("/{id}/delete")
    public String deleteTicket(@PathVariable Long id) {
        ticketService.deleteTicket(id);
        return "redirect:/tickets";
    }
}
