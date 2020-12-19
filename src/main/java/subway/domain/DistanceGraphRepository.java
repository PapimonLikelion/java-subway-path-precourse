package subway.domain;

import org.jgrapht.alg.shortestpath.DijkstraShortestPath;
import org.jgrapht.graph.DefaultWeightedEdge;
import org.jgrapht.graph.WeightedMultigraph;

import java.util.Collections;
import java.util.List;

public class DistanceGraphRepository {
    private static final WeightedMultigraph<Station, DefaultWeightedEdge> distanceGraph =
            new WeightedMultigraph(DefaultWeightedEdge.class);

    public static void addStation(Station station) {
        distanceGraph.addVertex(station);
    }

    public static void addTimeBetweenStations(Station source, Station destination, int time) {
        distanceGraph.setEdgeWeight(distanceGraph.addEdge(source, destination), time);
    }

    public static List<Station> findShortestPath(Station source, Station destination) {
        DijkstraShortestPath dijkstraShortestPath = new DijkstraShortestPath(distanceGraph);
        List<Station> shortestPath = null;
        try {
            shortestPath = dijkstraShortestPath.getPath(source, destination).getVertexList();
        } catch (NullPointerException e) {
            return null;
        }
        return Collections.unmodifiableList(shortestPath);
    }

    public static int getEdgeDistance(Station source, Station destination) {
        return (int) distanceGraph.getEdgeWeight(distanceGraph.getEdge(source, destination));
    }
}
