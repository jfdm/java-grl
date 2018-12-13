# A Quick and Dirty implementation of the GRL.

Here we present the GRL as a library.

The library has been designed to facilitate the creation of new modelling languages based on the GRL itself.

* The project is managed using gradle.
* There are minimal tests.
* See examples in tests on how to use.
* Documentation is sparse.

## Limitations

+ We do not model all constraints on links between actors and intentional elements.
  + With regards to dependencies we do not check:
    + At least one of the GRL linkable elements linked by a dependency link shall be an actor
definition or an intentional element contained in an actor definition or an indicator
contained in an actor definition.
    + If the source and destination linked by a dependency link are intentional elements or
indicators, then these intentional elements and indicators shall not be contained in the
same actor definition.
+ Indicators are not modelled.
+ Things that are not decomposable have fields that imply they are decomposable...
