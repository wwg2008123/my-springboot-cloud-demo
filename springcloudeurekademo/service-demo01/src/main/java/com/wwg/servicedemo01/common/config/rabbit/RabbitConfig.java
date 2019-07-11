/*
package com.wwg.servicedemo01.common.config.rabbit;

import org.springframework.amqp.core.*;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class RabbitConfig {

    final static String queueOneName = "oneMessageQueue";
    final static String queueTwoName = "twoMessageQueue";
    final static String directExchangeName = "DirectExchange";
    final static String topicExchangeName = "TopicExchange";

    @Bean(name = queueOneName)
    public Queue queueOne() {
        return new Queue(queueOneName);
    }

    @Bean(name = queueTwoName)
    public Queue queueTwo() {
        return new Queue(queueTwoName);
    }

    @Bean
    public DirectExchange directExchange() {
        return new DirectExchange(directExchangeName);
    }

    @Bean
    public TopicExchange topicExchange() {
        return new TopicExchange(topicExchangeName);
    }

    @Bean
    Binding bindingDirectExchangeQueue(@Qualifier(queueOneName) Queue queueMessage, DirectExchange directExchange) {
        return BindingBuilder.bind(queueMessage).to(directExchange).with("direct.message");
    }

    @Bean
    Binding bindingTopicExchangeQueue(@Qualifier(queueTwoName) Queue queue, TopicExchange topicExchange) {
        return BindingBuilder.bind(queue).to(topicExchange).with("topic.order");
    }


}
*/
