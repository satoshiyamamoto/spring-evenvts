package sandbox.springevents

import org.slf4j.LoggerFactory
import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication
import org.springframework.context.ApplicationEventPublisher
import org.springframework.context.event.EventListener
import org.springframework.stereotype.Component
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RestController

@SpringBootApplication
class SpringEventsApplication

fun main(args: Array<String>) {
    runApplication<SpringEventsApplication>(*args)
}

@RestController
class Controller(
        val publisher: ApplicationEventPublisher
) {

    @GetMapping("/")
    fun handleRequest() {
        publisher.publishEvent(Event("request", "Hello"))
    }
}

@Component
class EventHandler {
    private val logger = LoggerFactory.getLogger(EventHandler::class.java)

    @EventListener
    fun handleEvent(event: Event) = logger.info(event.toString())
}

data class Event(val type: String, val message: String)