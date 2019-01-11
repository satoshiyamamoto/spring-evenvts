package sandbox.springevents

import org.slf4j.LoggerFactory
import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication
import org.springframework.context.ApplicationEventPublisher
import org.springframework.context.event.EventListener
import org.springframework.stereotype.Component
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RestController
import java.util.*

@SpringBootApplication
class SpringEventsApplication

fun main(args: Array<String>) {
    runApplication<SpringEventsApplication>(*args)
}

@RestController
class Publisher(val publisher: ApplicationEventPublisher) {

    @GetMapping("/")
    fun publish() = publisher.publishEvent(Event("request", UUID.randomUUID().toString()))
}

@Component
class Subscriber {
    private val logger = LoggerFactory.getLogger(Subscriber::class.java)

    @EventListener
    fun subscribe(event: Event) = logger.info(event.toString())
}

data class Event(val type: String, val data: Any)