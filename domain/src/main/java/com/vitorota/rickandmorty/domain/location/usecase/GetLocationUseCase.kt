package com.vitorota.rickandmorty.data.location.usecase

import com.vitorota.rickandmorty.data.UseCase
import com.vitorota.rickandmorty.data.location.entity.Location
import com.vitorota.rickandmorty.data.location.repository.LocationRepository
import com.vitorota.rickandmorty.data.location.usecase.GetLocationUseCase.Params

/**
 *
 * @author Vitor Ota
 * @since 17/01/2019
 */
class GetLocationUseCase(val repo: LocationRepository) : UseCase<Location, Params> {
    override fun execute(params: Params): Location = repo.get(params.id)

    data class Params(val id: Int)
}