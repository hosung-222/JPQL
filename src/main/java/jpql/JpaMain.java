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

            Team team = new Team();
            team.setName("teamA");
            em.persist(team);

//            for (int i = 0; i <100 ; i++) {
//                Member member = new Member();
//                member.setUsername("member" + i);
//                member.setAge(i);
//                member.setTeam(team);
//
//                em.persist(member);
//            }
            Member member = new Member();
            member.setUsername("teamA" );
            member.setAge(10);
            member.setTeam(team);

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
//            //결과가 하나일때 .getSingleResult -> 결과가 없으면 NoResultException, 둘 이상이면, NonUniqueResultException 발생
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


            //페이징
//            //** setFirstResult : 조회 시작 위치, setMaxResult : 조회할 데이터 수 **
//            List<Member> result = em.createQuery("select m from Member m order by m.age desc ",Member.class)
//                            .setFirstResult(1)
//                                    .setMaxResults(10)
//                                            .getResultList();
//            System.out.println("result.size = " + result.size());
//            for (Member member1: result) {
//                System.out.println("member1 = " + member1);
//            }



            //조인
//            //내부 조인 [inner생략 가능]
//            String query = "select m from Member m inner join m.team t ";
//            List<Member> result = em.createQuery(query,Member.class)
//                            .setFirstResult(1)
//                                    .setMaxResults(10)
//                                            .getResultList();
//            //외부 조인 left Outer조인  [outer생략 가능]
//            String query1 = "select m from Member m left outer join m.team t ";
//            List<Member> result1 = em.createQuery(query1,Member.class)
//                    .setFirstResult(1)
//                    .setMaxResults(10)
//                    .getResultList();
//            //세타 조인
//            String query2 = "select m from Member m, Team t where m.username = t.name";
//            List<Member> result2 = em.createQuery(query2,Member.class)
//                    .setFirstResult(1)
//                    .setMaxResults(10)
//                    .getResultList();
            //ON 절
//            //1. 조인 대상 필터링
//            String query = "select m from Member m left JOIN m.team t on t.name = 'teamA'";
//            List<Member> result = em.createQuery(query,Member.class).getResultList();
//            System.out.println("result = " + result.size());
//            //2. 연관관계 없는 외부조인
//            String query = "select m from Member m left join Team  t on m.username = t.name";
//            List<Member> result = em.createQuery(query,Member.class).getResultList();
//            System.out.println("result = " + result.size());



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
