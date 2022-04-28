package hello.hellospring.repository;

import hello.hellospring.domain.Member;

import java.util.List;
import java.util.Optional;

public interface MemberRepository {
    Member save(Member member); //저장소에 멤버 저장
    Optional<Member> findById(Long id); //id로 멤버 찾음
    Optional<Member> findByName(String name); //이름으로 멤버 찾음
    List<Member> findAll(); //모든 Member 찾음
}
