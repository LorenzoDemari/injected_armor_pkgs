package it.emarolab.sit.reasonerCore;


import it.emarolab.amor.owlInterface.OWLReferences;
import it.emarolab.sit.SITBase;
import it.emarolab.sit.owloopDescriptor.SceneIndividualDescriptor;
import it.emarolab.sit.realObject.GeometricPrimitive;
import it.emarolab.sit.sceneRepresentation.FullSceneRepresentation;
import org.apache.jena.reasoner.Reasoner;

import java.sql.Timestamp;
import java.util.Date;
import java.util.Set;

/**
 *
 * Evaluates if the node given by the dialogue module (recognize()) is in the graph.
 *         In case a complete match is found the retrieving counter of all nodes of the branch are
 *         increased. Otherwise it creates a ’dummy node’ with addNode() and increases the retrieving
 *         score for the parent nodes and finally deletes the dummy node remove().


   */

public class Retrieving extends ReasonerBase implements AddNode, RemoveNode, BuildScene, GetBranch,Recognition {





    private float retrievingScore;


    public Retrieving(OWLReferences ontoRef) {
        super(ontoRef);
    }

    public OWLReferences Retrieve(FullSceneRepresentation recognition ,Set<GeometricPrimitive> objects )
     {


         Timestamp stamp = new Timestamp(System.currentTimeMillis());
         Date date = new Date(stamp.getTime());
         System.out.println("%%%%%%%%%  Timestamp Start <" + this +"> : " + date + "  %%%%%%%%%%%%%%");

       ontoRef=this.SaveNdOpenOnto();
       String nodeName="";




        boolean flag=false;
        if (recognition.getRecognitionConfidence()!=1)
        {

            nodeName=AddNode.addNode(recognition);

            flag=true;

        }

        SceneIndividualDescriptor descrip= Recognition.Recognize(recognition,ontoRef);
         descrip.removeData(SITBase.DATA_PROPERTY.RETRIEVING_COUNTER);
         descrip.writeSemantic();
        CleanIndividuals.Clean(objects,recognition,ontoRef);
        ontoRef=this.SaveNdOpenOnto();


       if (flag) {

            RemoveNode.deletenode(nodeName, ontoRef);
        }


        ontoRef.saveOntology(ontoPath);


         stamp = new Timestamp(System.currentTimeMillis());
         date = new Date(stamp.getTime());
         System.out.println("%%%%%%%%%  Timestamp Finish <" + this +"> : " + date + "  %%%%%%%%%%%%%%");


        return ontoRef;
    }

    public float getRetrievingScore() {
        return retrievingScore;
    }

    public void setRetrievingScore(float retrievingScore) {
        this.retrievingScore = retrievingScore;
    }


}
