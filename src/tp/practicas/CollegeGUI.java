package tp.practicas;
import java.util.List;
import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import javax.swing.UIManager.*;

public class CollegeGUI extends JFrame {

    static private OfferedCourses offeredCourses = new OfferedCourses();
    static private EnrolledStudents enrolledStudents = new EnrolledStudents();

    //Variable de control que indica el modo de ordenación actual.
    static private int sortMode = 0;

    private JTextArea data;
    private JRadioButton orderName;
    private JRadioButton orderId;
    private JButton addStudent;
    private JButton enrollStudent;

    private JMenuBar menuBar;
    private JMenu menu;
    private JMenuItem itemAddStudent, itemEnrollStudent, itemExit;

    private void constructMenu() {
        menuBar = new JMenuBar();
        setJMenuBar(menuBar);
        menu = new JMenu("Menu");
        itemAddStudent = new JMenuItem("Add new student");
        itemEnrollStudent = new JMenuItem("Enroll student in course");
        itemExit = new JMenuItem("Exit");
        menu.add(itemAddStudent);
        menu.add(itemEnrollStudent);
        menu.add(itemExit);
        menuBar.add(menu);
    }
    private Component constructControls() {
        orderName = new JRadioButton("Order by student's name", true);
        orderId = new JRadioButton("Order by student's id", false);
        ButtonGroup bg = new ButtonGroup();
        bg.add(orderName);
        bg.add(orderId);
        addStudent = new JButton("Add new student");
        enrollStudent = new JButton("Enroll student in course");
        JPanel east = new JPanel();
        east.setLayout(new BoxLayout(east, BoxLayout.PAGE_AXIS));
        JPanel order = new JPanel();
        order.setLayout(new BoxLayout(order, BoxLayout.PAGE_AXIS));
        order.add(orderName);
        order.add(orderId);
        order.setBorder(BorderFactory.createTitledBorder("List order"));
        east.add(order);
        east.add(addStudent);
        east.add(enrollStudent);
        return east;
    }

    private Component constructInfo() {
        data = new JTextArea();
        data.setEditable(false);
        showEnrolledStudentsByName();
        return new JScrollPane(data);
    }

    //Método que muestra a través del JTextArea los estudiantes ordenados por su nombre.
    private void showEnrolledStudentsByName() {
        data.setText("");
        for (Student student : enrolledStudents.getStudentsOrderByName()) {
            //Añade el estudiante al JTextArea.
            data.append(student.toString() + "\n");
        }
        //Modo de ordenación a 0 (Ordenación por nombre).
        this.sortMode = 0;
    }

    //Método que muestra a través del JTextArea los estudiantes ordenados por su ID.
    private void showEnrolledStudentsById() {
        data.setText("");
        for (Student student : enrolledStudents.getStudentsOrderById()) {
            //Añade el estudiante al JTextArea.
            data.append(student.toString() + "\n");
        }
        //Modo de ordenación a 1 (Ordenación por ID).
        this.sortMode = 1;
    }

    //Listener RadioButton de ordenación por Nombre.
    private void orderNameAction() {
        orderName.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent e) {
                showEnrolledStudentsByName();
            }
        });
    }

    //Listener RadioButton de ordenación por ID.
    private void orderIdAction() {
        orderId.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent e) {
                showEnrolledStudentsById();
            }
        });
    }

    //Listener para añadir estudiante a través de la barra de menú.
    private void addStudentMenuAction(JTextArea data) {
        itemAddStudent.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent e) {
                //Se crea un nuevo objeto de tipo diálogo de la clase AddStudentDialog.
                new AddStudentDialog(enrolledStudents, data, sortMode);
            }
        });
    }

    //Listener para matricular a estudiante a través de la barra de menú.
    private void enrollStudentMenuAction(JTextArea data) {
        itemEnrollStudent.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent e) {
                //Se crea un array de String que almacenará los nombres de todos los estudiantes.
                String[] students = new String[enrolledStudents.getStudentsOrderById().size()];
                int cont = 0;
                //Se van añadiendo todos los estudiantes al array ordenados por su ID (Para tener el mismo orden que el ComboBox).
                for (Student student : enrolledStudents.getStudentsOrderById()) {
                    students[cont] = Integer.toString(student.getId()) + "-" + student.getName();
                    cont++;
                }

                //Se crea un array de String que almacenará los nombres de todass las asignaturas.
                String[] courses = new String[offeredCourses.getCourses().size()];
                cont = 0;
                //Se van añadiendo todas las asignaturas al array.
                for (Course course : offeredCourses.getCourses()) {
                    courses[cont] = course.toString();
                    cont++;
                }

                //Se crea un nuevo objeto de tipo diálogo de la clase EnrollStudentDialog.
                new EnrollStudentDialog(enrolledStudents, offeredCourses, sortMode, data, students, courses);
            }
        });
    }

    //Listener para cerrar el sistema a través del botón de EXIT del menú.
    private void exitMenuAction() {
        itemExit.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent e) {
                dispose();
            }
        });
    }

    //Listener para añadir nuevo estudiante a través del botón.
    private void addStudentBottonAction(JTextArea data) {
        addStudent.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent e) {
                new AddStudentDialog(enrolledStudents, data, sortMode);
            }
        });
    }

    //Listener para matricular a un estudiante a través del botón.
    private void enrollStudentBottonAction(JTextArea data) {
        enrollStudent.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent e) {
                String[] students = new String[enrolledStudents.getStudentsOrderById().size()];
                int cont = 0;
                for (Student student : enrolledStudents.getStudentsOrderById()) {
                    students[cont] = Integer.toString(student.getId()) + "-" + student.getName();
                    cont++;
                }

                String[] courses = new String[offeredCourses.getCourses().size()];
                cont = 0;
                for (Course course : offeredCourses.getCourses()) {
                    courses[cont] = course.toString();
                    cont++;
                }

                new EnrollStudentDialog(enrolledStudents, offeredCourses, sortMode, data, students, courses);
            }
        });
    }

    public CollegeGUI(){
        super("College Management");
        InitialData.init(offeredCourses, enrolledStudents);
        constructMenu();
        JPanel pane = new JPanel();
        pane.setLayout(new BorderLayout());
        pane.add(constructInfo(), BorderLayout.CENTER);
        pane.add(constructControls(), BorderLayout.EAST);
        setContentPane(pane);
        setLocation(50,50);
        setSize(700,200);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setVisible(true);

        //Llamadas a los Listener para que estén activos.
        orderNameAction();
        orderIdAction();
        addStudentBottonAction(this.data);
        enrollStudentBottonAction(this.data);
        addStudentMenuAction(this.data);
        enrollStudentMenuAction(this.data);
        exitMenuAction();
    }

    public static void main (String[] args) {
        try {
            UIManager.setLookAndFeel("javax.swing.plaf.nimbus.NimbusLookAndFeel");
        } catch(Exception e) {}

        new CollegeGUI();
    }

}