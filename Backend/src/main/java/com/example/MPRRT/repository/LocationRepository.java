package com.example.MPRRT.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import com.example.MPRRT.entity.Location;

public interface LocationRepository extends JpaRepository<Location, Long> {
}