package com.example.book_my_show.Services;


import com.example.book_my_show.Convertor.UserConvertor;
import com.example.book_my_show.EntryDTOs.UserDto;
import com.example.book_my_show.Models.User;
import com.example.book_my_show.UserRepository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserService {

    @Autowired
    UserRepository userRepository;

    public String addUser(UserDto userDto) throws Exception, NullPointerException{

       User user= UserConvertor.covertDtoToEntity(userDto);
       userRepository.save(user);

       return "User added successfully...";
    }

}
