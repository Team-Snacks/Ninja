package com.example.demo.repository;
import com.example.demo.entity.UserWidget;
import com.example.demo.entity.UserWidgetId;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface UserWidgetRepository extends JpaRepository<UserWidget, UserWidgetId> {
    UserWidget save(UserWidget userEntity);

    @Query(value = "SELECT * FROM USER_WIDGET WHERE USER_ID = ?1", nativeQuery = true)
    UserWidget[] findWidgets(Long id);
}
