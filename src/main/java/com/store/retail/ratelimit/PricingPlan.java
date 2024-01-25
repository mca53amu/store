package com.store.retail.ratelimit;

import io.github.bucket4j.Bandwidth;
import io.github.bucket4j.Refill;

import java.time.Duration;

enum PricingPlan {
    FREE(20, 20, 1), BASIC(40, 40, 1), PROFESSIONAL(100, 100, 1);
    private final int capacity;
    private final int tokens;
    private final int hours;

    PricingPlan(final int capacity, final int tokens, final int hours) {
        this.capacity = capacity;
        this.tokens = tokens;
        this.hours = hours;
    }

    static PricingPlan resolvePlanFromApiKey(String apiKey) {
        if (apiKey == null || apiKey.isEmpty()) {
            return FREE;
        } else if (apiKey.startsWith("PX001-")) {
            return PROFESSIONAL;
        } else if (apiKey.startsWith("BX001-")) {
            return BASIC;
        }
        return FREE;
    }

    public Bandwidth getLimit() {
        return Bandwidth.classic(capacity, Refill.intervally(tokens, Duration.ofHours(hours)));
    }
}