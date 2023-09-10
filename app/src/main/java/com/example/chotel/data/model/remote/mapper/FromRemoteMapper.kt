package com.example.chotel.data.model.remote.mapper

import com.example.chotel.data.model.remote.RemoteDTO
import com.example.chotel.domain.model.DomainModel
import com.example.chotel.domain.model.mapper.Mapper

fun interface FromRemoteMapper<in T : RemoteDTO, out R : DomainModel> : Mapper<T, R>
