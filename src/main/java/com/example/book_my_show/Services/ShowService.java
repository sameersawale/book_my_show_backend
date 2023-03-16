package com.example.book_my_show.Services;

import com.example.book_my_show.Controller.ShowController;
import com.example.book_my_show.Convertor.ShowConvertor;
import com.example.book_my_show.EntryDTOs.ShowDto;
import com.example.book_my_show.Enum.SeatType;
import com.example.book_my_show.Models.*;
import com.example.book_my_show.UserRepository.MovieRepository;
import com.example.book_my_show.UserRepository.TheaterRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class ShowService {

    @Autowired
    MovieRepository movieRepository;

    @Autowired
    TheaterRepository theaterRepository;

    public String addShow(ShowDto showDto){

        //create show entity
        Show show= ShowConvertor.convertDtoToEntity(showDto);

        int movieId=showDto.getMovieId();

        int theaterId=showDto.getTheaterId();

        Movie movie=movieRepository.findById(movieId).get();

        Theater theater=theaterRepository.findById(theaterId).get();

        //setting the attributes of foreignKey
        show.setMovie(movie);
        show.setTheater(theater);

        //pending attributes listOfShowSeatsEntity
        List<ShowSeats>showSeatsList=createShowSeat(showDto, show);

        show.setShowSeatsList(showSeatsList);

        //My goal is to create the showSeatEntity

        List<Show> showList=movie.getShowsList();
        showList.add(show);

        movieRepository.save(movie);

        List<Show> showList1=theater.getShowList();
        showList1.add(show);
        theater.setShowList(showList1);

        theaterRepository.save(theater);

        return "the show has been added successfully...";

    }

    private List<ShowSeats> createShowSeat(ShowDto showDto, Show show){


        //now the goal is to create the showSeatEntity
        //we need to set its attributes

        Theater theater=show.getTheater();

        List<TheaterSeat> theaterSeatList=theater.getTheaterSeatList();

        List<ShowSeats> showSeatsList=new ArrayList<>();

        for(TheaterSeat theaterSeat:theaterSeatList){
            ShowSeats showSeats=new ShowSeats();

            showSeats.setSeatNo(theaterSeat.getSeatNo());
            showSeats.setSeatType(theaterSeat.getSeatType());

            if(theaterSeat.getSeatType().equals(SeatType.CLASSIC))
                showSeats.setPrice(showDto.getClassicSeatPrice());
            else
                showSeats.setPrice(showDto.getExecutiveSeatPrice());

            showSeats.setBooked(false);
            showSeats.setShow(show); //parent : foreign key for show seat entity

            showSeatsList.add(showSeats);

        }

        return showSeatsList;
    }

}
