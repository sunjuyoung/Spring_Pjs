package hellojpa;

import hellojpa.entity.Member;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.Persistence;

public class Main {
    public static void main(String[] args) {
        System.out.println("Hello");

        EntityManagerFactory emf =
                Persistence.createEntityManagerFactory("hello");

        EntityManager em = emf.createEntityManager();
        EntityTransaction tx =  em.getTransaction();
        tx.begin();

        try{
            Member member = new Member();
            member.setId(100L);
            member.setName("Kim");

            em.persist(member);

            tx.commit();
        }catch(Exception e){
            tx.rollback();
        }finally {
            em.close();;
        }
        emf.close();
        System.out.println("종료");
    }
}
