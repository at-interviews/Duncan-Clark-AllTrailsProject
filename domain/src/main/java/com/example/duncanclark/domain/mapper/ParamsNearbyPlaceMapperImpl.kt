package com.example.duncanclark.domain.mapper

import com.example.duncanclark.domain.common.mapper.Mapper
import com.example.duncanclark.domain.model.params.Center
import com.example.duncanclark.domain.model.params.Circle
import com.example.duncanclark.domain.model.params.FieldMask
import com.example.duncanclark.domain.model.params.IncludedType
import com.example.duncanclark.domain.model.params.LocationRestriction
import com.example.duncanclark.domain.model.params.BodyParamsForNearbyPlaces
import com.example.duncanclark.domain.model.params.ParamsForNearbyPlaces
import com.example.duncanclark.domain.model.params.nearbyPlacesLunch
import javax.inject.Inject

class ParamsNearbyPlaceMapperImpl @Inject constructor()
    : Mapper<Pair<Double, Double>, ParamsForNearbyPlaces> {

    override fun invoke(input: Pair<Double, Double>): ParamsForNearbyPlaces {
        return ParamsForNearbyPlaces(
            fieldMasks = FieldMask.nearbyPlacesLunch(),
            bodyParams = BodyParamsForNearbyPlaces(
                locationRestriction = createLocationRestriction(input),
                includedTypes = IncludedType.nearbyPlacesLunch(),
            )
        )
    }

    private fun createLocationRestriction(latLng: Pair<Double, Double>): LocationRestriction {
        return LocationRestriction(
                Circle(
                    center = Center(
                        latitude = latLng.first,
                        longitude = latLng.second,
                    )
                )
            )
    }
}