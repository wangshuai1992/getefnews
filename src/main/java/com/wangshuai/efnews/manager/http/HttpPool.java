package com.wangshuai.efnews.manager.http;

import org.apache.http.impl.client.CloseableHttpClient;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;

/**
 * TODO
 *
 * @author wangshuai
 * @version V1.0
 * @date 2017-12-30 01:31
 */
@Component
public class HttpPool {

    private MessageHttpClient mhc;

    @Value("${efnews.http.timeout}")
    private int timeout;

    @Value("${efnews.http.maxtotal}")
    private int maxtotal;

    @Value("${efnews.http.maxperroute}")
    private int maxPerRoute;

    @PostConstruct
    public void init() {
        CloseableHttpClient httpClient = MessageHttpClientBuilder.custom().timeout(timeout).pool(maxtotal, maxPerRoute).build();
        mhc = new MessageHttpClient(httpClient);
    }

    public MessageHttpClient getMhc() {
        return mhc;
    }

}
