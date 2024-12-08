package com.example.chatapp.controller;

import com.example.chatapp.model.Message;
import com.example.chatapp.repository.MessageRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
public class ChatController {

    @Autowired
    private MessageRepository messageRepository;

    // Load the chat page
    @GetMapping("/chat")
    public String chatPage(Model model) {
        List<Message> messages = messageRepository.findAll();
        model.addAttribute("messages", messages);
        return "chat";
    }

    // Add a new message
    @PostMapping("/chat")
    public String addMessage(@RequestParam String sender, @RequestParam String recipient, @RequestParam String content) {
        Message message = new Message(sender, recipient, content);
        messageRepository.save(message);
        return "redirect:/chat";
    }
}
