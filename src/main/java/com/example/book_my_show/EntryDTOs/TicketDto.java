package com.example.book_my_show.EntryDTOs;

import jdk.dynalink.linker.LinkerServices;
import lombok.Data;

import java.util.ArrayList;
import java.util.List;

@Data
public class TicketDto {

    private int showId;

    private List<String> requestedSeats=new ArrayList<>();

    private int userId;


}
