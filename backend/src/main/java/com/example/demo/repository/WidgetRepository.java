package com.example.demo.repository;

import com.example.demo.entity.Widget;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface WidgetRepository extends JpaRepository<Widget, Long> {


    Widget save(Widget widget);
    @Query(value = "SELECT * FROM WIDGET WHERE NAME = ?1", nativeQuery = true)
    Widget findByName(String name);

    @Query(value = "SELECT * FROM WIDGET WHERE ID = ?1", nativeQuery = true)
    Widget findByWidgetId(Long id);
}
