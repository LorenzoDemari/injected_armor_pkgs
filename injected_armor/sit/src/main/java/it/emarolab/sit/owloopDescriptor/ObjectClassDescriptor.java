package it.emarolab.sit.owloopDescriptor;

import it.emarolab.amor.owlInterface.OWLReferences;
import it.emarolab.amor.owlInterface.SemanticRestriction;
import it.emarolab.owloop.aMORDescriptor.MORAxioms;
import it.emarolab.owloop.aMORDescriptor.MORConcept;
import it.emarolab.owloop.aMORDescriptor.utility.MORConceptBase;
import it.emarolab.sit.SITBase;
import it.emarolab.sit.realObject.*;
import org.semanticweb.owlapi.model.OWLClass;
import org.semanticweb.owlapi.model.OWLNamedIndividual;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

/**
 * The  <a href="https://github.com/EmaroLab/owloop">OWLOOP</a> {@code Descriptor} for a class representing an abstract Scene.
 * <p>
 *     This is an OWLClass {@code Descriptor} based on the
 *     <a href="https://github.com/EmaroLab/owloop">OWLOOP</a> API.
 *     It is in charge to synchronise the individuals classified in {@code this}
 *     class, as well as sub and super classes. It also implements
 *     method for ontological class definition and based on this, it
 *     computes the scene class cardinality.
 *
 * <div style="text-align:center;"><small>
 * <b>File</b>:        it.emarolab.sit.owloopDescriptor.SceneClassDescriptor <br>
 * <b>Licence</b>:     GNU GENERAL PUBLIC LICENSE. Version 3, 29 June 2007 <br>
 * <b>Author</b>:      Buoncompagni Luca (luca.buoncompagni@edu.unige.it) <br>
 * <b>affiliation</b>: EMAROLab, DIBRIS, University of Genoa. <br>
 * <b>date</b>:        05/06/17 <br>
 * </small></div>
 */
public class ObjectClassDescriptor
        extends MORConceptBase
        implements MORConcept.Sub<ObjectClassDescriptor>,
        MORConcept.Classify<ObjectIndividualDescriptor> {

    private MORAxioms.Concepts subConcept = new MORAxioms.Concepts();
    private MORAxioms.Individuals classifiedIndividual = new MORAxioms.Individuals();

    public ObjectClassDescriptor(OWLClass instance, String ontoName) {
        super(instance, ontoName);
    }
    public ObjectClassDescriptor(String instanceName, String ontoName) {
        super(instanceName, ontoName);
    }
     public ObjectClassDescriptor(OWLClass instance, OWLReferences ontoName) {
        super(instance, ontoName);
    }
    public ObjectClassDescriptor(String instanceName, OWLReferences ontoName) {
        super(instanceName, ontoName);
    }

    @Override
    public List<MappingIntent> readSemantic() {
        List<MappingIntent> r = Sub.super.readSemantic();
        r.addAll( Classify.super.readSemantic());
        return r;
    }

    @Override
    public List<MappingIntent> writeSemantic() {
        List<MappingIntent> r = Classify.super.writeSemantic();
        r.addAll( Sub.super.writeSemantic());
        return r;
    }


    @Override
    public ObjectClassDescriptor getNewSubConcept(OWLClass instance, OWLReferences ontology) {
        return new ObjectClassDescriptor( instance, ontology);
    }

    @Override
    public MORAxioms.Concepts getSubConcept() {
        return subConcept;
    }

    @Override
    public ObjectIndividualDescriptor getNewIndividualClassified(OWLNamedIndividual instance, OWLReferences ontology) {
        return new ObjectIndividualDescriptor( instance, ontology);
    }

    @Override
    public MORAxioms.Individuals getIndividualClassified() {
        return classifiedIndividual;
    }



    @Override
    public String toString() {
        return "SceneClassDescriptor{" +
                NL + "\t\t\t" +
                "," + NL + "\t⇐ " + classifiedIndividual +
                "," + NL + "\t⊃ " + subConcept +
                NL + "}";


    }

}
