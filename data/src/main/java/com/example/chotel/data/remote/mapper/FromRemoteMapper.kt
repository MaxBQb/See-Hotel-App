package com.example.chotel.data.remote.mapper

import com.example.chotel.data.remote.RemoteDTO

fun interface FromRemoteMapper<in T : RemoteDTO, out R : com.example.chotel.domain.model.DomainModel> :
    com.example.chotel.domain.model.mapper.Mapper<T, R>
