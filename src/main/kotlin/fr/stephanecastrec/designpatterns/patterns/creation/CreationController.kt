package fr.stephanecastrec.designpatterns

import fr.stephanecastrec.designpatterns.patterns.creation.builder.Car
import fr.stephanecastrec.designpatterns.patterns.creation.factory.TransportFactory
import fr.stephanecastrec.designpatterns.patterns.creation.singleton.Session
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.ui.Model
import org.springframework.web.bind.annotation.*
import org.springframework.web.server.ResponseStatusException

@RestController
@RequestMapping("/creation")
class CreationController {

    @GetMapping("/builder")
    fun builderPattern(
        @RequestParam(required = false) color: String,
        @RequestParam(required = false) seats: Short)= Car.Builder().color(color).seats(seats).build()


    @GetMapping("/factory")
    fun factoryPattern(@RequestParam type: String) = TransportFactory.Factory.create(type).drive()

    @GetMapping("/singleton")
    fun singletonPatternGet( @RequestParam key: String) = Session.getData(key)

    @PostMapping("/singleton")
    fun singletonPatternAddValue(
        @RequestParam key: String,
        @RequestParam value: String) = Session.setData(key, value)

}
