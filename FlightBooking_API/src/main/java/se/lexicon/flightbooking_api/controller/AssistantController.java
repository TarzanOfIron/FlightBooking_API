package se.lexicon.flightbooking_api.controller;


import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;
import reactor.core.publisher.Flux;
import se.lexicon.flightbooking_api.service.AssistantService;

@CrossOrigin(origins = "http://localhost:5173")
@RestController
@RequestMapping("/api/flightAssistant")
public class AssistantController {

    private final AssistantService assistantService;

    @Autowired
    public AssistantController(AssistantService assistantService) {
        this.assistantService = assistantService;

    }

//    @PostMapping("/chat")
//    public Flux<String> chatMemory(
////            @RequestParam
////            @NotNull(message = "Conversation ID cannot be null")
////            @NotBlank(message = "Conversation ID cannot be blank")
////            @Size(max = 36, message = "Conversation ID cannot exceed 36 characters")
////            String conversationId,
//            @RequestParam
//            @NotNull(message = "Question cannot be null")
//            @NotBlank(message = "Question cannot be blank")
//            @Size(max = 200, message = "Question cannot exceed 200 characters")
//            String question) {
//        System.out.println(question);
//        return assistantService.chatMemory(question, "123");
//    }

    @GetMapping(value = "/chat", produces = MediaType.TEXT_EVENT_STREAM_VALUE)
    public Flux<String> chatMemoryWithGet(
//            @RequestParam
//            @NotNull(message = "Conversation ID cannot be null")
//            @NotBlank(message = "Conversation ID cannot be blank")
//            @Size(max = 36, message = "Conversation ID cannot exceed 36 characters")
//            String conversationId,
            @RequestParam
            @NotNull(message = "Question cannot be null")
            @NotBlank(message = "Question cannot be blank")
            @Size(max = 200, message = "Question cannot exceed 200 characters")
            String question) {
        System.out.println(question);
        return assistantService.chatMemory(question, "123");
    }
}
