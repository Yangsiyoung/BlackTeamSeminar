package com.ysjleader.weightlog.domain;

import com.querydsl.jpa.impl.JPAQueryFactory;
import com.ysjleader.weightlog.dto.MemberDTO;
import com.ysjleader.weightlog.dto.QMemberDTO;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;

import static com.ysjleader.weightlog.domain.QMember.member;

@Repository
public class MemberRepositoryImpl implements MemberRepositoryCustom{
    private final JPAQueryFactory queryFactory;

    public MemberRepositoryImpl(EntityManager entityManager) {
        this.queryFactory = new JPAQueryFactory(entityManager);
    }

    @Override
    public MemberDTO search(Long id) {
        return queryFactory
                        .select(new QMemberDTO(
                                member.id
                                , member.userID
                                , member.email
                        )).from(member)
                        .where(member.id.eq(id))
                        .fetchOne();
    }
}
