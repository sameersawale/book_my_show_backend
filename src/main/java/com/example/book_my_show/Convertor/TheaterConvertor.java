package com.example.book_my_show.Convertor;

import com.example.book_my_show.EntryDTOs.TheaterDto;
import com.example.book_my_show.Models.Theater;

public class TheaterConvertor {

    public static Theater convertDtoToEntity(TheaterDto theaterDto){

        Theater theater= Theater.builder().location(theaterDto.getLocation()).name(theaterDto.getName())

                .build();


        return theater;
    }
}
