package fr.stephanecastrec.designpatterns.patterns.creation.builder

class Car private constructor(
    val color: String?,
    val seats: Short?
){
    data class Builder(
        var color: String? = null,
        var seats: Short? = 1
    ) {
        fun color(color: String) = apply { this.color = color}
        fun seats(seats: Short) = apply { this.seats = seats}
        fun build() = Car(color, seats)
    }
}