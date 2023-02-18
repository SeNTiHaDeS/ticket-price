package com.hiberus.price.service;



import com.hiberus.price.model.Destination;
import com.hiberus.price.model.Price;
import com.hiberus.price.model.PriceId;
import com.hiberus.price.model.Transport;
import com.hiberus.price.repository.PriceRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
@Slf4j
public class PriceServiceImpl implements PriceService {

    PriceRepository priceRepository;

    @Autowired
    public PriceServiceImpl(PriceRepository priceRepository) {
        this.priceRepository = priceRepository;
    }

    @Override
    public Price getPrice(Destination destination, Transport transport) {
        return priceRepository.getById(new PriceId(destination, transport));
    }

    @Override
    public void setPrice(Price price) {
        priceRepository.save(price);
    }
}
