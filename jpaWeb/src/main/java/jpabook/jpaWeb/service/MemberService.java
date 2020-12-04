package jpabook.jpaWeb.service;

import jpabook.jpaWeb.domain.Member;
import jpabook.jpaWeb.repository.MemberRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional(readOnly = true)
@RequiredArgsConstructor
public class MemberService {


    private final MemberRepository repository;


/*    public MemberService(MemberRepository memberRepository){
        this.repository = memberRepository;
    }*/

/*    public void setMemberRepository(MemberRepository memberRepository){
        this.repository = memberRepository;
    }*/

    /**
     * 회원 가입
     * @param member
     * @return
     */
    @Transactional    //default readOnly = false
    public Long join(Member member){
        validateDuplicateMember(member);
        repository.save(member);
        return member.getId();
    }

    //중복 검사
    private void validateDuplicateMember(Member member) {
        List<Member> findMember = repository.findByName(member.getName());
        //findMember.size();
        if(!findMember.isEmpty()){
            throw new IllegalStateException("이미 존재하는 회원입니다");
        }
        //멀티스레드 동시 접속 , 동시 생성 방지를위해 name 은 unique설정
    }


    public List<Member> findMembers(){
        return repository.findAll();

    }


    public Member findOne(Long id){
        return repository.findOne(id);

    }

}
