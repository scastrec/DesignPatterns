package fr.stephanecastrec.designpatterns

import org.springframework.http.ResponseEntity
import org.springframework.ui.Model
import org.springframework.web.bind.annotation.*

@RestController
class HealthController {

    @GetMapping("/health")
    fun health(model: Model): ResponseEntity<Unit> {
        return ResponseEntity.ok().build()
    }
}