package org.rgz_ttt.juntask.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Entity
public class Subscriptions {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @Column(name = "sub_name", length = 64, nullable = false, unique = true)
    private String serviceName;

    @ManyToOne
    @JoinColumn(name = "user_id")
    private User user;
}
