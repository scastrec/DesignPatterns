package fr.stephanecastrec.designpatterns.patterns.creation.factory

import src.main.kotlin.fr.stephanecastrec.designpatterns.patterns.creation.factory.TrainServices
import src.main.kotlin.fr.stephanecastrec.designpatterns.patterns.creation.factory.TransportServices

class TransportFactory {

    companion object Factory {
        fun create(typeString: String) : TransportServices {
            System.out.println("Factory create  " + typeString)

            var type : TransportType = TransportType.valueOf(typeString);
            if (TransportType.Train.equals(type)) {
                return TrainServices()
            } else if (TransportType.Cars.equals(type)) {
                return CarServices()
            } else {
                throw UnsupportedOperationException()
            }
        }
    }
}