package it.emarolab.sit.reasonerCore;

import it.emarolab.amor.owlInterface.OWLReferences;
import it.emarolab.owloop.aMORDescriptor.MORAxioms;
import it.emarolab.sit.Dictionary_reasoner;
import it.emarolab.sit.SITBase;
import it.emarolab.sit.owloopDescriptor.SceneClassDescriptor;
import it.emarolab.sit.owloopDescriptor.SceneIndividualDescriptor;
import it.emarolab.sit.sceneRepresentation.FullSceneRepresentation;

import java.util.Set;

/**
 * Scans the graph in order to find the nodes that match the ones given by the caller.
 * Updates the retrieving and storing counter (r j and s j ) *
 *
 */

public interface Recognition {


    static SceneIndividualDescriptor Recognize (FullSceneRepresentation recognition, OWLReferences ontoRef)

{
    SceneIndividualDescriptor bestRec = null;

    String property="";
    String callerName = Thread.currentThread().getStackTrace()[2].getClassName();
    int hascounter = 0;

/*
    SceneClassDescriptor uuuu= new SceneClassDescriptor("Scene-0",ontoRef);
    uuuu.readSemantic();


    boolean flag=true;
    MORAxioms.Concepts csup  =uuuu.getSuperConcept();
*//*Object[] objects2 = csup.toArray();*//*
    Set<String> nome= ontoRef.getOWLObjectName(csup);
    for( String lllls : nome){
        if( lllls.equals("Trash")) {
            flag=false;
        }



    }*/

    if (recognition.getRecognitionConfidence()==1)
    {

/*        String name = recognition.getBestRecognitionDescriptor().getIndividualClassified().toString();
        name=name.replace("{", "").replace("}", "");*/
        String name= recognition.getBestRecognitionDescriptor().getInstance().toString();
        name=name.substring(name.indexOf("#")+1, name.length()-1);
        name=name.replace(SITBase.CLASS.SCENE+"-", SITBase.INDIVIDUAL.SCENE);
        System.out.println("name of the scene: recognition:"+ name);
        bestRec = new SceneIndividualDescriptor(name, ontoRef);
        bestRec.readSemantic();



        if (callerName.contains("Storing"))
        {
             property= Dictionary_reasoner.STORING_COUNTER;
        }
        else if (callerName.contains("Retrieving"))
        {
            property = Dictionary_reasoner.RETRIEVING_COUNTER;
        }


        hascounter = (int) PropertyManager.ReadProperty(property, bestRec);
        hascounter++;

        bestRec.removeData(property);
        bestRec.addData(property, hascounter);
        bestRec.writeSemantic();
    }
    return bestRec;

}


}
