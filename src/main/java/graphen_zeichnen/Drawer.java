package graphen_zeichnen;

public class Drawer {

    final static String punkt = "X";
    final static String linke_line = "|";
    final static String untere_line = "---";
    final static String platz_halter = "  ";

    static Coordinate2D[] resort_table(Coordinate2D[] graph) {
        int size = graph.length;

        for (int step = 1; step < size; step++) {
            Coordinate2D key = graph[step];
            int j = step - 1;

            while (j >= 0 && key.y > graph[j].y) {
                graph[j + 1] = graph[j];
                --j;
            }
            graph[j + 1] = key;
        }
        return graph;
    }

    static void print_graphen(Coordinate2D[] graph, int breite) {
        System.out.println("/\\");
        System.out.print(linke_line);
        for (int y = 0; y < graph.length; y++) {
            Coordinate2D point = graph[y];
            int steps_right = point.x;
            if (y > 0) {
                if (point.y == graph[y - 1].y) {
                    steps_right = steps_right - graph[y - 1].x - 1;
                }
            }

            for (int i = 0; i < steps_right; i++) {
                System.out.print(platz_halter);
            }
            System.out.print(punkt);
            if (y != graph.length - 1) {
                int steps_down = point.y - graph[y + 1].y;
                if (steps_down == 0) {
                    continue;
                }
                System.out.println();
                for (int i = 1; i < steps_down; i++) {
                    System.out.println(linke_line);
                }
                System.out.print(linke_line);
            }
        }
        System.out.println();
        for (int i = 1; i < graph[graph.length - 1].y; i++) {
            System.out.println(linke_line);
        }
        System.out.println();
        for (int i = 0; i < breite; i++) {
            System.out.print(untere_line);
        }
        System.out.println(">");
    }

    static void draw(Coordinate2D[] orgial_graph) {
        int breite = orgial_graph[orgial_graph.length - 1].x;
        Coordinate2D[] resortet_graph = resort_table(orgial_graph);
        print_graphen(resortet_graph, breite);

    }
}
