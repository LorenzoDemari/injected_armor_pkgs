# injected_armor
an extention of [ARMOR](https://arxiv.org/abs/1706.10151) with OWLOOP and the injected service SIT.

# Overview 

This repository contains a [ROSJAVA](http://wiki.ros.org/rosjava) packages for managing OWL ontologies in a robotic architecture.
The repository contains three ROS packages:
- [injected_armor_msgs](https://github.com/EmaroLab/injected_armor_pkgs/tree/master/injected_armor_msgs): contains the definition of the messages for interacting with the ontology through ARMOR ROS service.
- [injected_armor](https://github.com/EmaroLab/injected_armor_pkgs/tree/master/injected_armor): contains the services and libraries for interacting with OWL ontologies.
- [armor_py_client_api](https://github.com/EmaroLab/injected_armor_pkgs/tree/master/armor_py_client_api): contains an example, a test script, and common utility fot using ARMOR from a Python ROS client. 

The `injected_armor` package contains three modules:
- [amor](https://github.com/EmaroLab/injected_armor_pkgs/tree/master/injected_armor/amor): is the Java library that interacts with OWL API and Reasoners in a trade safe manner. This is the core for using OWL ontologies.
- [owloop](https://github.com/EmaroLab/injected_armor_pkgs/tree/master/injected_armor/owloop): is an extension of `amor` that allows to interact with ontology in an Object Oriented Programming manner.
- [armor](https://github.com/EmaroLab/injected_armor_pkgs/tree/master/injected_armor/armor): is a ROS service that depends on `amor` and `injected_armor_msgs` for allow ROS clients to manipulate ontologies from other packages.
- [sit](https://github.com/EmaroLab/injected_armor_pkgs/tree/master/injected_armor/sit): is a library that implements the Scene Identification and Tagging (SIT) algorithm. It depends on `owloop` (which consequentially depends on `amor`). This library should be used by `armor` in order to inject a new service. 

Please check the `README` file and `Doc` folder inside each components of the above lists for more information. 
All of them have been imported from their original repository, which is linked inside the documentation of each module.

# Installation

This repository must be a child of the src folder in your workspace to reach the maven repository (set in the `build.gradle` inside each module as `../../../../devel/share/maven/`).

Run `catkin_make` and test the basic armor service with
``roslaunch armor_py_client_api armorTest.launch``

# Todo

A sit test is starting (at line 46 in [this file](https://github.com/EmaroLab/injected_armor_pkgs/blob/master/injected_armor/armor/src/main/java/it/emarolab/armor/ARMORMainService.java)) by default as an *hello world* example, where you should set the correct path of the ontology (at line 33 in [this file](https://github.com/EmaroLab/injected_armor_pkgs/blob/master/injected_armor/sit/src/main/java/it/emarolab/sit/SITBase.java)).

Those lines (toughener with the message definitions in `injected_armor_msgs`), whould be changed in order to allow a ROS client to call the SIT algorithm through an `armor` request.


# Prerequisites
- **Ubuntu Version 16.04.5 LTS (Xenial) release:**
  - Link to the Official [Release Ubuntu Site](http://releases.ubuntu.com/16.04/).
  - The above Ubuntu edition was required in our project scope in order to run easily all the packages.
- **ROS Kinetic Kame distribution:**
  - Link to the Wiki [ROS Kinetic Page](http://wiki.ros.org/kinetic).
  - ROS Kinetic Installation Instructions:
      - Select the Platform in which your ROS distribution will be installed – in our case “Ubuntu”.
      - Configure your Ubuntu repositories1 to allow "restricted", "universe", and "multiverse". 
      - Setup your computer to accept software from packages.ros.org:
      
      ```
      > sudo sh -c 'echo "deb http://packages.ros.org/ros/ubuntu $(lsb_release -sc) main"  >
      /etc/apt/sources.list.d/ros-latest.list'
      ```
      
      - Setup your keys:
      
      ```
      > sudo apt-key adv --keyserver hkp://ha.pool.sks-keyservers.net:80 --recv-key 
      1C365BD9FF1F717815A3895523BAEEB01FA116
      ```
      
      - Installation – You should make sure your Debian package is up-to-date:
      
      ```
      > sudo apt-get update
      ```
      
      - There are many different libraries and tools in ROS – For generalization we have chosen the most complete one among the four default configurations - **Desktop-Full Install**: `ROS, rqt, rviz, robot-generic libraries, 2D/3D simulators, navigation and 2D/3D perception `(but in practical you can chose the most suitable one for you):
      
      ```
      > sudo apt-get install ros-kinetic-desktop-full
      ```
      
      - Initialize `rosdep` – Before using ROS, you need to initialize rosdep. This allow you to easily install system dependencies for sources need to be compiled: 
      
      ```
      > sudo rosdep init
      > rosdep update
      ```
      
      - Tip: Every time you need to compile the code it’s required to source the setup.*sh file. If you want to avoid it, you can add the following instruction at the end of the hidden file “Home/.bashrc”:
      
      ```
      > source /opt/ros/kinetic/setup.bash
      ```
      
- **RosJava Version:**
  - Link: [http://wiki.ros.org/rosjava](http://wiki.ros.org/rosjava).
  - RosJava provides both a client library for ROS communications in java as well as growing list of core tools (e.g. tf, geometry) and drivers. 
  - What’s the goals of RosJava?
    - Provide a way for ROS people to build, package and use RosJava packages. 
    - Also provide a way for non-ROS people to just pull RosJava binaries for linking their own projects to a ROS robot.
  - Build Tools:
    - *Java* - This support at the moment is for the supported openjdk on the ROS Ubuntu platforms of reference.
    - *Gradle* - It’s the tool used for managing single and multiple Java project builds. CMake would be its equivalent for C++ projects. It provides a powerful scripting language as well as plugin support for a variety of modules and extensions. 
    - *Maven* - The role of this platform comes in when we need to retrieve and deploy Java dependencies. It can be locally on your filesystem, in a github repo, on a private server.
    - *Catkin* - We use catkin only very minimally. Its role is to provide ROS package information and be the glue that sequences builds across multiple repositories, possibly – and here we fall in our case -  maintained by different people. 
  - Installation Methods:
    - *Source Installation* - a mostly self-contained source installation of core components and messages for a working catkin-rosjava development environment.
    - *Deb Installation (used)* - install Rosjava debs and overlay your Rosjava/android source workspace on top of these. 
    - *No ROS Installation* - use the Rosjava/message libraries in your own development environment via maven. 
  - Deb Installation:
    - *Debs* - All the rosjava debs are provided by a single rosjava metapackage: 
    
    ```
    > sudo apt-get install ros-kinetic-rosjava
    ```
    
    - *Your Own Sources* - You can now proceed to add your own source packages on top by creating an overlay on top of /opt/ros/kinetic (substitute words in bold to the correct field)  
    
    ```
    > mkdir -p ~/myjava/src
		  > cd ~/myjava/src
    > wstool init-j4 https://raw.githubusercontent.com/<USERNAME>/rosinstalls/kinetic-devel/ myjava.rosinstall
		  > source /opt/ros/kinetic/setup.bash
		  > cd ~/myjava
		  > rosdep update
		  > rosdep install --from-paths src -i -y
		  > catkin_make
    ```
    
    - Tip: In the above macro section it was showed a shortcut whose role consisted of avoiding periodically the sourcing of  setup.*sh file. The same could be applied here with the code:
    
    ```
    > source ~/rosjava/devel/setup.bash
    ```
    
- **ARMOR package:**
  - Link: [https://github.com/EmaroLab/armor/](https://github.com/EmaroLab/armor/).
  - This management system for single and multi-ontology architecture allows to load, query and modify multiple ontologies. Although it’s ease to use, ARMOR1 provides a large share of OWL APIs functions and capabilities in a simple server-client architecture. The system’s goal is to erase the use of ontologies in robotics by bringing these features to developers working under ROS.
  - Installation from repositories:
    - Click on the green button “Clone or Download”
    - You can chose either “Download ZIP” or copy the link showed and, from the terminal, type:
    
    ```
    > git clone https://github.com/EmaroLab/armor.git 
    ```
    
    - You can immediately see the “armor” package in your source folder. 
    - The last step consist of modifying the external script link (able to include the buildscript block) and substitute it with another url, in order to configure correctly the maven repository . Go into  “**PATH_TO_YOUR_WORKSPACE**/src/injected_armor_pkgs/injected_armor/build.gradle”.
Inside this file **replace** the link:
    
    ```
    > https://github.com/rosjava/rosjava_bootstrap/raw/kinetic/buildscript.gradle 
    ```
    
    with:
    
    ```
    > https://github.com/rosjava/rosjava_bootstrap/raw/indigo/buildscript.gradle
    ```
    
- **AMOR package** (optional installation):
  - Link:[https://github.com/EmaroLab/multi_ontology_reference](https://github.com/EmaroLab/multi_ontology_reference).
  - As mentioned above, this belongs less capabilities than the ARMOR package; however, AMOR package is considered a complete library of helper functions for OWL ontologies.
  - Installation from repositories:
    -  We can chose either “Download ZIP” or copy the link showed and, from the terminal, type:
    
    ``` 
    > git clone https://github.com/EmaroLab/multi_ontology_reference.git 
    ```
    
    - We can see the “armor” package in our source folder. 
    
- **Intellij IDEA:**
  - Link:[https://www.jetbrains.com/idea/](https://www.jetbrains.com/idea/).
  - The worth aspect of this clever Java IDE is characterized by the maneuverability of surfing among the classes, which makes the job easier for a developer. This peculiarity guaranteed us a key role in our project caused by the huge amount of classes.
  - Download steps:
    - Sign in with e-mail and password (in case no account has been created yet, register it).
    - The “Ultimate” version is given only for academic purpose or under payment. Otherwise, the software is given under free license (with less characteristics).
    - Download it.
    - In the first execution of the program it’s required to sign in and configure Project Setting.
    - Furthermore, it would be a good practice launch the IDE from command line in order to use it concurrently with ROS. For doing so, type:
    
    ```
    > bash -i -c "PATH_TO_IDEA/bin/idea.sh" %f
    ```
    
- **Protégé:**
  - Link:[https://protege.stanford.edu/download/protege/4.0/installanywhere/](https://protege.stanford.edu/download/protege/4.0/installanywhere/).
  - Linux Instructions:
    - Download from the link above, choosing the correct version for the OS in use.
    - After downloaded, open a shell command and type cd for opening the directory in which we want the installation folder.
    - Type onto the shell command:
    
    ```
    > sh ./install_protege_4.0.2.bin
    ```
    
    - Then, inside the above folder, type for running:
    
    ```
    >./run.sh
    ```
    


## Contacts

For comment, discussions or support refer to this git repository open issues before to contact us (more detailed contacts to the authors is further specified in each module)
 - [luca.buoncompagni@edu.unige.it](mailto:luca.buoncompagni@edu.unige.it).


