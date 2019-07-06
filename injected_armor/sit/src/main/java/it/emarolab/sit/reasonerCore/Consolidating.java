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
import java.util.Set;


/**
Evaluates the scores of the branch (EvaluateScore()) of the last modified
        node, as each nodes score also takes into account the children’s scores.
        Finally calls
        normalization() in order make the scores of the complete tree comparable.
*/

public class Consolidating extends ReasonerBase implements GetBranch{


    private float nodeScore;



    public Consolidating(OWLReferences ontoRef) {
        super(ontoRef);
    }

    public OWLReferences Consolidate(String nodeToConsolidate) {


        Timestamp stamp = new Timestamp(System.currentTimeMillis());
        Date date = new Date(stamp.getTime());
        System.out.println("%%%%%%%%%  Timestamp Start <" + this +"> : " + date + "  %%%%%%%%%%%%%%");

        MORAxioms.Concepts csup=null;
        do {

            nodeScore = GetBranch.EvaluationScore(nodeToConsolidate, ontoRef);
            //ontoRef.saveOntology(ontoPath);
            ontoRef=this.SaveNdOpenOnto();
            //this.SaveNdOpenOnto();
            ontoRef.setOWLEnquirerCompletenessFlag(false);
            SceneClassDescriptor nodeDesc = new SceneClassDescriptor(nodeToConsolidate, ontoRef);
            nodeDesc.readSemantic();
            csup = nodeDesc.getSuperConcept();
            Set<String> nome= ontoRef.getOWLObjectName(csup);
            for( String lllls : nome) {

                if (!lllls.equals(SITBase.CLASS.SCENE))
                    nodeToConsolidate = lllls;
            }

            ontoRef=this.SaveNdOpenOnto();


        }
        while(csup.size()>1);



          float normFactor=  GetBranch.Normalization(ontoRef);

        SceneClassDescriptor scoreDesc = new SceneClassDescriptor("Score", ontoRef);
        scoreDesc.readSemantic();
        MORAxioms.Individuals indScore = scoreDesc.getIndividualClassified();


        for (OWLNamedIndividual i : indScore) {
            System.out.println("indScore   " + i.toString());
            SceneIndividualDescriptor indScoreDesc = new SceneIndividualDescriptor(i, ontoRef);
            indScoreDesc.readSemantic();
            indScoreDesc.getDataSemantics();
            float temp= PropertyManager.ReadProperty(Dictionary_reasoner.SCORE, indScoreDesc);
            temp=temp/normFactor;

            indScoreDesc.removeData(Dictionary_reasoner.SCORE);
            indScoreDesc.addData(Dictionary_reasoner.SCORE, temp);
            indScoreDesc.writeSemantic();


        }
        ontoRef=this.SaveNdOpenOnto();



        stamp = new Timestamp(System.currentTimeMillis());
        date = new Date(stamp.getTime());
        System.out.println("%%%%%%%%%  Timestamp Finish <" + this +"> : " + date + "  %%%%%%%%%%%%%%");

        return ontoRef;
    }


    public void setNodeScore(float nodeScore) {

        this.nodeScore = nodeScore;
    }

    public float getNodeScore() {

        return nodeScore;
    }
}
