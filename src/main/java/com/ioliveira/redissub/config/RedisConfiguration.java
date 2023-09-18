package com.ioliveira.redissub.config;

import com.ioliveira.redissub.subscribers.RedisMessageSubscriber;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.redis.connection.lettuce.LettuceConnectionFactory;
import org.springframework.data.redis.listener.ChannelTopic;
import org.springframework.data.redis.listener.RedisMessageListenerContainer;
import org.springframework.data.redis.listener.adapter.MessageListenerAdapter;

@Configuration
public class RedisConfiguration {

    @Bean
    public RedisMessageListenerContainer redisContainer() {
        RedisMessageListenerContainer container = new RedisMessageListenerContainer();
        container.setConnectionFactory(lettuceConnectionFactory());

        //Multiple channels
        container.addMessageListener(
                new MessageListenerAdapter(new RedisMessageSubscriber()),
                new ChannelTopic("channel1")
        );

        container.addMessageListener(
                new MessageListenerAdapter(new RedisMessageSubscriber()),
                new ChannelTopic("channel2")
        );

        return container;
    }

    @Bean
    LettuceConnectionFactory lettuceConnectionFactory() {
        return new LettuceConnectionFactory();
    }

}
