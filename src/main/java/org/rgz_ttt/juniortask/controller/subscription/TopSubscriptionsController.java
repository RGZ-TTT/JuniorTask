package org.rgz_ttt.juntask.controller.subscription;

import lombok.RequiredArgsConstructor;
import org.rgz_ttt.juntask.dto.SubscriptionsDto;
import org.rgz_ttt.juntask.service.SubscriptionService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping(("/subscriptions"))
@RequiredArgsConstructor
public class TopSubscriptionsController {

    private static final Logger logger = LoggerFactory.getLogger(TopSubscriptionsController.class);

    private SubscriptionService subscriptionService;

    @GetMapping("/top")
    public ResponseEntity<List<SubscriptionsDto>> getTop3Subscriptions() {
        logger.info("Получен запрос на получение топ-3 подписок");
        List<SubscriptionsDto> topSubscriptions = subscriptionService.getTop3Subscriptions();
        logger.debug("Найдено {} подписок", topSubscriptions.size());
        return ResponseEntity.ok(topSubscriptions);
    }
}
