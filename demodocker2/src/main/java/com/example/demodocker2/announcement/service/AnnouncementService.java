package com.example.demodocker2.announcement.service;
import com.example.demodocker2.announcement.model.AnnouncementEntityDto;
import com.example.demodocker2.announcement.model.AnnouncementEntityMapper;
import com.example.demodocker2.announcement.repository.AnnouncementEntityRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class AnnouncementService {

    private final AnnouncementEntityRepository announcementRepository;

    @Autowired
    public AnnouncementService(AnnouncementEntityRepository announcementRepository) {
        this.announcementRepository = announcementRepository;
    }

    public List<AnnouncementEntityDto> findAll() {
        return this.announcementRepository.findAll()
                .stream().map(AnnouncementEntityMapper.INSTANCE::mapAnnouncementToDto)
                .collect(Collectors.toList());
    }

}
