package com.library.service;

import com.library.model.Member;
import com.library.repository.MemberRepository;
import com.library.validation.MemberValidator;
import java.util.List;
import java.util.Optional;

public class MemberService {
    private final MemberRepository memberRepository;
    private final MemberValidator memberValidator;

    public MemberService(MemberRepository memberRepository) {
        this.memberRepository = memberRepository;
        this.memberValidator = new MemberValidator();
    }

    public void addMember(Member member) {
        memberValidator.validate(member);
        memberRepository.save(member);
    }

    public Optional<Member> findById(String id) {
        return memberRepository.findById(id);
    }

    public List<Member> getAllMembers() {
        return memberRepository.findAll();
    }

    public void updateMember(Member member) {
        memberValidator.validate(member);
        memberRepository.update(member);
    }

    public void deleteMember(String id) {
        memberRepository.delete(id);
    }
}