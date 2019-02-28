package it.emarolab.sit;

import it.emarolab.amor.owlDebugger.Logger;
import it.emarolab.amor.owlInterface.OWLReferences;
import it.emarolab.amor.owlInterface.OWLReferencesInterface;
import it.emarolab.amor.owlInterface.SemanticRestriction;
import it.emarolab.owloop.aMORDescriptor.MORAxioms;
import it.emarolab.sit.owloopDescriptor.SceneClassDescriptor;
import it.emarolab.sit.owloopDescriptor.SceneIndividualDescriptor;
import it.emarolab.sit.realObject.*;
import it.emarolab.sit.sceneRepresentation.FullSceneRepresentation;
import org.apache.jena.base.Sys;
import org.semanticweb.owlapi.model.OWLOntologyChange;

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
public class TestMain
        implements SITBase{

    private static final String ONTO_NAME = "ONTO_NAME"; // an arbitrary name to refer the ontology

    public static void main(String[] args){

        // load ontology
        OWLReferences ontoRef = OWLReferencesInterface.OWLReferencesContainer.newOWLReferenceFromFileWithPellet(
                ONTO_NAME, ONTO_FILE_SCORE, ONTO_IRI, true);

        // suppress aMOR log
        Logger.setPrintOnConsole( false);

        // initialise objects
        Set< GeometricPrimitive> objects = new HashSet<>();


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


        System.out.println( "Object " + objects);
        System.out.println("1 ----------------------------------------------");


        SpatialSimplifier simplifier = new SpatialSimplifier( objects);
        FullSceneRepresentation recognition = new FullSceneRepresentation( simplifier, ontoRef);

        //   Test_nuovo primaScena= new Test_nuovo(simplifier, ontoRef, objects);

        Test_nuovo primaScena= new Test_nuovo(recognition, ontoRef, objects);

       /* // load ontology
        ontoRef = OWLReferencesInterface.OWLReferencesContainer.newOWLReferenceFromFileWithPellet(
                ONTO_NAME + "new", "/home/maren/test_nuovo.owl", ONTO_IRI, true);


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


        simplifier = new SpatialSimplifier( objects);


        recognition = new FullSceneRepresentation( simplifier, ontoRef);


        Test_nuovo secondaScena= new Test_nuovo(recognition, ontoRef, objects);

*/


/*

        // load ontology
        ontoRef = OWLReferencesInterface.OWLReferencesContainer.newOWLReferenceFromFileWithPellet(
                ONTO_NAME + "newnew", "/home/maren/test_nuovo.owl", ONTO_IRI, true);
*/
/*
        simplifier = new SpatialSimplifier( objects);
        Test_nuovo teryaScena= new Test_nuovo(simplifier, ontoRef, objects);



        // load ontology
        ontoRef = OWLReferencesInterface.OWLReferencesContainer.newOWLReferenceFromFileWithPellet(
                ONTO_NAME + "newnewnew", "/home/maren/test_nuovo.owl", ONTO_IRI, true);

        simplifier = new SpatialSimplifier( objects);
        Test_nuovo quartaScena= new Test_nuovo(simplifier, ontoRef, objects);




        // load ontology
        ontoRef = OWLReferencesInterface.OWLReferencesContainer.newOWLReferenceFromFileWithPellet(
                ONTO_NAME + "newnewnewnew", "/home/maren/test_nuovo.owl", ONTO_IRI, true);
*//*



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

        System.out.println( "Object " + objects);
        System.out.println("4 ----------------------------------------------");





        // check recognition and learn if is the case
        simplifier = new SpatialSimplifier( objects);
        //SceneRepresentation recognition2 = new SceneRepresentation( simplifier2, ontoRef);
        recognition = new FullSceneRepresentation( simplifier, ontoRef);




        simplifier = new SpatialSimplifier( objects);
        Test_nuovo Nuova_scena= new Test_nuovo(recognition, ontoRef, objects);

*/

/*       ////////////////////////////Forgetting ////////////////////

        SceneClassDescriptor remover= new SceneClassDescriptor("TestScene-0", ontoRef);
        remover.readSemantic();
        remover.getDefinitionConcept().clear();
        remover.writeSemantic();
        remover.addSuperConcept( "Trash");
        remover.writeSemantic();

        System.out.println( " $$$$$ " + remover.getDefinitionConcept());


*/


/*
      //  remover.readSemantic();
 //    OWLOntologyChange ontochange = ontoRef.removeClass("TestScene-0");
 //    System.out.println( "///////////--------////" + ontochange  );

//        System.out.println( "///////////////" + remover.getOWLClass("TestScene-1").getClass() + "********* "+ remover.getGround()  );
/*
      OWLOntologyChange ontochange = ontoRef.removeSubClassOf("Orientable", "GeometricPrimitive" );

      ontoRef.getOWLOntology().applyDirectChange(ontochange);


      System.out.println( "///////////////" + remover.toString() + "000000000"+remover. );

     //   OWLOntologyChange ontochange = remover.getOntology().removeClass("TestScene-1");

        OWLOntologyChange ontochange =     ontoRef.removeClass(remover.getGround().getGroundInstance());
        ontoRef.getOWLOntology().applyDirectChange(ontochange);

//remover.writeSemantic();


*/

/*
        ontoRef = OWLReferencesInterface.OWLReferencesContainer.newOWLReferenceFromFileWithPellet(
                ONTO_NAME + "newnnn", "/home/maren/test_nuovo.owl", ONTO_IRI, true);


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

        s = new Sphere( ontoRef);
        s.shouldAddTime( true);
        s.setCenter( .3f, .3f, .3f);
        s.setRadius( .1f);
        objects.add( s);


        simplifier = new SpatialSimplifier( objects);
        //SceneRepresentation recognition2 = new SceneRepresentation( simplifier2, ontoRef);
        recognition = new FullSceneRepresentation( simplifier, ontoRef);




        simplifier = new SpatialSimplifier( objects);
         Nuova_scena= new Test_nuovo(recognition, ontoRef, objects);

*/


        ontoRef = OWLReferencesInterface.OWLReferencesContainer.newOWLReferenceFromFileWithPellet(
                ONTO_NAME + "newnnnnn", "/home/maren/test_nuovo.owl", ONTO_IRI, true);


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

/*
        ci = new Cylinder( ontoRef);
        ci.shouldAddTime( true);
        ci.setCenter( .4f, .5f, .6f);
        ci.setApex( 0f, 0f, 1f);
        ci.setAxis( 0f, 0f, 1f);
        ci.setRadius( .2f);
        ci.setHeight( .06f);
        objects.add( ci);

*/



        SpatialSimplifier simplifier2 = new SpatialSimplifier( objects);
        FullSceneRepresentation recognition2 = new FullSceneRepresentation(simplifier2,ontoRef);
       // recognition = new FullSceneRepresentation( simplifier, ontoRef);
        SceneClassDescriptor remover = new SceneClassDescriptor("TestScene-0", ontoRef);


        String noDel  = "TestScene-0";


        // simplifier = new SpatialSimplifier( objects);
        Test_nuovo Nuova_scena= new Test_nuovo(recognition2, ontoRef, objects);

/*
        removeNode ("TestScene-0", ontoRef)

        public void removeNode (String noDel)
        {

            SceneClassDescriptor remover = new SceneClassDescriptor(noDel, this);
            remover.readSemantic();
            remover.getDefinitionConcept().clear();
            remover.writeSemantic();
            remover.addSuperConcept("Trash");
            remover.writeSemantic();
            System.out.println( " $$$$$ " + remover.getDefinitionConcept());
        }
/*

        ///////Get branch////////////



        SceneClassDescriptor remover= new SceneClassDescriptor("TestScene-4", ontoRef);


        remover.readSemantic();

        System.out.println("---------remover sub: "+remover.getSubConcept().toString()+  "-------remover super"+remover.getSuperConcept().toString());

        MORAxioms.Concepts csub  =remover.getSubConcept();
        MORAxioms.Concepts csup  =remover.getSuperConcept();

        /////////////
        float ad=1;
        float ac=1;
        float as=1;
        float ar=1;


        float jd=1;
        float sj=1;
        float rj=1;

        Object[] objects1 = csub.toArray();

 /*       MORAxioms.Concepts[] scosub = new MORAxioms.Concepts[csub.size()];

        for (i:csub.size()) {
            scosub[i]= csub.toArray()
        }
        float csi= ad*jd+ ac +as*sj+ar*rj;

*/
        ontoRef.synchronizeReasoner();


        System.out.println("666----------------------------------------------");


        ontoRef.saveOntology("/home/maren/test_nuovo.owl");




     /*   ontoRef = OWLReferencesInterface.OWLReferencesContainer.newOWLReferenceFromFileWithPellet(
                ONTO_NAME + "newnewnewnewnew", "/home/maren/test_nuovo.owl", ONTO_IRI, true);

        simplifier = new SpatialSimplifier( objects);
        Test_nuovo sestaScena= new Test_nuovo(simplifier, ontoRef, objects);
*/
    }
}
