import javax.persistence.EntityManager;

import com.jpa.estudiante.EstudianteEntity;
import org.apache.logging.log4j.*;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.Persistence;

public class Test {
    static Logger log = LogManager.getRootLogger();
    public static void main(String[] args){
        //crearEstudiante();
        //recuperarPorId();
        //actualizarEstudiante();
        eliminarEstudiante();

    }
    private static void crearEstudiante(){
        EntityManagerFactory emf = Persistence.createEntityManagerFactory("EstudiantePU");
        EntityManager em = emf.createEntityManager();
        EntityTransaction tx = em.getTransaction();
        tx.begin();
        EstudianteEntity pro = new EstudianteEntity(1,"HG100721","tONY","gUIDO");
        em.persist(pro);
        tx.commit();
        System.out.println("#### NUEVO ESTUDIANTE INGRESADO ####");
        System.out.println("ID: " + pro.getIdestudiante());
        System.out.println("Nombres: " + pro.getNombres());
        System.out.println("Apellidos: " + pro.getApellidos());
        System.out.println("Carnet: " + pro.getCarnet());
        em.close();
    }
    private static void recuperarPorId(){
        EntityManagerFactory emf = Persistence.createEntityManagerFactory("EstudiantePU");
        EntityManager em = emf.createEntityManager();
        EntityTransaction tx = em.getTransaction();
        tx.begin();
        EstudianteEntity pro = em.find(EstudianteEntity.class, 1);
        tx.commit();
        if (pro != null) {
            System.out.println("####ESTUDIANTE ENCONTRADO####");
            System.out.println("ID: " + pro.getIdestudiante());
            System.out.println("Nombres: " + pro.getNombres());
            System.out.println("Apellidos: " + pro.getApellidos());
            System.out.println("Carnet: " + pro.getCarnet());
        } else {
            System.out.println("No se encontró ningún estudiante con ese ID");
        }
        em.close();
    }

    private static void actualizarEstudiante(){
        EntityManagerFactory emf = Persistence.createEntityManagerFactory("EstudiantePU");
        EntityManager em = emf.createEntityManager();
        EntityTransaction tx = em.getTransaction();
        tx.begin(); //INICIA TRANSFERENCIA

        EstudianteEntity pro = em.find(EstudianteEntity.class, 1);
        tx.commit();//FINALIZA TRANSFERENCIA
        System.out.println("####ESTUDIANTE ENCONTRADO####");
        System.out.println("ID: " + pro.getIdestudiante());
        System.out.println("Nombres: " + pro.getNombres());
        System.out.println("Apellidos: " + pro.getApellidos());
        System.out.println("Carnet: " + pro.getCarnet());

        pro.setNombres("Marcos"); //MODIFICAMOS EL VALOR
        EntityTransaction tx2 = em.getTransaction(); //NUEVA TRANSACTION
        tx2.begin();// LA EMPEZAMOS

        em.merge(pro);// ELIMINAMOS EL REGISTRO ANTERIOR
        tx2.commit();
        System.out.println("####REGISTRO ACTUALIZADO ####");
        System.out.println("ID: " + pro.getIdestudiante());
        System.out.println("Nombres: " + pro.getNombres());
        System.out.println("Apellidos: " + pro.getApellidos());
        System.out.println("Carnet: " + pro.getCarnet());
        em.close();

    }

    private static void eliminarEstudiante(){
        EntityManagerFactory emf = Persistence.createEntityManagerFactory("EstudiantePU");
        EntityManager em = emf.createEntityManager();
        EntityTransaction tx = em.getTransaction();
        tx.begin();

        EstudianteEntity pro = em.find(EstudianteEntity.class, 1);
        tx.commit();
        System.out.println("####ESTUDIANTE ENCONTRADO####");
        System.out.println("ID: " + pro.getIdestudiante());
        System.out.println("Nombres: " + pro.getNombres());
        System.out.println("Apellidos: " + pro.getApellidos());
        System.out.println("Carnet: " + pro.getCarnet());

        EntityTransaction tx2 = em.getTransaction();
        tx2.begin();

        em.remove(em.merge(pro));
        tx2.commit();
        System.out.println("####REGISTRO ELIMINADO ####");
        em.close();

    }
}
