package src.main.kotlin.fr.stephanecastrec.designpatterns.patterns.creation.factory

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

class TrainServices : TransportServices{

    override fun drive(): String {
        return "I'm conducting a train";
    }
}