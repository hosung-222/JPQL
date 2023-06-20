package jpql;

import javax.persistence.*;
import java.util.List;

public class JpaMain {
    public static void main(String [] args){
        EntityManagerFactory emf = Persistence.createEntityManagerFactory("hello");
        EntityManager em = emf.createEntityManager();

        EntityTransaction tx = em.getTransaction();
        tx.begin();
        try{
            Member member = new Member();
            member.setUsername("member1");
            member.setAge(10);
            em.persist(member);

            //반환 타입
//            //TypedQuery : 반환 타입이 명확할 때 사용
//            TypedQuery<Member> query = em.createQuery("select m from Member m", Member.class);
//            //Query : 반환 타입이 명확하지 않을 때 사용
//            Query query1 = em.createQuery("select m.username, m.age from Member m");

            //결과 조회
//            // 결과가 하나 이상일때 : .getResultList  -> 결과가 없으면 빈 리스트 반환
//            TypedQuery<Member> query = em.createQuery("select m from Member m", Member.class);
//            List<Member> resultList = query.getResultList();
//            for (Member member1: resultList) {
//                System.out.println("member1=" + member1);
//            }
//            //결과가 하나일때 -> 결과가 없으면 NoResultException, 둘 이상이면, NonUniqueResultException 발생
//            TypedQuery<Member> query = em.createQuery("select m from Member m where m.id = 1", Member.class);
//            Member member1 = query.getSingleResult();
//            System.out.println("member=" + member1);


            //파라미터 바인딩
            //이름 기준
//            Member result = em.createQuery("select m from Member m where m.username = :username", Member.class)
//                    .setParameter("username", "member1")
//                    .getSingleResult();
//            System.out.println("Result = " + result.getUsername());
//            //위치 기준 (사용자제)
//            Member result1 = em.createQuery("select m from Member m where m.username = ?1", Member.class)
//                    .setParameter(1, "member1")
//                    .getSingleResult();
//            System.out.println("Result = " + result.getUsername());


            //프로젝션
//            // 엔티티 프로젝션
//            List<Team> result = em.createQuery("select t from Member m join m.team t", Team.class)
//                            .getResultList();
//            // 임베디드 타입 프로젝션
//            em.createQuery("select O.address from Order O", Address.class)
//                    .getResultList();
//            //스칼라 타입 프로젝션
//            em.createQuery("select distinct m.username, m.age from Member m")
//                    .getResultList();


            //프로젝션 여러 값 조회
//            //1. Query type
//            List resultList = em.createQuery("select distinct m.username, m.age from Member m")
//                    .getResultList();
//            Object o = resultList.get(0);
//            Object[] result = (Object[]) o;
//            System.out.println("username = " + result[0]);
//            System.out.println("age = " + result[1]);
//            //2.Object[] type
//            List<Object[]> resultList1 = em.createQuery("select distinct m.username, m.age from Member m")
//                    .getResultList();
//            Object[] result1 = resultList1.get(0);
//            System.out.println("username = " + result1[0]);
//            System.out.println("age = " + result1[1]);
//            //3.*****new 명령어로 조회(제일 깔끔)*****
//            List<MemberDTO> result2 = em.createQuery("select distinct new jpql.MemberDTO(m.username, m.age) from Member m", MemberDTO.class)
//                    .getResultList();
//            MemberDTO memberDTO = result2.get(0);
//            System.out.println("username = " + memberDTO.getUsername());
//            System.out.println("age = " + memberDTO.getAge());


            em.flush();
            tx.commit();
        }catch (Exception e){
            tx.rollback();;
            e.printStackTrace();
        }finally {
            em.close();
        }emf.close();
    }

}
