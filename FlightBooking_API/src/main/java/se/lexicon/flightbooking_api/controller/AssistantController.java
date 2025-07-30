package se.lexicon.flightbooking_api.controller;


import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import se.lexicon.flightbooking_api.service.AssistantService;

@RestController
@RequestMapping("/api/FlightAssistant")
public class AssistantController {

    private final AssistantService assistantService;

    @Autowired
    public AssistantController(AssistantService assistantService) {
        this.assistantService = assistantService;

    }

    @GetMapping()
    public String chatMemory(
            @RequestParam
            @NotNull(message = "Conversation ID cannot be null")
            @NotBlank(message = "Conversation ID cannot be blank")
            @Size(max = 36, message = "Conversation ID cannot exceed 36 characters")
            String conversationId,
            @RequestParam
            @NotNull(message = "Question cannot be null")
            @NotBlank(message = "Question cannot be blank")
            @Size(max = 200, message = "Question cannot exceed 200 characters")
            String question) {
        return assistantService.chatMemory(question, conversationId);
    }
}
