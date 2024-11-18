package org.example.Data;

import org.example.models.Libro;
import org.example.models.Persona;
import org.example.models.Prestamo;
import org.example.models.Usuario;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Arrays;
import java.util.List;


public class Data {
    private SessionFactory sessionFactory;
    private boolean dataInit;

    public Data() {
        this.sessionFactory = new Configuration().configure().buildSessionFactory();
    }

    public void enterData() throws ParseException {
        if (dataInit) {
            System.out.println("los datos ya han sido creados ya");
            return;
        }

        try {

            Session session = sessionFactory.openSession();
            session.beginTransaction();

            SimpleDateFormat formato = new SimpleDateFormat("yyyy-mm-dd");

            //registro personas
            Persona persona1 = new Persona();
            persona1.setNombre("Luis");
            persona1.setApellido("Rosania");
            persona1.setSexo("masculino");

            Persona persona2 = new Persona();
            persona2.setNombre("Maria");
            persona2.setApellido("Gonzalez");
            persona2.setSexo("femenino");

            Persona persona3 = new Persona();
            persona3.setNombre("Carlos");
            persona3.setApellido("Fernandez");
            persona3.setSexo("masculino");

            Persona persona4 = new Persona();
            persona4.setNombre("Ana");
            persona4.setApellido("Lopez");
            persona4.setSexo("femenino");

            Persona persona5 = new Persona();
            persona5.setNombre("Jorge");
            persona5.setApellido("Martinez");
            persona5.setSexo("masculino");

            //registro libros
            Libro libro1 = new Libro();
            libro1.setAutor("pablo neruda");
            libro1.setTitulo("cien sonetos de amor");
            libro1.setIsbn("23");

            Libro libro2 = new Libro();
            libro2.setAutor("Gabriel García Márquez");
            libro2.setTitulo("Cien años de soledad");
            libro2.setIsbn("45");

            Libro libro3 = new Libro();
            libro3.setAutor("Julio Cortázar");
            libro3.setTitulo("Rayuela");
            libro3.setIsbn("67");

            Libro libro4 = new Libro();
            libro4.setAutor("Jorge Luis Borges");
            libro4.setTitulo("Ficciones");
            libro4.setIsbn("89");

            Libro libro5 = new Libro();
            libro5.setAutor("Mario Vargas Llosa");
            libro5.setTitulo("La ciudad y los perros");
            libro5.setIsbn("101");


            //registro usuarios
            Usuario usuario1 = new Usuario();
            usuario1.setIdPersona(persona1);
            usuario1.setRol("empleado");


            Usuario usuario2 = new Usuario();
            usuario2.setIdPersona(persona2);
            usuario2.setRol("administrador");

            Usuario usuario3 = new Usuario();
            usuario3.setIdPersona(persona3);
            usuario3.setRol("empleado");


            Usuario usuario4 = new Usuario();
            usuario4.setIdPersona(persona4);
            usuario4.setRol("empleado");


            Usuario usuario5 = new Usuario();
            usuario5.setIdPersona(persona5);
            usuario5.setRol("administrador");

            //registros de prestamos
            Prestamo prestamo1 = new Prestamo();
            prestamo1.setIdUsuario(usuario1);
            prestamo1.setIdLibro(libro1);
            prestamo1.setActivo(true);
            prestamo1.setFechaPrestamo(formato.parse("2024-06-02"));
            prestamo1.setFechaDevolucion(formato.parse("0000-00-00"));

            Prestamo prestamo2 = new Prestamo();
            prestamo2.setIdUsuario(usuario2);
            prestamo2.setIdLibro(libro2);
            prestamo2.setActivo(true);
            prestamo2.setFechaPrestamo(formato.parse("2024-08-15"));
            prestamo2.setFechaDevolucion(formato.parse("0000-00-00"));

            Prestamo prestamo3 = new Prestamo();
            prestamo3.setIdUsuario(usuario3);
            prestamo3.setIdLibro(null);
            prestamo3.setActivo(false);
            prestamo3.setFechaPrestamo(formato.parse("2024-09-01"));
            prestamo3.setFechaDevolucion(formato.parse("2024-09-10"));

            Prestamo prestamo4 = new Prestamo();
            prestamo4.setIdUsuario(usuario4);
            prestamo4.setIdLibro(null);
            prestamo4.setActivo(false);
            prestamo4.setFechaPrestamo(formato.parse("2024-10-05"));
            prestamo4.setFechaDevolucion(formato.parse("2024-10-15"));

            Prestamo prestamo5 = new Prestamo();
            prestamo5.setIdUsuario(usuario5);
            prestamo5.setIdLibro(libro5);
            prestamo5.setActivo(true);
            prestamo5.setFechaPrestamo(formato.parse("2024-11-12"));
            prestamo5.setFechaDevolucion(formato.parse("0000-00-00"));

            List<Object> entities = Arrays.asList(persona1, persona2, persona3, persona4, persona5, usuario1, usuario2, usuario3, usuario4, usuario5, libro1, libro2, libro3, libro4, libro5, prestamo1, prestamo2, prestamo3, prestamo4, prestamo5);
            for (Object entity : entities) {
                session.persist(entity);
            }


            session.getTransaction().commit();
            dataInit = true;
            System.out.println("Los datos han sido agregados correctamente");
            session.close();
        } catch (HibernateException error) {
            System.out.println("el error es " + error.getMessage());
       }
   }
}
