package com.example.demodocker2.announcement.repository;
import com.example.demodocker2.announcement.model.AnnouncementEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AnnouncementEntityRepository extends JpaRepository<AnnouncementEntity, Long> {
}
