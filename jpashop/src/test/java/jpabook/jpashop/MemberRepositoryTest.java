package jpabook.jpashop;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.Rollback;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import javax.transaction.Transactional;

import static org.assertj.core.api.Assertions.assertThat;


@ExtendWith(SpringExtension.class)
@SpringBootTest
class MemberRepositoryTest {

    @Autowired
    MemberRepository memberRepository;

    @Test
    @Transactional
    @Rollback(false) //Transactional 은 테스트가 끝나고 롤백한다
    public void testMemeber() throws Exception{
        Member member = new Member();
        member.setUsername("memberA");

        Long saveId = memberRepository.save(member);

        Member findMember = memberRepository.find(saveId);

        assertThat(findMember.getId()).isEqualTo(member.getId());


      // Assertions.assertTrue(findMember.getId().equals(member.getId()));
      // Assertions.assertTrue(findMember.getUsername().equals(member.getUsername()));

        assertThat(findMember).isEqualTo(member);
        System.out.println(findMember == member);

    }
}