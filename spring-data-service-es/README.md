# Import test data
* Start Elasticsearch
* Unzip sample data accounts.zip
* Import sample data to elasticsearch

```
curl -H 'Content-Type: application/x-ndjson' -XPOST 'localhost:9200/bank/account/_bulk?pretty' --data-binary @accounts.json
```

# References
* https://www.elastic.co/guide/en/kibana/current/tutorial-load-dataset.html 
* https://javadeveloperzone.com/spring-boot/spring-boot-elastic-search-example/