package io.klira.openbroker.meta

import com.fasterxml.jackson.databind.JsonNode
import io.klira.cloudevents.CloudEvent
import io.klira.cloudevents.serialize.cloudEventJson
import io.klira.openbroker.model.Address
import io.klira.openbroker.model.Applicant
import io.klira.openbroker.model.Application
import io.klira.openbroker.events.ApplicationCreated
import io.klira.openbroker.model.BankAccount
import io.klira.openbroker.model.DataProtectionContext
import io.klira.openbroker.model.EmploymentStatus
import io.klira.openbroker.model.ExistingLoan
import io.klira.openbroker.model.ExistingLoanType
import io.klira.openbroker.model.HousingType
import io.klira.openbroker.model.MaritalStatus
import io.klira.openbroker.model.Reference
import io.klira.openbroker.model.Responsibility
import io.klira.openbroker.serialize.parseEvent
import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Assertions.assertNotNull
import org.junit.jupiter.api.Assertions.assertTrue
import org.junit.jupiter.api.Test

class SerializationTest {

    @Test
    fun testSerializeOpenBrokerApplicationCreated() {
        val applicant = Applicant(
            ssn = "198902071312",
            phone = "+46735711057",
            employmentStatus = EmploymentStatus.FULL_TIME,
            employmentStatusSinceYear = 2017,
            employmentStatusSinceMonth = 2,
            dependentChildren = 0,
            housingType = HousingType.RENTED,
            employerName = "Zensum AB",
            employerPhone = "+46811122233",
            monthlyIncome = 10_000,
            housingCostPerMonth = 3_000,
            maritalStatus = MaritalStatus.COHABITING,
            bankAccount = BankAccount("8888", "00000003"),
            citizenships = listOf("SE"),
            countriesOfResidence = listOf("SE"),
            taxResidentOf = listOf("SE"),
            tentativeAddress = Address(
                firstName = "Christin",
                lastName = "Strömberg",
                address = "Hultom 264",
                postalCode = "25258",
                city = "Helsingborg"
            )
        )
        val app = Application(
            applicant = applicant,
            existingLoans = listOf(
                ExistingLoan(4000, 22, true, ExistingLoanType.CAR_LOAN, Responsibility.SHARED),
                ExistingLoan(15_000, 56, false, ExistingLoanType.STUDENT_LOAN, Responsibility.MAIN_APPLICANT)
            ),
            loanAmount = 20_000,
            termMonths = 24,
            extensions = null
        )
        val appCreated = ApplicationCreated(
            application = app,
            brokerReference = Reference("1", "io.klira"),
            dataProtectionContext = DataProtectionContext.FICTIONAL
        )

        val jsonEvent: CloudEvent<JsonNode> = cloudEventJson(
            event = appCreated,
            eventType = EventTypePrivateUnsecuredLoan.APPLICATION_CREATED.toString(),
            eventTypeVersion = "v0",
            source = "https://some-domain.io"
        )

        assertNotNull(jsonEvent.data)
        assertTrue(jsonEvent.data!!.toString().isNotEmpty())
    }

    @Test
    fun testDeserializeOpenBrokerApplicationCreate() {
        val event: CloudEvent<JsonNode> = cloudEventJson(TestObjects.fullApplicationCreatedJson)
        val applicationCreated: ApplicationCreated = parseEvent<ApplicationCreated>(event)!!
        assertEquals("1", applicationCreated.brokerReference.id)
        assertEquals("Strömberg", applicationCreated.application.applicant.tentativeAddress?.lastName)
    }
}