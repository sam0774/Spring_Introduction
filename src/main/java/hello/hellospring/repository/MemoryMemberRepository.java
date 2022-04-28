package hello.hellospring.repository;

import hello.hellospring.domain.Member;

import java.util.*;

public class MemoryMemberRepository implements MemberRepository{
    private static long sequence = 0L; //id
    private static Map<Long, Member> store = new HashMap<>(); //멤버들을 저장할 저장소
    //실무에서는 동시성 문제 고려해야하므로 HashMap, long 쓰면 안됨


    @Override
    public Member save(Member member) {
        member.setId(++sequence); //id는 회원가입할때의 id가 아니라, 시스템 내부적 id임
                                 //name은 고객이 회원가입할때 적을 예정이라 우리가 안적음
        store.put(member.getId(), member); //store에 저장
        return member;
    }

    @Override
    public Optional<Member> findById(Long id) {
        return Optional.ofNullable(store.get(id));
    }
    //Optional.ofNullable로 감싸주면 null일 경우에도 추가연산이 가능함

    @Override
    public Optional<Member> findByName(String name) {
        return store.values().stream()
                .filter(member->member.getName().equals(name)) //멤버이름과 파라미터로 넘어온 문자열이 같아야 filter를 통과함
                .findAny(); //하나라도 찾으면 Optional로 반환. 없으면 Optional에 null 포함해서 반환
    }
    //valuse메소드: 해당 Mapf의 모든 value를 모아서 Collection형태로 반환

    @Override
    public List<Member> findAll() {
        return new ArrayList<>(store.values()); //Map의 모든 value들을 ArrayList로 만들어서 반환
    }
}
