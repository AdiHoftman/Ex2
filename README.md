# project of the POKEMON GAME


The project has two parts, in the first part we built a directed weighted graph and in the second part we get a “pokemon game”  and we need to efficient it, in this game the agents try to eat the pokemons as fast as they can, the agents run on a directed weighted graph created a graph with nodes, and make actions on that graph.
This project contains weighted graph data structure and implementation of several algorithms on weighted directed graph.
************************
[the pokemon game](https://github.com/AdiHoftman/Ex2#the-pokemon-game)

[the graph](https://github.com/AdiHoftman/Ex2#the-graph)


we will start to explain from the seconed part of the project, to make people more interest.
*********************
# the pokemon game:
in this section i will explain what actually is the **pokemon game**.
the pokemon game is really to create an efficient and smart algorithm that uses **threads and GUI** to represent us the game.
the algorithm need to be with high score but we have actually a limit of moves so you must do it smart, to make the thread go sleep and when to use them to move the agent
accros the map.
this game work by thread which activate **game.move()** and in that part we have to try and think what agent will go to which pokemon.
we use Jframe to show the game and the map to the user.


**********************
# the graph:
[1. NodeData](https://github.com/AdiHoftman/Ex2--OOP/blob/main/README.md#nodedata)

[2. EdgeData](https://github.com/AdiHoftman/Ex2--OOP/blob/main/README.md#edgedata)

[3. DWGraph_DS](https://github.com/AdiHoftman/Ex2--OOP/blob/main/README.md#dwgraph_ds)

[4. GeoLocation](https://github.com/AdiHoftman/Ex2--OOP/blob/main/README.md#geolocation)

[5. DWGraph_Algo](https://github.com/AdiHoftman/Ex2--OOP/blob/main/README.md#dwgraph_algo)

# NodeData:
This class represents the set of operations applicable on a node (vertex) in an (undirectional) weighted graph.

## NodeData objects:
int key – represents the id of the nodes.
geo_location location – represents the location of the nodes.
double weight – represents the weight of the nodes.
int tag – represents the temporal data of the nodes.
String info – represents the information of the nodes.

## Main Methods:
getKey() – return the id of the node.
getLocation() – return the location of the node.
setLocation(geo_location p) – change the location of the node.
getWeight() – return the weight of the node.
setWeight() – change the weight of then node.

# EdgeData:
This class represents the set of operations applicable on a directional edge in a directional weighted graph.

## EdgeData objects:
int src – represents which node the edge came from.
int dest – represents the destination of the edge.
double weight - represents how much time it take to run on the edge, higher weight means longer to pass.
String info – represents the information of the edge.
int tag – represents the temporal data of the edges.

## Main Methods:
getSrc() – return from which node of the edge came from.
getDest() – return destination of the edge.
getWeight() – return weight of the edge.
setWeight(double e) – change the weight of the edge.

# DWGraph_DS:
This class represents a directional weighted graph.
DWGraph_DS objects:
HashMap<Integer, node_data> vertices - represents a hash that get an id of a node.
HashMap<Integer,HashMap<Integer, edge_data>> edges - represents a hash that get all the edges in the graph.
HashMap<Integer,HashMap<Integer, edge_data>> neighbors – represents the node’s neighbors and the edges between them.
int nodeSize – represents the number of the nodes on the graph.
int edgeSize – represents the number of edges on the graph.
int mC – counts all the changes in the graph.

## Main Methods:
getNode(int key) – returns the node_data by the node_id, the complexity is O(1).
getEdge(int src, int dest) – returns the data of the edge(src, dest), null if none, the complexity is O(1).
addNode(node_data n) – adds a new node to the graph with the given node_data, the complexity is O(1).
connect(int src, int dest) – add a edge between node src to node dest and update the weight of the edge, the complexity is O(1).
getV() –  return all the nodes in the graph, the complexity is O(1).
getE(int node_id) – return all the edges that getting out from this node.
removeNode(int key) – delete the specific node from the graph and delete all the edges that getting in or out from it, and return the node that deleted, the complexity is O(k).
removeEdge(int src, int dest) – delete the edge from the graph, the complexity is O(1).
nodeSize() – return the number of nodes in the graph, the complexity is O(1).
edgeSize() – return the number of the edges in the graph, the complexity is O(1).
getMC() – return the number of changes in the graph.

# GeoLocation:
This class represents a geo location <x,y,z>, aka Point3D.
GeoLocation objects:
double x, double y, double z.
Main Methods :
distance(geo_location g) – calculate the distance between 2 points.

# DWGraph_Algo:
 This class represents some algorithms in a directed weighted graph.
DWGraph_Algo objects:
directed_weighted_graph graph – a graph that represents the DWGraph_DS and can uses it’s functions.
HashMap<Integer, HashMap<Integer, node_data>> aba – a HashMap that get the parent node for another node.

## Main Methods:
init(directed_weightd_graph g) – initialize the graph.
getGraph() – return the underlying in the graph.
copy() – return a deep copy by create a new graph and save all the object’s source graph in the new graph.
isConnected() - checks if the graph is connected or not connected, so we will go through all the nodes in the graph and checks if from one node to second node has a path, return true if and only if the graph is connected, base on BFS algorithm.
shortestPathDist(int src, int dest) - calculate the shortest path distance from src to dest and return the shortest path, base on Dijkstra’s algorithm.
shortestPath(int src, int dest) - calculate the shortest path distance from src to dest and return the List of nodes in the graph that passed of them until it arrive to dest.
save(String file) - saves the weighted graph to the given file name, return true if the file is save.
load(String file) – loads a graph to this graph algorithm, if it successful, the function return true, else, return false.



