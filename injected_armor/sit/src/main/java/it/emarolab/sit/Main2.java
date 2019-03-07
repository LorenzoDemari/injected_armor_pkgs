package it.emarolab.sit;

import it.emarolab.amor.owlDebugger.Logger;
import it.emarolab.amor.owlInterface.OWLReferences;
import it.emarolab.amor.owlInterface.OWLReferencesInterface;
import it.emarolab.sit.SITBase;
import it.emarolab.sit.SpatialSimplifier;
import it.emarolab.sit.realObject.*;
import it.emarolab.sit.reasonerCore.*;
import it.emarolab.sit.sceneRepresentation.FullSceneRepresentation;

import java.sql.Timestamp;
import java.util.Date;
import java.util.HashSet;
import java.util.Set;

/**
 * A runnable example to show how to use the SIT.
 * <p>
 *     This is a simple example that shows how to use the system out of the
 *     ROS environment. It is based in the ontology located in
 *     {@code resources/t_box/empty-scene.owl}. It creates
 *     a scene of two objects, try to recognise it and,
 *     since it cannot, it learn a new representation.
 *     Finally reapply the recognition to show that after words it is
 *     able to recognise it.
 *     <br>
 *     This is done two time, by adding a new object, in order to show
 *     the ability to hierarchically describe scenes and asses
 *     a recognition confidence.
 *     <br>
 *     Numerical value of the object coefficients are given just
 *     for showing purposes and the scene does not have a specific representation.
 *
 * <div style="text-align:center;"><small>
 * <b>File</b>:        it.emarolab.sit.Test <br>
 * <b>Licence</b>:     GNU GENERAL PUBLIC LICENSE. Version 3, 29 June 2007 <br>
 * <b>Author</b>:      Buoncompagni Luca (luca.buoncompagni@edu.unige.it) <br>
 * <b>affiliation</b>: EMAROLab, DIBRIS, University of Genoa. <br>
 * <b>date</b>:        05/06/17 <br>
 * </small></div>
 */
public class Main2
        implements SITBase, RemoveNode, GetBranch {

    private static final String ONTO_NAME = "ONTO_NAME"; // an arbitrary name to refer the ontology

    public static void main(String[] args){

        Timestamp stamp = new Timestamp(System.currentTimeMillis());
        Date date = new Date(stamp.getTime());
        System.out.println("%%%%%%%%%  Timestamp Start <main> " + date + "  %%%%%%%%%%%%%%");

        // load ontology
        OWLReferences ontoRef = OWLReferencesInterface.OWLReferencesContainer.newOWLReferenceFromFileWithPellet(
                ONTO_NAME, ONTO_FILE_FULL, ONTO_IRI, true);
        String ontoPath= "/home/lorenzo/test_nuovo.owl";



        // suppress aMOR log
        Logger.setPrintOnConsole( false);

        // initialise objects
        Set< GeometricPrimitive> objects = new HashSet<>();




        Sphere s = new Sphere( ontoRef);
        s.shouldAddTime( true);
        s.setCenter( .3f, .3f, .3f);
        s.setRadius( .1f);
        objects.add( s);




        Cone c = new Cone( ontoRef);
        c.shouldAddTime( true);
        c.setCenter( .3f, .3f, .3f);
        c.setAxis( .0f, .1f, .0f);
        c.setApex( .2f, .3f, .4f);
        c.setRadius( .1f);
        c.setHeight( .05f);
        objects.add( c);




        Cylinder ci = new Cylinder( ontoRef);
        ci.shouldAddTime( true);
        ci.setCenter( .4f, .5f, .6f);
        ci.setApex( 0f, 0f, 1f);
        ci.setAxis( 0f, 0f, 1f);
        ci.setRadius( .2f);
        ci.setHeight( .06f);
        objects.add( ci);



        SpatialSimplifier simplifier = new SpatialSimplifier( objects);
        FullSceneRepresentation recognition = new FullSceneRepresentation(simplifier,ontoRef);


        Storing Storer = new Storing(ontoRef);
        ontoRef = Storer.Store(recognition, objects);

        ReasonerBase reasonerBase=new ReasonerBase(ontoRef);
        reasonerBase.SaveNdOpenOnto();

        objects.clear();

        Consolidating Consolidator = new Consolidating(ontoRef);
        ontoRef=Consolidator.Consolidate("Scene-101");


/*        Forgetting Forgetter = new Forgetting(ontoRef);
        ontoRef=Forgetter.Forget();*/



        /////////////////Seconda Scena/////////////////
        /////////////////Sfera, piano, cono, ciindro//////////////

/*
        Reasoner = new ReasonerBase(ontoRef);
        ontoRef=  Reasoner.SaveNdOpenOnto();

        objects.clear();

        s = new Sphere( ontoRef);
        s.shouldAddTime( true);
        s.setCenter( .3f, .3f, .3f);
        s.setRadius( .1f);
        objects.add( s);



      c = new Cone( ontoRef);
        c.shouldAddTime( true);
        c.setCenter( .3f, .3f, .3f);
        c.setAxis( .0f, .1f, .0f);
        c.setApex( .2f, .3f, .4f);
        c.setRadius( .1f);
        c.setHeight( .05f);
        objects.add( c);




        ci = new Cylinder( ontoRef);
        ci.shouldAddTime( true);
        ci.setCenter( .4f, .5f, .6f);
        ci.setApex( 0f, 0f, 1f);
        ci.setAxis( 0f, 0f, 1f);
        ci.setRadius( .2f);
        ci.setHeight( .06f);
        objects.add( ci);


        SpatialSimplifier simplifierR = new SpatialSimplifier( objects);
        FullSceneRepresentation recognitionR =recognitionss;
        //recognition = new FullSceneRepresentation(simplifierR,ontoRef);

        CleanIndividuals.Clean(objects,recognitionR,ontoRef);


        Retrieving Retriever= new Retrieving(ontoRef);
        ontoRef=Retriever.Retrieve(recognitionR, objects);*/


        stamp = new Timestamp(System.currentTimeMillis());
        date = new Date(stamp.getTime());
        System.out.println("%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%% finisco alle"+ date);

    }
}
