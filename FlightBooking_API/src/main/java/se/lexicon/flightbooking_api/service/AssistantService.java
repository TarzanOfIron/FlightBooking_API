package se.lexicon.flightbooking_api.service;

import reactor.core.publisher.Flux;

public interface AssistantService {

    Flux<String> chatMemory(String query, String conversationId);

    void resetChat (String conversationId);
}
