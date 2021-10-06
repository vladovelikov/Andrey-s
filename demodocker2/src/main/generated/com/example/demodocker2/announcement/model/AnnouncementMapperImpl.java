package com.example.demodocker2.announcement.model;

import javax.annotation.processing.Generated;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    date = "2021-10-06T20:41:48+0300",
    comments = "version: 1.3.1.Final, compiler: javac, environment: Java 11.0.6 (JetBrains s.r.o)"
)
public class AnnouncementMapperImpl implements AnnouncementMapper {

    @Override
    public Announcement mapAnnouncementDtoToEntity(AnnouncementDto announcementDto) {
        if ( announcementDto == null ) {
            return null;
        }

        Announcement announcement = new Announcement();

        announcement.setId( announcementDto.getId() );
        announcement.setCreatedOn( announcementDto.getCreatedOn() );
        announcement.setUpdatedOn( announcementDto.getUpdatedOn() );
        announcement.setTitle( announcementDto.getTitle() );
        announcement.setDescription( announcementDto.getDescription() );

        return announcement;
    }

    @Override
    public AnnouncementDto mapAnnouncementToDto(Announcement announcementEntity) {
        if ( announcementEntity == null ) {
            return null;
        }

        AnnouncementDto announcementDto = new AnnouncementDto();

        announcementDto.setId( announcementEntity.getId() );
        announcementDto.setCreatedOn( announcementEntity.getCreatedOn() );
        announcementDto.setUpdatedOn( announcementEntity.getUpdatedOn() );
        announcementDto.setTitle( announcementEntity.getTitle() );
        announcementDto.setDescription( announcementEntity.getDescription() );

        return announcementDto;
    }
}
