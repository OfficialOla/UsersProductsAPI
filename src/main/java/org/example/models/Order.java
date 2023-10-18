package org.example.models;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.OneToOne;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
@Table(name = "orders")
@Entity
@Data
public class Order {
    @Id
    private int orderId;
    @OneToOne
    private Product productId;
    @OneToOne
    private User userId;

}
