package com.example.chotel.presentation.model.mapper

import com.example.chotel.presentation.model.PresentationDTO

fun interface ToPresentationMapper<in T : com.example.chotel.domain.model.DomainModel, out R : PresentationDTO> :
    com.example.chotel.domain.model.mapper.Mapper<T, R>
