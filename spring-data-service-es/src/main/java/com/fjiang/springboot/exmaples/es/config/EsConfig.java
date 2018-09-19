package com.fjiang.springboot.exmaples.es.config;

import java.net.InetAddress;

import org.elasticsearch.client.Client;
import org.elasticsearch.common.settings.Settings;
import org.elasticsearch.common.transport.InetSocketTransportAddress;
import org.elasticsearch.transport.client.PreBuiltTransportClient;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.elasticsearch.core.ElasticsearchOperations;
import org.springframework.data.elasticsearch.core.ElasticsearchTemplate;

@Configuration
public class EsConfig {
    @SuppressWarnings("resource")
    @Bean
    public Client client() throws Exception {
        return new PreBuiltTransportClient(Settings.EMPTY).addTransportAddress(
                new InetSocketTransportAddress(InetAddress.getByName("127.0.0.1"), 9300));
    }

    @Bean
    public ElasticsearchOperations elasticsearchTemplate() throws Exception {
        return new ElasticsearchTemplate(client());
    }

}