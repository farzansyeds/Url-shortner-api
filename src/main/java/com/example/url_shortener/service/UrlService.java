package com.example.url_shortener.service;

import com.example.url_shortener.model.Url;
import com.example.url_shortener.repository.UrlRepository;
import org.springframework.stereotype.Service;

import java.util.Optional;
import java.util.Random;

@Service
public class UrlService {

    private final UrlRepository urlRepository;

//    dependancy injection
    public UrlService(UrlRepository urlRepository){
        this.urlRepository=urlRepository;
    }

    public String shortenUrl(String originalUrl){
        String shortCode = generateShortCode();

        Url url = new Url(originalUrl, shortCode);

        urlRepository.save(url);

        return shortCode;
    }

    public Optional<Url> getOriginalUrl(String shortCode){
        return urlRepository.findByShortCode(shortCode);
    }

    private String generateShortCode(){
        String characters = "abcdefghijklmnopqrstuvwxyzABCDEFGHIJKLMNOPQRSTUVWXYZ0123456789";
        StringBuilder shortCode = new StringBuilder();
        Random random = new Random();

        for(int i=0;i<6;i++){
            int index = random.nextInt(characters.length());
            shortCode.append(characters.charAt(index));
        }

        return shortCode.toString();
    }


}
