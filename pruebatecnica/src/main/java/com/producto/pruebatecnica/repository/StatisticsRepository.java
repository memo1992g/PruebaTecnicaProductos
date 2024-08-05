
package com.producto.pruebatecnica.repository;

import com.producto.pruebatecnica.model.Statistics;
import org.springframework.data.jpa.repository.JpaRepository;

public interface StatisticsRepository extends JpaRepository<Statistics, Long> {
    Statistics findByCategory(String category);
}
