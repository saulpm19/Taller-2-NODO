package org.example.Controllers;

import org.example.connection.DatabaseConnection;
import org.example.models.Persona;
import org.hibernate.Session;
import org.hibernate.Transaction;

import java.util.List;

public class PersonaController
{
    private DatabaseConnection databaseConnection;

    public PersonaController(){
        this.databaseConnection = new DatabaseConnection();
    }

    //metodo para guardar datos nuevos
    public  void crearPersona(Persona persona){
        Transaction transaction = null;
        try(Session session = databaseConnection.getSession()){
            transaction = session.beginTransaction();
            session.save(persona);
            transaction.commit();
        }catch (Exception e){
            if(transaction != null){
                transaction.rollback();
            }
            e.printStackTrace();
        }
    }

    //metodo para obtener la persona por id
    public Persona obtenerPersonaId(int id){
        try(Session session = databaseConnection.getSession()) {
            return session.get(Persona.class, id);
        } catch (RuntimeException e) {
            throw new RuntimeException(e);
        }
    }

    //metodo para obtener todas las personas
    public List<Persona> obtenerPersonas(){
        try(Session session = databaseConnection.getSession()){
            return session.createQuery("FROM persona", Persona.class).list();
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    //metodo para actualizar una persona
    public  void  actualizarPersona(Persona persona){
        Transaction transaction = null;
        try(Session session = databaseConnection.getSession()){
            transaction = session.beginTransaction();
            session.update(persona);
            transaction.commit();
        }catch (Exception e){
            if (transaction != null){
                transaction.rollback();
            }
            e.printStackTrace();
        }
    }

    //metodo para eliminar una persona
    public  void eliminarPersona(int id){
        Transaction transaction = null;
        try(Session session = databaseConnection.getSession()){
            transaction = session.beginTransaction();
            Persona persona = session.get(Persona.class, id);
            if (persona != null){
                session.delete(persona);
                transaction.commit();
            }
        }catch (Exception e){
            if(transaction != null){
                transaction.rollback();
            }
            e.printStackTrace();
       }
   }
}
