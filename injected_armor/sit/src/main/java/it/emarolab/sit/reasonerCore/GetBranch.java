package it.emarolab.sit.reasonerCore;

import it.emarolab.amor.owlInterface.OWLReferences;
import it.emarolab.owloop.aMORDescriptor.MORAxioms;
import it.emarolab.sit.SITBase;
import it.emarolab.sit.owloopDescriptor.SceneClassDescriptor;
import it.emarolab.sit.owloopDescriptor.SceneIndividualDescriptor;
import org.semanticweb.owlapi.model.OWLNamedIndividual;

/**
 *
 * Contains functions that scan the graph to either evaluate the score of each node or
 * the normalization factor.
 *
 */


public interface GetBranch {



    static float EvaluationScore(String nodeName, OWLReferences ontoRef)

    {
        float ad=1;
        float ac=1;
        float as=1;
        float ar=1;
        float jd=1;
        float sj=1;
        float rj=1;
        float sumSon=0;
        float scoreIndScene =0;
        float totalScore=0;
        float sumsondiv=0;

        ontoRef.setOWLEnquirerCompletenessFlag(true);

        String sceneNumber= nodeName.substring(nodeName.indexOf("-")+1,nodeName.length());
        String sceneName= SITBase.INDIVIDUAL.SCENE+sceneNumber;

        SceneClassDescriptor nodeDesc = new SceneClassDescriptor(nodeName, ontoRef);
        nodeDesc.readSemantic();

        MORAxioms.Concepts csub  =nodeDesc.getSubConcept();
        Object[] objects1 = csub.toArray();


        for (int i=0; i < objects1.length; i++)
        {

            String f = objects1[i].toString().replace("<", "").replace(">", "");
            String indScene = SITBase.INDIVIDUAL.SCENE + f.substring(f.length() - 1, f.length());

            SceneIndividualDescriptor indSceneDesc = new SceneIndividualDescriptor(indScene, ontoRef);
            indSceneDesc.readSemantic();


            scoreIndScene = PropertyManager.ReadProperty(SITBase.DATA_PROPERTY.SCORE, indSceneDesc);
            sumSon = sumSon + scoreIndScene;


        }
       int den =objects1.length-1;

   /*     try {
            sumsondiv= sumSon/(objects1.length-1);
        }

        catch (Exception e)
        {
            sumsondiv=0;
        }

*/



        if (den==0) den=1;
        sumsondiv= sumSon/den;
          /**/

        SceneIndividualDescriptor indSceneDesc2= new SceneIndividualDescriptor(sceneName, ontoRef);
        indSceneDesc2.readSemantic();
        sj=PropertyManager.ReadProperty(SITBase.DATA_PROPERTY.STORING_COUNTER, indSceneDesc2);
        rj=PropertyManager.ReadProperty(SITBase.DATA_PROPERTY.RETRIEVING_COUNTER, indSceneDesc2);
        jd=nodeDesc.getCardinality();
        totalScore= ad*jd+ac*sumsondiv+as*sj+ar*rj;

        indSceneDesc2.removeData(SITBase.DATA_PROPERTY.SCORE);
        indSceneDesc2.addData(SITBase.DATA_PROPERTY.SCORE, totalScore);
        indSceneDesc2.writeSemantic();


        return totalScore;
    }



    static float Normalization (OWLReferences ontoRef)
    {
        float normFactor=0;
        SceneClassDescriptor scoreDesc = new SceneClassDescriptor("Score", ontoRef);
        scoreDesc.readSemantic();
        MORAxioms.Individuals indScore = scoreDesc.getIndividualClassified();


        for (OWLNamedIndividual i : indScore) {
            System.out.println("indScore   " + i.toString());
            SceneIndividualDescriptor indScoreDesc = new SceneIndividualDescriptor(i, ontoRef);
            indScoreDesc.readSemantic();
            indScoreDesc.getDataSemantics();
            normFactor+= PropertyManager.ReadProperty(SITBase.DATA_PROPERTY.SCORE, indScoreDesc);
        }

        return normFactor;
    }
}
