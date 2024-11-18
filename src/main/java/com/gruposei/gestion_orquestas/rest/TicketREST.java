package com.gruposei.gestion_orquestas.rest;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.gruposei.gestion_orquestas.model.Show;
import com.gruposei.gestion_orquestas.model.Ticket;
import com.gruposei.gestion_orquestas.model.User;
import com.gruposei.gestion_orquestas.responses.ApiRequestException;
import com.gruposei.gestion_orquestas.responses.ResponseHandler;
import com.gruposei.gestion_orquestas.service.PaymentRequestService;
import com.gruposei.gestion_orquestas.service.ShowService;
import com.gruposei.gestion_orquestas.service.TicketService;
import com.gruposei.gestion_orquestas.service.UserService;

@CrossOrigin(origins = "*", methods= {RequestMethod.GET,RequestMethod.POST})
@RestController
@RequestMapping("/api/tickets")
public class TicketREST {

    @Autowired
    private TicketService ticketService;

    @Autowired
    private ShowService showService;

    @Autowired
    private UserService userService;

    @Autowired
    private BCryptPasswordEncoder bCryptPasswordEncoder;

    @Autowired
    private ResponseHandler responseHandler;

    @Autowired
    private PaymentRequestService paymentRequestService;

//    @PostMapping
//    private ResponseEntity<Object> pagado(@RequestBody MercadopagoBackurls p) throws MPConfException {
//
//        MercadopagoBackurls temporal = mercadopagoBackurlsService.create(p);
//
//        Optional<PaymentRequest> paymentRequest = paymentRequestService.findByExternalReference(p.getExternal_reference());
//
//        if(!paymentRequest.isPresent())
//            throw  new ApiRequestException("009");
//
//        try{
//
//            paymentRequest.get().setPaid(true);
//            paymentRequestService.create(paymentRequest.get());
//
//            List<Ticket> tickets = ticketService.create(paymentRequest.get().getUser(),paymentRequest.get().getShow(),paymentRequest.get().getQuantity());
//            return responseHandler.generateResponse("000",tickets);
//        }
//        catch(Exception e){
//
//            throw  new ApiRequestException("002");
//        }
//
//
//    }

//    @PostMapping(params = {"user_id","show_id"})
//    private ResponseEntity<Object> save(@RequestParam("user_id") User user,@RequestParam("show_id") Show show){
//
//        try{
//
//            Ticket temporal = ticketService.create(user,show);
//            return responseHandler.generateResponse("000",temporal);
//        }
//        catch(Exception e){
//
//            throw  new ApiRequestException("002");
//        }
//    }

    @PostMapping("/saveTicket")
    private ResponseEntity<Object> saveTicket(@RequestBody JsonNode p) throws JsonProcessingException{

        ObjectMapper mapper = new ObjectMapper();
        JsonNode rootNode = mapper.readTree(String.valueOf(p));
        Long show_id = mapper.convertValue(rootNode.get("show_id"), Long.class);
        int quantity = mapper.convertValue(rootNode.get("quantity"), Integer.class);
        Long user_id = mapper.convertValue(rootNode.get("user_id"), Long.class);

        Optional<Show> show = showService.findById(show_id);
        Optional<User> user = userService.findById(user_id);
        try{

            List<Ticket> tickets = ticketService.create(user.get(),show.get(),quantity);
            return responseHandler.generateResponse("000",tickets);
        }
        catch(Exception e){

            throw  new ApiRequestException("002");
        }
    }

    @PostMapping(value = "/checkin", params = "code")
    private ResponseEntity<Object> setAsUsed(@RequestParam("code") String code){

        Optional<Ticket> ticket = ticketService.findByCode(code);

        if(ticket.isPresent() && !ticket.get().isUsed()){

            ticket.get().setUsed(true);
            Ticket temporal = ticketService.update(ticket.get());
            try{

                return responseHandler.generateResponse("000",temporal);
            }
            catch(Exception e){

                throw  new ApiRequestException("002");
            }
        }
        else{

            throw  new ApiRequestException("013");
        }
    }

    @GetMapping
    private ResponseEntity<Object> getAll(){

        try{

            List<Ticket> cloths = ticketService.getAll();
            return responseHandler.generateResponse("000",cloths);
        }
        catch(Exception e){

            throw new ApiRequestException("002");
        }
    }

    @DeleteMapping(params = "id")
    public ResponseEntity<Object> deleteById(@RequestParam("id") Long id) {

        Optional<Ticket> cloth= ticketService.findById(id);

        if(!cloth.isPresent()){

            throw new ApiRequestException("005");
        }

        try {
            ticketService.deleteById(id);
            return responseHandler.generateResponse("000",cloth);
        }
        catch (Exception e){

            throw  new ApiRequestException("002");
        }
    }

    @RequestMapping(params = "id")
    public ResponseEntity<Object> getById(@RequestParam("id") Long id) {

        Optional<Ticket> cloth= ticketService.findById(id);

        if(!cloth.isPresent()){

            throw new ApiRequestException("005");
        }
        try {

            return responseHandler.generateResponse("000",cloth);
        }
        catch (Exception e){

            throw  new ApiRequestException("002");
        }
    }

    @RequestMapping(params = "user_id")
    public ResponseEntity<Object> getByUser(@RequestParam("user_id") Long user_id) {

        Optional<User> user = userService.findById(user_id);

        if(!user.isPresent()){

            throw new ApiRequestException("005");
        }

        List<Optional<Ticket>> cloths = ticketService.findByUser(user.get());

//        if(cloths.isEmpty()){
//
//            throw new ApiRequestException("000");
////            return responseHandler.generateResponse("000","No hay entradas compradas");
//        }

        try{

            return responseHandler.generateResponse("000",cloths);
        }
        catch(Exception e){

            throw new ApiRequestException("002");
        }
    }

    @RequestMapping(params = "show_id")
    public ResponseEntity<Object> getByShow(@RequestParam("show_id") Long show_id) {

        Optional<Show> show = showService.findById(show_id);

        if(!show.isPresent()){

            throw new ApiRequestException("005");
        }

        List<Optional<Ticket>> cloths = ticketService.findByShow(show.get());

        if(cloths.isEmpty()){

            throw new ApiRequestException("005");
        }

        try{

            return responseHandler.generateResponse("000",cloths);
        }
        catch(Exception e){

            throw new ApiRequestException("002");
        }
    }


}
