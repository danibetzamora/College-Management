package tp.practicas;

import java.util.*;

public class Student {

    /***
     * Clase Student.
     * Encargada de crear los estudiantes.
     * Todos los estudiantes tiene un ID y un nombre.
     */

    private int studentId;
    private String studentName;
    private List<Course> enrolled = new ArrayList<Course>();

    public Student (int studentId, String studentName) {
        this.studentId = studentId;
        this.studentName = studentName;
    }

    public int getId () {
        return this.studentId;
    }

    public String getName () {
        return this.studentName;
    }

    //Método para comprobar si la asignatura pasada por parámetro (ID)...
    //Se encuentra entre las asignaturas en las que el estudiante está matriculado.
    public boolean courseIdInGroup (int courseId) {
        for (Course enrolledCourse : this.enrolled) {
            if (enrolledCourse.getCode() == courseId) {
                return true;
            }
        }
        return false;
    }

    //Añade la asignatura pasada por parámetro a las asignaturas del estudiante (Lo matricula).
    //Si el estudiante ya está matriculado de esa asignatura devuelve falso.
    public boolean enrollCourse (Course course) {
        if (courseIdInGroup(course.getCode())) {
            return false;
        }
        this.enrolled.add(course);
        return true;
    }

    //Elimina la asignatura pasada por ID de las asignaturas del estudiante (Anula matriculación).
    public boolean unenrollCourse (int courseId) {
        for (Course enrolledCourse : this.enrolled) {
            if (enrolledCourse.getCode() == courseId) {
                this.enrolled.remove(enrolledCourse);
                return true;
            }
        }
        return false;
    }

    //Devuelve una lista de las asignaturas del estudiante ordenada por el ID de la asignatura.
    public Collection<Course> getEnrolledCourses () {
        List<Course> enrolledCourses = new ArrayList<Course>();
        enrolledCourses.addAll(this.enrolled);
        Collections.sort(enrolledCourses);
        return enrolledCourses;
    }

    @Override
    public String toString () {
        //Ordenación de la lista de asignaturas por ID de asignatura.
        Collections.sort(this.enrolled);

        //Inicia la String devuelta con el ID del estudiante y su nombre.
        String studentToString = this.getId() + "-" + this.getName() + "[";

        //Si el estudiante no está matriculado de ninguna asignatura...
        //Se cierra el corchete y no se muestra ninguna asignatura.
        if (this.enrolled.size() == 0) {
            studentToString += "]";
        }

        //Se recorren todas las asignaturas y se añade el toString de cada asignatura a la String resultado.
        for (Course enrolledCourse : this.enrolled) {
            //Si es la última asignatura, se cierra el corchete.
            //Si no es la última asignatura, se añade una coma.
            if (this.enrolled.indexOf(enrolledCourse) == this.enrolled.size()-1) {
                studentToString += (enrolledCourse.toString() + "]");
            } else {
                studentToString += (enrolledCourse.toString() + ", ");
            }
        }

        return studentToString;
    }

    //Comparador de estudiantes por nombre.
    public static Comparator<Student> nameComparator = new Comparator<Student>() {
        public int compare (Student student1, Student student2) {
            if (student1.getName().compareTo(student2.getName()) == 0) {
                if (student1.getId() > student2.getId()) {
                    return 1;
                } else {
                    return -1;
                }
            }
            return student1.getName().compareTo(student2.getName());
        }
    };

    //Comparador de estudiantes por ID.
    public static Comparator<Student> IdComparator = new Comparator<Student>() {
        public int compare (Student student1, Student student2) {
            if (student1.getId() == student2.getId()) {
                return 0;
            } else if (student1.getId() > student2.getId()) {
                return 1;
            } else {
                return -1;
            }
        }
    };

}