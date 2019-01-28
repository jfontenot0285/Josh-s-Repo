@/modules
modules contains the class modules and functions called from main.py

################
Included Modules
################
statsclass.py
=============
Includes superclass "Stats" which contains attributes that assign specific stats to a player or weapon object and getter and setter methods for easy access for changing those attributes

weaponclass.py
==============
Includes class "Weaponstats" which is a subclass of "Stats". Class "Weaponstats"inherits and overrides attributes 'St' and 'At' from Stats class, and includes 2 new attributes: 'Da' and 'Ap'. The remaining stats are not used by Weaponstats class and are set to 0.

functions.py
============
Includes all functions associated with running the program such as rolling dice, assigning stats, etc.


