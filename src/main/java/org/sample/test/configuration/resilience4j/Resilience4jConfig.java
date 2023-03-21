package org.sample.test.configuration.resilience4j;

import io.github.resilience4j.circuitbreaker.CircuitBreaker;
import io.github.resilience4j.core.registry.EntryAddedEvent;
import io.github.resilience4j.core.registry.EntryRemovedEvent;
import io.github.resilience4j.core.registry.EntryReplacedEvent;
import io.github.resilience4j.core.registry.RegistryEventConsumer;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Slf4j
@Configuration
public class Resilience4jConfig {

    @Bean
    public RegistryEventConsumer<CircuitBreaker> resilience4JCircuitBreakerEventConsumer() {
        return new RegistryEventConsumer<>() {
            @Override
            public void onEntryAddedEvent(EntryAddedEvent<CircuitBreaker> entryAddedEvent) {
                entryAddedEvent.getAddedEntry()
                    .getEventPublisher()
                    .onStateTransition(event ->
                        log.warn("circuit breaker added event : {}", event)
                    );
            }

            @Override
            public void onEntryRemovedEvent(EntryRemovedEvent<CircuitBreaker> entryRemoveEvent) {
                entryRemoveEvent.getRemovedEntry()
                    .getEventPublisher()
                    .onStateTransition(event ->
                        log.warn("circuit breaker removed event : {}", event)
                    );
            }

            @Override
            public void onEntryReplacedEvent(EntryReplacedEvent<CircuitBreaker> entryReplacedEvent) {
                entryReplacedEvent.getOldEntry()
                    .getEventPublisher()
                    .onStateTransition(event ->
                        log.warn("circuit breaker replaced event old : {}", event)
                    );
                entryReplacedEvent
                    .getNewEntry()
                    .getEventPublisher()
                    .onStateTransition(event ->
                        log.warn("circuit breaker replaced event new: {}", event)
                    );
            }
        };
    }

}
