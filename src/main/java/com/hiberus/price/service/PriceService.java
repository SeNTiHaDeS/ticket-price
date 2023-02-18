package com.hiberus.price.service;


import com.hiberus.price.model.Destination;
import com.hiberus.price.model.Price;
import com.hiberus.price.model.Transport;

public interface PriceService {
    Price getPrice(
            Destination destination, Transport transport);
    void setPrice(Price price);
}
