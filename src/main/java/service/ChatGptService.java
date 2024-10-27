package service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import request.ChatGptRequest;
import response.ChatGptResponse;

import java.util.logging.Logger;

@Service
public class ChatGptService {

    private Logger logger = Logger.getLogger(ChatGptService.class.getName());

    @Value("${openai.model}") //recuperar valores do yml
    private String model;
    @Value("${openai.api.url}")
    private String url;

    @Autowired
    private RestTemplate restTemplate;

    public Object chat(String prompt){
        logger.info("Starting chat");

        ChatGptRequest request = new ChatGptRequest(model, prompt);
        restTemplate.postForObject(url, request, ChatGptResponse.class);

        logger.info("Processing prompt");
        ChatGptResponse response = restTemplate.postForObject(url,request,ChatGptResponse.class);


        return response;
    }
}
