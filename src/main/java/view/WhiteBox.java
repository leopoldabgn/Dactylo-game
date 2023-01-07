package view;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;
public final class WhiteBox extends JPanel {

  /**
 * This class represents a white box that can be used as a container for other components. It has a
 * transparent background and a white border with rounded corners.
 */

  public WhiteBox() {
      setOpaque(false);
      setBorder(new EmptyBorder(10, 15, 10, 15));
      setLayout(new GridBagLayout());
  }

  @Override
  protected void paintComponent(Graphics g) {
      super.paintComponent(g);
      g.setColor(HomeView.backgroundColor);
      g.fillRect(5, 5, getWidth() - 10, getHeight() - 10);
      // g.setColor(Color.BLACK);
      
      Graphics2D g2d = (Graphics2D)g;
      g2d.setRenderingHint(RenderingHints.KEY_ANTIALIASING,
      RenderingHints.VALUE_ANTIALIAS_ON); 

      g2d.setRenderingHint(RenderingHints.KEY_TEXT_ANTIALIASING,RenderingHints.VALUE_TEXT_ANTIALIAS_ON); 
      g2d.setStroke(new BasicStroke(5));
      // g2d.drawRoundRect(2, 2, getWidth() - 6, getHeight() - 6, 20, 20);
  }
}
