package com.example.user.service.services.impl;

import com.example.user.service.entities.Hotel;
import com.example.user.service.entities.Rating;
import com.example.user.service.entities.User;
import com.example.user.service.exceptions.ResourceNotFound;
import com.example.user.service.externals.services.HotelService;
import com.example.user.service.externals.services.RatingService;
import com.example.user.service.repositories.UserRepository;
import com.example.user.service.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;
import java.util.stream.Stream;

@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private RestTemplate restTemplate;

    @Autowired
    private HotelService hotelService;

    @Autowired
    private RatingService ratingService;

    @Override
    public User saveUser(User user) {
        String uid = UUID.randomUUID().toString();
        user.setUid(uid);
        return userRepository.save(user);
    }

    @Override
    public List<User> getAllUser() {
        return userRepository.findAll();
    }

    @Override
    public User getUser(String uid) {
        var user = userRepository.findById(uid).orElseThrow(()-> new ResourceNotFound("User id not found"));
//        Rating[] ratinglist = restTemplate.getForObject("http://RATINGSERVICE/ratings/users/"+user.getUid(), Rating[].class);

       var ratinglist  = ratingService.getHotel(user.getUid());
        var userRatingList = ratinglist.stream().peek(r->{

//            ResponseEntity<Hotel> hotelResponseEntity = restTemplate.getForEntity("http://HOTELSERVICE/hotels/"+r.getHotelId(), Hotel.class);
//            r.setHotel(hotelResponseEntity.getBody());

            r.setHotel(hotelService.getHotel(r.getHotelId()));
        }).collect(Collectors.toList());

        user.setRatingList(userRatingList);

        return user;
    }
}
