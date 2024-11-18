package org.example;

import org.example.Controllers.LibroController;
import org.example.Controllers.PersonaController;
import org.example.Data.Data;
import org.example.connection.DatabaseConnection;
import org.example.models.Persona;

import java.util.Scanner;

//TIP To <b>Run</b> code, press <shortcut actionId="Run"/> or
// click the <icon src="AllIcons.Actions.Execute"/> icon in the gutter.
public class Main
{
    public static void main(String[] args) throws Exception
    {
        DatabaseConnection dbconnect = new DatabaseConnection();
        dbconnect.connectDb();

        Data addData = new Data();
        addData.enterData();


        Scanner scanner = new Scanner(System.in);

        System.out.println("ingrese los datos para crear la persona");
        Persona persona = new Persona();

        System.out.println("ingrese el nombre");
        String nombre = scanner.nextLine();
        persona.setNombre(nombre);

        System.out.println("ingrese el apellido");
        String apellido = scanner.nextLine();
        persona.setApellido(apellido);

        System.out.println("ingrese el sexo");
        String sexo = scanner.nextLine();
        persona.setSexo(sexo);

        PersonaController personaDao = new PersonaController();
        //personaDao.crearPersona(persona);

        System.out.println("ingrese una persona a eliminar");
        int id = scanner.nextInt();
        //personaDao.eliminarPersona(id);

        LibroController libroDao = new LibroController();
        System.out.println("ingrese un libro a eliminar");
        int idlibro = scanner.nextInt();
        //libroDao.eliminarLibro(idlibro);


    }
}