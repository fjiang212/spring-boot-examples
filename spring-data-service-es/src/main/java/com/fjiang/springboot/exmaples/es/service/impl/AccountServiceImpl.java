package com.fjiang.springboot.exmaples.es.service.impl;

import java.util.List;

import org.elasticsearch.index.query.QueryBuilder;
import org.elasticsearch.index.query.QueryBuilders;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.elasticsearch.core.ElasticsearchTemplate;
import org.springframework.data.elasticsearch.core.query.NativeSearchQuery;
import org.springframework.data.elasticsearch.core.query.NativeSearchQueryBuilder;
import org.springframework.stereotype.Service;

import com.fjiang.springboot.exmaples.es.service.AccountService;

@Service
public class AccountServiceImpl implements AccountService {
    @Autowired
    private ElasticsearchTemplate esTemplate;

    @Override
    public List<String> searchAccount() {
        QueryBuilder query = QueryBuilders.boolQuery()
                                          .should(QueryBuilders.queryStringQuery("F").lenient(true).field("gender"));

        NativeSearchQuery build = new NativeSearchQueryBuilder().withIndices("bank").withQuery(query).build();

        return esTemplate.queryForIds(build);
    }

}
