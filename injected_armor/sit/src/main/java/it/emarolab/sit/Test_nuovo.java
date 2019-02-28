package it.emarolab.sit;

import it.emarolab.amor.owlInterface.OWLReferences;
import it.emarolab.owloop.aMORDescriptor.MORAxioms;
import it.emarolab.sit.owloopDescriptor.*;
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
public class Test_nuovo
        implements SITBase{


    public Test_nuovo(FullSceneRepresentation recognition, OWLReferences ontoRef, Set< GeometricPrimitive> objects) {

        //public Test_nuovo(SpatialSimplifier simplifier, OWLReferences ontoRef, Set< GeometricPrimitive> objects) {
        //  FullSceneRepresentation recognition = new FullSceneRepresentation( simplifier, ontoRef);
/*

SceneClassDescriptor remover= new SceneClassDescriptor("TestScene2", ontoRef);
remover.getOntology().removeClass("TestScene2");
*/

        recognition.getSceneDescriptor().addData("hasScore", 0.5);
        recognition.getSceneDescriptor().addData("hasStoringCounter", 0);
        recognition.getSceneDescriptor().addData("hasRetrievingCounter", 0);

        recognition.getSceneDescriptor().writeSemantic();


        System.out.println("Recognised with best confidence: " + recognition.getRecognitionConfidence() + " should learn? " + recognition.shouldLearn());
        System.out.println("Best recognised class: " + recognition.getBestRecognitionDescriptor());
        System.out.println("Other recognised classes: " + recognition.getSceneDescriptor().getTypeIndividual());
        float hascore = 0;
        int hasstoringcounter=0;
        int hasretrievingcounter=0;

        SceneIndividualDescriptor sceneIndDescr = new SceneIndividualDescriptor(recognition.getSceneIndName(), ontoRef);
        sceneIndDescr.readSemantic();

        System.out.println("getdatasemantic" + sceneIndDescr.getDataSemantics());

        for (MORAxioms.DataSemantic l : sceneIndDescr.getDataSemantics()) {
            String f = l.getValues().toString().replace("{", "").replace("}", "");
            if (l.getSemantic().toString().contains("hasScore")) {
                hascore = Float.valueOf(f);
                System.out.println("hasScore" + " "+ hascore + " " +recognition.getSceneIndName());
            }
            else  if (l.getSemantic().toString().contains("hasStoringCounter")) {
                hasstoringcounter  = (int) Integer.valueOf(f);

                System.out.println("hasStoringCounter" + " "+hasstoringcounter + " " +recognition.getSceneIndName());
            }
            else  if (l.getSemantic().toString().contains("hasRetrievingCounter")) {
                hasretrievingcounter = (int) Integer.valueOf(f);
                System.out.println("hasRetrievingCounter" + " " +hasretrievingcounter + " " +recognition.getSceneIndName());
            }
        }

        System.out.println("sono quaaaaaaaa " + recognition.getRecognitionConfidence());



        if (recognition.getRecognitionConfidence()==1)
        {


            System.out.println("sono quaaaaaaaa issimo " + hasstoringcounter);

            System.out.println(recognition.getBestRecognitionDescriptor().getIndividualClassified().toString());
            String name = recognition.getBestRecognitionDescriptor().getIndividualClassified().toString();
            name=name.replace("{", "").replace("}", "");
            System.out.println(name);

            SceneIndividualDescriptor bestRec = new SceneIndividualDescriptor(name, ontoRef);


            hasstoringcounter= (int) bestRec.readproperty("hasStoringCounter");

            hasstoringcounter++;

            System.out.println("hasStoringCounter dopo" + " "+ hasstoringcounter + " " );


            bestRec.removeData("hasStoringCounter");
            bestRec.addData("hasStoringCounter", hasstoringcounter);
            bestRec.writeSemantic();

        }


        // learn the new scene if is the case
        if (recognition.shouldLearn()) {
            System.out.println("Learning.... ");
            recognition.learn("TestScene-" + (recognition.getID() - 1));
        }


        recognition.getSceneDescriptor().writeSemantic();

        System.out.println("2 ----------------------------------------------");

        // check recognition after learning
        System.out.println("Recognised with best confidence: " + recognition.getRecognitionConfidence() + " should learn? " + recognition.shouldLearn());
        System.out.println("Best recognised class: " + recognition.getBestRecognitionDescriptor());
        System.out.println("Other recognised classes: " + recognition.getSceneDescriptor().getTypeIndividual());

        System.out.println("3 ----------------------------------------------");
        System.out.println("3 ----------------------------------------------");
        System.out.println("3 ----------------------------------------------");



      ObjectClassDescriptor ocd= new ObjectClassDescriptor(CLASS.PRIMITIVE, ontoRef);
      ocd.readSemantic();

      System.out.println("$$$$$$$$$$$$$"+ ocd.readSemantic());

      Set<ObjectIndividualDescriptor> objIndv = ocd.buildIndividualClassified();
      Set<GeometricPrimitive> objectsRead = ocd.buildScene(objIndv,ontoRef);

      System.out.println("$$$$$ " + objectsRead);


   /*   System.out.println( "§§§§" + ocd.getIndividualClassified());

      ocd.getSubConcept()

      try{
          Plane pp = new Plane( "P-1", ontoRef);
          pp.readSemantic();
          System.out.println("%%%%%% " + pp);
      }  catch (Exception e){
          System.out.println( "P1 not found");
      }*/

        // clean ontology
        //ontoRef.removeIndividual( recognition.getSceneDescriptor().getInstance());
        for (GeometricPrimitive i : objects)
            ontoRef.removeIndividual(i.getInstance());

        recognition.getSceneDescriptor().removeTypeIndividual("Scene");
        recognition.getSceneDescriptor().writeSemantic();



        ontoRef.synchronizeReasoner();

        System.out.println("4 ----------------------------------------------");

        ontoRef.saveOntology("/home/maren/test_nuovo.owl");

    }
}