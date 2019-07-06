package it.emarolab.sit;

public interface Dictionary_reasoner extends SITBase {

    /**
     * The name of the data property used to define the
     * the score of each individuals Scene in the ontology.
     */
    String SCORE = "hasScore";

    /**
     * The name of the data property used to define the
     * the storing counter of each individuals Scene in the ontology.
     */
    String STORING_COUNTER = "hasStoringCounter";

    /**
     * The name of the data property used to define the
     * retrieving counter of each individuals Scene in the ontology.
     */
    String RETRIEVING_COUNTER = "hasRetrievingCounter";
    /**
     * The prefix used for all the individuals
     * that define an abstract scene representation
     * (i.e.: belonging to the {@link SITBase.CLASS#SPHERE}).
     */
    String SCENE = "Sn-";



}
