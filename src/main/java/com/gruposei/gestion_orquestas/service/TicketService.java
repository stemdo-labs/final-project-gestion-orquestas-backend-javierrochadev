package com.gruposei.gestion_orquestas.service;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Optional;

import javax.mail.MessagingException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import com.google.zxing.WriterException;
import com.gruposei.gestion_orquestas.model.Show;
import com.gruposei.gestion_orquestas.model.Ticket;
import com.gruposei.gestion_orquestas.model.User;
import com.gruposei.gestion_orquestas.repositories.TicketRepository;
import com.itextpdf.text.DocumentException;

@Service
public class TicketService {

    @Autowired
    private TicketRepository ticketRepository;

    @Autowired
    private UserService userService;

    @Autowired
    private ShowService showService;

    @Autowired
    private BCryptPasswordEncoder bCryptPasswordEncoder;

    public Ticket create(User user, Show show){

        Ticket ticket = new Ticket();
        ticket.setUser(user);
        ticket.setShow(show);
        ticket = ticketRepository.save(ticket);
        String encodedCode = bCryptPasswordEncoder.encode(buildCode(ticket.getId(),user,show));
        ticket.setCode(encodedCode);
        ticket.setUsed(false);
        ticket.setPurchaseDate(new Date(System.currentTimeMillis()));

        return ticketRepository.save(ticket);
    }

    public List<Ticket> create(User user, Show show,int quantity) throws DocumentException, IOException, WriterException, MessagingException {

        List<Ticket> tickets = new ArrayList<Ticket>();
        for(int i=0;i<quantity;i++){

            Ticket ticket = new Ticket();
            ticket.setUser(user);
            ticket.setShow(show);
            ticket.setUsed(false);
            ticket.setPurchaseDate(new Date(System.currentTimeMillis()));
            ticket =ticketRepository.save(ticket);
//            String encodedCode="12345";
            String encodedCode = bCryptPasswordEncoder.encode(buildCode(ticket.getId(),user,show));
            ticket.setCode(encodedCode);
            ticket =ticketRepository.save(ticket);
            tickets.add(ticket);

        }

        return tickets;
    }

    public String buildCode(Long id, User user,Show show){
        String username = user.getUsername();
        String show_id = show.getId().toString();

        return id.toString() +  username + show_id + "ENTRADA";
    }

    public Ticket update(Ticket ticket){

        return ticketRepository.save(ticket);
    }

    public List<Ticket> getAll(){

        return ticketRepository.findAll();
    }

    public void delete(Ticket p){

        ticketRepository.delete(p);
    }

    public void deleteById(Long id){

        ticketRepository.deleteById(id);
    }

    public void deleteByCode(String code){

        ticketRepository.deleteByCode(code);
    }

    public void deleteByUserAndShow(User user, Show show){

        ticketRepository.deleteByUserAndShow(user,show);
    }

    public Optional<Ticket> findById(Long id){

        return ticketRepository.findById(id);
    }

    public List<Optional<Ticket>> findByUser(User user){

        return ticketRepository.findByUser(user);
    }

    public List<Optional<Ticket>> findByShow(Show show){

        return ticketRepository.findByShow(show);
    }

    public Optional<Ticket> findByCode(String code){

        return ticketRepository.findByCode(code);
    }

    public boolean ticketExist(Ticket ticket){

        String rawCode = buildCode(ticket.getId(),ticket.getUser(),ticket.getShow());
        boolean match = bCryptPasswordEncoder.matches(rawCode,ticket.getCode());

        return match;
    }
}
