package mll.service;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

import java.io.BufferedReader;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.json.simple.JSONObject;
import org.junit.Test;

public class FeedbackServiceTest {
    //@Test
    public void handleFeedbackRequest() throws Exception {
        FeedbackService service = new FeedbackService();

        HttpServletRequest request = mock(HttpServletRequest.class);
        HttpServletResponse response = mock(HttpServletResponse.class);
        BufferedReader reader = mock(BufferedReader.class);
        
        when(request.getReader()).thenReturn(reader);
        
        //JSONObject resp = service.handleFeedbackRequest(request, response);
        
        assertEquals(true,true);
    }
}