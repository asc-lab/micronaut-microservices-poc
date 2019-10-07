package pl.altkom.asc.lab.micronaut.poc.dashboard.domain;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Getter;

import java.time.LocalDate;

@AllArgsConstructor(access = AccessLevel.PROTECTED)
@Getter
public class LocalDateRange {
    private final LocalDate from;
    private final LocalDate to;

    public static LocalDateRange between(LocalDate from, LocalDate to) {
        return new LocalDateRange(from,to);
    }
}
