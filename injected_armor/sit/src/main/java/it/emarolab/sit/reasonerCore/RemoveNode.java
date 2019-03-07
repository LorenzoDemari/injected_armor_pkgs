package it.emarolab.sit.reasonerCore;

import it.emarolab.amor.owlInterface.OWLReferences;
import it.emarolab.sit.owloopDescriptor.SceneClassDescriptor;

/**
 *
 * Deletes a node from the graph and puts it into the trash bin.
 *
 */

public interface RemoveNode
{

    static boolean deletenode(String sceneName, OWLReferences ontoRef)
    {
        SceneClassDescriptor remover = new SceneClassDescriptor(sceneName, ontoRef);
        remover.readSemantic();
        remover.getDefinitionConcept().clear();
        remover.writeSemantic();
        remover.addSuperConcept("Trash");
        remover.writeSemantic();
        System.out.println(" $$$$$ " + remover.getDefinitionConcept());

        return true;
    }
}
