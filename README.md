<p align="center"> 
<img src="https://github.com/JJoeNapoli/injected_armor_pkgs/blob/master/Edited_sit_algorithm.jpeg">
</p>
<p align="center"> 
<img src="https://github.com/JJoeNapoli/injected_armor_pkgs/blob/master/Architecture_human_like_memory.jpeg">
</p>

# injected_armor
an extention of [ARMOR](https://arxiv.org/abs/1706.10151) with OWLOOP and the injected service SIT.

# Overview 

This repository contains a [ROSJAVA](http://wiki.ros.org/rosjava) packages for managing OWL ontologies in a robotic architecture.
The repository contains three ROS packages:
- [injected_armor_msgs](https://github.com/JJoeNapoli/injected_armor_pkgs/tree/master/injected_armor_msgs): contains the definition of the messages for interacting with the ontology through ARMOR ROS service.
- [injected_armor](https://github.com/JJoeNapoli/injected_armor_pkgs/tree/master/injected_armor): contains the services and libraries for interacting with OWL ontologies.
- [armor_py_client_api](https://github.com/JJoeNapoli/injected_armor_pkgs/tree/master/armor_py_client_api): contains an example, a test script, and common utility fot using ARMOR from a Python ROS client. 

The `injected_armor` package contains three modules:
- [amor](https://github.com/JJoeNapoli/injected_armor_pkgs/tree/master/injected_armor/amor): is the Java library that interacts with OWL API and Reasoners in a trade safe manner. This is the core for using OWL ontologies.
- [owloop](https://github.com/JJoeNapoli/injected_armor_pkgs/tree/master/injected_armor/owloop): is an extension of `amor` that allows to interact with ontology in an Object Oriented Programming manner.
- [armor](https://github.com/JJoeNapoli/injected_armor_pkgs/tree/master/injected_armor/armor): is a ROS service that depends on `amor` and `injected_armor_msgs` for allow ROS clients to manipulate ontologies from other packages.
- [sit](https://github.com/JJoeNapoli/injected_armor_pkgs/tree/master/injected_armor/sit): is a library that implements the Scene Identification and Tagging (SIT) algorithm. It depends on `owloop` (which consequentially depends on `amor`). This library should be used by `armor` in order to inject a new service. 


# Installation

This repository must be a child of the src folder in your workspace to reach the maven repository (set in the `build.gradle` inside each module as `../../../../devel/share/maven/`).

Run `catkin_make` and test the basic armor service with
``roslaunch armor_py_client_api armorTest.launch``


# Prerequisites
- **Ubuntu Version 16.04.5 LTS (Xenial) release:**
  - Link to the Official [Release Ubuntu Site](http://releases.ubuntu.com/16.04/).
  - The above Ubuntu edition was required in our project scope in order to run easily all the packages.
- **ROS Kinetic Kame distribution:**
  - Link to the Wiki [ROS Kinetic Page](http://wiki.ros.org/kinetic).
      
  - Tip: Every time you need to compile the code it’s required to source the setup.*sh file. If you want to avoid it, you can add the following instruction at the end of the hidden file “Home/.bashrc”:
      
      ```
      > source /opt/ros/kinetic/setup.bash
      ```
      
- **RosJava Version:**
  - Link: [http://wiki.ros.org/rosjava](http://wiki.ros.org/rosjava).
  - RosJava provides both a client library for ROS communications in java as well as growing list of core tools (e.g. tf, geometry) and drivers.  
  - Installation Methods:
    - *Source Installation* - a mostly self-contained source installation of core components and messages for a working catkin-rosjava development environment.
    - *Deb Installation (used)* - install Rosjava debs and overlay your Rosjava/android source workspace on top of these. 
    - *No ROS Installation* - use the Rosjava/message libraries in your own development environment via maven. 
  - Deb Installation:
    - *Debs* - All the rosjava debs are provided by a single rosjava metapackage: 
    
    ```
    > sudo apt-get install ros-kinetic-rosjava
    ```
    
  - Tip: In the above macro section it was showed a shortcut whose role consisted of avoiding periodically the sourcing of  setup.*sh file. The same could be applied here with the code:
    
    ```
    > source ~/rosjava/devel/setup.bash
    ```
    
- **ARMOR package:**
  - Link: [armor](https://github.com/JJoeNapoli/injected_armor_pkgs/tree/master/injected_armor/armor).
  - [README.MD](https://github.com/JJoeNapoli/injected_armor_pkgs/blob/master/injected_armor/armor/README.MD).
  - This management system for single and multi-ontology architecture allows to load, query and modify multiple ontologies. Although it’s ease to use, ARMOR1 provides a large share of OWL APIs functions and capabilities in a simple server-client architecture. The system’s goal is to erase the use of ontologies in robotics by bringing these features to developers working under ROS.

  
- **AMOR package** (optional installation):
  - Link:[amor](https://github.com/JJoeNapoli/injected_armor_pkgs/blob/master/injected_armor/amor).
  - [README.MD](https://github.com/JJoeNapoli/injected_armor_pkgs/blob/master/injected_armor/amor/README.MD).
  - As mentioned above, this belongs less capabilities than the ARMOR package; however, AMOR package is considered a complete library of helper functions for OWL ontologies.
  
  
    
- **Intellij IDEA:**
  - Link:[IDEA](https://www.jetbrains.com/idea/).
  - The worth aspect of this clever Java IDE is characterized by the maneuverability of surfing among the classes, which makes the job easier for a developer. This peculiarity guaranteed us a key role in our project caused by the huge amount of classes.
    
- **Protégé:**
  - Link:[Protégé](https://protege.stanford.edu/download/protege/4.0/installanywhere/).
  - The function of this software during development of rosjava classes has a key role thanks to the graphical aspect, which offers an intuitive comprehension of the node graph and how it works.  


## Contacts

For comment, discussions or support refer to this git repository open issues before to contact us (more detailed contacts to the authors is further specified in each module)
 - [luca.buoncompagni@edu.unige.it](mailto:luca.buoncompagni@edu.unige.it).


