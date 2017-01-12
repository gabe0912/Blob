# Blob
=======================

Bridgewater coding challenge #2
> A Blob is a shape in two-dimensional integer coordinate space where all cells have at least one
adjoining cell to the right, left, top, or bottom that is also occupied. Given a 10x10 array of
boolean values that represents a Blob uniformly selected at random from the set of all possible
Blobs that could occupy that array, write a program that will determine the Blob boundaries.
Optimize first for finding the correct result, second for performing a minimum number of cell
Boolean value reads, and third for the elegance and clarity of the solution. 


How to get the code:
--------------------

    $ git clone https://github.com/gabe0912/Blob.git
    $ cd Blob

How to run the program:
-----------------------
    $ java -jar out/artifacts/blob_jar/blob.jar inputBlob.txt
    
How to see the source code:
---------------------------
https://github.com/gabe0912/Blob/tree/master/src/main/java

Main-Class: BlobBoundary.java

Clarifications/Assumptions:
---------------------------

1. Blobs are a significant percentage of the entire matrix, so walking the edge should be efficient enough
1. We start "walking" clockwise from the origin

Limitations:
-----------

1. Could be more optimal by assuming blobs are always connected - this would lower the number of cells read
1. Could potentially also save reads by iterating counter-clockwise once initial position is found since we already know elements on the left, top, and top-left positions must be clear
