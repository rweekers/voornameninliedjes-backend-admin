package nl.orangeflamingo.voornameninliedjesbackendadmin.config

import nl.orangeflamingo.voornameninliedjesbackendadmin.domain.Song
import nl.orangeflamingo.voornameninliedjesbackendadmin.domain.User
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.context.event.ApplicationReadyEvent
import org.springframework.context.annotation.Configuration
import org.springframework.context.event.EventListener
import org.springframework.data.mongodb.core.MongoTemplate
import org.springframework.data.mongodb.core.convert.MongoConverter
import org.springframework.data.mongodb.core.index.MongoPersistentEntityIndexResolver

@Configuration
class MongoConfig {

    @Autowired
    private lateinit var mongoTemplate: MongoTemplate
    @Autowired
    private lateinit var mongoConverter: MongoConverter

    @EventListener(ApplicationReadyEvent::class)
    fun initIndicesAfterStartup() {
        val resolver = MongoPersistentEntityIndexResolver(mongoConverter.mappingContext)

        val indexOpsSong = mongoTemplate.indexOps(Song::class.java)
        resolver.resolveIndexFor(Song::class.java).forEach { indexOpsSong.ensureIndex(it) }

        val indexOpsUser = mongoTemplate.indexOps(User::class.java)
        resolver.resolveIndexFor(User::class.java).forEach { indexOpsUser.ensureIndex(it) }
    }
}