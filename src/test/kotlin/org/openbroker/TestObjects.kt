package org.openbroker

object TestObjects {
    internal val mainApplicant1 =
        """
        {
            "ssn": "198712167249",
            "phone": "+46705431052",
            "secondaryPhone": [],
            "emailAddress": "anton@zensum.se",
            "employmentStatus": "FULL_TIME",
            "employmentStatusSinceYear": 2017,
            "employmentStatusSinceMonth": 2,
            "dependentChildren": 0,
            "housingType": "RENTED",
            "housingCostPerMonth": 3000,
            "employerName": "Zensum AB",
            "employerPhone": "+46811122233",
            "monthlyIncome": 10000,
            "childSupportReceivedMonthly": null,
            "childSupportPaidMonthly": null,
            "maritalStatus": "COHABITING",
            "bankAccount": {
                "clearingNo": "8888",
                "accountNo": "00000003"
            },
            "citizenships": [
                "SE"
            ],
            "countriesOfResidence": [
                "SE"
            ],
            "taxResidentOf": [
                "SE"
            ],
            "tentativeAddress": {
                "firstName": "Christin",
                "lastName": "Strömberg",
                "address": "Hultom 264",
                "postalCode": "25258",
                "city": "Helsingborg"
            }
        }
		""".trimIndent()

    internal val coApplicant1 =
        """
        {
            "ssn": "198012167241",
            "phone": "+46705431052",
            "secondaryPhone": [],
            "emailAddress": "simon@zensum.se",
            "employmentStatus": "FULL_TIME",
            "employmentStatusSinceYear": 2017,
            "employmentStatusSinceMonth": 2,
            "dependentChildren": 15,
            "housingType": "OWN_APARTMENT",
            "housingCostPerMonth": 5300,
            "employerName": "Zensum AB",
            "employerPhone": "+46811122233",
            "monthlyIncome": 20000,
            "childSupportReceivedMonthly": 700,
            "childSupportPaidMonthly": 3000,
            "maritalStatus": "MARRIED",
            "bankAccount": {
                "clearingNo": "8888",
                "accountNo": "00000003"
            },
            "citizenships": [
                "SE"
            ],
            "countriesOfResidence": [
                "SE"
            ],
            "taxResidentOf": [
                "SE", "BM"
            ],
            "tentativeAddress": {
                "firstName": "Simon",
                "lastName": "Albinsson",
                "address": "Regeringsgatan 52",
                "postalCode": "11111",
                "city": "Stockholm"
            }
        }
		""".trimIndent()

    internal val fullApplicationCreatedJson =
        """
		{
    "cloudEventsVersion" : "0.1",
    "eventType" : "org.open-broker.v0.se.PrivateUnsecuredLoanApplicationCreated",
    "eventTypeVersion" : "v0",
    "source" : "/mycontext",
    "eventID" : "C234-1234-1234",
    "eventTime" : "2018-04-05T17:31:00Z",
    "extensions" : {
      "comExampleExtension" : "value"
    },
    "contentType" : "application/json",
    "data": {
		"application": {
			"applicant": $mainApplicant1,
			"coApplicant": null,
			"existingLoans": [
				{
					"loanAmount": 4000,
					"monthlyPayment": 22,
					"shouldRefinance": true,
					"existingLoanType": "CAR_LOAN",
					"responsibility": "SHARED"
				},
				{
					"loanAmount": 15000,
					"monthlyPayment": 56,
					"shouldRefinance": false,
					"existingLoanType": "STUDENT_LOAN",
					"responsibility": "MAIN_APPLICANT"
				}
			],
			"extensions": {
			    "io.klira.someExtensionProperty": 42,
				"io.klira.someOtherExtensionProperty": 10000
			},
			"loanAmount": 20000,
			"loanPurpose": "OTHER",
			"refinanceAmount": 0,
			"termMonths": 24
		},
		"brokerReference": {
			"issuer": "io.klira",
			"id": "1"
		},
		"dataProtectionContext": "FICTIONAL"
	}
}
	""".trimIndent()

    internal val fullApplicationCreatedWitCoApplicantJson =
        """
		{
    "cloudEventsVersion" : "0.1",
    "eventType" : "org.open-broker.v0.se.PrivateUnsecuredLoanApplicationCreated",
    "eventTypeVersion" : "v0",
    "source" : "/mycontext",
    "eventID" : "C234-1234-1234",
    "eventTime" : "2018-04-05T17:31:00Z",
    "extensions" : {
      "comExampleExtension" : "value"
    },
    "contentType" : "application/json",
    "data": {
		"application": {
			"applicant": $mainApplicant1,
			"coApplicant": $coApplicant1,
			"existingLoans": [
				{
					"loanAmount": 4000,
					"monthlyPayment": 22,
					"shouldRefinance": true,
					"existingLoanType": "CAR_LOAN",
					"responsibility": "SHARED"
				},
				{
					"loanAmount": 15000,
					"monthlyPayment": 56,
					"shouldRefinance": false,
					"existingLoanType": "STUDENT_LOAN",
					"responsibility": "MAIN_APPLICANT"
				}
			],
			"extensions": {
			    "io.klira.someExtensionProperty": 42,
				"io.klira.someOtherExtensionProperty": 10000
			},
			"loanAmount": 20000,
			"loanPurpose": "OTHER",
			"refinanceAmount": 0,
			"termMonths": 24
		},
		"brokerReference": {
			"issuer": "io.klira",
			"id": "1"
		},
		"dataProtectionContext": "FICTIONAL"
	}
}
	""".trimIndent()

    internal val invalidEventType =
        """
{
    "cloudEventsVersion" : "0.1",
    "eventType" : "org.open-broker.v0.se.PrivateUnsecuredLoanApplication",
    "eventTypeVersion" : "v0",
    "source" : "/mycontext",
    "eventID" : "C234-1234-1234",
    "eventTime" : "2018-04-05T17:31:00Z",
    "extensions" : {
      "comExampleExtension" : "value"
    },
    "contentType" : "application/json",
    "data": {
		"application": {
			"applicant": {
				"ssn": "198712167249",
				"phone": "+46705431052",
				"secondaryPhone": [],
				"emailAddress": "anton@zensum.se",
				"employmentStatus": "FULL_TIME",
				"employmentStatusSinceYear": 2017,
				"employmentStatusSinceMonth": 2,
				"dependentChildren": 0,
				"housingType": "RENTED",
				"housingCostPerMonth": 3000,
				"employerName": "Zensum AB",
				"employerPhone": "+46811122233",
				"monthlyIncome": 10000,
				"childSupportReceivedMonthly": null,
				"childSupportPaidMonthly": null,
				"maritalStatus": "COHABITING",
				"bankAccount": {
					"clearingNo": "8888",
					"accountNo": "00000003"
				},
				"citizenships": [
					"SE"
				],
				"countriesOfResidence": [
					"SE"
				],
				"taxResidentOf": [
					"SE"
				],
				"tentativeAddress": {
					"firstName": "Christin",
					"lastName": "Strömberg",
					"address": "Hultom 264",
					"postalCode": "25258",
					"city": "Helsingborg"
				}
			},
			"coApplicant": null,
			"existingLoans": [
				{
					"loanAmount": 4000,
					"monthlyPayment": 22,
					"shouldRefinance": true,
					"existingLoanType": "CAR_LOAN",
					"responsibility": "SHARED"
				},
				{
					"loanAmount": 15000,
					"monthlyPayment": 56,
					"shouldRefinance": false,
					"existingLoanType": "STUDENT_LOAN",
					"responsibility": "MAIN_APPLICANT"
				}
			],
			"extensions": {
			    "io.klira.someExtensionProperty": 42
			},
			"loanAmount": 20000,
			"loanPurpose": "OTHER",
			"refinanceAmount": 0,
			"termMonths": 24
		},
		"brokerReference": {
			"issuer": "io.klira",
			"id": "2"
		},
		"dataProtectionContext": "FICTIONAL"
	}
}
		""".trimIndent()

    internal val acceptOffer =
        """
        {
		    "cloudEventsVersion" : "0.1",
            "eventType" : "org.open-broker.v0.se.PrivateUnsecuredLoanOfferAccepted",
            "eventTypeVersion" : "v0",
            "source" : "/mycontext",
            "eventID" : "C234-1234-1234",
            "eventTime" : "2018-04-05T17:31:00Z",
            "extensions" : {
              "comExampleExtension" : "value"
            },
            "contentType" : "application/json",
            "data": {
			    "brokerReference": {
				    "id": "12345",
					"issuer": "io.klira"
				},
				"bankAccount": {
				    "clearingNo": "8989",
					"accountNo": "123456789"
				},
				"requestedCredit": 25000
			}
        }

		""".trimIndent()

    internal val rejectOffer =
        """
        {
		    "cloudEventsVersion" : "0.1",
            "eventType" : "org.open-broker.v0.se.PrivateUnsecuredLoanOfferRejected",
            "eventTypeVersion" : "v0",
            "source" : "/mycontext",
            "eventID" : "C234-1234-1234",
            "eventTime" : "2018-04-05T17:31:00Z",
            "extensions" : {
              "comExampleExtension" : "value"
            },
            "contentType" : "application/json",
            "data": {
			    "brokerReference": {
				    "id": "12345",
					"issuer": "io.klira"
				}
			}
        }

		""".trimIndent()
}