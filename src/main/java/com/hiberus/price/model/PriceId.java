package com.hiberus.price.model;

import java.io.Serializable;
import java.util.Objects;

public class PriceId implements Serializable {
    Destination destination;
    Transport transport;

    public PriceId() {

    }

    public PriceId(Destination destination, Transport transport) {
        this.destination = destination;
        this.transport = transport;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        PriceId priceId = (PriceId) o;
        return destination == priceId.destination && transport == priceId.transport;
    }

    @Override
    public int hashCode() {
        return Objects.hash(destination, transport);
    }
}
