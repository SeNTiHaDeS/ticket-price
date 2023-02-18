package com.hiberus.price.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Entity
@IdClass(PriceId.class)
@Table(name = "prices")
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Price {
    @Id
    @Enumerated(EnumType.STRING)
    Destination destination;

    @Id
    @Enumerated(EnumType.STRING)
    Transport transport;

    @Getter
    Double price;
}
