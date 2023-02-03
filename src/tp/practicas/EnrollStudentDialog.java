package tp.practicas;

import javax.swing.*;
import java.awt.*;

class EnrollStudentDialog extends JDialog {

    /***
     * Clase EnrollStudentDialog.
     * Clase que gestiona el diálogo para matricular estudiantes.
     */

    private JComboBox<String> students;
    private JComboBox<String> courses;
    private JButton accept;
    private JButton cancel;

    //Cuando se pulsa el botón para matricular un nuevo estudiante...
    //Se crea un diálogo de esta clase (EnrollStudentDialog).
    //A esta clase se le pasa por parámetro, al crearse, la lista de estudiantes, la lista de asignaturas...
    //el TextArea mostrado, el orden establecido (ID o nombre), y una lista de Strings de los estudiantes y las asignaturas.
    //Los parámetros son pasados al diálogo cuando se pulsa el botón de añadir estudiante (A través de un evento).
    public EnrollStudentDialog(EnrolledStudents enrolledStudents, OfferedCourses offeredCourses, int sortMode, JTextArea studentsTextArea, String[] students, String[] courses) {
        setTitle("Enroll student in course");

        //Se crean dos ComboBox, y se les pasa por parámetro los array de Strings.
        //De esta manera, los ComboBox pueden mostrar los diferentes estudiantes y asignaturas disponibles para seleccionar.
        this.students = new JComboBox<>(students);
        this.courses = new JComboBox<>(courses);

        //Creación de los botones de aceptar y cancelar, llamando a los métodos Listener.
        this.accept = new JButton("Accept");
        acceptButtonAction(enrolledStudents, offeredCourses, sortMode, studentsTextArea);
        this.cancel = new JButton("Cancel");
        cancelButtonAction();

        //Generación del panel contenedor del diálogo.
        Container panel= getContentPane();
        panel.setLayout(new BorderLayout());

        //Panel que contiene los ComboBoxes.
        JPanel comboBoxPanel = new JPanel();
        comboBoxPanel.setLayout(new FlowLayout());
        comboBoxPanel.add(new JLabel("Student: "));
        comboBoxPanel.add(this.students);
        comboBoxPanel.add(new JLabel("Course"));
        comboBoxPanel.add(this.courses);

        //Panel que contiene los botones.
        JPanel buttonPanel = new JPanel();
        buttonPanel.setLayout(new FlowLayout(FlowLayout.CENTER));
        buttonPanel.add(accept);
        buttonPanel.add(cancel);

        //Se añaden los paneles de los ComboBox y de los botones al panel general.
        panel.add(comboBoxPanel, BorderLayout.NORTH);
        panel.add(buttonPanel, BorderLayout.SOUTH);
        setDefaultCloseOperation(DISPOSE_ON_CLOSE);
        pack();
        setVisible(true);
    }

    //Método Listener del botón de aceptar.
    private void acceptButtonAction(EnrolledStudents enrolledStudents, OfferedCourses offeredCourses, int sortMode, JTextArea studentsTextArea) {
        this.accept.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent e) {
                //Se obtiene la lista ordenada por ID de los estudiantes (Para que tenga el mismo orden mostrado en el ComboBox).
                //De esa lista se obtiene el estudiante que se encuentre en el mismo índice que el índice del estudiante seleccionado en el ComboBox.
                //Se llama al método del estudiante "enrollCourse" para matricularlo de una asignatura.
                //De la clase "offeredCourses" se obtiene la lista de asignaturas y de ella se obtiene la asignatura con el mismo índice que la seleccionada en el ComboBox.
                //El alumno será matriculado de la asignatura en cuestión.
                enrolledStudents.getStudentsOrderById().get(students.getSelectedIndex()).enrollCourse(offeredCourses.getCourses().get(courses.getSelectedIndex()));
                //Si el modo de ordenación es 0 (Ordenación por nombre de estudiante)...
                //Se recorre la lista de estudiantes ordenada por nombre, y se van añadiendo los estudiantes al TextArea.
                if (sortMode == 0) {
                    studentsTextArea.setText("");
                    for (Student student : enrolledStudents.getStudentsOrderByName()) {
                        studentsTextArea.append(student.toString() + "\n");
                    }
                //Si el modo de ordenación no es 0 (La ordenación es por ID de estudiante).
                } else {
                    studentsTextArea.setText("");
                    for (Student student : enrolledStudents.getStudentsOrderById()) {
                        studentsTextArea.append(student.toString() + "\n");
                    }
                }
                //Función que cierra la ventana después de haber pulsado el botón de aceptar.
                dispose();
            }
        });
    }

    //Método Listener del botón de cancelar.
    private void cancelButtonAction() {
        this.cancel.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent e) {
                dispose();
            }
        });
    }

}