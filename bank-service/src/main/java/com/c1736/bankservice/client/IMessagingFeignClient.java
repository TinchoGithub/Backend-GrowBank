package com.c1736.bankservice.client;

import com.c1736.bankservice.client.dto.MessagingDTO;
import com.c1736.bankservice.client.interceptor.FeignClientInterceptor;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

@FeignClient(name = "Messaging-Service", url = "http://messaging-service:8093/api/v1/", configuration = FeignClientInterceptor.class)
public interface IMessagingFeignClient {

//    @PostMapping("/sendEmail")
//    public ResponseEntity<String> sendEmail(@RequestBody MessagingDTO email);
}
