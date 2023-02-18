package com.hiberus.price.model.dto;
import com.hiberus.price.model.Destination;
import com.hiberus.price.model.Transport;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Builder
public class PostPriceRequestDto {
    private Transport transport;
    private Destination destination;
    private Double price;
}

