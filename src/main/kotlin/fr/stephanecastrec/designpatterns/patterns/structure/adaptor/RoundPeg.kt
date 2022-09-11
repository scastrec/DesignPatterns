package fr.stephanecastrec.designpatterns.patterns.structure.adaptor

interface RoundPeg {
    fun getRadius(): Double;
}

class RoundPegimpl(private val radius: Double): RoundPeg {
    override fun getRadius(): Double {
        return this.radius;
    }
}