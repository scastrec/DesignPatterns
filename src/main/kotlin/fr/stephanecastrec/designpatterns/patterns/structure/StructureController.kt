package fr.stephanecastrec.designpatterns.patterns.structure

import fr.stephanecastrec.designpatterns.patterns.creation.builder.Car
import fr.stephanecastrec.designpatterns.patterns.creation.factory.TransportFactory
import fr.stephanecastrec.designpatterns.patterns.creation.singleton.Session
import fr.stephanecastrec.designpatterns.patterns.structure.adaptor.*
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.ui.Model
import org.springframework.web.bind.annotation.*
import org.springframework.web.server.ResponseStatusException

@RestController
@RequestMapping("/structure")
class StructureController {

    @GetMapping("/adaptor")
    fun adaptorPattern(
        @RequestParam(required = true) holeRadius: Double,
        @RequestParam(required = false) roundRadius: Double?,
        @RequestParam(required = false) squareWidth: Double?): Boolean {
            var objectToInsert: RoundPeg
            if (roundRadius != null) {
                objectToInsert = RoundPegimpl(roundRadius)
            } else if (squareWidth != null) {
                objectToInsert = RoundSquareAdaptor(SquarePeg(squareWidth))
            } else {
                throw UnsupportedOperationException()
            }
        return RoundHole(holeRadius).fits(objectToInsert)
    }

}
