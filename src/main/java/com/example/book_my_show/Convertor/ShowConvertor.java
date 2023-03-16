package com.example.book_my_show.Convertor;

import com.example.book_my_show.EntryDTOs.ShowDto;
import com.example.book_my_show.Models.Show;

public class ShowConvertor {

    public static Show convertDtoToEntity(ShowDto showDto){

       Show show=Show.builder()
               .showDate(showDto.getLocalDate()).
               showTime(showDto.getLocalTime()).
               showType(showDto.getShowType())
               .build();

       return show;
    }
}
