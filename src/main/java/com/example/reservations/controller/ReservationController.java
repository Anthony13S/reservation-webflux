package com.example.reservations.controller;

import com.example.reservations.model.ReservationEvent;
import com.example.reservations.util.ReservationFilters;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import reactor.core.publisher.Flux;

import java.util.List;

@RestController
public class ReservationController {

    @GetMapping(value = "/api/reservations/stream", produces = MediaType.APPLICATION_NDJSON_VALUE)
    public Flux<ReservationEvent> streamReservations() {

        // 5 reservas en memoria: 3 válidas, 2 inválidas
        ReservationEvent r1 = new ReservationEvent(
        "RES-001", "Maria Fernanda Lopez", 250.50,
        List.of("maria.lopez@espe.edu.ec"));

        ReservationEvent r2 = new ReservationEvent(
        "RES-002", "Carlos Andrade", 0.0, // inválida: precio no > 0
        List.of("carlos.andrade@espe.edu.ec"));

        ReservationEvent r3 = new ReservationEvent(
        "RES-003", "Sofia Ramirez", 340.00,
        List.of("sofia.ramirez@espe.edu.ec", "backup@espe.edu.ec"));

        ReservationEvent r4 = new ReservationEvent(
        "RES-004", "Jorge Paredes", 180.75,
        List.of()); // inválida: lista de emails vacía

        ReservationEvent r5 = new ReservationEvent(
        "RES-005", "Anthony Quishpe", 410.20,
        List.of("asleon7@espe.edu.ec"));

        return Flux.just(r1, r2, r3, r4, r5)
                .filter(ReservationFilters.isValidReservation)
                .doOnNext(ReservationFilters.logReservation)
                .defaultIfEmpty(defaultReservation);
    }

}
