package com.kishan.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;

import com.kishan.entity.Ticket;
import com.kishan.service.ITicketService;

@RestController
@RequestMapping("/tickets")
public class TicketController {

    @Autowired
    private ITicketService ticketService;

    @PostMapping
    public Ticket createTicket(
            @RequestBody Ticket ticket,
            Authentication authentication) {

        ticket.setEmployeeUsername(authentication.getName());

        return ticketService.createTicket(ticket);
    }
    
    @GetMapping("/my")
    public List<Ticket> getMyTickets(Authentication authentication) {
        return ticketService.getMyTickets(authentication.getName());
    }
}