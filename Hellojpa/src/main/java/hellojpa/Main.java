package hellojpa;

import hellojpa.entity.Member;
import hellojpa.entity.MemberType;
import hellojpa.entity.Team;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.Persistence;
import javax.swing.plaf.metal.MetalMenuBarUI;
import java.util.List;

public class Main {
    public static void main(String[] args) {
        System.out.println("Hello");

        EntityManagerFactory emf =
                Persistence.createEntityManagerFactory("hello");

        EntityManager em = emf.createEntityManager();
        EntityTransaction tx =  em.getTransaction();
        tx.begin();

        try{
            Team team = new Team();
            team.setName("teamA");
            em.persist(team);

            Member member = new Member();
            //member.setId(100L);
            member.setName("Kim");
            member.setMemberType(MemberType.USER);
            //member.setTeam(team);

            //team.getMembers().add(member); 연관관계 주인이 아니므로 null
            member.setTeam(team);
            em.persist(member);

            em.flush();
            em.clear();


            //
 /*           Member findMember = em.find(Member.class,member.getId());
            Team findTeam = findMember.getTeam();
            List<Member> members = findTeam.getMembers();
            for(Member m: members){
                System.out.println(m);
            }*/


            //Team findTeam = em.find(Team.class,teamId);

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
