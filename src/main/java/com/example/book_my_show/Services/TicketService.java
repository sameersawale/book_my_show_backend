package com.example.book_my_show.Services;

import com.example.book_my_show.Convertor.TicketConvertor;
import com.example.book_my_show.EntryDTOs.TicketDto;
import com.example.book_my_show.Models.Show;
import com.example.book_my_show.Models.ShowSeats;
import com.example.book_my_show.Models.Ticket;
import com.example.book_my_show.Models.User;
import com.example.book_my_show.UserRepository.ShowRepository;
import com.example.book_my_show.UserRepository.TheaterRepository;
import com.example.book_my_show.UserRepository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.UUID;

@Service
public class TicketService {

    @Autowired
    TheaterRepository theaterRepository;

    @Autowired
    ShowRepository showRepository;

    @Autowired
    UserRepository userRepository;

    public String addTicket(TicketDto ticketDto) throws Exception{

        //create ticketEntity from entryDto

        Ticket ticket= TicketConvertor.convertDtoToEntity(ticketDto);

        //validation check if the requested seats are available or not

        boolean  isValidRequest=checkValidityOfRequestedSeats(ticketDto);

        if(isValidRequest==false){
            throw new Exception("Requested seats are not available");
        }
        //calculate the total amount
        Show show=showRepository.findById(ticketDto.getShowId()).get();

        List<ShowSeats> showSeatsList=show.getShowSeatsList();

        List<String> requestedSeats=ticketDto.getRequestedSeats();

        int totalAmount=0;

        for(ShowSeats showSeats:showSeatsList){
            if(requestedSeats.contains(showSeats.getSeatNo())){
                totalAmount=totalAmount+showSeats.getPrice();
                showSeats.setBooked(true);
                showSeats.setBookedAt(new Date());
            }
        }

        ticket.setTotalAmount(totalAmount);


        //setting the other attributes for ticketEntity
        ticket.setMovieName(show.getMovie().getMovieName());
        ticket.setShowDate(show.getShowDate());
        ticket.setShowTime(show.getShowTime());
        ticket.setTheaterName(show.getTheater().getName());

        //setting the foreign key attributes
        User user=userRepository.findById(ticketDto.getUserId()).get();

        ticket.setUser(user);
        ticket.setShow(show);

        //we need to set that string that talk about requested seat
        String allotedSeats=getAllotedSeatsFromShowSeats(requestedSeats);
        ticket.setBookedSeats(allotedSeats);

        //save the parents
        List<Ticket>ticketList=show.getTicketList();
        ticketList.add(ticket);
        show.setTicketList(ticketList);

        showRepository.save(show);

        List<Ticket>ticketList1=user.getBookTicketList();
        ticketList1.add(ticket);
        userRepository.save(user);



        return "Ticket has successfully booked..";


    }

    private String getAllotedSeatsFromShowSeats(List<String> requestedSeats){
        String result="";
        for(String seat:requestedSeats){
            result=result+seat+", ";
        }
        return result;
    }


    private boolean checkValidityOfRequestedSeats(TicketDto ticketDto){
        int showId= ticketDto.getShowId();

        List<String> requestedSeats=ticketDto.getRequestedSeats();

        Show show=showRepository.findById(showId).get();

        List<ShowSeats> listOfSeats=show.getShowSeatsList();

        //iterating over list of seats for that particular show
        for (ShowSeats showSeats:listOfSeats){
            String seatNo=showSeats.getSeatNo();

            if(requestedSeats.contains(seatNo)){
                if(showSeats.isBooked()==true)
                    return false;

            }
        }
        return true;

    }
}
