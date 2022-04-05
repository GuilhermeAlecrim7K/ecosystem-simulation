# Ecosystem simulation

This is an assignment of an Object Oriented Programming class in college. It was so much fun that I decided to upload it here.

The challenge was:

*Write a program in Java to simulate an ecosystem containing two types of creatures: bears and fish.*
*The ecosystem consists of a river, which is modeled after a relatively large vector (at least 100 elements). Each element of the vector can be a Bear, a Fish or null.*
*For each interval, based on a random process, each animal tries to move to an adjacent position of the vector or stay where they are. If two animals of the same type are about to collide in the same cell, then both stay where they are, but a new instance of the same kind of animal must be put in a random empty cell (that is, previously filled with null). If a Bear and a Fish collide, the Fish dies (that is, it disappears).*
*The simulation ends when there are no more Fish in the river.*