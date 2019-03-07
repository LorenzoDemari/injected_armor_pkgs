package it.emarolab.sit.owloopDescriptor;

import it.emarolab.amor.owlInterface.OWLReferences;
import it.emarolab.owloop.aMORDescriptor.MORAxioms;
import it.emarolab.owloop.aMORDescriptor.MORIndividual;
import it.emarolab.owloop.aMORDescriptor.utility.MORIndividualBase;
import it.emarolab.owloop.aMORDescriptor.utility.dataProperty.MORHierarchicalDataProperty;
import it.emarolab.owloop.aMORDescriptor.utility.objectProperty.MORHierarchicalObjectProperty;
import it.emarolab.sit.SITBase;
import org.semanticweb.owlapi.model.OWLClass;
import org.semanticweb.owlapi.model.OWLNamedIndividual;

import java.util.List;


public class ObjectIndividualDescriptor
        extends MORIndividualBase
        implements MORIndividual.Type<ObjectClassDescriptor>,
        SITBase{


    private MORAxioms.Concepts individualTypes = new MORAxioms.Concepts();

    public ObjectIndividualDescriptor(OWLNamedIndividual instance, OWLReferences onto) {
        super(instance, onto);
    }
    public ObjectIndividualDescriptor(String instanceName, OWLReferences onto) {
        super(instanceName, onto);
    }
   public ObjectIndividualDescriptor(OWLNamedIndividual instance, String ontoName) {
        super(instance, ontoName);
    }
    public ObjectIndividualDescriptor(String instanceName, String ontoName) {
        super(instanceName, ontoName);
    }


    @Override
    public List<MappingIntent> readSemantic() {
        List<MappingIntent> r = Type.super.readSemantic();
        return r;
    }

    @Override
    public List<MappingIntent> writeSemantic() {
        List<MappingIntent> r = Type.super.writeSemantic();
        return r;
    }


    @Override // you can change the returning type to any implementations of MORConcept
    public ObjectClassDescriptor getNewTypeIndividual(OWLClass instance, OWLReferences ontology) {
        return new ObjectClassDescriptor( instance, ontology);
    }

    @Override
    public MORAxioms.Concepts getTypeIndividual() {
        return individualTypes;
    }


    @Override
    public String toString() {
        return "SceneIndividualDescriptor{" +
                NL + "\t\t\t" +
                "," + NL + "\t∈ " + individualTypes +
                NL + "}";
    }
}
