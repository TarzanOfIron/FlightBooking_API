package se.lexicon.flightbooking_api.service;

import org.springframework.ai.chat.memory.ChatMemory;
import org.springframework.ai.chat.messages.UserMessage;
import org.springframework.ai.chat.model.ChatResponse;
import org.springframework.ai.chat.prompt.Prompt;
import org.springframework.ai.openai.OpenAiChatModel;
import org.springframework.ai.openai.OpenAiChatOptions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
public class AssistantServiceImpl implements AssistantService {

    private ChatMemory chatMemory;
    private final OpenAiChatModel openAiChatModel;

    @Autowired
    public AssistantServiceImpl(ChatMemory chatMemory, OpenAiChatModel openAiChatModel) {
        this.chatMemory = chatMemory;
        this.openAiChatModel = openAiChatModel;
    }

    @Override
    public String chatMemory(String query, String conversationId) {
        if (query == null || query.isEmpty()) {
            throw new RuntimeException("Query is empty");
        }

        if (conversationId == null || conversationId.trim().isEmpty()) {
            throw new RuntimeException("ConversationId is empty");
        }

        UserMessage userMessage = UserMessage.builder().text(query).build();
        chatMemory.add( conversationId, userMessage );

        Prompt prompt = Prompt.builder()
                .messages(chatMemory.get(conversationId))
                .chatOptions(OpenAiChatOptions.builder()
                        .model("gpt-4.1-mini")
                        .temperature(0.2)
                        .maxTokens(500)
                        .build())
                .build();

        ChatResponse chatResponse = openAiChatModel.call(prompt);
        chatMemory.add(conversationId, chatResponse.getResult().getOutput());

        return chatResponse.getResult().getOutput().getText();
    }

    @Override
    public void resetChat(String conversationId) {
        if (conversationId == null || conversationId.trim().isEmpty()) {
            throw new RuntimeException("ConversationId is empty");
        }

        chatMemory.clear(conversationId);
    }
}
