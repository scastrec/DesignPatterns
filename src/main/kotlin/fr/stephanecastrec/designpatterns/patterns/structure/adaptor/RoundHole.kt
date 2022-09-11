package fr.stephanecastrec.designpatterns.patterns.structure.adaptor

class RoundHole(val radius: Double) {

    fun fits(peg: RoundPeg): Boolean = this.radius > peg.getRadius();
}