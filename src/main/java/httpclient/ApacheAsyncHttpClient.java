package httpclient;

import org.apache.http.HttpResponse;
import org.apache.http.client.config.RequestConfig;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.client.methods.HttpRequestBase;
import org.apache.http.concurrent.FutureCallback;
import org.apache.http.conn.routing.HttpRoute;
import org.apache.http.conn.ssl.TrustSelfSignedStrategy;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.nio.client.CloseableHttpAsyncClient;
import org.apache.http.impl.nio.client.HttpAsyncClients;
import org.apache.http.impl.nio.conn.PoolingNHttpClientConnectionManager;
import org.apache.http.nio.conn.ssl.SSLIOSessionStrategy;
import org.apache.http.pool.PoolStats;
import org.apache.http.ssl.SSLContexts;
import org.apache.http.util.EntityUtils;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.net.ssl.SSLContext;
import java.lang.reflect.Field;
import java.net.URL;
import java.util.Map;
import java.util.Objects;
import java.util.Set;
import java.util.concurrent.*;

/**
 * @Des:
 * @Author: jiangchuan
 * <p>
 * @Date: 20-9-15
 */
public class ApacheAsyncHttpClient implements HttpClientTemplate{

    private static final Logger LOGGER = LoggerFactory.getLogger(ApacheAsyncHttpClient.class);

    public static final String DEFAULT_CHARSET = "UTF-8";

    private static final String URL_TYPE_HTTPS = "https";

    private CloseableHttpAsyncClient asyncHttpClient;

    private CloseableHttpAsyncClient asyncHttpsClient;


    private ScheduledExecutorService scheduledExecutorService = Executors.newScheduledThreadPool(1);


    public ApacheAsyncHttpClient() throws Exception {
        SSLContext sslcontext = SSLContexts.custom()
                .loadTrustMaterial(new TrustSelfSignedStrategy())
                .build();
        // Allow TLSv1 protocol only
        SSLIOSessionStrategy sslSessionStrategy = new SSLIOSessionStrategy(
                sslcontext,
                new String[] { "TLSv1" },
                null,
                SSLIOSessionStrategy.getDefaultHostnameVerifier());

        RequestConfig requestConfig = RequestConfig.custom()
                .setConnectionRequestTimeout(3000)
                .setSocketTimeout(3000)
                .setConnectTimeout(3000).build();

        asyncHttpClient = HttpAsyncClients.custom()
                .setDefaultRequestConfig(requestConfig)
                .setMaxConnTotal(3000)
                .setMaxConnPerRoute(300)
                .build();

        asyncHttpsClient =  HttpAsyncClients.custom()
                .setDefaultRequestConfig(requestConfig)
                .setMaxConnPerRoute(3000)
                .setMaxConnTotal(300)
                .setSSLStrategy(sslSessionStrategy)
                .build();
        Field[] declaredFields = asyncHttpClient.getClass().getDeclaredFields();
        Field connmgr = asyncHttpClient.getClass().getDeclaredField("connmgr");
        connmgr.setAccessible(true);
        PoolingNHttpClientConnectionManager clientConnectionManager = (PoolingNHttpClientConnectionManager) connmgr.get(asyncHttpClient);


        scheduledExecutorService.scheduleWithFixedDelay(new Runnable() {
            @Override
            public void run() {
                try {
                    Set<HttpRoute> routes = clientConnectionManager.getRoutes();
                    for (HttpRoute route : routes) {
                        PoolStats stats = clientConnectionManager.getStats(route);
                        LOGGER.info("PoolingNHttpClientConnectionManager route : {},  poolStats : {}", route.toString(),stats);
                    }
                } catch (Exception e){
                    e.printStackTrace();
                }
            }
        },0, 1, TimeUnit.SECONDS);
        asyncHttpClient.start();
        asyncHttpsClient.start();

    }

    @Override
    public CompletableFuture<String> get(String url) {
        CompletableFuture<HttpResponse> requestFuture = new CompletableFuture<>();
        CompletableFuture<String> responseFuture = new CompletableFuture<>();

        HttpGet httpGet = new HttpGet(url);
        URL httpUrl = null;
        try {
            httpUrl = new URL(url);
            String host = httpUrl.getHost();
            String key = host + httpUrl.getPath();


            if (URL_TYPE_HTTPS.equals(httpUrl.getProtocol())) {
                asyncHttpsClient.execute(httpGet, wrapCallback(key,requestFuture));
            } else {
                asyncHttpClient.execute(httpGet, wrapCallback(key,requestFuture));
            }
        } catch (Throwable e) {
            requestFuture.completeExceptionally(e);
        }
        requestFuture.whenComplete((httpResponse, throwable)->{
            try {
                if(throwable != null){
                    responseFuture.completeExceptionally(throwable);
                } else {
                    responseFuture.complete(EntityUtils.toString(httpResponse.getEntity(), "UTF-8"));
                }
            } catch (Exception e){
                responseFuture.completeExceptionally(e);
            }

        });
        return responseFuture;
    }

    @Override
    public CompletableFuture<String> get(String url, Map<String, String> headers, int socketTimeout, int connectTimeout, int connectionRequestTimeout) {
        CompletableFuture<HttpResponse> requestFuture = new CompletableFuture<>();
        CompletableFuture<String> responseFuture = new CompletableFuture<>();
        RequestConfig requestConfig = RequestConfig.custom()
                .setSocketTimeout(socketTimeout)
                .setConnectTimeout(connectTimeout)
                .setConnectionRequestTimeout(connectionRequestTimeout)
                .build();
        HttpGet httpGet = new HttpGet(url);
        httpGet.setConfig(requestConfig);
        if (Objects.nonNull(headers) && !headers.isEmpty())
            addHeaders(httpGet, headers);

        URL httpUrl = null;
        try {
            httpUrl = new URL(url);
            String host = httpUrl.getHost();
            String key = host + httpUrl.getPath();

            if (URL_TYPE_HTTPS.equals(httpUrl.getProtocol())) {
                asyncHttpsClient.execute(httpGet, wrapCallback(key, requestFuture));
            } else {
                asyncHttpClient.execute(httpGet, wrapCallback(key, requestFuture));
            }
        } catch (Throwable e) {
            requestFuture.completeExceptionally(e);
        }
        requestFuture.whenComplete((httpResponse, throwable)->{
            try {
                if(throwable != null){
                    responseFuture.completeExceptionally(throwable);
                } else {
                    responseFuture.complete(EntityUtils.toString(httpResponse.getEntity(), "UTF-8"));
                }
            }catch (Exception e){
                responseFuture.completeExceptionally(e);
            }

        });
        return responseFuture;
    }

    @Override
    public CompletableFuture<String> post(String url, String params) {
        CompletableFuture<HttpResponse> requestFuture = new CompletableFuture<>();
        CompletableFuture<String> responseFuture = new CompletableFuture<>();

        HttpPost httpPost = new HttpPost(url);
        StringEntity entity = new StringEntity(params, DEFAULT_CHARSET);
        httpPost.setEntity(entity);

        URL httpUrl = null;
        try {
            httpUrl = new URL(url);
            String host = httpUrl.getHost();
            String key = host + httpUrl.getPath();

            if (URL_TYPE_HTTPS.equals(httpUrl.getProtocol())) {
                asyncHttpsClient.execute(httpPost, wrapCallback(key, requestFuture));
            } else {
                asyncHttpClient.execute(httpPost, wrapCallback(key, requestFuture));
            }
        }  catch (Throwable e) {
            requestFuture.completeExceptionally(e);
        }
        requestFuture.whenComplete((httpResponse, throwable)->{
            try {
                if(throwable != null){
                    responseFuture.completeExceptionally(throwable);
                } else {
                    responseFuture.complete(EntityUtils.toString(httpResponse.getEntity(), "UTF-8"));
                }
            }catch (Exception e){
                responseFuture.completeExceptionally(e);
            }

        });
        return responseFuture;
    }

    @Override
    public CompletableFuture<String> post(String url, String params, Map<String, String> headers, int socketTimeout, int connectTimeout, int connectionRequestTimeout) {
        CompletableFuture<HttpResponse> requestFuture = new CompletableFuture<>();
        CompletableFuture<String> responseFuture = new CompletableFuture<>();

        RequestConfig requestConfig = RequestConfig.custom()
                .setSocketTimeout(socketTimeout)
                .setConnectTimeout(connectTimeout)
                .setConnectionRequestTimeout(connectionRequestTimeout)
                .build();

        HttpPost httpPost = new HttpPost(url);
        StringEntity entity = new StringEntity(params, DEFAULT_CHARSET);
        httpPost.setEntity(entity);

        httpPost.setConfig(requestConfig);
        if (Objects.nonNull(headers) && !headers.isEmpty())
            addHeaders(httpPost, headers);

        URL httpUrl = null;
        try {
            httpUrl = new URL(url);
            String host = httpUrl.getHost();
            String key = host + httpUrl.getPath();


            if (URL_TYPE_HTTPS.equals(httpUrl.getProtocol())) {
                asyncHttpsClient.execute(httpPost, wrapCallback(key, requestFuture));
            } else {
                asyncHttpClient.execute(httpPost, wrapCallback(key, requestFuture));
            }
        } catch (Throwable e) {
            requestFuture.completeExceptionally(e);
        }
        requestFuture.whenComplete((httpResponse, throwable)->{
            try {
                if(throwable != null){
                    responseFuture.completeExceptionally(throwable);
                } else {
                    responseFuture.complete(EntityUtils.toString(httpResponse.getEntity(), "UTF-8"));
                }
            }catch (Exception e){
                responseFuture.completeExceptionally(e);
            }

        });
        return responseFuture;
    }

    public void addHeaders(HttpRequestBase request, Map<String, String> headers) {

        for (Map.Entry<String, String> header : headers.entrySet()) {
            request.addHeader(header.getKey(), header.getValue());
        }
    }

    /**
     * 先使用最简单的线程模型，由apache httpclient线程执行后续业务逻辑，直到writeAndFlush后，切换到netty线程
     *  中间可以增加一个worker线程池负责处理中间业务
     * @param future
     * @param <T>
     * @return
     */

    private <T> FutureCallback<T> wrapCallback(String key, CompletableFuture<T> future) {


        long start = System.nanoTime();


        return new FutureCallback<T>() {
            @Override
            public void completed(T t) {
                long durationInNanos = System.nanoTime() - start;
                future.complete(t);
            }

            @Override
            public void failed(Exception e) {
                long durationInNanos = System.nanoTime() - start;
                LOGGER.error("async httpClient callback error : {}",e);
                future.completeExceptionally(e);
            }

            @Override
            public void cancelled() {
                long durationInNanos = System.nanoTime() - start;
                LOGGER.error("async httpClient callback error : {}","cancelled");
                future.completeExceptionally(new Exception("cancelled"));
            }
        };
    }


}
