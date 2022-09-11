package fr.stephanecastrec.designpatterns.patterns.structure.adaptor

class RoundSquareAdaptor(val squarePeg: SquarePeg) : RoundPeg {
    override fun getRadius(): Double {
        return squarePeg.width * Math.sqrt(2.0) / 2
    }

}