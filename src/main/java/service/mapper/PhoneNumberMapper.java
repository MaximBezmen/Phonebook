package service.mapper;

import entity.PhoneNumber;
import service.dto.PhoneNumberDto;

import java.util.ArrayList;
import java.util.List;

public class PhoneNumberMapper {
    public PhoneNumberDto toDto(PhoneNumber entity) {
        PhoneNumberDto dto;
        if (entity == null) {
            return null;
        }
        dto = new PhoneNumberDto();
        dto.setId(entity.getId());
        dto.setNumber(entity.getNumber());
        dto.setCodeOfCountry(entity.getCodeOfCountry());
        dto.setOperatorCode(entity.getOperatorCode());
        dto.setComment(entity.getComment());
        dto.setType(entity.getType());
        return dto;
    }

    public PhoneNumber toEntity(PhoneNumberDto dto) {
        PhoneNumber entity;
        if (dto == null) {
            return null;
        }
        entity = new PhoneNumber();
        entity.setId(dto.getId());
        entity.setNumber(dto.getNumber());
        entity.setCodeOfCountry(dto.getCodeOfCountry());
        entity.setOperatorCode(dto.getOperatorCode());
        entity.setComment(dto.getComment());
        entity.setType(dto.getType());
        return entity;
    }

    public List<PhoneNumberDto> toDtos(List<PhoneNumber> entities) {
        List<PhoneNumberDto> dtos;
        if (entities.isEmpty() || entities == null) {
            return null;
        }
        dtos = new ArrayList<>();
        for (PhoneNumber phoneNumber : entities) {
            dtos.add(toDto(phoneNumber));
        }
        return dtos;
    }

    public List<PhoneNumber> toEntities(List<PhoneNumberDto> dtos) {
        List<PhoneNumber> entities;
        if (dtos.isEmpty() || dtos == null) {
            return null;
        }
        entities = new ArrayList<>();
        for (PhoneNumberDto dto : dtos) {
            entities.add(toEntity(dto));
        }
        return entities;
    }
}

