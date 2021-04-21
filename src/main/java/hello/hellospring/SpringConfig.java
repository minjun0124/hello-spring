package hello.hellospring;

import hello.hellospring.repository.MemberRepository;
import hello.hellospring.repository.jpaMemberRepository;
import hello.hellospring.service.MemberService;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class SpringConfig {
/*   JPA 사용으로 대체됨
    private DataSource dataSource;

    @Autowired
    public SpringConfig(DataSource dataSource) {
        this.dataSource = dataSource;
    }
*/

/* spring-data-jpa 로 변경

// 원래는 persistenceContext 필요하지만 알아서 DI 해준다
//    @PersistenceContext
    private EntityManager em;

    @Autowired
    public SpringConfig(EntityManager em) {
        this.em = em;
    }
*/

    private final MemberRepository memberRepository;

    public SpringConfig(MemberRepository memberRepository) {
        this.memberRepository = memberRepository;
    }

    @Bean
    public MemberService memberService() {
        return new MemberService(memberRepository);
    }
/* spring-data-jpa : SpringDataJpaMemberRepository 에서 구현체를 proxy기술을 사용하여
                     만들고 spring bean에 등록까지 완료함

    @Bean
    public MemberRepository memberRepository() {
//        return new MemoryMemberRepository();
//        return new JdbcMemberRepository(dataSource);
//        return new JdbcTemplateMemberRepository(dataSource);
        return new jpaMemberRepository(memberRepository);
    }
*/
}
