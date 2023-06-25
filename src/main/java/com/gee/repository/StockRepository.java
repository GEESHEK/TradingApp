package com.gee.repository;

import com.gee.model.Stock;
import org.springframework.data.jpa.repository.JpaRepository;

public interface StockRepository extends JpaRepository<Stock,Integer> {

    boolean existsStockById(Integer id);

}
