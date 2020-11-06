package com.csp.seckill.mq;

import com.csp.seckill.consts.Constants;
import lombok.extern.slf4j.Slf4j;
import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.kafka.support.Acknowledgment;
import org.springframework.stereotype.Component;

/**
 * @description: 消费者
 * @author: csp52872
 * @date: 2020/10/31
 */
@Slf4j
@Component
public class MessageConsumer {

    @KafkaListener(topics = Constants.TOPIC, containerFactory = "ackContainerFactory")
    public void listen(ConsumerRecord record, Acknowledgment acknowledgment) {
        try {
            String message = record.value().toString();
            log.info("message={}", message);
        } catch (Exception e) {
            log.error("handler listen error", e);
        } finally {
            // 手动提交 offset
            acknowledgment.acknowledge();
        }
    }
}
