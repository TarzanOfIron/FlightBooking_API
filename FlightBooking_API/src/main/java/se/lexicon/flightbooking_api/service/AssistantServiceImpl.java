package se.lexicon.flightbooking_api.service;

import org.springframework.ai.chat.client.ChatClient;
import org.springframework.ai.chat.client.advisor.MessageChatMemoryAdvisor;
import org.springframework.ai.chat.memory.ChatMemory;
import org.springframework.ai.chat.model.ChatResponse;
import org.springframework.ai.openai.OpenAiChatOptions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
public class AssistantServiceImpl implements AssistantService {

    private final ChatMemory chatMemory;
    private final ChatClient chatClient;
    private final FlightBookingService flightBookingService;

    //Todo: complete the system message

    private String systemMessage = """
            You are a flight assistant, helping users to handle their flights.
            Your tasks includes the following:
            1: Booking a flight for the user using the "booking request" tool, the bookingRequest needs a name and an email
            2: Canceling a flight for the user using the "cancelFlight" tool
            3: Listing the user their flights using the "findBookingsByEmail" tool
            4: Listing all the flights using "findAll" tool
            5: Listing all available flights using "findAvailableFlights" tool
             
            If asked anything not related to the previously mentioned tasks or the users personal information, respectfully deline
            to answer the question or task and explain what you are able to help with.
            """;

    @Autowired
    public AssistantServiceImpl(ChatMemory chatMemory, ChatClient.Builder chatClientBuilder, FlightBookingService flightBookingService) {
        this.chatClient = chatClientBuilder
                .defaultAdvisors(MessageChatMemoryAdvisor.builder(chatMemory).build())
                .build();
        this.flightBookingService = flightBookingService;
        this.chatMemory = chatMemory;
    }

    @Override
    public String chatMemory(String query, String conversationId) {
        if (query == null || query.isEmpty()) {
            throw new RuntimeException("Query is empty");
        }

        if (conversationId == null || conversationId.trim().isEmpty()) {
            throw new RuntimeException("ConversationId is empty");
        }

        ChatResponse chatResponse = this.chatClient.prompt()
                .user(query)
                .system(systemMessage)
                .options(OpenAiChatOptions.builder().temperature(0.2).maxTokens(500).build())
                .tools(flightBookingService)
                .advisors(advisorSpec -> advisorSpec.param(ChatMemory.CONVERSATION_ID, conversationId))
                .call()
                .chatResponse();


        return chatResponse != null ? chatResponse.getResult().getOutput().getText() : "No Response Returned";
    }

    @Override
    public void resetChat(String conversationId) {
        if (conversationId == null || conversationId.trim().isEmpty()) {
            throw new RuntimeException("ConversationId is empty");
        }
        chatMemory.clear(conversationId);
    }
}
