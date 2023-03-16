package com.example.book_my_show.EntryDTOs;

import com.example.book_my_show.Enum.MovieGenre;
import com.example.book_my_show.Enum.MovieLanguage;
import lombok.Data;

@Data
public class MovieDto {

    private String movieName;

    private double ratings;

    private int duration;

    private MovieLanguage language;

    private MovieGenre genre;
}
