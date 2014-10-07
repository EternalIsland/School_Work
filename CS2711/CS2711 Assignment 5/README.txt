This is a very picky program that uses the JGraph library. It must have an exactly perfect input in order to work.In order to use JGraph I had to reference the library in Eclipse and so I have only tested it in Eclipse itself. I have no idea if it will run from the command line.

It reads in a text file containing a list of verticies (communties) and makes an undirected weighted graph out of them.
It then reads the list of edges bewteen those vertcies and each edge's weight.

The input file must be formatted EXACTLY like this:

Vertex 1
Vertex 2
Vertex 3
.
.
.
Vertex n

vertex 1 to Vertex 3 at 5.0
Vertex 2 to Vertex 3 at 7.5

There must be a blank line after the list of communities, before the edges. The edges are written as "Vertex X to Vertex Y at COST". They must be written exactly this way.