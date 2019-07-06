package it.emarolab.sit.reasonerCore;

import it.emarolab.owloop.aMORDescriptor.MORAxioms;
import it.emarolab.sit.Dictionary_reasoner;
import it.emarolab.sit.SITBase;
import it.emarolab.sit.owloopDescriptor.SceneIndividualDescriptor;
import it.emarolab.sit.sceneRepresentation.FullSceneRepresentation;


/**
 *
 * Reads the properties (scores and counters), initializes properties to a default
 * value and evaluates the level for a node
 *
 */

public interface PropertyManager {



    static float ReadProperty(String property, SceneIndividualDescriptor IndDescr)
    {
        float prop= 0;
        for( MORAxioms.DataSemantic ll : IndDescr.getDataSemantics()){


            String f =  ll.getValues().toString().replace("{","").replace("}","");


            if (ll.getSemantic().toString().contains(property)) {
                prop  =  Float.valueOf(f);

                System.out.println(property + " "+ prop + " " );
            }

        }
        return prop;
    }

    static void InitalizeProperty(FullSceneRepresentation recognition) {

        recognition.getSceneDescriptor().addData(Dictionary_reasoner.SCORE, 0.5);
        recognition.getSceneDescriptor().addData(Dictionary_reasoner.STORING_COUNTER, 0);
        recognition.getSceneDescriptor().addData(Dictionary_reasoner.RETRIEVING_COUNTER, 0);
        recognition.getSceneDescriptor().writeSemantic();

    }



    static String EvaluateLevel(SceneIndividualDescriptor IndDescr) {

        float score = PropertyManager.ReadProperty(Dictionary_reasoner.SCORE, IndDescr);
        String level= "";

        if (score < 0.2)
            level = "WEAK";
        else if (score > 0.6)
            level = "HIGH";
        else if (score < 0.6 && score > 0.2)
            level = "LOW";

        return level;

    }

}
