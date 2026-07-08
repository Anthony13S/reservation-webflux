package com.example.reservations.util;

import com.example.reservations.model.ReservationEvent;

import java.util.function.Consumer;
import java.util.function.Predicate;

/**
 * Clase utilitaria con reglas de negocio expresadas como funciones lambda.
 */
public final class ReservationFilters {

    private ReservationFilters() {
        // Utility class: no se debe instanciar
    }

    /**
     * Un evento de reserva es válido si:
     *  - el precio es mayor a 0
     *  - la lista de emails no está vacía
     */
    public static final Predicate<ReservationEvent> isValidReservation = event ->
            event.getPrice() != null
                    && event.getPrice() > 0
                    && event.getEmails() != null
                    && !event.getEmails().isEmpty();

    /**
     * Simula el "procesamiento" de un evento imprimiéndolo por consola.
     */
    public static final Consumer<ReservationEvent> logReservation = event ->
            System.out.println("[PROCESADO] " + event);

}
