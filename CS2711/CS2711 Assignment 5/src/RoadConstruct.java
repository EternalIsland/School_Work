/**
 * @author Matthew Hynes(201200318)
 * @date 20/11/2013
 * 
 * Please refer to README file for instructions on use.
 */

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;

import org.jgrapht.alg.KruskalMinimumSpanningTree;
import org.jgrapht.graph.DefaultWeightedEdge;
import org.jgrapht.graph.SimpleWeightedGraph;

public class RoadConstruct {

	private static BufferedReader rd;
	private static SimpleWeightedGraph<String, DefaultWeightedEdge> graph;

	public static void main(String[] args) {

		File f = new File(args[0]);
		graph = new SimpleWeightedGraph<String, DefaultWeightedEdge>(
				DefaultWeightedEdge.class);

		/**
		 * Parsing the text file
		 */
		try {
			rd = new BufferedReader(new FileReader(f));

			String s = "";
			String[] roads, cost;
			DefaultWeightedEdge d;
			while (!(s = rd.readLine()).equals("")) {
				graph.addVertex(s);
			}
			while ((s = rd.readLine()) != null) {
				roads = s.split(" to ");
				cost = roads[1].split(" at ");
				d = graph.addEdge(roads[0], cost[0]);

				graph.setEdgeWeight(d, Double.parseDouble(cost[1]));
			}

		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}

		// converts graph into a minimum spanning tree using Kruskal's algorithm
		KruskalMinimumSpanningTree<String, DefaultWeightedEdge> krusk = new KruskalMinimumSpanningTree<String, DefaultWeightedEdge>(
				graph);

		if (krusk.getEdgeSet().size() != graph.edgeSet().size()) {
			System.out.println("Cannot connect all roads");
		} else {
			System.out.println(krusk.getEdgeSet());
		}
	}
}
