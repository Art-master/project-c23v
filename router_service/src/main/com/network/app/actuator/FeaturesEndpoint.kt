package com.network.app.actuator

import org.springframework.boot.actuate.endpoint.annotation.*
import org.springframework.stereotype.Component
import java.util.concurrent.ConcurrentHashMap

@Component
@Endpoint(id = "features")
class FeaturesEndpoint {
    private val features: MutableMap<String, Feature> = ConcurrentHashMap()
    @ReadOperation
    fun features(): Map<String, Feature> {
        return features
    }

    @ReadOperation
    fun feature(@Selector name: String): Feature? {
        return features[name]
    }

    @WriteOperation
    fun configureFeature(@Selector name: String, feature: Feature) {
        features[name] = feature
    }

    @DeleteOperation
    fun deleteFeature(@Selector name: String) {
        features.remove(name)
    }

    class Feature {
        var enabled: Boolean? = null
    }
}