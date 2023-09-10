package com.example.chotel.presentation.model.mapper

import com.example.chotel.domain.model.DomainModel
import com.example.chotel.domain.model.mapper.Mapper
import com.example.chotel.presentation.model.PresentationDTO

fun interface ToPresentationMapper<in T : DomainModel, out R : PresentationDTO> : Mapper<T, R>
