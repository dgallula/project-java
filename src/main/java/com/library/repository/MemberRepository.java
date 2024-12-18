package com.library.repository;

import com.library.model.Member;
import java.util.*;

public class MemberRepository {
    private final Map<String, Member> members = new HashMap<>();

    public void save(Member member) {
        members.put(member.getId(), member);
    }

    public void update(Member member) {
        members.put(member.getId(), member);
    }

    public Optional<Member> findById(String id) {
        return Optional.ofNullable(members.get(id));
    }

    public List<Member> findAll() {
        return new ArrayList<>(members.values());
    }

    public void delete(String id) {
        members.remove(id);
    }
}