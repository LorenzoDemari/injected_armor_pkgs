package it.emarolab.sit;

import it.emarolab.amor.owlDebugger.Logger;
import it.emarolab.amor.owlInterface.OWLReferences;
import it.emarolab.amor.owlInterface.OWLReferencesInterface;
import it.emarolab.owloop.aMORDescriptor.MORAxioms;
import it.emarolab.sit.owloopDescriptor.SceneClassDescriptor;
import it.emarolab.sit.owloopDescriptor.SceneIndividualDescriptor;
import it.emarolab.sit.realObject.*;
import it.emarolab.sit.sceneRepresentation.FullSceneRepresentation;

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
public class Test
        implements SITBase{

    private static final String ONTO_NAME = "ONTO_NAME"; // an arbitrary name to refer the ontology

    public static void main(String[] args){

        // load ontology
        OWLReferences ontoRef = OWLReferencesInterface.OWLReferencesContainer.newOWLReferenceFromFileWithPellet(
                ONTO_NAME, ONTO_FILE, ONTO_IRI, true);

        // suppress aMOR log
        Logger.setPrintOnConsole( false);

        // initialise objects
        Set< GeometricPrimitive> objects = new HashSet<>();

        SpatialSimplifier simplifier0 = new SpatialSimplifier( objects);

        FullSceneRepresentation recognition0 = new FullSceneRepresentation( simplifier0, ontoRef);


        // define objects
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



//double punteggio = 0.544;


     //   Scor punteggio= new Scor(ontoRef);

      //  System.out.println( "score " + punteggio.getScor());


        System.out.println( "Object " + objects);
        System.out.println("1 ----------------------------------------------");

        // the SceneRecognition needs a SpatialSimplifier object
        // to semplify the characteristics of the relations during learning
        SpatialSimplifier simplifier = new SpatialSimplifier( objects);
        // create scene and reason for recognition
        //SceneRepresentation recognition1 = new SceneRepresentation( simplifier, ontoRef);
        FullSceneRepresentation recognition1 = new FullSceneRepresentation( simplifier, ontoRef);





        // if you want the relation to be human friendly again
        //simplifier.populateHumanFriendlyRelationSet();
        // and eventually
        //simplifier.readObjectSemantics( true);
        //objects = simplifier.getObjects();


        System.out.println( "Recognised with best confidence: " + recognition1.getRecognitionConfidence() + " should learn? " + recognition1.shouldLearn());
        System.out.println( "Best recognised class: " + recognition1.getBestRecognitionDescriptor());
        System.out.println( "Other recognised classes: " + recognition1.getSceneDescriptor().getTypeIndividual());

        // learn the new scene if is the case
        if ( recognition1.shouldLearn()) {
            System.out.println("Learning.... ");
            recognition1.learn("Scene");
        }



        recognition1.getSceneDescriptor().addData(DATA_PROPERTY.SCORE, 0.6);



        SceneIndividualDescriptor sceneIndDescr3 = new SceneIndividualDescriptor("Sn-1", ontoRef);
        sceneIndDescr3.readSemantic();
        for( MORAxioms.DataSemantic l : sceneIndDescr3.getDataSemantics()){
            String f3 =  l.getValues().toString().replace("{","").replace("}","");
            Float ff3 = Float.valueOf(f3);
            System.out.println(l.getSemantic() + " " +ff3);
        }

        sceneIndDescr3.addData( "DATA_PROPERTY.STORING_COUNTER", 0);
        sceneIndDescr3.addData( "hasRetrievingCounter", 8);
        sceneIndDescr3.writeSemantic();


        recognition1.getSceneDescriptor().removeTypeIndividual( "Scene");
        recognition1.getSceneDescriptor().writeSemantic();

        System.out.println("2 ----------------------------------------------");

        // check recognition after learning
        System.out.println( "Recognised with best confidence: " + recognition1.getRecognitionConfidence() + " should learn? " + recognition1.shouldLearn());
        System.out.println( "Best recognised class: " + recognition1.getBestRecognitionDescriptor());
        System.out.println( "Other recognised classes: " + recognition1.getSceneDescriptor().getTypeIndividual());

        System.out.println("3 ----------------------------------------------");
        System.out.println("3 ----------------------------------------------");
        System.out.println("3 ----------------------------------------------");

       // clean ontology
        //ontoRef.removeIndividual( recognition1.getSceneDescriptor().getInstance());
        for ( GeometricPrimitive i : objects)
            ontoRef.removeIndividual( i.getInstance());
        ontoRef.synchronizeReasoner();


        ontoRef.saveOntology("/home/lorenzo/aaa.owl");
        // load ontology
        ontoRef = OWLReferencesInterface.OWLReferencesContainer.newOWLReferenceFromFileWithPellet(
                ONTO_NAME + "new", "/home/lorenzo/aaa.owl", ONTO_IRI, true);

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

       Cone c = new Cone( ontoRef);
        c.shouldAddTime( true);
        c.setCenter( .3f, .3f, .3f);
        c.setAxis( .0f, .1f, .0f);
        c.setApex( .2f, .3f, .4f);
        c.setRadius( .1f);
        c.setHeight( .05f);
        objects.add( c);

        System.out.println( "Object " + objects);
        System.out.println("4 ----------------------------------------------");



        // check recognition and learn if is the case
        SpatialSimplifier simplifier2 = new SpatialSimplifier( objects);
        //SceneRepresentation recognition2 = new SceneRepresentation( simplifier2, ontoRef);
        FullSceneRepresentation recognition2 = new FullSceneRepresentation( simplifier2, ontoRef);
        System.out.println( "Recognised with best confidence: " + recognition2.getRecognitionConfidence() + " should learn? " + recognition2.shouldLearn());
        if ( recognition2.shouldLearn()) {
            System.out.println("Learning.... ");
            recognition2.learn("Scene2");
        }


        System.out.println( "Recognised with best confidence: " + recognition2.getRecognitionConfidence() + " should learn? " + recognition2.shouldLearn());
        System.out.println( "Best recognised class: " + recognition2.getBestRecognitionDescriptor());
        System.out.println( "Other recognised classes: " + recognition2.getSceneDescriptor().getTypeIndividual());

        System.out.println("5 ----------------------------------------------");



        Set<SceneClassDescriptor> recognitionClasses2 = recognition2.getSceneDescriptor().buildTypeIndividual();
        for ( SceneClassDescriptor cl1 : recognitionClasses2) {
            for (SceneClassDescriptor cl2 : recognitionClasses2) {
                if (!cl1.equals(cl2)) {
                    System.out.println(" is " + cl1.getInstance().getIRI().getRemainder().get() +
                            " subclass of " + cl2.getInstance().getIRI().getRemainder().get() + "? " + cl1.getSubConcept().contains(cl2.getInstance()));
                    System.err.println(cl1);
                }
            }
        }


        SceneIndividualDescriptor sceneIndDescr2 = new SceneIndividualDescriptor("Sn-2", ontoRef);
        sceneIndDescr2.readSemantic();
        for( MORAxioms.DataSemantic l : sceneIndDescr2.getDataSemantics()){
            String f2 =  l.getValues().toString().replace("{","").replace("}","");
            Float ff2 = Float.valueOf(f2);
            System.out.println(l.getSemantic() + " " +ff2);
        }

        sceneIndDescr2.addData( DATA_PROPERTY.STORING_COUNTER, 1);
        sceneIndDescr2.writeSemantic();


        sceneIndDescr2.addData( "hasRetrievingCounter", 9);
        sceneIndDescr2.writeSemantic();




        System.out.println("6 ----------------------------------------------");


        recognition2.getSceneDescriptor().removeTypeIndividual( "Scene");
        recognition2.getSceneDescriptor().writeSemantic();
        // clean ontology
   //    ontoRef.removeIndividual( recognition2.getSceneDescriptor().getInstance());
        for ( GeometricPrimitive i : objects)
            ontoRef.removeIndividual( i.getInstance());
        ontoRef.synchronizeReasoner();

        ontoRef.saveOntology("/home/lorenzo/aaa.owl");
        // load ontology
        ontoRef = OWLReferencesInterface.OWLReferencesContainer.newOWLReferenceFromFileWithPellet(
                ONTO_NAME + "newNew", "/home/lorenzo/aaa.owl", ONTO_IRI, true);


        // augment the scene

        objects.clear();

        // define objects
         s = new Sphere( ontoRef);
        s.shouldAddTime( true);
        s.setCenter( .3f, .3f, .3f);
        s.setRadius( .1f);
        objects.add( s);
/*
        s = new Sphere( ontoRef);
        s.shouldAddTime( true);
        s.setCenter( .3f, .3f, .3f);
        s.setRadius( .1f);
        objects.add( s);

        Cone c2 = new Cone( ontoRef);
        c2.shouldAddTime( true);
        c2.setCenter( .11f, .4f, .6f);
        c2.setAxis( .5f, .2f, .2f);
        c2.setApex( .3f, .5f, .2f);
        c2.setRadius( .2f);
        c2.setHeight( .06f);
        objects.add( c2);*/

        Cone c2 = new Cone( ontoRef);
        c2.shouldAddTime( true);
        c2.setCenter( .11f, .4f, .6f);
        c2.setAxis( .5f, .2f, .2f);
        c2.setApex( .3f, .5f, .2f);
        c2.setRadius( .2f);
        c2.setHeight( .06f);
        objects.add( c2);

         p = new Plane( ontoRef);
        p.shouldAddTime( true);
        p.setAxis( .5f, .4f, .1f);
        p.setCenter( .3f, .1f, .1f);
        p.setHessian( .5f);
        objects.add( p);

        Cylinder ci = new Cylinder( ontoRef);
        ci.shouldAddTime( true);
        ci.setCenter( .4f, .5f, .6f);
        ci.setApex( 0f, 0f, 1f);
        ci.setAxis( 0f, 0f, 1f);
        ci.setRadius( .2f);
        ci.setHeight( .06f);
        objects.add( ci);


        System.out.println( "Object " + objects);
        System.out.println("44 ----------------------------------------------");

        // check recognition and learn if is the case
        SpatialSimplifier simplifier3 = new SpatialSimplifier( objects);

        //SceneRepresentation recognition3 = new SceneRepresentation( simplifier3, ontoRef);
        FullSceneRepresentation recognition3 = new FullSceneRepresentation( simplifier3, ontoRef);
      //  ontoRef.saveOntology("/home/lorenzo/aaa.owl");
        System.out.println( "Recognised with best confidence: " + recognition3.getRecognitionConfidence() + " should learn? " + recognition3.shouldLearn());
        if ( recognition3.shouldLearn()) {
            System.out.println("Learning.... ");
            recognition3.learn("Scene-3");
        }



        System.out.println( "Recognised with best confidence: " + recognition3.getRecognitionConfidence() + " should learn? " + recognition3.shouldLearn());
        System.out.println( "Best recognised class: " + recognition3.getBestRecognitionDescriptor());
        System.out.println( "Other recognised classes: " + recognition3.getSceneDescriptor().getTypeIndividual());

        System.out.println("55 ----------------------------------------------");


        Set<SceneClassDescriptor> recognitionClasses3 = recognition3.getSceneDescriptor().buildTypeIndividual();
        for ( SceneClassDescriptor cl1 : recognitionClasses3) {
            for (SceneClassDescriptor cl2 : recognitionClasses3) {
               // Set<SceneClassDescriptor> a = cl1.buildSuperConcept();
                if (!cl1.equals(cl2)) {
                    System.out.println(" is " + cl1.getInstance().getIRI().getRemainder().get() +
                            " subclass of " + cl2.getInstance().getIRI().getRemainder().get() + "? " + cl1.getSubConcept().contains(cl2.getInstance()));
                    System.err.println(cl1);
                }
            }
        }

        recognition3.getSceneDescriptor().addData(DATA_PROPERTY.SCORE, 0.8);

        //  sceneIndDescr.addData( DATA_PROPERTY.SCORE, 0.4);
        recognition3.getSceneDescriptor().addData("DATA_PROPERTY.STORING_COUNTER", 60);

        recognition3.getSceneDescriptor().writeSemantic();


        float hascore=0;

        SceneIndividualDescriptor sceneIndDescr = new SceneIndividualDescriptor("Sn-3", ontoRef);
        sceneIndDescr.readSemantic();
        for( MORAxioms.DataSemantic l : sceneIndDescr.getDataSemantics()){
            String f =  l.getValues().toString().replace("{","").replace("}","");
            Float ff = Float.valueOf(f);
            if (l.getSemantic().toString().contains(DATA_PROPERTY.SCORE)) {
                 hascore = ff;
                System.out.println(DATA_PROPERTY.SCORE + " " + ff);
            }
        }
        hascore+=1;
        recognition3.getSceneDescriptor().removeData(DATA_PROPERTY.SCORE);

        recognition3.getSceneDescriptor().addData(DATA_PROPERTY.SCORE, hascore);
        recognition3.getSceneDescriptor().writeSemantic();


        //sceneIndDescr.addData( "DATA_PROPERTY.STORING_COUNTER", 4);
        sceneIndDescr.addData( "hasRetrievingCounter", 4);
        sceneIndDescr.writeSemantic();




        // clean ontology
        recognition3.getSceneDescriptor().removeTypeIndividual( "Scene");
        recognition3.getSceneDescriptor().writeSemantic();

    /*    ontoRef.removeIndividual( recognition3.getSceneDescriptor().getInstance());*/
        for ( GeometricPrimitive i : objects)
            ontoRef.removeIndividual( i.getInstance());


            ontoRef.removeIndividual( recognition0.getSceneDescriptor().getInstance());
        ontoRef.synchronizeReasoner();





        System.out.println("66 ----------------------------------------------");






        ontoRef.saveOntology("/home/lorenzo/test.owl");


    }
}
