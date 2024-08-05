
        package com.producto.pruebatecnica.service;

import com.producto.pruebatecnica.model.Product;
import com.producto.pruebatecnica.model.Statistics;
import com.producto.pruebatecnica.repository.StatisticsRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class StatisticsService {
    @Autowired
    private StatisticsRepository statisticsRepository;

    @Async
    public void updateStatistics(Product product) {
        Statistics stats = statisticsRepository.findByCategory(product.getCategory());
        if (stats == null) {
            stats = new Statistics();
            stats.setCategory(product.getCategory());
            stats.setProductCount(1L);
        } else {
            stats.setProductCount(stats.getProductCount() + 1);
        }
        statisticsRepository.save(stats);
    }

    public List<Statistics> getAllStatistics() {
        return statisticsRepository.findAll();
    }
}
