package github.com.azkalum.converter;

import jakarta.persistence.AttributeConverter;
import jakarta.persistence.Converter;

import java.time.LocalTime;

@Converter
public class LocalTimeConverter implements AttributeConverter<LocalTime, String> {

    @Override
    public String convertToDatabaseColumn(LocalTime localTime) {
        return localTime.toString();
    }

    @Override
    public LocalTime convertToEntityAttribute(String timeString) {
        return LocalTime.parse(timeString);
    }
}
