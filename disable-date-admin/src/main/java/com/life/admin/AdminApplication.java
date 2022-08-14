package com.life.admin;

import org.apache.http.HttpHost;
import org.elasticsearch.client.RestClient;
import org.elasticsearch.client.RestHighLevelClient;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.web.client.RestTemplate;

@SpringBootApplication
public class AdminApplication {

    public static void main(String[] args) {
        SpringApplication.run(AdminApplication.class, args);
    }

    @Bean
    public RestHighLevelClient client(@Value("${es.url}") String esUrl) {
        return new RestHighLevelClient(RestClient.builder(
//                HttpHost.create("http://175.178.177.105:9200")
                HttpHost.create(esUrl)
        ));
    }

    /**
     * 创建RestTemplate 并注入Spring容器
     *
     * @return
     */
    @Bean
    public RestTemplate restTemplate() {
        return new RestTemplate();
    }
}
