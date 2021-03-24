/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.escuelaing.arem.parcial.parcialWebServer;

import com.despegar.http.client.GetMethod;
import com.despegar.http.client.HttpClientException;
import com.despegar.http.client.HttpResponse;
import com.despegar.sparkjava.test.SparkServer;
import org.junit.ClassRule;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;
import spark.servlet.SparkApplication;

/**
 *
 * @author Nicolás
 */
public class ParcialWebServerTest {

    public static class WebAppTestSparkApp implements SparkApplication {

        @Override
        public void init() {
            new ParcialWebServer().run();
        }

        @ClassRule
        public static SparkServer<WebAppTestSparkApp> testServer = new SparkServer<>(WebAppTestSparkApp.class, 4567);

        /**
         * Test of main method, of class ParcialWebServer.
         *
         * @throws com.despegar.http.client.HttpClientException
         */
        @Test
        public void testAsinLocal() throws HttpClientException {

            GetMethod request = testServer.get("/asin", false);
            HttpResponse httpResponse = testServer.execute(request);
            assertEquals(200, httpResponse.code());
        }
    }
}
