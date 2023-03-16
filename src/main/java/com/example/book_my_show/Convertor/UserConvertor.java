package com.example.book_my_show.Convertor;

import com.example.book_my_show.EntryDTOs.UserDto;
import com.example.book_my_show.Models.User;

public class UserConvertor {

    //static is kept to avoid calling via objects/instance
    public static User covertDtoToEntity(UserDto userDto){

        User user=User.builder().age(userDto.getAge()).address(userDto.getAddress())
                .email(userDto.getEmail()).mobNo(userDto.getMobNo())
                .name(userDto.getName()).build();
        return user;
    }
}
