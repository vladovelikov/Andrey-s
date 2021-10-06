package com.example.demodocker2;
import com.example.demodocker2.announcement.model.AnnouncementEntity;
import com.example.demodocker2.announcement.repository.AnnouncementEntityRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import java.time.Instant;

@Component
public class TabulaApplicationInit implements CommandLineRunner {

    private final AnnouncementEntityRepository announcementRepository;

    @Autowired
    public TabulaApplicationInit(AnnouncementEntityRepository announcementRepository) {
        this.announcementRepository = announcementRepository;
    }


    @Override
    public void run(String... args) throws Exception {
        if (announcementRepository.count() == 0) {
            AnnouncementEntity announcementEntity = new AnnouncementEntity();
            announcementEntity.setTitle("Department Meeting");
            announcementEntity.setDescription("Monthly meeting");
            announcementEntity.setCreatedOn(Instant.now());
            announcementEntity.setUpdatedOn(Instant.now());
            this.announcementRepository.save(announcementEntity);
        }
    }
}
