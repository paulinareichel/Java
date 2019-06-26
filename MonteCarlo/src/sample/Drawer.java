package sample;

import java.awt.image.BufferedImage;
import java.util.Random;
import java.awt.*;

import javafx.concurrent.Task;
import javafx.embed.swing.SwingFXUtils;
import javafx.scene.canvas.GraphicsContext;

public class Drawer extends Task {
    private double x;
    private double y;
    private Integer powx;
    private Integer powy;
    GraphicsContext gc;
    BufferedImage bi;

    private Random rand = new Random();
    private Integer symulationPoints;
    private double value;

    public Drawer(GraphicsContext gc, Integer symulationPoints) {
        this.gc = gc;
        this.symulationPoints = symulationPoints;
        this.powx = 0;
        this.powy = 0;
        bi = new BufferedImage(295, 318, BufferedImage.TYPE_INT_RGB);
    }


    @Override
    protected Object call() throws Exception {
        for (int i = 0; i < symulationPoints; i++) {
            x = -8 + 16 * rand.nextDouble();
            y = -8 + 16 * rand.nextDouble();

            if (Equation.calc(x, y)) {
                bi.setRGB((int) (147.5 - 147.5 * x / 8), (int) (159 - 159 * y / 8), Color.YELLOW.getRGB()); powx++;
            }
            else {
                bi.setRGB((int) (147.5 - 147.5 * x / 8), (int) (159 - 159 * y / 8), Color.BLUE.getRGB());
                powy++;
            }

            if (i % 1000 == 0) {
                gc.drawImage(SwingFXUtils.toFXImage(bi, null), 0, 0);
                value = (double) powx / (powx + powy);
                Controller.value = value;
                updateProgress(i, symulationPoints);
            }
            if (isCancelled()) {
                break;
            }
        }
        return value;
    }
}
