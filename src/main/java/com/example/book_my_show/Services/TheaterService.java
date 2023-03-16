package com.example.book_my_show.Services;

import com.example.book_my_show.Convertor.TheaterConvertor;
import com.example.book_my_show.EntryDTOs.TheaterDto;
import com.example.book_my_show.Enum.SeatType;
import com.example.book_my_show.Models.Theater;
import com.example.book_my_show.Models.TheaterSeat;
import com.example.book_my_show.Models.Ticket;
import com.example.book_my_show.UserRepository.TheaterRepository;
import com.example.book_my_show.UserRepository.TheaterSeatRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class TheaterService {

    @Autowired
    TheaterRepository theaterRepository;

    @Autowired
    TheaterSeatRepository theaterSeatRepository;


    public String addTheater(TheaterDto theaterDto) throws Exception{


        if(theaterDto.getName()==null || theaterDto.getLocation()==null){
            throw new Exception("Name or location is not valid");
        }
        Theater theater= TheaterConvertor.convertDtoToEntity(theaterDto);

        List<TheaterSeat> theaterSeatList=createTheaterSeats(theaterDto, theater);

        theater.setTheaterSeatList(theaterSeatList);

        theaterRepository.save(theater);
        return "Theater added successfully...";
    }

    private List<TheaterSeat> createTheaterSeats(TheaterDto theaterDto, Theater theater){
        int noOfClassicSeats=theaterDto.getClassicSeatsCount();

        int noOfPremiumSeats= theaterDto.getPremiumSeatsCount();

        List<TheaterSeat>theaterSeatList=new ArrayList<>();

        for(int count=1; count<=noOfClassicSeats; count++){

            //we need to make a new theater seat entity

            TheaterSeat theaterSeat=TheaterSeat.builder().seatType(SeatType.CLASSIC).seatNo(count+ "C").theater(theater)
                    .build();

            theaterSeatList.add(theaterSeat);
        }

        for(int count=1; count<=noOfPremiumSeats; count++){
            TheaterSeat theaterSeat= TheaterSeat.builder().seatType(SeatType.EXECUTIVE).seatNo(count+"E").theater(theater)
                    .build();

            theaterSeatList.add(theaterSeat);
        }

        return theaterSeatList;
    }
}
