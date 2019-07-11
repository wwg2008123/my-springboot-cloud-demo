package com.wwg.gatewayzuul.config;

import com.netflix.hystrix.exception.HystrixTimeoutException;
import org.springframework.cloud.netflix.zuul.filters.route.FallbackProvider;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.client.ClientHttpResponse;
import org.springframework.stereotype.Component;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.nio.charset.Charset;

/**
 * 熔断处理
 */
@Component
public class ServiceFallbackProvider implements FallbackProvider {
    @Override
    public String getRoute() {
        ///ervice id ,如果想要支持所有的就return "*" or return null;
        return "*";
    }

    @Override
    public ClientHttpResponse fallbackResponse(String route, Throwable cause) {
        if (cause instanceof HystrixTimeoutException) {
            return response(HttpStatus.GATEWAY_TIMEOUT);
        } else {
            return this.fallbackResponse();
        }
    }

    public ClientHttpResponse fallbackResponse() {
        return this.response(HttpStatus.INTERNAL_SERVER_ERROR);
    }

    private ClientHttpResponse response(final HttpStatus httpStatus) {
        ClientHttpResponse response = new ClientHttpResponse() {
            @Override
            public HttpHeaders getHeaders() {
                //headers 设定
                HttpHeaders httpHeaders = new HttpHeaders();
                MediaType mediaType = new MediaType("application", "json", Charset.forName("UTF-8"));
                httpHeaders.setContentType(mediaType);
                return httpHeaders;
            }

            @Override
            public InputStream getBody() throws IOException {
                String result ="服务部不可用，请稍后再试。"+ getStatusCode();
                return new ByteArrayInputStream(result.getBytes());
            }

            @Override
            public HttpStatus getStatusCode() throws IOException {
                return httpStatus;
            }

            @Override
            public int getRawStatusCode() throws IOException {
                return httpStatus.value();
            }

            @Override
            public String getStatusText() throws IOException {
                return httpStatus.getReasonPhrase();
            }

            @Override
            public void close() {

            }
        };

        return response;
    }

}
