package com.example.book_my_show.Convertor;

import com.example.book_my_show.EntryDTOs.MovieDto;
import com.example.book_my_show.Models.Movie;

public class MovieConvertor {

    public static Movie convertDtoToEntity(MovieDto movieDto){

        Movie movie=Movie.builder().movieName(movieDto.getMovieName()).movieLanguage(movieDto.getLanguage())
                .movieGenre(movieDto.getGenre()).rating(movieDto.getRatings()).duration(movieDto.getDuration())
                .build();
        return movie;
    }
}
