package com.app.springbootmavenapp.mappers;

import com.app.springbootmavenapp.dto.EntityDto;
import com.app.springbootmavenapp.model.EntityBase;

public interface CustomMapper<D extends EntityDto, E extends EntityBase> {

	public E toEntity(D entityDto);

	public D toDto(E entity);

}
