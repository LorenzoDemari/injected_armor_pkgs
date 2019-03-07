package it.emarolab.sit;

import it.emarolab.amor.owlDebugger.Logger;
import it.emarolab.amor.owlInterface.OWLReferences;
import it.emarolab.amor.owlInterface.OWLReferencesInterface;
import it.emarolab.amor.owlInterface.SemanticRestriction;
import it.emarolab.owloop.aMORDescriptor.MORAxioms;
import it.emarolab.sit.owloopDescriptor.ObjectClassDescriptor;
import it.emarolab.sit.owloopDescriptor.ObjectIndividualDescriptor;
import it.emarolab.sit.owloopDescriptor.SceneClassDescriptor;
import it.emarolab.sit.owloopDescriptor.SceneIndividualDescriptor;
import it.emarolab.sit.realObject.*;
import it.emarolab.sit.reasonerCore.*;
import it.emarolab.sit.sceneRepresentation.FullSceneRepresentation;
import org.apache.jena.base.Sys;
import org.semanticweb.owlapi.model.OWLNamedIndividual;
import org.semanticweb.owlapi.model.OWLOntologyChange;
import org.semanticweb.owlapi.model.OWLRuntimeException;

import java.sql.Timestamp;
import java.util.Date;
import java.util.HashSet;
import java.util.Set;

/**
 *
 * This is a test main that simulates the encoding of different scenes and
 * launches storing, consolidating, forgetting and retrieving.
 *
 * In this example we simulate the storing of the following scenes:
 *  - Scene-0: sphere, plane (stored twice)
 *  - Scene-1: sphere, plane, cone, cylinder
 *  - Scene-2: sphere, plane, cone
 *  - Scene-3: sphere, cone, cylinder
 *
 *  After the storing, it performs the consolidating (from leaf to root).
 *  Then it performs the forgetting which deletes all nodes with a level < 0.2.
 *  Finally, it simulates the request of the dialogue module,
 *  assuming to retrieve a scene that exactly matches an existing node.
 *
 *  We are using the classes and interfaces in the package reasonerCore
 *
 */
public class Main
        implements SITBase, RemoveNode, GetBranch {

    private static final String ONTO_NAME = "ONTO_NAME"; // an arbitrary name to refer the ontology

    public static void main(String[] args){

        Timestamp stamp = new Timestamp(System.currentTimeMillis());
        Date date = new Date(stamp.getTime());
        System.out.println("%%%%%%%%%  Timestamp Start <main> " + date + "  %%%%%%%%%%%%%%");

        // load ontology
        OWLReferences ontoRef = OWLReferencesInterface.OWLReferencesContainer.newOWLReferenceFromFileWithPellet(
                ONTO_NAME, ONTO_FILE, ONTO_IRI, true);
        String ontoPath= "/home/lorenzo/test_nuovo.owl";



        // suppress aMOR log
        Logger.setPrintOnConsole( false);

        // initialise objects
        Set< GeometricPrimitive> objects = new HashSet<>();

/**   FirstScene    */
        Sphere s = new Sphere( ontoRef);
        s.shouldAddTime( true);
        s.setCenter( .3f, .3f, .3f);
        s.setRadius( .1f);
        objects.add( s);

        Plane p = new Plane( ontoRef);
        p.shouldAddTime( true);
        p.setAxis( .5f, .4f, .1f);
        p.setCenter( .3f, .1f, .1f);
        p.setHessian( .5f);
        objects.add( p);


        System.out.println( "Object " + objects);
        System.out.println("1 ----------------------------------------------");


        SpatialSimplifier simplifier = new SpatialSimplifier( objects);
        FullSceneRepresentation recognition = new FullSceneRepresentation( simplifier, ontoRef);

//STORING
        Storing Storer= new Storing(ontoRef);
        ontoRef = Storer.Store(recognition, objects);

       ReasonerBase Reasoner = new ReasonerBase(ontoRef);
        ontoRef=  Reasoner.SaveNdOpenOnto();

/*         Storer= new Storing(ontoRef);
        ontoRef = Storer.Store(recognition, objects);*/



/**     scena tentativo di storing doppio come dovrebbe funzionare  **/

        objects.clear();


        Sphere s1 = new Sphere( ontoRef);
        s1.shouldAddTime( true);
        s1.setCenter( .3f, .3f, .3f);
        s1.setRadius( .1f);
        objects.add( s1);

        Plane p1 = new Plane( ontoRef);
        p1.shouldAddTime( true);
        p1.setAxis( .5f, .4f, .1f);
        p1.setCenter( .3f, .1f, .1f);
        p1.setHessian( .5f);
        objects.add( p1);


        System.out.println( "Object " + objects);
        System.out.println("1 ----------------------------------------------");


        SpatialSimplifier simplifierN = new SpatialSimplifier( objects);
        FullSceneRepresentation recognitionN = new FullSceneRepresentation( simplifierN, ontoRef);

//STORING
        Storer= new Storing(ontoRef);
        ontoRef = Storer.Store(recognitionN, objects);



/**   SecondScene    */
        Reasoner = new ReasonerBase(ontoRef);
        ontoRef=  Reasoner.SaveNdOpenOnto();

        objects.clear();

        s = new Sphere( ontoRef);
        s.shouldAddTime( true);
        s.setCenter( .3f, .3f, .3f);
        s.setRadius( .1f);
        objects.add( s);

        p = new Plane( ontoRef);
        p.shouldAddTime( true);
        p.setAxis( .5f, .4f, .1f);
        p.setCenter( .3f, .1f, .1f);
        p.setHessian( .5f);
        objects.add( p);

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


        SpatialSimplifier simplifier2 = new SpatialSimplifier( objects);
        FullSceneRepresentation recognition2 = new FullSceneRepresentation(simplifier2,ontoRef);

//STORING
        Storer= new Storing(ontoRef);
        ontoRef = Storer.Store(recognition2, objects);


/**   ThirdScene    */
        Reasoner = new ReasonerBase(ontoRef);
        ontoRef=  Reasoner.SaveNdOpenOnto();

        // augment the scene
        objects.clear();

        s = new Sphere( ontoRef);
        s.shouldAddTime( true);
        s.setCenter( .3f, .3f, .3f);
        s.setRadius( .1f);
        objects.add( s);

        p = new Plane( ontoRef);
        p.shouldAddTime( true);
        p.setAxis( .5f, .4f, .1f);
        p.setCenter( .3f, .1f, .1f);
        p.setHessian( .5f);
        objects.add( p);

        c = new Cone( ontoRef);
        c.shouldAddTime( true);
        c.setCenter( .3f, .3f, .3f);
        c.setAxis( .0f, .1f, .0f);
        c.setApex( .2f, .3f, .4f);
        c.setRadius( .1f);
        c.setHeight( .05f);
        objects.add( c);


        simplifier = new SpatialSimplifier( objects);
        FullSceneRepresentation recognition7 = new FullSceneRepresentation(simplifier, ontoRef);

//STORING
        Storer= new Storing(ontoRef);
        ontoRef = Storer.Store(recognition7, objects);


/**   FourthScene    */
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

        SpatialSimplifier simplifierss = new SpatialSimplifier( objects);
        FullSceneRepresentation recognitionss = new FullSceneRepresentation(simplifierss,ontoRef);

//STORING
        Storer= new Storing(ontoRef);
        ontoRef = Storer.Store(recognitionss, objects);


//CONSOLIDATING (starting from the leaf)
        Consolidating Consolidator = new Consolidating(ontoRef);
        ontoRef=Consolidator.Consolidate("Scene-2");

//FORGETTING
        Forgetting Forgetter = new Forgetting(ontoRef);
        ontoRef=Forgetter.Forget();



/**     A new Scene to be Retrieved     */
        Reasoner = new ReasonerBase(ontoRef);
        ontoRef=  Reasoner.SaveNdOpenOnto();
/*
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
        objects.add( ci);*/


        //SpatialSimplifier simplifierR = new SpatialSimplifier( objects);
        FullSceneRepresentation recognitionR =recognition2;
        //recognition = new FullSceneRepresentation(simplifierR,ontoRef);

        CleanIndividuals.Clean(objects,recognitionR,ontoRef);

//RETRIEVING
        Retrieving Retriever= new Retrieving(ontoRef);
        ontoRef=Retriever.Retrieve(recognitionR, objects);


        stamp = new Timestamp(System.currentTimeMillis());
        date = new Date(stamp.getTime());
        System.out.println("%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%% finisco alle"+ date);

    }
}
