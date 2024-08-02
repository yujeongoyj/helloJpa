package hellojpa;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.Persistence;
import java.util.List;

public class JpaMain {

    public static void main(String[] args) {
        // Persistence 로 entityManagerFactory 를 생성
        // entityManagerFactory 는 애플리케이션 로딩 시점에 꼭!!!! 하나만 만들어야함
        EntityManagerFactory emf = Persistence.createEntityManagerFactory("hello");

        // 실제 DB에 저장하는 트랜잭션을 할 때마다 이 EntityManger를 꼭!! 만들어야함
        EntityManager em = emf.createEntityManager();
        // jpa에서 모든 변경사항은 트랜잭션 안에서 수행되기 때문에 트랜잭션도 만들기
        EntityTransaction tx = em.getTransaction();
        tx.begin();

        // 이 안에 실제 동작하는 코드 작성
        try {
            // entityManager 를 마치 자바 컬랙션처럼 동작한다 생각
            // 내 객체를 대신 저장해주는 놈이라고 생각
          /*  Member findMember =  em.find(Member.class, 1L); // (entity 의 클래스, pk)
            findMember.setName("Hello");

            System.out.println("findMember = " + findMember.getId());
            System.out.println("findMember = " + findMember.getName());*/



            // 만약에 조인 등 복잡한 쿼리가 필요한 상황에서는 jpql 이라는 것을 사용한다
            // sql과 유사하지만 데이터베이스가 아닌 엔티티 객체를 대상으로 쿼리르 작성한다.
            // jpql 쿼리는 엔티티 객체의 속성을 사용하여 데이터베이스를 조회한다.
            List<Member> result = em.createQuery("select m from Member as m", Member.class)
                    .getResultList(); // 두번째 인자는 엔티티타입, // getResultList 는 쿼리를 실행하고 결과를 List 형태로 반환

            for (Member member: result) {
                System.out.println("member.name = " + member.getName());
            }


            // persist로 멤버를 저장

            tx.commit();
        } catch (Exception e) {
            tx.rollback();
        } finally {
            // 실제 애플리케이션이 완전히 끝나면 entityManagerFactory 닫아줘야함
            em.close();
        }
        emf.close();

    }
}
