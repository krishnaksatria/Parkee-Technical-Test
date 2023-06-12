package com.id.parkee.di

import com.id.parkee.room.data.RoomRepository
import com.id.parkee.room.domain.RoomRepositoryContract
import org.koin.dsl.module

val roomRepositoryCoreModule = module {
    single<RoomRepositoryContract> {
        RoomRepository(
            roomDao = get<ParkeeDatabase>().roomDao()
        )
    }
}
