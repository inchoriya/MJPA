package com.icia.mjpa.dto;

import lombok.Data;

@Data
public class MemberDTO {

    private String mId;
    private String mPw;
    private String mName;
    private String mBirth;
    private String mGender;
    private String mEmail;
    private String mPhone;

    public static MemberDTO toDTO(MemberEntity entity){
        MemberDTO dto = new MemberDTO();

        // entity의 정보를 getter로 불러와서 setter로 dto에 저장한다.
        dto.setMId(entity.getMId());
        dto.setMPw(entity.getMPw());
        dto.setMName(entity.getMName());
        dto.setMBirth(entity.getMBirth());
        dto.setMGender(entity.getMGender());
        dto.setMEmail(entity.getMEmail());
        dto.setMPhone(entity.getMPhone());

        return dto;
    }

}
