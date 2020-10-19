package com.example.compasso.util;

import java.time.LocalDateTime;
import java.time.ZoneOffset;
import java.util.Date;

import ma.glasnost.orika.MappingContext;
import ma.glasnost.orika.converter.BidirectionalConverter;
import ma.glasnost.orika.metadata.Type;

public class LocalDateTimeConverter extends BidirectionalConverter<Date, LocalDateTime> {
	
	@Override
    public LocalDateTime convertTo(Date source, Type<LocalDateTime> destinationType, MappingContext mappingContext) {
        return LocalDateTime.ofInstant(source.toInstant(), ZoneOffset.UTC);
    }

    @Override
    public Date convertFrom(LocalDateTime source, Type<Date> destinationType, MappingContext mappingContext) {
        return Date.from(source.toInstant(ZoneOffset.UTC));
    }


}
