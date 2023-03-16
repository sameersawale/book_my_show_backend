package com.example.book_my_show.Controller;

import com.example.book_my_show.EntryDTOs.TicketDto;
import com.example.book_my_show.Services.TicketService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/ticket")
public class TicketController {

    @Autowired
    TicketService ticketService;

    @PostMapping("/book")
    public String bookTicket(@RequestBody TicketDto ticketDto){

        try{
            String result=ticketService.addTicket(ticketDto);
            return result;
        }
        catch (Exception e){
            return "";
        }
    }

}
