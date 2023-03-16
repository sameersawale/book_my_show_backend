package com.example.book_my_show.Services;


import com.example.book_my_show.Convertor.MovieConvertor;
import com.example.book_my_show.EntryDTOs.MovieDto;
import com.example.book_my_show.Models.Movie;
import com.example.book_my_show.UserRepository.MovieRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class MovieService {


    @Autowired
    MovieRepository movieRepository;

    public String addMovie(MovieDto movieDto) throws Exception{
        Movie movie= MovieConvertor.convertDtoToEntity(movieDto);
        movieRepository.save(movie);

        return "Movie added successfully..";
    }
}
