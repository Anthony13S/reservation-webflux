package com.example.reservations.model;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Objects;

/**
 * Modelo inmutable que representa un evento de reserva de vuelo.
 *
 * Requisitos técnicos cumplidos:
 *  - Todos los campos son 'final'.
 *  - No existen setters.
 *  - La lista de emails se copia defensivamente al entrar (constructor)
 *    y al salir (getter), evitando que una referencia externa mute
 *    el estado interno del objeto.
 */
public final class ReservationEvent {

    private final String id;
    private final String passengerName;
    private final Double price;
    private final List<String> emails;

    public ReservationEvent(String id, String passengerName, Double price, List<String> emails) {
        this.id = id;
        this.passengerName = passengerName;
        this.price = price;
        // Copia defensiva de entrada: si 'emails' es null, se guarda una lista vacía inmutable
        this.emails = (emails == null)
                ? Collections.emptyList()
                : Collections.unmodifiableList(new ArrayList<>(emails));
    }

    public String getId() {
        return id;
    }

    public String getPassengerName() {
        return passengerName;
    }

    public Double getPrice() {
        return price;
    }

    public List<String> getEmails() {
        // Copia defensiva de salida: se retorna una nueva lista para que
        // nadie pueda modificar la interna a través de la referencia devuelta
        return new ArrayList<>(emails);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof ReservationEvent)) return false;
        ReservationEvent that = (ReservationEvent) o;
        return Objects.equals(id, that.id)
                && Objects.equals(passengerName, that.passengerName)
                && Objects.equals(price, that.price)
                && Objects.equals(emails, that.emails);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, passengerName, price, emails);
    }

    @Override
    public String toString() {
        return "ReservationEvent{" +
                "id='" + id + '\'' +
                ", passengerName='" + passengerName + '\'' +
                ", price=" + price +
                ", emails=" + emails +
                '}';
    }
}
