package tp.practicas;

import java.util.Comparator;

public class Course implements Comparable<Course> {

    /***
     * Clase Course.
     * Encargada de la creación de las asignaturas.
     * Todas las asignaturas tienen un ID y un nombre.
     */

    private int courseId;
    private String courseName;

    public Course (int courseId, String courseName) {
        this.courseId = courseId;
        this.courseName = courseName;
    }

    public int getCode () {
        return this.courseId;
    }

    public String getName () {
        return this.courseName;
    }

    @Override
    public String toString () {
        return "(" + this.getCode() + ")" + this.getName();
    }

    //Método compareTo para ordenar asignaturas por código (ID).
    @Override
    public int compareTo(Course course) {
        if (this.getCode() == course.getCode()) {
            return 0;
        } else if (this.getCode() > course.getCode()) {
            return 1;
        } else {
            return -1;
        }
    }

    //Comparador usado para ordenar asignaturas por su nombre.
    public static Comparator<Course> toStringComparator = new Comparator<Course>() {
        public int compare (Course course1, Course course2) {
            return course1.toString().compareTo(course2.toString());
        }
    };

}