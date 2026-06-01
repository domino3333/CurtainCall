package com.curtaincall.service;

import java.time.LocalDate;

public interface PerformanceCollectService {

    int collectPerformances(LocalDate from, LocalDate to);

    int collectPerformances(LocalDate from, LocalDate to, long delayMillis);
}
