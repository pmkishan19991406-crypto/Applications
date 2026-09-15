package com.kishan.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import com.kishan.entity.Ticket;
import com.kishan.enums.TicketStatus;
import com.kishan.service.ITicketService;

@RestController
@RequestMapping("/admin/tickets")
public class AdminTicketController {

    @Autowired
    private ITicketService ticketService;

    @GetMapping
    public List<Ticket> getAllTickets() {
        return ticketService.getAllTickets();
    }

    @PutMapping("/{id}/status")
    public Ticket updateStatus(
            @PathVariable Integer id,
            @RequestParam TicketStatus status) {

        return ticketService.updateStatus(id, status);
    }
}