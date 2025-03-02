package org.rgz_ttt.juntask.repository;

import org.rgz_ttt.juntask.model.Subscriptions;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface SubscriptionRepository extends CrudRepository<Subscriptions, Long> {

    List<Subscriptions> findByUserId(Long userId);

    @Query(nativeQuery = true, value = """
            SELECT s FROM Subscription s
            ORDER BY s.serviceName ASC LIMIT 3""")
    List<Subscriptions> findTop3ByOrderByServiceName();
}
