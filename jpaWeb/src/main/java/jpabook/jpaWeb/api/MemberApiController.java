package jpabook.jpaWeb.api;

import jpabook.jpaWeb.domain.Address;
import jpabook.jpaWeb.domain.Member;
import jpabook.jpaWeb.service.MemberService;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import javax.validation.constraints.NotEmpty;
import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequiredArgsConstructor
public class MemberApiController {

    private final MemberService memberService;

    @GetMapping("/api/v1/members")
    public List<Member> membersV1(){
        return memberService.findMembers();
    }

    @GetMapping("/api/v2/members")
    public Result membersV2(){
       List<Member> findMembers = memberService.findMembers();
/*       for(Member member : findMembers){
           new MemberDto(member.getName(),member.getAddress());
       }*/

      List<MemberDto> collect =
              findMembers.stream().map(m-> new MemberDto(m.getName(),m.getAddress()))
                      .collect(Collectors.toList());
       return new Result(collect.size(),collect);
    }

    //json 배열타입으로 그대로 내보내긴 보단 data감싸 ,유지보수 및 확장성을 높인다(count 추가)
    
    @Data
    @AllArgsConstructor
    static class Result<T>{
        private int count;
        private T data;

    }

    @Data
    @AllArgsConstructor
    static class MemberDto{
        private String name;
        private Address address;
    }


    //엔티티를 외부에 노출하거나, 파라미터로 받는것을 피하자
    //엔티티를 변경하면 api 스펙이 변경된다
    @PostMapping("/api/v1/members")
    public CreateMemberResponse saveMemberV1(@RequestBody @Valid Member member){
        Long id = memberService.join(member);
        return new CreateMemberResponse(id);

    }

    @PostMapping("/api/v2/members")
    public CreateMemberResponse saveV2(@RequestBody @Valid CreateMemberRequest request){
        Member member = new Member();
        member.setName(request.getName());
        Long id = memberService.join(member);
        return new CreateMemberResponse(id);
    }

    /**
     * 회원 수정
     * @param id
     * @param request
     * @return
     */
    @PutMapping("/api/v2/members/{id}")
    public UpdateMemberResponse updateMemberV2(@PathVariable("id") Long id,
                                               @RequestBody @Valid UpdateMemberRequest request){

        memberService.update(id,request.getName()); //리턴값을 Member로 하거나 밑에 로직을 추가한다
        Member findMember = memberService.findOne(id);  //커맨드와 쿼리를 분리 , 쿼리는 다시 추가한다
        return new UpdateMemberResponse(findMember.getId(),findMember.getName());

    }

    @Data
    static class UpdateMemberRequest{
        private String name;

    }

    @Data
    @AllArgsConstructor
    static class UpdateMemberResponse{
        private Long id;
        private String name;

    }

    @Data
    static class CreateMemberRequest{
        @NotEmpty
        private String name;

    }

    @Data
    static class CreateMemberResponse {
        private Long id;

        public CreateMemberResponse( Long id) {
            this.id = id;
        }
    }
}
