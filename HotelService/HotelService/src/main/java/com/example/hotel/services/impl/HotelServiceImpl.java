package com.example.hotel.services.impl;

import com.example.hotel.entites.Hotel;
import com.example.hotel.exception.ResourceNotFound;
import com.example.hotel.repositories.HotelRepo;
import com.example.hotel.services.HotelService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
public class HotelServiceImpl implements HotelService {

    @Autowired
    private HotelRepo hotelRepo;

    @Override
    public Hotel create(Hotel hotel) {
        hotel.setId(UUID.randomUUID().toString());
        return hotelRepo.save(hotel);
    }

    @Override
    public List<Hotel> getAll() {
        return hotelRepo.findAll();
    }

    @Override
    public Hotel get(String hId) {
        return hotelRepo.findById(hId).orElseThrow(()-> new ResourceNotFound("Hotel not found"));
    }
}
