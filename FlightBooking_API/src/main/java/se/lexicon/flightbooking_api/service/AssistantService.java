package se.lexicon.flightbooking_api.service;

public interface AssistantService {

    String chatMemory(String query, String conversationId);

    void resetChat (String conversationId);
}
