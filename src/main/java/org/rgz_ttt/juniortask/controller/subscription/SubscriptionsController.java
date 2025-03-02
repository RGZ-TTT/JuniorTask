package org.rgz_ttt.juntask.controller.subscription;

import lombok.RequiredArgsConstructor;
import org.rgz_ttt.juntask.dto.SubscriptionsDto;
import org.rgz_ttt.juntask.service.SubscriptionService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController("/users/{userId}/subscriptions")
@RequiredArgsConstructor
public class SubscriptionsController {

    private static final Logger logger = LoggerFactory.getLogger(SubscriptionsController.class);

    private SubscriptionService subscriptionService;

    @PostMapping
    public Optional<SubscriptionsDto> addSubscription(@PathVariable Long userId,
                                                      @RequestBody SubscriptionsDto subscriptionDTO) {
        logger.info("Получен запрос на добавление подписки для пользователя с ID {}: {}", userId, subscriptionDTO);
        return subscriptionService.addSubscription(userId, subscriptionDTO);
    }

    @GetMapping
    public List<SubscriptionsDto> getSubscriptions(@PathVariable Long userId) {
        logger.info("Получен запрос на получение подписок для пользователя с ID: {}", userId);
        return subscriptionService.getSubscriptionsByUserId(userId);
    }

    @DeleteMapping("/{subscriptionId}")
    public ResponseEntity<Void> deleteSubscription(
            @PathVariable Long userId,
            @PathVariable Long subscriptionId) {
        logger.info("Получен запрос на удаление подписки с ID {} для пользователя с ID {}", subscriptionId, userId);
        subscriptionService.deleteSubscription(userId, subscriptionId);
        return ResponseEntity.noContent().build();
    }
}
