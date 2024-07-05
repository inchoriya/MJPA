package com.icia.mjpa.dao;

import com.icia.mjpa.dto.MemberEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface MemberRepository extends JpaRepository<MemberEntity, String> {

}
