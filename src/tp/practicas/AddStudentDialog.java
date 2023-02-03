package tp.practicas;

import javax.swing.*;
import java.awt.*;

class AddStudentDialog extends JDialog{

    /***
     * Clase AddStudentDialog.
     * Clase que gestiona el diálogo para añadir estudiantes.
     */

    private JTextField studentId;
    private JTextField studentName;
    private JButton accept;
    private JButton cancel;

    //Cuando se pulsa el botón para añadir un nuevo estudiante...
    //Se crea un diálogo de esta clase (AddStudentDialog).
    //A esta clase se le pasa por parámetro, al crearse, la lista de estudiantes, el TextArea mostrado, y el orden establecido (ID o nombre).
    //Los parámetros son pasados al diálogo cuando se pulsa el botón de añadir estudiante (A través de un evento).
    public AddStudentDialog(EnrolledStudents enrolledStudents, JTextArea studentsTextArea, int sortMode) {
        //Título del diálogo emergente.
        setTitle("Add new student");

        //Campos a rellenar (ID y Nombre).
        this.studentId = new JTextField(5);
        this.studentName = new JTextField(10);

        //Botones de aceptar y cancelar.
        this.accept = new JButton("Accept");
        acceptButtonAction(enrolledStudents, studentsTextArea, sortMode); //Método listener de botón de aceptar.
        this.cancel = new JButton("Cancel"); //Método listener de botón de cancelar.
        cancelButtonAction();

        //Panel general encargado de contener los paneles de los campos y los botones.
        Container panel= getContentPane();
        panel.setLayout(new BorderLayout());


        //Panel usado por los campos a rellenar.
        JPanel fieldsPanel = new JPanel();
        fieldsPanel.setLayout(new BorderLayout());

        //Panel ubicado dentro del panel de los campos, destinado al ID.
        JPanel IdPanel = new JPanel();
        IdPanel.setLayout(new FlowLayout(FlowLayout.LEFT));
        IdPanel.add(new JLabel("Student ID: "));
        IdPanel.add(studentId);

        //Panel ubicado dentro del panel de los campos, destinado al Nombre.
        JPanel namePanel = new JPanel();
        namePanel.setLayout(new FlowLayout(FlowLayout.LEFT));
        namePanel.add(new JLabel("Student name: "));
        namePanel.add(studentName);

        //Se añaden los paneles del ID y del Nombre al panel general de los campos.
        fieldsPanel.add(IdPanel, BorderLayout.NORTH);
        fieldsPanel.add(namePanel, BorderLayout.SOUTH);

        //Panel que contiene los botones  de aceptar y cancelar.
        JPanel buttonPanel = new JPanel();
        buttonPanel.setLayout(new FlowLayout(FlowLayout.CENTER));
        buttonPanel.add(accept);
        buttonPanel.add(cancel);

        //Se añade al panel general del diálogo los paneles de los campos y los botones.
        panel.add(fieldsPanel, BorderLayout.NORTH);
        panel.add(buttonPanel, BorderLayout.SOUTH);
        setDefaultCloseOperation(DISPOSE_ON_CLOSE);
        pack();
        setVisible(true);
    }

    //Listener del botón de aceptar.
    private void acceptButtonAction(EnrolledStudents enrolledStudents, JTextArea studentsTextArea, int sortMode) {
        this.accept.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent e) {
                //Si el botón de aceptar es pulsado...
                //Se añade a la lista de estudiantes pasada por parámetro un nuevo estudiante.
                //Para crear al estudiante se usa el texto que hay en los campos del diálogo (ID y Nombre).
                enrolledStudents.addStudent(new Student(Integer.parseInt(studentId.getText()), studentName.getText().substring(0, 1).toUpperCase() + studentName.getText().substring(1).toLowerCase()));
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

    //Listener del botón de cancelar.
    private void cancelButtonAction() {
        this.cancel.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent e) {
                dispose();
            }
        });
    }

}