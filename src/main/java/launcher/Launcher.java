
package launcher;

import javax.swing.SwingUtilities;

import utils.Utils;
import view.Window;
/**
The Launcher class is the entry point of the application. It creates a new instance of the Window class,
which is the main window of the application.
*/
public final class Launcher {    
    public static void main(String[] args) {
        Utils.log("Starting application...");
        SwingUtilities.invokeLater(() -> {
            new Window(800, 500);
        });
    }

}
