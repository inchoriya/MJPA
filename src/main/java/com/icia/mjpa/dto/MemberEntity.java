package com.icia.mjpa.dto;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Data;

@Data
@Entity
@Table(name="MJPA")
public class MemberEntity {

    @Id
    private String mId;

    @Column
    private String mPw;

    @Column
    private String mName;

    @Column
    private String mBirth;

    @Column
    private String mGender;

    @Column
    private String mEmail;

    @Column
    private String mPhone;

    public static MemberEntity toEntity(MemberDTO dto){
        MemberEntity entity = new MemberEntity();

        // dto의 정보를 getter로 불러와서 setter로 entity에 저장한다.
        entity.setMId(dto.getMId());
        entity.setMPw(dto.getMPw());
        entity.setMName(dto.getMName());
        entity.setMBirth(dto.getMBirth());
        entity.setMGender(dto.getMGender());
        entity.setMEmail(dto.getMEmail());
        entity.setMPhone(dto.getMPhone());

        return entity;
    }

}
