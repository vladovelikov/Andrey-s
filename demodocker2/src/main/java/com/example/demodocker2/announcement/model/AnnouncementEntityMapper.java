package com.example.demodocker2.announcement.model;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

@Mapper
public interface AnnouncementEntityMapper {

    AnnouncementEntityMapper INSTANCE = Mappers.getMapper(AnnouncementEntityMapper.class);

    AnnouncementEntity mapAnnouncementDtoToEntity(AnnouncementEntityDto announcementDto);

    AnnouncementEntityDto mapAnnouncementToDto(AnnouncementEntity announcementEntity);
}
