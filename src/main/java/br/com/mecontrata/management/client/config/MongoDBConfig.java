package br.com.mecontrata.management.client.config;

import com.mongodb.WriteConcern;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.mongodb.core.WriteConcernResolver;

@Configuration
public class MongoDBConfig {

    @Bean
    public WriteConcernResolver writeConcernResolver() {
        return action -> {
            return WriteConcern.ACKNOWLEDGED;
        };
    }

}
