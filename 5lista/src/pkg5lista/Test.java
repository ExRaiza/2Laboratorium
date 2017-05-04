//package pkg5lista;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseWheelEvent;
import java.awt.geom.AffineTransform;
import java.awt.geom.Ellipse2D;
import java.awt.geom.GeneralPath;
import java.awt.geom.Point2D;
import java.awt.geom.Rectangle2D;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.security.AccessController;
import java.security.PrivilegedAction;
import java.util.ArrayList;
import javax.swing.*;

/**
 * The applet painting shapes like circle, rectangle and other polygons. Can saving shapes and openes them from file.
 * @author Norbert Ropiak
 * @version 1.0 
 */

public class Test extends JApplet implements ActionListener {
    /**
     * The main {@link ArrayList} with shapes
     */
    private static ArrayList<Shape> shapes;
    JPanel pan;
    private static ArrayList<Point2D.Double> points;
    /**
     * The second {@link ArrayList} with colors
     */
    private static ArrayList<Color> colors;
    /**
     * The variable for setting what to paint
     *  2 = circle 4 = rectangle 0 = polygon
     */
    private static int drawingShape = 4; 

    JMenuBar appMenuBar;
    JMenu appFile, appShapes;
    JMenuItem appMenuItem;

    Polygon poly = new Polygon();
    Shape s = null;
    Color color = null;
    Shape paintedShape;
    Shape changedShape;
    Shape enlargedPoly;
    GeneralPath path;
    AffineTransform genTr;

    /**
     * @see JApplet#init()
     */
    
    @Override
    public void init() {
        setLayout(new BorderLayout());
        pan = new Paint();
        add("Center", pan);
        
        points = new ArrayList<Point2D.Double>();
        shapes = new ArrayList<Shape>();
        colors = new ArrayList<Color>();

        path = new GeneralPath();
        genTr = null;
        
        initMenu();
    }

    /**
     * Creates main menu and info button
     */
    
    private void initMenu(){
        appMenuBar = new JMenuBar();

        appFile = new JMenu("File");
        appFile.setMnemonic(KeyEvent.VK_F);
        appMenuBar.add(appFile);

        appMenuItem = new JMenuItem("Open");
        appMenuItem.addActionListener(this);
        appMenuItem.setActionCommand("Open");
        appFile.add(appMenuItem);

        appMenuItem = new JMenuItem("Save");
        appMenuItem.addActionListener(this);
        appMenuItem.setActionCommand("Save");
        appFile.add(appMenuItem);

        appShapes = new JMenu("Shapes");
        appShapes.setMnemonic(KeyEvent.VK_S);
        appMenuBar.add(appShapes);

        appMenuItem = new JMenuItem("Circle");
        appMenuItem.addActionListener(this);
        appMenuItem.setActionCommand("Circle");
        appShapes.add(appMenuItem);

        appMenuItem = new JMenuItem("Rectangle");
        appMenuItem.addActionListener(this);
        appMenuItem.setActionCommand("Rectangle");
        appShapes.add(appMenuItem);

        appMenuItem = new JMenuItem("Polygon");
        appMenuItem.addActionListener(this);
        appMenuItem.setActionCommand("Polygon");
        appShapes.add(appMenuItem);

        JButton button = new JButton("Info");
        button.addActionListener(this);
        button.setActionCommand("Info");
        appMenuBar.add(button);

        setJMenuBar(appMenuBar);
    }
    
    /**
     * @see JApplet#start() 
     */
    
    @Override
    public void start() {

    }

    /**
     * @see JApplet#stop() 
     */
    
    @Override
    public void stop() {

    }

    /**
     * The main method creates frame
     * @param args 
     */
    
    public static void main(String[] args) {
        JFrame frame = new MyFrame(new Test());
        EventQueue.invokeLater(new Runnable() {
            @Override
            public void run() {
                frame.setVisible(true);
            }
        });
    }

    /**
     * The wrapped {@link JPanel}. 
     * The area where user painting.
     */
    
    class Paint extends JPanel {

        Shape s;
         
        public Paint() {
            MovingAddingAdapter adapter = new MovingAddingAdapter();
            addMouseListener(adapter);
            addMouseMotionListener(adapter);
            addMouseWheelListener(adapter);
        }

        @Override
        public void paintComponent(Graphics g) {
            super.paintComponent(g);

            doDrawing(g);
        }

        /**
         * Paints all shapes with colors from {@link ArrayList}
         * @param g {@link Graphics}  
         */
        
        private void doDrawing(Graphics g) {
            Graphics2D g2 = (Graphics2D) g;

            for (int i = 0; i < shapes.size(); i++) {
                if (shapes.get(i).equals(changedShape)) {
                    shapes.set(i, enlargedPoly);
                }

                if (shapes.get(i).equals(paintedShape)) {
                    g2.setColor(color);
                    colors.set(i, color);
                    g2.fill(shapes.get(i));
                    paintedShape = null;
                } else {
                    g2.setColor(colors.get(i));
                    g2.fill(shapes.get(i));
                }
            }

            g2.setColor(Color.BLACK);
            for (Point2D.Double pt : points) {
                g2.fillRect((int) pt.getX(), (int) pt.getY(), 5, 5);
            }

            g2.setColor(Color.BLACK);
            g2.draw(path);
        }

    }

    /**
     * The wrapped {@link MouseAdapter}.
     * Creates, moves and changes colors of shapes
     */
    
    class MovingAddingAdapter extends MouseAdapter {
        /**
         * coordinate x of mouse click
         */
        private int x;
        /**
         * coordinate y of mouse click
         */
        private int y;

        /**
         * Creates shape linked to {@link drawingShape}
         * @param e {@link MouseEvent}
         */
        
        @Override
        public void mousePressed(MouseEvent e) {
            x = e.getX();
            y = e.getY();

            s = null;

            for (Shape sh : shapes) {
                if (sh.contains(x, y)) {
                    s = sh;
                }
            }

            if (s != null && e.getButton() == MouseEvent.BUTTON3) {
                color = JColorChooser.showDialog(null, "Choose a color", color);
                paintedShape = s;
            }

            if (s == null) {
                points.add(new Point2D.Double(x, y));
                if (drawingShape == 4 || drawingShape == 0) {
                    poly.addPoint(x, y);
                }
            }

            if (drawingShape == 2) {
                if (points.size() == 2) {
                    double length = points.get(0).distance(points.get(1));
                    shapes.add(new Ellipse2D.Double(points.get(0).x - length, points.get(0).y - length, length * 2, length * 2));
                    colors.add(Color.DARK_GRAY);
                    points.clear();
                }
            }
            if (drawingShape == 4) {
                if (points.size() == 3) {
                    Rectangle2D rect = poly.getBounds2D();
                    shapes.add(rect);
                    colors.add(new Color(160, 30, 30));
                    points.clear();
                    poly.reset();
                }
            }
            if (drawingShape == 0) {
                if (points.size() == 1) {
                    path.moveTo(x, y);
                }
                if (points.size() > 1 && s == null) {
                    path.lineTo(x, y);
                }
                if (points.size() > 2 && new Point.Double(x, y).distance(points.get(0)) <= 10) {
                    Polygon temp = new Polygon();
                    for (int i = 0; i < points.size() - 1; i++) {
                        temp.addPoint((int) points.get(i).x, (int) points.get(i).y);
                    }
                    shapes.add(temp);
                    colors.add(new Color(30, 160, 40));
                    points.clear();
                    poly = new Polygon();
                    path.reset();
                }
            }

            repaint();
        }

        @Override
        public void mouseDragged(MouseEvent e) {
            int dx = e.getX() - x;
            int dy = e.getY() - y;

            for (Shape sh : shapes) {
                if (sh.contains(x, y)) {
                    points.clear();
                    poly.reset();
                    s = sh;
                }
            }
            if (s instanceof Rectangle2D) {
                Rectangle2D rect = (Rectangle2D) s;
                rect.setFrame(rect.getX() + dx, rect.getY() + dy, rect.getWidth(), rect.getHeight());
                repaint();
            } else if (s instanceof Ellipse2D) {
                Ellipse2D ell = (Ellipse2D) s;
                ell.setFrame(ell.getX() + dx, ell.getY() + dy, ell.getWidth(), ell.getHeight());
                repaint();
            } else {
                AffineTransform matrix = new AffineTransform();
                matrix.translate(dx, dy);
                changedShape = s;
                enlargedPoly = (Shape) matrix.createTransformedShape(s);
                s = enlargedPoly;
                repaint();
            }
            x += dx;
            y += dy;

        }

        @Override
        public void mouseWheelMoved(MouseWheelEvent e) {
            double enlarge = e.getWheelRotation() * 6;

            if (s != null) {
                if (s instanceof Rectangle2D) {
                    Rectangle2D rect = (Rectangle2D) s;
                    rect.setFrame(rect.getX(), rect.getY(), rect.getWidth() + enlarge, rect.getHeight() + enlarge);
                    repaint();
                } else if (s instanceof Ellipse2D) {
                    Ellipse2D ell = (Ellipse2D) s;
                    ell.setFrame(ell.getX(), ell.getY(), ell.getWidth() + enlarge, ell.getHeight() + enlarge);
                    repaint();
                } else {
                    AffineTransform matrix = new AffineTransform();
                    if (enlarge > 0) {
                        matrix.translate(s.getBounds2D().getX(), s.getBounds2D().getY());
                        matrix.scale(1.5, 1.5);
                        matrix.translate(-s.getBounds2D().getX(), -s.getBounds2D().getY());
                    }
                    if (enlarge < 0) {
                        matrix.translate(s.getBounds2D().getX(), s.getBounds2D().getY());
                        matrix.scale(0.5, 0.5);
                        matrix.translate(-s.getBounds2D().getX(), -s.getBounds2D().getY());
                    }
                    changedShape = s;
                    enlargedPoly = (Shape) matrix.createTransformedShape(s);
                    s = enlargedPoly;
                    repaint();
                }
            }
        }
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        points.clear();
        path.reset();
        switch (e.getActionCommand()) {
            case "Circle":
                drawingShape = 2;
                break;
            case "Rectangle":
                drawingShape = 4;
                break;
            case "Polygon":
                drawingShape = 0;
                break;
            case "Info":
                JOptionPane.showMessageDialog(null, "Paint 2.0 \nAuthor: Norbert Ropiak \nDrawing shapes \nVersion: 1.58");
                break;
            case "Open":
                shapes.clear();
                colors.clear();
                Shape sh = null;
                Color co = null;
                try (ObjectInputStream ois = new ObjectInputStream(new FileInputStream("save.ser"))) {
                    int n = ois.readInt();
                    for (int i = 0; i < n; i++) {
                        sh = (Shape) ois.readObject();
                        co = (Color) ois.readObject();
                        shapes.add(sh);
                        colors.add(co);
                    }
                } catch (Exception ex) {
                    ex.printStackTrace();
                }
                break;
            case "Save":
                AccessController.doPrivileged(new PrivilegedAction<Object>() {
                    @Override
                    public Object run() {
                        int len = shapes.size();
                        try (ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream("save.ser"))) {
                            oos.writeInt(len);
                            for (int i = 0; i < shapes.size(); i++) {
                                oos.writeObject(shapes.get(i));
                                oos.writeObject(colors.get(i));
                            }
                        } catch (Exception ex) {
                            ex.printStackTrace();
                        }

                        return null;
                    }
                });
                break;
            default:
                break;
        }
        repaint();
    }
}

/**
 * The wrapped {@link JFrame}
 * @author norbe
 */
class MyFrame extends JFrame {

    public MyFrame(Test a) {
        this.setSize(new Dimension(400, 400));
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        initUI();
    }

    /**
     * Initiates {@link JApplet} and adds this to frame
     */
    
    private void initUI() {
        setLayout(new BorderLayout());
        Test applet = new Test();
        applet.init();
        add(applet, BorderLayout.CENTER);
    }
}
