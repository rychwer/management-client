package br.com.mecontrata.management.client.service.impl;

import br.com.mecontrata.management.client.domain.ClientDTO;
import br.com.mecontrata.management.client.domain.avro.ClientNotification;
import br.com.server.resource.service.KafkaService;
import lombok.extern.slf4j.Slf4j;
import org.apache.kafka.clients.producer.Callback;
import org.apache.kafka.clients.producer.RecordMetadata;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

@Service("emailNotification")
@Slf4j
public class EmailNotificationServiceImpl extends KafkaService<ClientDTO, ClientNotification> {

    @Value("${kafka.topic.email}")
    private String topicEmail;

    @Override
    protected String getTopic() {
        return topicEmail;
    }

    @Override
    protected ClientNotification configureProducer(ClientDTO clientDTO) {
        ClientNotification clientNotification = ClientNotification.newBuilder()
                .setFirstName(clientDTO.getFirstName())
                .setLastName(clientDTO.getLastName())
                .setEmail(clientDTO.getEmail())
                .build();
        return clientNotification;
    }

    @Override
    protected Callback notificationCallback() {
        return new Callback() {
            @Override
            public void onCompletion(RecordMetadata metadata, Exception exception) {
                if(exception == null) {
                    log.info(metadata.toString());
                } else {
                    log.error(exception.getMessage(), exception.getCause());
                }
            }
        };
    }
}
