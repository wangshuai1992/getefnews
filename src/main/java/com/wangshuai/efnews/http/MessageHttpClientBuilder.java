package com.wangshuai.efnews.http;

import org.apache.http.client.config.RequestConfig;
import org.apache.http.impl.client.HttpClientBuilder;
import org.apache.http.impl.conn.PoolingHttpClientConnectionManager;

/**
 * TODO
 *
 * @author wangshuai
 * @version V1.0
 * @since 2017-09-05 10:15
 */
public class MessageHttpClientBuilder extends HttpClientBuilder {
    private MessageHttpClientBuilder() {

    }

    public static MessageHttpClientBuilder custom() {
        return new MessageHttpClientBuilder();
    }

    public MessageHttpClientBuilder timeout(int timeout) {
        RequestConfig config = RequestConfig.custom()
                .setConnectionRequestTimeout(timeout)
                .setConnectTimeout(timeout)
                .setSocketTimeout(timeout)
                .build();
        return (MessageHttpClientBuilder) this.setDefaultRequestConfig(config);
    }

    public MessageHttpClientBuilder pool(int maxTotal, int defaultMaxPerRoute) {
        PoolingHttpClientConnectionManager cm = new PoolingHttpClientConnectionManager();
        // Increase max total connection to 200
        cm.setMaxTotal(maxTotal);
        // Increase default max connection per route to 20
        cm.setDefaultMaxPerRoute(defaultMaxPerRoute);
        return (MessageHttpClientBuilder) this.setConnectionManager(cm);
    }

}
