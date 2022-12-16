package com.library.converter;


import com.library.status.YnStatus;

import javax.persistence.AttributeConverter;
import javax.persistence.Converter;

@Converter(autoApply = true)
public class YnStatusConverter implements AttributeConverter<YnStatus, String> {

    // JPA EnumType 적용 Converter

    @Override
    public String convertToDatabaseColumn(YnStatus attribute) {
        return String.valueOf(attribute);
    }

    @Override
    public YnStatus convertToEntityAttribute(String dbData) {
        return YnStatus.valueOf(dbData);
    }

}
