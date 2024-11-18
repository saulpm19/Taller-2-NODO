package org.example.Controllers;

import org.example.connection.DatabaseConnection;
import org.example.models.Libro;
import org.hibernate.Session;
import org.hibernate.Transaction;

import java.util.List;

public class LibroController {
    private DatabaseConnection databaseConnection;

    public LibroController() {
        this.databaseConnection = new DatabaseConnection();
    }

    //metodo para guardar datos nuevos
    public void crearLibro(Libro libro) {
        Transaction transaction = null;
        try (Session session = databaseConnection.getSession()) {
            transaction = session.beginTransaction();
            session.save(libro);
            transaction.commit();
        } catch (Exception e) {
            if (transaction != null) {
                transaction.rollback();
            }
            e.printStackTrace();
        }
    }

    //metodo para obtener el libro por id
    public Libro obtenerLibroId(int id) {
        try (Session session = databaseConnection.getSession()) {
            return session.get(Libro.class, id);
        } catch (RuntimeException e) {
            throw new RuntimeException(e);
        }
    }

    //metodo para obtener todos los libros
    public List<Libro> obtenerLibro() {
        try (Session session = databaseConnection.getSession()) {
            return session.createQuery("FROM persona", Libro.class).list();
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    //metodo para actualizar una Libro
    public void actualizarLibro(Libro Libro) {
        Transaction transaction = null;
        try (Session session = databaseConnection.getSession()) {
            transaction = session.beginTransaction();
            session.update(Libro);
            transaction.commit();
        } catch (Exception e) {
            if (transaction != null) {
                transaction.rollback();
            }
            e.printStackTrace();
        }
    }

    //metodo para eliminar una Libro
    public void eliminarLibro(int id) {
        Transaction transaction = null;
        try (Session session = databaseConnection.getSession()) {
            transaction = session.beginTransaction();
            Libro libro = session.get(Libro.class, id);
            if (libro != null) {
                session.delete(libro);
                transaction.commit();
            }
        } catch (Exception e) {
            if (transaction != null) {
                transaction.rollback();
            }
            e.printStackTrace();
        }
    }
}
