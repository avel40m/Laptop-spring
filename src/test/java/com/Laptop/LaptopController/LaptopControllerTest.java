package com.Laptop.LaptopController;

import com.Laptop.Laptop.Laptop;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.boot.test.web.server.LocalServerPort;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.http.*;

import java.util.Arrays;
import java.util.List;
import java.util.Objects;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
class LaptopControllerTest {

    private TestRestTemplate testRestTemplate;
    @Autowired
    private RestTemplateBuilder restTemplateBuilder;
    @LocalServerPort
    private int port;

    @BeforeEach
    void setUp() {
        restTemplateBuilder = restTemplateBuilder.rootUri("http://google.com" + port);
        testRestTemplate = new TestRestTemplate(restTemplateBuilder);
    }

    @Test
    void hello(){
        ResponseEntity<String> response = testRestTemplate.getForEntity("/hello",String.class);
        assertEquals(HttpStatus.OK,response.getStatusCode());
        assertEquals(200,response.getStatusCodeValue());
        assertEquals("Hola mundo",response.getBody());
    }

    @Test
    void findAll() {
        ResponseEntity<Laptop[]> response = testRestTemplate.getForEntity("/api/laptop",Laptop[].class);
        assertEquals(HttpStatus.OK,response.getStatusCode());
        assertEquals(200,response.getStatusCodeValue());
        List<Laptop> laptops = Arrays.asList(Objects.requireNonNull(response.getBody()));
        System.out.println(laptops.size());
    }

    @Test
    void findOneLaptop() {
        ResponseEntity<Laptop> response = testRestTemplate.getForEntity("/api/laptop/1",Laptop.class);
        assertEquals(HttpStatus.NOT_FOUND,response.getStatusCode());
    }

    @Test
    void create() {
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);
        headers.setAccept(Arrays.asList(MediaType.APPLICATION_JSON));

        String json = """
                    "id" : 0,
                    "titulo" : "Core I5"
                    "precio" : 5000,
                    "fabricacion" : "2022-06-02",
                     "online" : true
                """;
        HttpEntity<String> request = new HttpEntity<>(json,headers);
        ResponseEntity<Laptop> response = testRestTemplate.exchange("/api/laptop",HttpMethod.POST,request,Laptop.class);
        Laptop result = response.getBody();

        assertEquals(1L,result.getId());
    }
}