package fr.stephanecastrec.designpatterns

import fr.stephanecastrec.designpatterns.patterns.creation.factory.TransportFactory
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.ui.Model
import org.springframework.web.bind.annotation.*
import org.springframework.web.server.ResponseStatusException

@RestController
@RequestMapping("/creation")
class CreationController {

    @GetMapping("/builder")
    fun builderPattern(model: Model): ResponseEntity<Unit> {
        return ResponseEntity.ok().build()
    }

    @GetMapping("/factory")
    fun factoryPattern(@RequestParam type: String) = TransportFactory.Factory.create(type).drive()
}
