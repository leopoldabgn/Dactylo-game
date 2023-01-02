
package launcher;

import javax.swing.SwingUtilities;

import utils.Utils;
import view.Window;

public final class Launcher {    
    public static void main(String[] args) {
        Utils.log("Starting application...");
        SwingUtilities.invokeLater(() -> {
            new Window(800, 500);
        });
    }

}
