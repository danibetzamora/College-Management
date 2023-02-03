package tp.practicas;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class EnrolledStudents {

    /***
     * Clase EnrolledStudents.
     * Encargada de almacenar todos los estudiantes añadidos al sistema.
     */

    private List<Student> enrolledStudents;

    public EnrolledStudents () {
        this.enrolledStudents = new ArrayList<Student>();
    }

    //Método que añade un nuevo estudiante a la lista de estudiantes.
    //Si el ID del nuevo estudiante ya se encuentra en la lista devuelve falso.
    public boolean addStudent (Student newStudent) {
        for (Student student : this.enrolledStudents) {
            if (student.getId() == newStudent.getId()) {
                return false;
            }
        }
        this.enrolledStudents.add(newStudent);
        return true;
    }

    //Método que elimina un estudiante de la lista del estudiante.
    //Si encuentra el ID pasado por parámetro se elimina el estudiante correspondiente y devuelve true.
    //Si no lo encuentra, devuelve false.
    public boolean removeStudent (int studentId) {
        for (Student student : this.enrolledStudents) {
            if (student.getId() == studentId) {
                this.enrolledStudents.remove(student);
                return true;
            }
        }
        return false;
    }

    //Método que devuelve un estudiante si su ID coincide con el ID pasado por parámetro.
    public Student getStudent (int studentId) {
        for (Student student : this.enrolledStudents) {
            if (student.getId() == studentId) {
                return student;
            }
        }
        return null;
    }

    //Método que devuleve una lista de los estudiantes...
    // Que están matriculados de la asignatura pasada por parámetro.
    public List<Student> getStudentsByCourse (int courseId) {
        List<Student> studentsByCourse = new ArrayList<Student>();

        for (Student student : this.enrolledStudents) {
            //Si el estudiante está matriculado de la asignatura, se añade a la lista resultado.
            if (student.courseIdInGroup(courseId)) {
                studentsByCourse.add(student);
            }
        }

        boolean repeatedName = false;

        //Si en la lista resultado se repite algún nombre de estudiante, la variable "repeatedName" se pone a true.
        //Si no se repite ningún nombre, la variable sigue en false.
        for (int i = 0; i < studentsByCourse.size(); i++) {
            for (int j = i+1; j < studentsByCourse.size(); j++) {
                if (studentsByCourse.get(i).getName().equals(studentsByCourse.get(j).getName())) {
                    repeatedName = true;
                }
            }
        }

        //Si la variable está a true (Se repite nombre), se devuelve la lista ordenada por ID de estudiante.
        //Si está a false (No se repite nombre), la lista se devuelve ordenada por nombre de estudiante.
        if (repeatedName) {
            Collections.sort(studentsByCourse, Student.IdComparator);
        } else {
            Collections.sort(studentsByCourse, Student.nameComparator);
        }

        return studentsByCourse;
    }

    //Método que devuelve una lista con todos los estudiantes ordenada por nombre de estudiante.
    //Si se repite algún nombre, se ordenan los estudiantes por su ID.
    public List<Student> getStudentsOrderByName () {
        List<Student> studentsOrdered = new ArrayList<Student>();
        studentsOrdered.addAll(this.enrolledStudents);
        Collections.sort(studentsOrdered, Student.nameComparator);
        return studentsOrdered;
    /*
        boolean repeatedName = false;

        for (int i = 0; i < studentsOrdered.size(); i++) {
            for (int j = i+1; j < studentsOrdered.size(); j++) {
                if (studentsOrdered.get(i).getName().equals(studentsOrdered.get(j).getName())) {
                    repeatedName = true;
                }
            }
        }

        if (repeatedName) {
            Collections.sort(studentsOrdered, Student.IdComparator);
        } else {
            Collections.sort(studentsOrdered, Student.nameComparator);
        }

        return studentsOrdered;

     */
    }

    //Método que devuelve a todos los estudiantes en una lista ordenada por sus ID.
    public List<Student> getStudentsOrderById () {
        List<Student> studentsOrderedById = new ArrayList<Student>();
        studentsOrderedById.addAll(this.enrolledStudents);
        Collections.sort(studentsOrderedById, Student.IdComparator);
        return studentsOrderedById;
    }

}