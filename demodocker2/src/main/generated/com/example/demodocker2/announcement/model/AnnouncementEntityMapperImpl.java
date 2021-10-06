package com.example.demodocker2.announcement.model;

import javax.annotation.processing.Generated;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    date = "2021-10-06T20:46:54+0300",
    comments = "version: 1.3.1.Final, compiler: javac, environment: Java 11.0.6 (JetBrains s.r.o)"
)
public class AnnouncementEntityMapperImpl implements AnnouncementEntityMapper {

    @Override
    public AnnouncementEntity mapAnnouncementDtoToEntity(AnnouncementEntityDto announcementDto) {
        if ( announcementDto == null ) {
            return null;
        }

        AnnouncementEntity announcementEntity = new AnnouncementEntity();

        announcementEntity.setId( announcementDto.getId() );
        announcementEntity.setCreatedOn( announcementDto.getCreatedOn() );
        announcementEntity.setUpdatedOn( announcementDto.getUpdatedOn() );
        announcementEntity.setTitle( announcementDto.getTitle() );
        announcementEntity.setDescription( announcementDto.getDescription() );

        return announcementEntity;
    }

    @Override
    public AnnouncementEntityDto mapAnnouncementToDto(AnnouncementEntity announcementEntity) {
        if ( announcementEntity == null ) {
            return null;
        }

        AnnouncementEntityDto announcementEntityDto = new AnnouncementEntityDto();

        announcementEntityDto.setId( announcementEntity.getId() );
        announcementEntityDto.setCreatedOn( announcementEntity.getCreatedOn() );
        announcementEntityDto.setUpdatedOn( announcementEntity.getUpdatedOn() );
        announcementEntityDto.setTitle( announcementEntity.getTitle() );
        announcementEntityDto.setDescription( announcementEntity.getDescription() );

        return announcementEntityDto;
    }
}
