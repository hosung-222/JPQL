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
            member.setUsername("member1" );
            member.setAge(10);
            member.setTeam(team);
            member.setType(MemberType.ADMIN);

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
//            //이름 기준
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


            //조건식
//            //기본 case 식
//            String query = "select " +
//                    "case when m.age <= 10 then '학생요금' " +
//                    "when m.age >= 10 then '경로요금' " +
//                    "else '일반요금' " +
//                    "end " +
//                    "FROM Member m " ;
//            List<String> result = em.createQuery(query,String.class).getResultList();
//            for (String s: result) {
//                System.out.println("s = " + s);
//            }
//            //COALESCE : 하나씩 조회해서 null이 아니면 반환
//            String query = "select coalesce(m.username,'이름없는회원') from Member m";
//            List<String> result = em.createQuery(query,String.class).getResultList();
//            for (String s: result) {
//                System.out.println("s = " + s);
//            }
//            //NULLIF : 두 값이 같으면 null 반환, 다르면 첫번째 값 반환
//            String query = "select nullif(m.username, 'member1') from Member m ";
//            List<String> result = em.createQuery(query,String.class).getResultList();
//            for (String s: result) {
//                System.out.println("s = " + s);
//            }



            //JPQL 기본함수
//            //concat : 문자열 합치기
//            String query = "select concat('a','b') FROM Member m";
//            List<String> result = em.createQuery(query,String.class).getResultList();
//            for (String s: result) {
//                System.out.println("s = " + s);
//            }
//            //substring : 문자열 잘라내기
//            String query = "select substring(m.username,2,3) FROM Member m";
//            List<String> result = em.createQuery(query,String.class).getResultList();
//            for (String s: result) {
//                System.out.println("s = " + s);
//            }
//            //TRIM : 공백 제거
//            //LOWER, UPPER : 대소문자 변경
//            //LENGTH : 문자열 길이
//            //LOCATE : de의 위치 검색 -> s = 4
//            String query = "select LOCATE('de', 'abcdef') FROM Member m";
//            List<Integer> result = em.createQuery(query,Integer.class).getResultList();
//            for (Integer s: result) {
//                System.out.println("s = " + s);
//            }
//            // ABS, SQRT, MOD : 수학 함수
//            //SIZE, INDEX
//            String query = "select size(t.members) from Team t";
//            List<Integer> result = em.createQuery(query, Integer.class)
//                            .getResultList();

            //사용자 정의 함수
//            //1. MyH2Dialect 생성
//            //2. xml에 MyH2Dialect 등록
//            //3. 사용
//            String query = "select function('group_concat' , m.username) from Member m";
//            List<String> result = em.createQuery(query,String.class).getResultList();
//            for (String s: result) {
//                System.out.println("s = " + s);
//            }



            // 경로 표현식 특징
//            //상태 필드 -> m.username 이 끝부분 더 이상 경로 탐색 불가능
//            String query = "select m.username from Member m";
//            List<String> result = em.createQuery(query, String.class).getResultList();
//            //단일 값 연관 경로 -> m.team(묵시적 내부 조인 발생) 탐색 O (ex_m.team.name)
//            String query = "select m.team.name from Member m";
//            List<String> result = em.createQuery(query, String.class).getResultList();
//            //컬렉션 값 연관 경로 -> 묵시적 내부 조인 발생, 탐색 X
//            String query = "select t.members from Team t";
//            List<String> result = em.createQuery(query, String.class).getResultList();



//            //페치 조인
//            String jpql = "select m  from Member m join fetch m.team";
//            List<Member> members = em.createQuery(jpql, Member.class).getResultList();
//            for (Member m: members) {
//                //페치 조인으로 회원과 팀을 함께 조회해서 팀의 지연 로딩 x -> 즉시 영속성 관리
//                System.out.println(" username, team = " + m.getUsername() + "," + m.getTeam().getName());
//            }
//            //컬렉션 패치 조인
//            String jpql = "select t  from Team t join fetch t.members";
//            List<Team> teams = em.createQuery(jpql, Team.class).getResultList();
//            for (Team t: teams) {
//                //페치 조인으로 회원과 팀을 함께 조회해서 팀의 지연 로딩 x -> 즉시 로딩 됨
//                System.out.println("team = " + t.getName() + t);
//                for (Member m : t.getMembers()){
//                    System.out.println("m.getUsername() = " + m.getUsername() + member);
//                }
//            }
            /**
            teamname = 팀A, team = Team@0x100
            -> username = 회원1, member = Member@0x200 -> username = 회원2, member = Member@0x300 teamname = 팀A, team = Team@0x100
            -> username = 회원1, member = Member@0x200 -> username = 회원2, member = Member@0x300
            **/
//            // 일대다 조인으로 인한  중복 제거 -> DISTINCT
//            String jpql = "select distinct t  from Team t join fetch t.members";
//            List<Team> teams = em.createQuery(jpql, Team.class).getResultList();
//            System.out.println(teams.size());



            //엔티티 직접 사용
//            //엔티티를 파라미터로 전달 가능 ID로
//            String query = "select  m from Member m where m = :member";
//            List resultList = em.createQuery(query)
//                            .setParameter("member", member)
//                                    .getResultList();
//            // 외래키 값 직접 사용
//           String query = "select m from Member m where m.team = :team";
//           List resultList = em.createQuery(query)
//                   .setParameter("team", team)
//                   .getResultList();


            //NamedQuery
            List<Member> resultList = em.createNamedQuery("Member.findByUsername", Member.class)
                            .setParameter("username", "회원1")
                                    .getResultList();
            for (Member member1 : resultList ){
                System.out.println("member= " + member1);
            }



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
