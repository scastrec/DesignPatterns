package fr.stephanecastrec.designpatterns.patterns.creation.factory

import src.main.kotlin.fr.stephanecastrec.designpatterns.patterns.creation.factory.TransportServices

class CarServices : TransportServices {
    override fun drive(): String {
        return "I'm driving a car";
    }
}