package com.serai.challenge.controller;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.serai.challenge.model.OrderDto;
import com.serai.challenge.model.OrderRequest;
import com.serai.challenge.model.OrderResponse;
import com.serai.challenge.service.impl.OrderServiceImpl;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import org.apache.tomcat.util.codec.binary.Base64;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.*;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import java.nio.charset.Charset;

@RestController
@SecurityRequirement(name = "api")
public class OrderController {
    private static final Logger logger = LoggerFactory.getLogger(OrderController.class);

    @Autowired
    private OrderServiceImpl orderService;

    @RequestMapping(value="/fetch", method = RequestMethod.GET)
    public ResponseEntity fetchAll(){
        return new ResponseEntity(this.orderService.fetchAll(), HttpStatus.OK);
    }

    @RequestMapping(value="/generateOrder", method = RequestMethod.GET)
    public ResponseEntity generateOrder() {
        OrderDto dto = this.orderService.generateOrder();
        return new ResponseEntity(dto, HttpStatus.OK);
    }

    @RequestMapping(value="/placeOrder", method = RequestMethod.POST)
    public ResponseEntity placeOrder(@RequestBody OrderDto orderDto){
        OrderResponse res = this.orderService.processOrder(orderDto);
        return new ResponseEntity(res, HttpStatus.OK);
    }

    @RequestMapping(value="/generateAndPlaceOrder", method = RequestMethod.POST)
    public ResponseEntity generateAndPlaceOrder() throws JsonProcessingException {
        OrderDto orderDto = this.orderService.generateOrder();
//        String jsonReq = new ObjectMapper().writeValueAsString(orderDto);

        RestTemplate restTemplate = new RestTemplate();
        String url = "http://localhost:8080/placeOrder";

        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);
        headers.setBasicAuth("serai","serai");

        HttpEntity<OrderDto> request =
                new HttpEntity<OrderDto>(orderDto, headers);

        ResponseEntity<String> responseEntity = restTemplate.postForEntity(url, request, String.class);
        String body = responseEntity.getBody();

        HttpStatus statusCode = responseEntity.getStatusCode();
        int statusCodeValue = responseEntity.getStatusCodeValue();
        HttpHeaders resHeaders = responseEntity.getHeaders();

        if (logger.isDebugEnabled()){
            logger.debug("HTTP status：" + statusCode);
            logger.debug("HTTP Headers：" + resHeaders);
        }


        return responseEntity;

    }


}
