package net.sdia.app.ebankbot.agents;

import org.springframework.ai.chat.client.ChatClient;
import org.springframework.ai.chat.client.advisor.MessageChatMemoryAdvisor;
import org.springframework.ai.chat.memory.ChatMemory;
import org.springframework.ai.tool.ToolCallbackProvider;
import org.springframework.stereotype.Service;

@Service
public class EbankAIAgent {
    private final ChatClient chatClient;

    public EbankAIAgent(ChatClient.Builder builder,
                        ChatMemory chatMemory,
                        ToolCallbackProvider toolCallbackProvider) {
        this.chatClient = builder
                .defaultAdvisors(MessageChatMemoryAdvisor.builder(chatMemory).build())
                .defaultToolCallbacks(toolCallbackProvider)
                .build();
    }

    public String chat(String query) {
        return chatClient.prompt()
                .user(query)
                .advisors(advisorSpec -> advisorSpec.param(ChatMemory.CONVERSATION_ID, "ebankbot"))
                .call()
                .content();
    }
}
