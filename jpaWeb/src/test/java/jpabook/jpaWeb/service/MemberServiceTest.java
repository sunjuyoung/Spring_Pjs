package jpabook.jpaWeb.service;

import jpabook.jpaWeb.domain.Member;
import jpabook.jpaWeb.repository.MemberRepository;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.Rollback;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.transaction.annotation.Transactional;

import static org.junit.Assert.*;

@RunWith(SpringRunner.class)
@SpringBootTest
@Transactional
public class MemberServiceTest {

    @Autowired
    MemberService service;

    @Autowired
    MemberRepository repository;

    @Test
    //@Rollback(false) // flush 커밋을 안하고 롤백하기때문에 insert문을 확인할수없다 , DB확인시 사용
    public void 회원가입() throws Exception{
        //given
        Member member = new Member();
        member.setName("Kim");
        //when
        Long id = service.join(member);

        //then
        assertEquals(member,repository.findOne(id));

    }

    @Test(expected = IllegalStateException.class)
    public void 중복() throws Exception{
        Member member = new Member();
        member.setName("Kim");

        Member member2 = new Member();
        member2.setName("Kim");

        //when
        service.join(member);
        service.join(member2); //예외
/*        try{
            service.join(member2); //예외
        }catch (IllegalStateException e){
            return ;
        }*/

        //then
        fail("여기까지 오면 안되다");
    }

}