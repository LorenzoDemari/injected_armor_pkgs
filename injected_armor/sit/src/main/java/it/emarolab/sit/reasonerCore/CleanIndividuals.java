package it.emarolab.sit.reasonerCore;

import it.emarolab.amor.owlInterface.OWLReferences;
import it.emarolab.sit.SITBase;
import it.emarolab.sit.realObject.GeometricPrimitive;
import it.emarolab.sit.sceneRepresentation.FullSceneRepresentation;

import java.util.Set;


/**
 *
 * Removes all the geometric primitive Individuals from the ontology and the scene individ-
 * uals from the graph. *
 *
 */


public interface CleanIndividuals {

    static void  Clean (Set<GeometricPrimitive> objects, FullSceneRepresentation recognition, OWLReferences ontoRef)

    {
        for (GeometricPrimitive i : objects)
            ontoRef.removeIndividual(i.getInstance());

        recognition.getSceneDescriptor().removeTypeIndividual(SITBase.CLASS.SCENE);
        recognition.getSceneDescriptor().writeSemantic();



    }

}
