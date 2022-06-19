package br.pegoraro.code.timetracking.model;

import java.time.Instant;

public record Project(String id, String name, Instant creationTime, Instant lastUpdate, Instant activationDate, Instant projectFinish){}
