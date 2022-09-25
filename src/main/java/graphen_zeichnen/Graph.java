package graphen_zeichnen;

import java.io.IOException;
import java.util.ArrayList;

class Graph {
    public Coordinate2D[] graph;

    public void printAsList() {
        for (Coordinate2D point : graph) {
            System.out.println("x: " + point.x + " | y: " + point.y);
        }
    }

    public void draw() throws IOException, InterruptedException {
        var drawer = new Drawer(graph[0]);
        for (int i = 0; i < graph.length; i++) {
            drawer.drawPoint(graph[i]);
        }
        drawer.close();
    }

    public Graph(ArrayList<Coordinate2D> points) {
        int length = points.size();
        Coordinate2D[] graph = new Coordinate2D[length];
        for (int i = 0; i < length; i++) {
            graph[i] = points.get(i);
        }
        this.graph = graph;
    }
}
