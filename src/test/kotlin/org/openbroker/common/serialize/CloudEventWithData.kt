package org.openbroker.common.serialize

import com.fasterxml.jackson.module.kotlin.jacksonObjectMapper
import com.fasterxml.jackson.module.kotlin.readValue
import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Test
import org.openbroker.se.privateunsecuredloan.TestObjectsJson
import org.openbroker.cloudevents.CloudEvent
import org.openbroker.cloudevents.cloudEvent
import org.openbroker.common.OpenBrokerEvent
import org.openbroker.se.privateunsecuredloan.events.ApplicationCreated

class CloudEventWithData {
    @Test
    fun testCloudEventWithSameData() {
        val event: CloudEvent<ApplicationCreated> = cloudEvent(TestObjectsJson.fullApplicationCreatedJson)
        val eventErasedType: CloudEvent<*> = event.copy()
        val eventRestoredType: CloudEvent<OpenBrokerEvent> = eventErasedType.withData(event.data!!)
        assertEquals(eventRestoredType.data, event.data)
    }

    @Test
    fun testCloudEventWithErasedTypeViaJacksonData() {
        val event: CloudEvent<ApplicationCreated> = cloudEvent(TestObjectsJson.fullApplicationCreatedJson)
        val serialized: String = jacksonObjectMapper().writeValueAsString(event)
        val deserialized: CloudEvent<*> = jacksonObjectMapper().readValue(serialized)
        val eventRestoredType: CloudEvent<OpenBrokerEvent> = deserialized.withData(event.data!!)
        assertEquals(eventRestoredType.data, event.data)
    }
}