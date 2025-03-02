package org.rgz_ttt.juntask.service;

import lombok.RequiredArgsConstructor;
import org.rgz_ttt.juntask.dto.SubscriptionsDto;
import org.rgz_ttt.juntask.mapper.SubscriptionsMapper;
import org.rgz_ttt.juntask.model.Subscriptions;
import org.rgz_ttt.juntask.repository.UserRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class SubscriptionService {

    private static final Logger logger = LoggerFactory.getLogger(SubscriptionService.class);

    private final UserRepository userRepository;
    private final SubscriptionRepository subscriptionRepository;
    private final SubscriptionsMapper subscriptionsMapper;

    public Optional<SubscriptionsDto> addSubscription(Long userId, SubscriptionsDto subscriptionDto) {
        logger.info("Добавление подписки для пользователя с ID {}: {}", userId, subscriptionDto);
        return userRepository.findById(userId)
                .map(user -> {
                    Subscriptions subscription = subscriptionsMapper.toEntity(subscriptionDto);
                    subscription.setUser(user);
                    Subscriptions savedSubscription = subscriptionRepository.save(subscription);
                    logger.debug("Подписка успешно добавлена: {}", savedSubscription);
                    return subscriptionsMapper.toDto(savedSubscription);
                });
    }

    public List<SubscriptionsDto> getSubscriptionsByUserId(Long userId) {
        logger.info("Получение подписок для пользователя с ID: {}", userId);
        return subscriptionRepository.findByUserId(userId).stream()
                .map(subscriptionsMapper::toDto)
                .collect(Collectors.toList());
    }

    public void deleteSubscription(Long userId, Long subscriptionId) {
        logger.info("Удаление подписки с ID {} для пользователя с ID {}", subscriptionId, userId);
        subscriptionRepository.deleteById(subscriptionId);
        logger.debug("Подписка с ID {} успешно удалена", subscriptionId);
    }

    public List<SubscriptionsDto> getTop3Subscriptions() {
        logger.info("Получение топ-3 подписок");
        return subscriptionRepository.findTop3ByOrderByServiceName().stream()
                .map(subscriptionsMapper::toDto)
                .collect(Collectors.toList());
    }
}