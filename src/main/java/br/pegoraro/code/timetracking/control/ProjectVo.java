package br.pegoraro.code.timetracking.control;

import java.time.Instant;

public record ProjectVo(String id, String name, Instant creationTime, Instant lastUpdated, String status, Instant activeSince, String uri) {}
