package tp.practicas;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class OfferedCourses {

    /***
     * Clase OfferedCourses.
     * Encargada de almacenar todas las asignaturas añadidas al sistema.
     */

    private List<Course> offeredCourses;

    public OfferedCourses () {
        this.offeredCourses = new ArrayList<Course>();
    }

    //Método que comprueba si el ID de una asignatura ya está en la lista.
    //Si la asignatura ya está registrada devuelve true, si no, false.
    public boolean courseIdInOffered (int courseId) {
        for (Course course : this.offeredCourses) {
            if (course.getCode() == courseId) {
                return true;
            }
        }
        return false;
    }

    //Método que añade una nueva asignatura a la lista.
    //Si la asignatura ya está en la lista devuelve false.
    //Si no está en la lista, la añade y devuelve true.
    public boolean addCourse (Course newCourse) {
        if (courseIdInOffered(newCourse.getCode())) {
            return false;
        }
        this.offeredCourses.add(newCourse);
        return true;
    }

    //Método que elimina una asignatura si la encuentra.
    //Si no hay ningún código que coincida con la asignatura a eliminar, devuelve false.
    public boolean removeCourse (int courseCode) {
        for (Course course : this.offeredCourses) {
            if (course.getCode() == courseCode) {
                this.offeredCourses.remove(course);
                return true;
            }
        }
        return false;
    }

    //Método al que se le pasa un ID de asignatura y devuelve la asignatura correspondiente.
    public Course getCourse (int courseCode) {
        for (Course course : this.offeredCourses) {
            if (course.getCode() == courseCode) {
                return course;
            }
        }
        return null;
    }

    //Método que devuelve una copia de la lista de asignaturas ordenadas por nombre.
    public List<Course> getCourses () {
        List<Course> courses = new ArrayList<Course>();
        courses.addAll(this.offeredCourses);
        Collections.sort(courses, Course.toStringComparator);
        return courses;
    }

}