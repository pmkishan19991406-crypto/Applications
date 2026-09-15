package com.kishan.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.kishan.entity.Ticket;

public interface ITicketRepository extends JpaRepository<Ticket, Integer> {

    List<Ticket> findByEmployeeUsername(String employeeUsername);

}