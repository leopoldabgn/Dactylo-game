package launcher;

import javax.swing.SwingUtilities;

import view.Window;

public final class Launcher {
    
    public static void main(String[] args) {
        System.out.println("Projet Dactylo Game");
        
        SwingUtilities.invokeLater(() -> {
            new Window(800, 500);
        });
    }

}
