package graphen_zeichnen;

import com.googlecode.lanterna.terminal.DefaultTerminalFactory;
import com.googlecode.lanterna.terminal.Terminal;
import java.io.IOException;

public class Drawer {
    int terminalRows, terminalColumns;
    Terminal terminal;
    char symbols = 'X';
    Coordinate2D pointOld;

    // https://www.geeksforgeeks.org/dda-line-generation-algorithm-computer-graphics/
    void drawPoint(Coordinate2D pointNew) throws IOException, InterruptedException {
        int dx = pointNew.x - pointOld.x;
        int dy = pointNew.y - pointOld.y;

        int steps = Math.abs(dx) > Math.abs(dy) ? Math.abs(dx) : Math.abs(dy);
        double xinc = (double) dx / steps;
        double yinc = (double) dy / steps;

        double x = pointOld.x;
        double y = pointOld.y;

        for (int i = 0; i <= steps; i++) {
            if (x >= 0 && x <= terminalColumns / 3 - 1 && y >= 0
                    && y <= terminalRows - 1) { // look if the point is on the screen
                terminal.setCursorPosition(((int) Math.round(x) + 1) * 3 + 1,
                        terminalRows - (int) Math.round(y) - 1);
                terminal.putCharacter(symbols);
            }
            x += xinc;
            y += yinc;
        }
        terminal.flush();
        // save the old Point to draw the line
        pointOld = pointNew;
    }

    // put cursor below the graph
    void close() throws IOException {
        terminal.setCursorPosition(terminalColumns, terminalRows);
        System.out.println();
    }

    Drawer() throws IOException {
        pointOld = new Coordinate2D(0, 0);

        DefaultTerminalFactory defaultTerminalFactory = new DefaultTerminalFactory();
        terminal = defaultTerminalFactory.createTerminal();
        var size = terminal.getTerminalSize();

        // make space for the Graph
        for (int i = 0; i < size.getRows(); i++) {
            System.out.println();
        }
        // get terminal size
        terminalRows = size.getRows() - 4;
        terminalColumns = size.getColumns() - 10;

        // draw horizontally axis
        for (int i = 0; i < terminalRows; i++) {
            terminal.setCursorPosition(0, i);
            if (terminalRows - i >= 0 && terminalRows - i <= 10) {
                terminal.putCharacter(' ');
            }
            terminal.putString(String.valueOf(terminalRows - i - 1) + " |");
        }
        // draw vertically axis
        int count = 0;
        for (int i = 4; i < terminalColumns; i += 3) {
            terminal.setCursorPosition(i, terminalRows);
            terminal.putString(String.valueOf(count) + " ");
            count++;
        }
    }
}
