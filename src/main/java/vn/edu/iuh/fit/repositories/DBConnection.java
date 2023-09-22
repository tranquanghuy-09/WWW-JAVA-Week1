package vn.edu.iuh.fit.repositories;

import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.Persistence;

public class DBConnection {
    private static DBConnection instance;
    private EntityManagerFactory emf;

    private DBConnection(){
        try{
            emf = Persistence.createEntityManagerFactory("TranQuangHuy_20092731");
        }catch (Exception e){
            e.printStackTrace();
        }
    }

    public static synchronized DBConnection getInstance() {
        if (instance == null)
            instance = new DBConnection();
        return instance;
    }

    public EntityManager getEntityManager() {
        return emf.createEntityManager();
    }

    public static void main(String[] args) {
        EntityManager em = DBConnection.getInstance().getEntityManager();
        System.out.println("Kết nối thành công");
    }
}
