package it.emarolab.sit.reasonerCore;

import it.emarolab.amor.owlInterface.OWLReferences;
import it.emarolab.sit.SITBase;
import it.emarolab.sit.owloopDescriptor.ObjectClassDescriptor;
import it.emarolab.sit.owloopDescriptor.ObjectIndividualDescriptor;
import it.emarolab.sit.realObject.*;

import java.util.HashSet;
import java.util.Set;


/**
 * Reads all the geometric primitives from the ontology and saves them into a vector
 * of objects.
 *
 */


public interface BuildScene {

   static Set<GeometricPrimitive> buildScene (Set<ObjectIndividualDescriptor> objIndv, OWLReferences ontoRef) {
        Set<GeometricPrimitive> objectsRead = new HashSet<>();
        for (ObjectIndividualDescriptor o : objIndv) {
            Set<ObjectClassDescriptor> objTypes = o.buildTypeIndividual();
            for (ObjectClassDescriptor obj : objTypes) {
                if (obj.getSubConcept().size() <= 1) {
                    String objTypeName = ontoRef.getOWLObjectName(obj.getGround().getGroundInstance());
                    try {
                        GeometricPrimitive primitive = null;
                        if (objTypeName.equals(SITBase.CLASS.SPHERE)) {
                            primitive = new Sphere(o.getGround().getGroundInstance(), ontoRef);
                          //  System.out.println("**********" + o.getGround().getGroundInstance());
                        } else if (objTypeName.equals(SITBase.CLASS.PLANE)) {
                            primitive = new Plane(o.getGround().getGroundInstance(), ontoRef);
                          //  System.out.println("**********" + o.getGround().getGroundInstance());
                        } else if (objTypeName.equals(SITBase.CLASS.CONE)) {
                            primitive = new Cone(o.getGround().getGroundInstance(), ontoRef);
                           // System.out.println("**********" + o.getGround().getGroundInstance());
                        } else if (objTypeName.equals(SITBase.CLASS.CYLINDER)) {
                            primitive = new Cylinder(o.getGround().getGroundInstance(), ontoRef);
                           // System.out.println("**********" + o.getGround().getGroundInstance());
                        }
                        primitive.readSemantic();
                        objectsRead.add(primitive);
                    } catch (Exception e) {
                        System.err.println("cannot read object " + objTypeName);
                    }
                }
            }
        }

        return objectsRead;
    }
    }
