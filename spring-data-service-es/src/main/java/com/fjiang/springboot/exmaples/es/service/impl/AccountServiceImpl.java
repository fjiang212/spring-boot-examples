package com.fjiang.springboot.exmaples.es.service.impl;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import org.apache.http.util.EntityUtils;
import org.elasticsearch.action.search.SearchRequest;
import org.elasticsearch.action.search.SearchResponse;
import org.elasticsearch.client.RequestOptions;
import org.elasticsearch.client.Response;
import org.elasticsearch.client.RestHighLevelClient;
import org.elasticsearch.index.query.MatchQueryBuilder;
import org.elasticsearch.search.builder.SearchSourceBuilder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fjiang.springboot.exmaples.es.service.AccountService;

@Service
public class AccountServiceImpl implements AccountService {
    @Autowired
    private RestHighLevelClient client;

    @Autowired
    private SearchSourceBuilder sourceBuilder;

    @Autowired
    private ObjectMapper objectMapper;

    @Override
    public List<String> searchAccount() {
        List<String> results = new ArrayList<>();

        // get indices
        List<String> indices = getIndices("bank");
        if (indices.size() > 0) {

            String[] indexArray = indices.stream().toArray(String[]::new);

            // Build query
            MatchQueryBuilder builder = new MatchQueryBuilder("gender", "F");

            // Build source
            sourceBuilder.query(builder);

            String[] includeFields = new String[] { "account_number", "balance", "firstname" };
            sourceBuilder.fetchSource(includeFields, null);

            // set search request
            SearchRequest searchRequest = new SearchRequest(indexArray);
            searchRequest.source(sourceBuilder);

            SearchResponse searchResponse;
            try {
                searchResponse = client.search(searchRequest, RequestOptions.DEFAULT);
                searchResponse.getHits().forEach(hit -> {
                    results.add(hit.getSourceAsString());
                });
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        return results;
    }

    @SuppressWarnings("deprecation")
    public List<String> getIndices(String name) {

        Response response;
        try {
            response = client.getLowLevelClient().performRequest("GET", "/_aliases");
            String json = EntityUtils.toString(response.getEntity());

            Map<String, Object> aliases = objectMapper.readValue(json, new TypeReference<Map<String, Object>>() {
            });

            return aliases.keySet().stream().filter(index -> index.contains(name)).collect(Collectors.toList());

        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }
}
