package com.wangshuai.efnews.manager.http;

import org.apache.http.Header;
import org.apache.http.HttpEntity;
import org.apache.http.NameValuePair;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.client.protocol.HttpClientContext;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.message.BasicNameValuePair;
import org.apache.http.util.EntityUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

/**
 *  HTTP调用池
 * @author wangshuai
 * @version V1.0
 * @since 2017-09-05 10:13
 */
public class MessageHttpClient {

    /**
     * 日志
     */
    private static final Logger LOGGER = LoggerFactory.getLogger(MessageHttpClient.class);

    /**
     * 默认数据格式
     */
    private static String DEFAULT_ENCODING = "utf-8";

    /**
     * 客户端
     */
    private CloseableHttpClient client;

    /**
     * 初始化
     */
    public MessageHttpClient(CloseableHttpClient client) {
        this.client = client;
    }

    /**
     * post发送http请求
     *
     * @param url
     * @return
     */
    public String doPost(String url) {
        return doPost(url, null, null, null);
    }

    /**
     * post发送http请求，数据按照encoding进行字符格式转换
     *
     * @param url
     * @param encoding
     * @return
     */
    public String doPost(String url, String encoding) {
        return doPost(url, null, null, encoding);
    }

    /**
     * post发送http请求，带参数
     *
     * @param url
     * @param params
     * @return
     */
    public String doPost(String url, Map<String, String> params) {
        return doPost(url, params, null, null);
    }

    /**
     * post发送http请求，带参数和字符格式
     *
     * @param url
     * @param params
     * @param encoding
     * @return
     */
    public String doPost(String url, Map<String, String> params, String encoding) {
        return doPost(url, params, null, encoding);
    }

    /**
     * post发送http请求，带参数和表头定义
     *
     * @param url
     * @param params
     * @param headers
     * @return
     */
    public String doPost(String url, Map<String, String> params, Map<String, String> headers) {
        return doPost(url, params, headers, null);
    }

    /**
     * post发送http请求，带参数和表头定义，参数数据按照encoding进行字符格式转换
     *
     * @param url
     * @param params
     * @param headers
     * @param encoding
     * @return
     */
    public String doPost(String url, Map<String, String> params, Map<String, String> headers, String encoding) {
        String result = "";
        try {
            HttpPost post = new HttpPost(url);
            if (null != headers) {
                Iterator<String> it = headers.keySet().iterator();
                while (it.hasNext()) {
                    String key = it.next();
                    post.addHeader(key, headers.get(key));
                }
            }

            if (null == encoding || "".equals(encoding.trim())) {
                encoding = DEFAULT_ENCODING;
            }

            if (null != params && params.size() > 0) {
                List<NameValuePair> cons = new ArrayList<NameValuePair>();
                Iterator<String> it = params.keySet().iterator();
                while (it.hasNext()) {
                    String key = it.next();
                    cons.add(new BasicNameValuePair(key, params.get(key)));
                }
                post.setEntity(new UrlEncodedFormEntity(cons, encoding));
            }

            HttpClientContext context = HttpClientContext.create();

            CloseableHttpResponse response = client.execute(post, context);
            try {
                HttpEntity entity = response.getEntity();
                if (null != entity) {
                    result = EntityUtils.toString(entity, encoding);
                }
            } finally {
                response.close();
            }
        } catch (Exception e) {
            LOGGER.error("http post错误.{}", e);
        }
        return result;
    }

    /**
     * post发送http请求，带参数
     *
     * @param url
     * @param content
     * @return
     */
    public String doPostStr(String url, String content) {
        return doPostStr(url, content, null, null);
    }

    /**
     * post发送http请求，带参数和字符格式
     *
     * @param url
     * @param content
     * @param encoding
     * @return
     */
    public String doPostStr(String url, String content, String encoding) {
        return doPostStr(url, content, null, encoding);
    }

    /**
     * post发送http请求，带参数和表头定义
     *
     * @param url
     * @param content
     * @param headers
     * @return
     */
    public String doPostStr(String url, String content, Map<String, String> headers) {
        return doPostStr(url, content, headers, null);
    }

    /**
     * post发送http请求，带参数和表头定义，参数数据按照encoding进行字符格式转换
     *
     * @param url
     * @param content
     * @param headers
     * @param encoding
     * @return
     */
    public String doPostStr(String url, String content, Map<String, String> headers, String encoding) {
        String result = "";
        try {
            HttpPost post = new HttpPost(url);
            if (null != headers) {
                Iterator<String> it = headers.keySet().iterator();
                while (it.hasNext()) {
                    String key = it.next();
                    post.addHeader(key, headers.get(key));
                }
            }

            if (null == encoding || "".equals(encoding.trim())) {
                encoding = DEFAULT_ENCODING;
            }

            if (null != content && "".equals(content.trim()) == false) {
                StringEntity entity = new StringEntity(content, encoding);
                post.setEntity(entity);
            }

            HttpClientContext context = HttpClientContext.create();

            CloseableHttpResponse response = client.execute(post, context);
            try {
                HttpEntity entity = response.getEntity();
                if (null != entity) {
                    result = EntityUtils.toString(entity, encoding);
                }
            } finally {
                response.close();
            }
        } catch (Exception e) {
            LOGGER.error("http post错误.{}", e);
        }
        return result;
    }

    /**
     * get发送http请求，带参数和表头定义，参数数据按照encoding进行字符格式转换
     *
     * @param url
     * @param headers
     * @return
     */
    public String doGet(String url, Map<String, String> headers, String encoding) {
        String result = "";
        try {
            HttpGet getter = new HttpGet(url);
            if (null != headers) {
                Iterator<String> it = headers.keySet().iterator();
                while (it.hasNext()) {
                    String key = it.next();
                    getter.addHeader(key, headers.get(key));
                }
            }

            HttpClientContext context = HttpClientContext.create();

            CloseableHttpResponse response = client.execute(getter, context);
            try {
                HttpEntity entity = response.getEntity();
                if (null != entity) {
                    result = EntityUtils.toString(entity, encoding);
                }
            } finally {
                response.close();
            }
        } catch (Exception e) {
            LOGGER.error("http get错误.{}", e);
        }
        return result;
    }

    /**
     * get发送http请求，获取返回头
     *
     * @param url
     * @param headers
     * @return
     */
    public Header[] doGetResponseHeader(String url, Map<String, String> headers, String encoding) {
        Header[] respHeaders = null;
        try {
            HttpGet getter = new HttpGet(url);
            if (null != headers) {
                Iterator<String> it = headers.keySet().iterator();
                while (it.hasNext()) {
                    String key = it.next();
                    getter.addHeader(key, headers.get(key));
                }
            }

            HttpClientContext context = HttpClientContext.create();

            CloseableHttpResponse response = client.execute(getter, context);
            try {
                respHeaders = response.getAllHeaders();
            } finally {
                response.close();
            }
        } catch (Exception e) {
            LOGGER.error("http get错误.{}", e);
        }
        return respHeaders;
    }

    protected void doClose() {
        try {
            client.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
