package com.fjiang.springboot.exmaples.es.config;

import java.util.concurrent.TimeUnit;

import org.apache.http.HttpHost;
import org.elasticsearch.client.RestClient;
import org.elasticsearch.client.RestHighLevelClient;
import org.elasticsearch.common.unit.TimeValue;
import org.elasticsearch.search.builder.SearchSourceBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.fasterxml.jackson.databind.ObjectMapper;

@Configuration
public class EsConfig {
    @Bean(destroyMethod = "close")
    public RestHighLevelClient getRestHighLevelClient() throws Exception {
        return new RestHighLevelClient(RestClient.builder(new HttpHost("localhost", 9200, "http")));
    }

    @Bean
    public SearchSourceBuilder getSearchSourceBuilder() {
        SearchSourceBuilder sourceBuilder = new SearchSourceBuilder();
        sourceBuilder.timeout(new TimeValue(300, TimeUnit.SECONDS));

        return sourceBuilder;
    }

    @Bean
    public ObjectMapper mapper() {
        return new ObjectMapper();
    }
}