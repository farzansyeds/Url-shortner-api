package com.example.url_shortener;

import com.example.url_shortener.model.Url;
import com.example.url_shortener.service.UrlService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.net.URI;
import java.util.Optional;

@RestController
public class UrlController {

    private final UrlService urlService;

    public UrlController(UrlService urlService){
        this.urlService=urlService;
    }

    @PostMapping("/shorten")
        public String shortenUrl(@RequestBody String originalUrl){
            String shortCode = urlService.shortenUrl(originalUrl);

            return "http://localhost:8080/" + shortCode;
        }


    @GetMapping("/{shortCode}")
    public ResponseEntity<Void> redirectToOriginal(@PathVariable String shortCode){
        Optional<Url> urlOptional = urlService.getOriginalUrl(shortCode);

        if(urlOptional.isPresent()) {
            Url url = urlOptional.get();

            return ResponseEntity.status(HttpStatus.FOUND)
                    .location(URI.create(url.getOriginalUrl()))
                    .build();
        }else {
            return ResponseEntity.notFound().build();
        }
    }

}
