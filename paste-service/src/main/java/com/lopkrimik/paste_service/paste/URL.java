package com.lopkrimik.paste_service.paste;

import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Service;
import org.springframework.web.util.UriBuilder;
import org.springframework.web.util.UriComponentsBuilder;

import java.util.Random;

@Service
public class URL {
    private static final String BASE_URL = "http://localhost:8084/api/v1/paste/"+generateRandomHexString(6);
    public String takeUrl(String ... path){
        return UriComponentsBuilder.fromHttpUrl(BASE_URL)
                .pathSegment(path)
                .build()
                .toUriString();
    }

    public static String generateRandomHexString(int length){
        Random r = new Random();
        StringBuffer sb = new StringBuffer();
        while(sb.length() < length){
            sb.append(Integer.toHexString(r.nextInt()));
        }
        return sb.toString().substring(0, length);
    }
}
