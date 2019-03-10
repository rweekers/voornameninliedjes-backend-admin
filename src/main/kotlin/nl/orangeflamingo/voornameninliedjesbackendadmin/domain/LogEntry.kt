package nl.orangeflamingo.voornameninliedjesbackendadmin.domain

import java.time.Instant

data class LogEntry(

        val date: Instant = Instant.now(),
        val user: String
)