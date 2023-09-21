package com.example.egttask.repository;

import com.example.egttask.model.entity.RequestHistoryEntity;
import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface RequestHistoryRepository extends JpaRepository<RequestHistoryEntity, Long> {

    Optional<RequestHistoryEntity> findFirstByRequestIdAndClientId(String requestId, String clientId);
}
