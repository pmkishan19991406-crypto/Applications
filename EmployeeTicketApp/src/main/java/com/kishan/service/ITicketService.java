package com.kishan.service;

import java.util.List;

import com.kishan.enums.TicketStatus;
import com.kishan.entity.Ticket;

public interface ITicketService {

	 Ticket createTicket(Ticket ticket);
	 List<Ticket> getMyTickets(String username);
	 List<Ticket> getAllTickets();
	 Ticket updateStatus(Integer id, TicketStatus status);
}