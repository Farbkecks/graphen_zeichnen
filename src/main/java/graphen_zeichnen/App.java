package graphen_zeichnen;

public class App {
    public static void main(String[] args) throws Exception {
        String formula = "";
        switch (args.length) {
            case 0: {
                System.out.println("Bitte eine Formel angeben");
                System.exit(0);
            }
            case 1: {
                formula = args[0];
                break;
            }

            default: {
                System.out.println("Nur eine Formel angeben");
                System.exit(0);
            }
        }
        Graph graph = new Graph(Generator.generateBasicGraph(formula));
        graph.printAsList();
        graph.draw();
    }
}
