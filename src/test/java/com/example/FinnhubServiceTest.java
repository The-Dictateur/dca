package com.example;

import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.Assertions;
import org.mockito.*;

public class FinnhubServiceTest {

    @Mock
    private HttpClient mockHttpClient;

    @InjectMocks
    private FinnhubService service;
    

    @SuppressWarnings("unchecked")
    @Test
    public void testGetStockInfo() throws Exception  {
        mockHttpClient = Mockito.mock(HttpClient.class);
        HttpResponse<String> mockResponse = Mockito.mock(HttpResponse.class);

        Mockito.when(mockResponse.body()).thenReturn("{\"c\": 150.0, \"d\": 2.0, \"dp\": 1.3}");
        Mockito.when(mockHttpClient.send(Mockito.any(HttpRequest.class), Mockito.any(HttpResponse.BodyHandler.class)))
           .thenReturn(mockResponse);

        service = new FinnhubService("FAKE_APIKEY", mockHttpClient);

        // Act
        String result = service.getStockInfo("AAPL");

        // Assert
        Assertions.assertTrue(result.contains("\"c\": 150.0"));
    }
}
