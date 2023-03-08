package com.hiberus.price.controller;

import com.hiberus.price.model.Destination;
import com.hiberus.price.model.Price;
import com.hiberus.price.model.Transport;
import com.hiberus.price.model.dto.GetPriceResponseDto;
import com.hiberus.price.model.dto.PostPriceRequestDto;
import com.hiberus.price.service.PriceService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import javax.persistence.EntityNotFoundException;

@RestController
@Slf4j
public class PriceControllerImpl {

    PriceService priceService;

    @Autowired
    public PriceControllerImpl(PriceService priceService) {
        this.priceService = priceService;
    }

    @GetMapping(path = "/price")
    public ResponseEntity<?> getPrice(@RequestParam String destination, @RequestParam String transport) {
        try {
            Price price = priceService.getPrice(Destination.valueOf(destination), Transport.valueOf(transport));

            GetPriceResponseDto getPriceResponseDto = GetPriceResponseDto.builder()
                    .destination(destination)
                    .transport(transport)
                    .price(price.getPrice())
                    .build();

            return new ResponseEntity<GetPriceResponseDto>(getPriceResponseDto, HttpStatus.OK);
        }
        catch (IllegalArgumentException | EntityNotFoundException e) {
            return new ResponseEntity<>("", HttpStatus.BAD_REQUEST);
        }
    }


    @PostMapping(path = "/price")
    public ResponseEntity<?> postPrice(@RequestBody PostPriceRequestDto postPriceRequestDto) {
        Destination destination = postPriceRequestDto.getDestination();
        Transport transport = postPriceRequestDto.getTransport();
        Double price = postPriceRequestDto.getPrice();

        try {
            Price p = Price.builder()
                    .destination(destination)
                    .transport(transport)
                    .price(price)
                    .build();

            priceService.setPrice(p);

            return new ResponseEntity<>("", HttpStatus.OK);
        }
        catch (IllegalArgumentException e) {
            return new ResponseEntity<>("", HttpStatus.BAD_REQUEST);
        }
   }
}
