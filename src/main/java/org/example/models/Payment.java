package org.example.models;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.OneToOne;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
@AllArgsConstructor
@NoArgsConstructor
@Data
@Table(name = "payments")
@Entity
public class Payment {
    @Id
    private int paymentId;
    @OneToOne
    private Order orderId;
    private double paymentAmount;
    private LocalDateTime paymentTime;

}
