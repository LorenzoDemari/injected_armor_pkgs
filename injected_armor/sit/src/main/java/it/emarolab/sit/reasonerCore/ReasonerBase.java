package it.emarolab.sit.reasonerCore;

import it.emarolab.amor.owlInterface.OWLReferences;
import it.emarolab.amor.owlInterface.OWLReferencesInterface;

import static it.emarolab.sit.SITBase.ONTO_IRI;


/**
 *
 * contains the ontology instance.
 *
 */

public class ReasonerBase {


    OWLReferences ontoRef;

    String ontoPath= "/home/lorenzo/test_nuovo.owl";


    public ReasonerBase(OWLReferences ontoRef){
        this.ontoRef = ontoRef;
    }


    /**
     *
     * This function is developed in order to save/remove/reload the instance of the ontology
     *  It is required because of the bug of the OWLOOP libraries.
     * @return
     */

    public OWLReferences SaveNdOpenOnto ()
    {

        ontoRef.saveOntology(ontoPath);

        OWLReferencesInterface.OWLReferencesContainer.removeInstance(ontoRef);

        ontoRef = OWLReferencesInterface.OWLReferencesContainer.newOWLReferenceFromFileWithPellet(
                "ONTO_NAME", ontoPath, ONTO_IRI, true);

        return ontoRef;

    }

    public String getOntoPath() {
        return ontoPath;
    }

    public void setOntoRef(OWLReferences ontoRef) {
        this.ontoRef = ontoRef;
    }

    public void setOntoPath(String ontoPath) {
        this.ontoPath = ontoPath;
    }

    public OWLReferences getOntoRef() {
        return ontoRef;
    }




}
