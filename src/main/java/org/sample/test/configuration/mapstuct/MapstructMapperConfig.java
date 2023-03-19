package org.sample.test.configuration.mapstuct;

import org.mapstruct.*;

@MapperConfig(componentModel = "spring",
  collectionMappingStrategy = CollectionMappingStrategy.ACCESSOR_ONLY,
  nullValueCheckStrategy = NullValueCheckStrategy.ALWAYS,
  nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE,
  nullValueMappingStrategy = NullValueMappingStrategy.RETURN_DEFAULT
)
public interface MapstructMapperConfig {

}
