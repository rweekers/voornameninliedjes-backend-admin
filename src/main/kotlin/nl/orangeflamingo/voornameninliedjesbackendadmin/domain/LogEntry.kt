package nl.orangeflamingo.voornameninliedjesbackendadmin.domain

import com.fasterxml.jackson.annotation.JsonFormat
import java.time.Instant

data class LogEntry(

        @JsonFormat(pattern = "yyyy-MM-dd'T'HH:mm:ss.SSSZ", timezone = "Europe/Amsterdam")
        val date: Instant = Instant.now(),
        val user: String
)