package com.example.book_my_show.Models;

import com.example.book_my_show.Enum.MovieGenre;
import com.example.book_my_show.Enum.MovieLanguage;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.List;


@Entity
@Table(name = "Movie")
@Data
@NoArgsConstructor
@Builder
@AllArgsConstructor
public class Movie {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @Column(unique = true, nullable = false)
    private String movieName;

    private double rating;

    private int duration;

    @Enumerated(value = EnumType.STRING)
    private MovieLanguage movieLanguage;

    @Enumerated(value = EnumType.STRING)
    private MovieGenre movieGenre;

    @OneToMany(mappedBy = "movie", cascade = CascadeType.ALL)
    private List<Show>showsList=new ArrayList<>();


}
