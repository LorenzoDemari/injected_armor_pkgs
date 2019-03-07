package it.emarolab.sit.reasonerCore;

import it.emarolab.sit.SITBase;
import it.emarolab.sit.sceneRepresentation.FullSceneRepresentation;

/**
 *
 * Add a node to the graph in the correct position (increasing complexity from root to
 * leaves) and number the nodes in a consecutive manner
 *
 */

public interface AddNode {

    static String addNode(FullSceneRepresentation recognition){

        String nodeName=SITBase.CLASS.SCENE + "-" + (recognition.getID() -1);
        recognition.learn(nodeName);

        recognition.getSceneDescriptor().writeSemantic();

        return nodeName;
    }
}
