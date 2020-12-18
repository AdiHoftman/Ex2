package gameClient;

import api.*;
import gameClient.util.Point3D;
import gameClient.util.Range;
import gameClient.util.Range2D;
import javax.swing.*;
import java.awt.*;
import java.util.List;

/**
 * This class represents a very simple GUI class to present a
 * game on a graph - you are welcome to use this class - yet keep in mind
 * that the code is not well written in order to force you improve the
 * code and not to take it "as is".
 */
public class MyFrame extends javax.swing.JFrame {
    private Arena _ar;
    private gameClient.util.Range2Range _w2f;
    private Image image;
    private Graphics graphics;
    private int level;
    private long timeToEnd;
    private int grade = 0;
    private int id;

    MyFrame(String a) {
        super(a);
        int _ind = 10;
    }

    public void update(Arena ar) {
        this._ar = ar;
        updateFrame();
    }

    private void updateFrame() {
        Range rx = new Range(20, this.getWidth() - 20);
        Range ry = new Range(this.getHeight() - 10, 150);
        Range2D frame = new Range2D(rx, ry);
        directed_weighted_graph g = _ar.getGraph();
        _w2f = Arena.w2f(g, frame);
        if (timeToEnd >= 0)
            setTimeToEnd(timeToEnd - 1);
    }

    public void paint(Graphics g) {
        updateFrame();
            this.setBackground(Color.black);
            image = createImage(this.getWidth(), this.getHeight());
           graphics = image.getGraphics();
            drawGraph(graphics);
            drawTimeToEnd(graphics);
            drawInfo(graphics);
            drawLevel(graphics);
            drawGrade(graphics);
            drawID(graphics);
            drawAgants(graphics);
            drawPokemons(graphics);
            g.drawImage(image, 0, 0,this.getWidth(), this.getHeight(), this);
    }

    private void drawInfo(Graphics g) {
        List<String> str = _ar.get_info();
        String dt = "none";
        for (int i = 0; i < str.size(); i++) {
            g.drawString(str.get(i) + " dt: " + dt, 100, 60 + i * 200);
        }
    }

    private void drawGraph(Graphics g) {
        directed_weighted_graph gg = _ar.getGraph();
        for (node_data n : gg.getV()) {
            for (edge_data e : gg.getE(n.getKey())) {
                g.setColor(Color.WHITE);
                drawEdge(e, g);
            }
            g.setColor(Color.CYAN);
            drawNode(n, 8, g);
        }
    }

    private void drawPokemons(Graphics g) {
        ImageIcon imageA = new ImageIcon("./images/pacman1.png");
        ImageIcon imageB = new ImageIcon("./images/pacman2.png");
        ImageIcon imageC = new ImageIcon("./images/pacman3.png");
        ImageIcon imageD = new ImageIcon("./images/pacman4.png");

        for (CL_Pokemon pokemon : _ar.getPokemons()) {
            Point3D p = pokemon.getLocation();
            int r = 10;
            geo_location location = this._w2f.world2frame(p);
            if (pokemon.getValue() >= 0.0 && pokemon.getValue() < 6.0) {
                g.drawImage(imageA.getImage(), (int) location.x() - 20, (int) location.y() - 20, this);
            } else if (pokemon.getValue() >= 6.0 && pokemon.getValue() <= 10.0) {
                g.drawImage(imageB.getImage(), (int) location.x() - 20, (int) location.y() - 20, this);
            } else if (pokemon.getValue() > 10 && pokemon.getValue() <= 14) {
                g.drawImage(imageC.getImage(), (int) location.x() - 20, (int) location.y() - 20, this);
            } else {
                g.drawImage(imageD.getImage(), (int) location.x() - 20, (int) location.y() - 20, this);
            }
            g.setFont(new Font("", Font.BOLD, 5));
        }
    }

    private void drawAgants(Graphics g) {
        ImageIcon imageE = new ImageIcon("./images/pokemon1.png");
        int n = 0;
        for (CL_Agent agent : _ar.getAgents()) {
            geo_location location = agent.getLocation();
            int r = 8;
            geo_location fp = this._w2f.world2frame(location);
            g.drawImage(imageE.getImage(), (int) fp.x() - r, (int) fp.y() - r, this);

//            g.setColor(Color.red);
//            g.setPaintMode();
            g.setFont(new Font("", Font.BOLD, 15));
            // geo_location location = agent.getLocation();
            n += (int) agent.getValue();
            //g.fillOval((int) fp.x() - r, (int) fp.y() - r, 2 * r, 2 * r);
            //g.drawString("" + agent.getID(), (int) (fp.x() - r), (int) (fp.y() - r));
        }
        setGrade(n);
    }

    private void drawNode(node_data n, int r, Graphics g) {
        geo_location pos = n.getLocation();
        geo_location fp = this._w2f.world2frame(pos);
        g.fillOval((int) fp.x() - r, (int) fp.y() - r, 2 * r, 2 * r);
        g.setColor(Color.orange);
        g.setFont(new Font("", Font.BOLD, 15));
        g.drawString("" + n.getKey(), (int) fp.x(), (int) fp.y() - 4 * r);
    }

    private void drawEdge(edge_data e, Graphics g) {
        directed_weighted_graph gg = _ar.getGraph();
        geo_location s = gg.getNode(e.getSrc()).getLocation();
        geo_location d = gg.getNode(e.getDest()).getLocation();
        geo_location s0 = this._w2f.world2frame(s);
        geo_location d0 = this._w2f.world2frame(d);
        g.setColor(Color.WHITE);
        g.setFont(new Font("", Font.BOLD, 20));
        g.drawLine((int) s0.x(), (int) s0.y(), (int) d0.x(), (int) d0.y());
    }

    private void drawTimeToEnd(Graphics g) {
        g.setColor(Color.YELLOW);
        g.setFont(new Font("", Font.BOLD, 20));
        g.drawString("Time to end: " + timeToEnd / 100, 150, 60);
    }

    private void drawLevel(Graphics g) {
        g.setColor(Color.YELLOW);
        g.setFont(new Font("", Font.BOLD, 20));
        g.drawString("Level: " + level, 60, 60);
    }

    public void drawGrade(Graphics g) {
        g.setColor(Color.YELLOW);
        g.setFont(new Font(" ", Font.BOLD, 20));
        g.drawString("Grade: " + grade, 320, 60);
    }

    public void drawID(Graphics g) {
        g.setColor(Color.YELLOW);
        g.setFont(new Font("", Font.BOLD, 20));
        g.drawString("ID: " + id, 440, 60);
    }

    public void setLevel(int i) {
        this.level = i;
    }

    public void setTimeToEnd(long time) {
        this.timeToEnd = time;
    }

    public void setGrade(int g) {
        this.grade = g;
    }

    public void setID(int i) {
        this.id = i;
    }
}