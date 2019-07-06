package it.emarolab.sit.reasonerCore;

import it.emarolab.amor.owlInterface.OWLReferences;
import it.emarolab.owloop.aMORDescriptor.MORAxioms;
import it.emarolab.sit.Dictionary_reasoner;
import it.emarolab.sit.SITBase;
import it.emarolab.sit.owloopDescriptor.SceneClassDescriptor;
import it.emarolab.sit.owloopDescriptor.SceneIndividualDescriptor;
import org.semanticweb.owlapi.model.OWLNamedIndividual;

import java.sql.Timestamp;
import java.util.Date;

/**
 *
 * Reads all scores of the graph and evaluates their levels. If a node turns out to have
 * a weak level (EvaluateLevel()) it is deleted from the graph (deletenode()).
 */

public class Forgetting extends ReasonerBase implements GetBranch, RemoveNode, PropertyManager{


    private String level= "LOW";
    private float score=0;

    public Forgetting(OWLReferences ontoRef) {
        super(ontoRef);
    }

    public OWLReferences Forget() {

        Timestamp stamp = new Timestamp(System.currentTimeMillis());
        Date date = new Date(stamp.getTime());
        System.out.println("%%%%%%%%%  Timestamp Start <" + this +"> : " + date + "  %%%%%%%%%%%%%%");

        SceneClassDescriptor scoreDesc= new SceneClassDescriptor("Score",ontoRef);
        scoreDesc.readSemantic();
        MORAxioms.Individuals indScore = scoreDesc.getIndividualClassified();


        for ( OWLNamedIndividual i : indScore)
        {
            System.out.println("indScore   "+i.toString());
            SceneIndividualDescriptor indScoreDesc= new SceneIndividualDescriptor(i,ontoRef);
            indScoreDesc.readSemantic();
            indScoreDesc.getDataSemantics();

         //   float scorediognuno=PropertyManager.ReadProperty(SITBase.DATA_PROPERTY.SCORE,indScoreDesc);
            String level=PropertyManager.EvaluateLevel(indScoreDesc);

            System.out.println("risult"+ level);

            String sceneNumber= i.toString().substring(i.toString().indexOf("#")+4,i.toString().length()-1);

            String sceneName= SITBase.CLASS.SCENE+"-"+sceneNumber;
            if (level== "WEAK") {

                RemoveNode.deletenode(sceneName, ontoRef);

                indScoreDesc.removeData(Dictionary_reasoner.SCORE);
                indScoreDesc.removeData(Dictionary_reasoner.RETRIEVING_COUNTER);
                indScoreDesc.removeData(Dictionary_reasoner.STORING_COUNTER);
                ontoRef.removeIndividual(i);
                //indScoreDesc.removeTypeIndividual(INDIVIDUAL.SCENE+sceneNumber);

                indScoreDesc.readSemantic().clear();
                indScoreDesc.writeSemantic();


                ontoRef= this.SaveNdOpenOnto();

            }

        }

        ontoRef= this.SaveNdOpenOnto();

        stamp = new Timestamp(System.currentTimeMillis());
        date = new Date(stamp.getTime());
        System.out.println("%%%%%%%%%  Timestamp Finish <" + this +"> : " + date + "  %%%%%%%%%%%%%%");

            return ontoRef;
    }




    public String getLevel() {
        return level;
    }

    public void setLevel(String level) {
        this.level = level;
    }


}
