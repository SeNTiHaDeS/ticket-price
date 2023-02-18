package com.hiberus.price.controller;


import com.hiberus.price.model.dto.GetPriceResponseDto;
import com.hiberus.price.model.dto.PostPriceRequestDto;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;

public interface PriceController {

    @ApiOperation(value = "Returns the price for a given destination using a given transport")
    @ApiResponses({
            @ApiResponse(code = 200, message = "Price found", response = GetPriceResponseDto.class)
    })
    public ResponseEntity<?> getPrice(@RequestParam String destination, @RequestParam String transport);

    @ApiOperation(value = "Posts a price in the system")
    @ApiResponses({
            @ApiResponse(code = 201, message = "Price posted")
    })
    public ResponseEntity<?> postPrice(@RequestBody PostPriceRequestDto postPriceRequestDto);
}
