# Sorting

This assignment is about sorting lists of numbers.  The first part implements the "Bubble" sort.  This is probably the easiest sort to implement and one of the slowest.  The idea is that the program goes through the list of unsorted numbers and then the nodes "bubble up" until they are in the right place.  It has order n<sup>2</sup>.

In the second part of the assignment the goal is to compare the times for three different sorting algorithms.  This compares the "Bubble", "Heap" and "Merge" sorts.  I chose the Heap and Merge sorts because I had never done either one before.

The Merge sort works by splitting the list in half, sorting each half and then Merging the two halves so that the result is sorted.  It is done recursively.  The order for the algorithm is generally log<sub>2</sub>(n).  

For the Heap sort, start with the definition of a Heap:  A Heap is a Left Balanced Binary tree in which the largest node is the Root.  This is also true for all subtrees.  The Heap sort first moves the nodes around in a Left Balanced Binary Tree until it is a Heap. One then swaps the Root into the last unwalled position in the tree, walls off the newly swapped (former root) node and reheaps the remaining elements.  It also has order log<sub>2</sub>(n). 

The comparison program compares the speeds of the Bubble, Merge and Heap sorts.  It was run for random lists of integers containing 100,000, 200,000 and 1,000,000 integers.  I did not write the comparison program, just the sorting functions.  It is straight-forward program however.  The longest for any of the sorts was the Bubble sort for 1,000,000 which took about 47 minutes.  Most of the other runs had times in msec.  Even for 1,000,000 random integers the Heap and Merge sorts took under 240 msec.
