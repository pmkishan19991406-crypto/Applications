package com.kishan.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.kishan.ExceptionHandler.TicketNotFoundException;
import com.kishan.entity.Ticket;
import com.kishan.enums.TicketStatus;
import com.kishan.repository.ITicketRepository;

@Service
public class TicketServiceImpl implements ITicketService {

    @Autowired
    private ITicketRepository trepo;

    @Override
    public Ticket createTicket(Ticket ticket) {

        ticket.setStatus(TicketStatus.OPEN);

        return trepo.save(ticket);
    }

	@Override
	public List<Ticket> getMyTickets(String username) {
		return trepo.findByEmployeeUsername(username);
	}

	@Override
	public List<Ticket> getAllTickets() {
		return trepo.findAll();
	}

	@Override
	public Ticket updateStatus(Integer id, TicketStatus status) {

	    Ticket ticket = trepo.findById(id)
	            .orElseThrow(() ->
	                    new TicketNotFoundException("Ticket not found with id: " + id));

	    ticket.setStatus(status);

	    return trepo.save(ticket);
	}
}