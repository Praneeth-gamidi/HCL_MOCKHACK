package com.example.MPRRT.entity;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import lombok.Data; 
import jakarta.persistence.Table;
import jakarta.persistence.Id;
import jakarta.persistence.GenerationType;
@Entity
@Table(name = "work_orders")
@Data 
@NoArgsConstructor
@AllArgsConstructor
public class WorkOrder {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
}

