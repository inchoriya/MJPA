package com.icia.mjpa.service;

import com.icia.mjpa.dao.MemberRepository;
import com.icia.mjpa.dto.MemberDTO;
import com.icia.mjpa.dto.MemberEntity;
import jakarta.servlet.http.HttpSession;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.web.servlet.ModelAndView;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class MemberService {

    private final MemberRepository mrepo;

    private final PasswordEncoder pwEnc;

    private final HttpSession session;

    private ModelAndView mav;

    // JPA(Java Persistence API)
    // 자바 객체랑 관계형 데이터베이스 사이의 매핑을 관리하는 API
    // SQL문을 쓰지 않고도 CRUD 작업을 할 수 있다.
    // (1) save(entity) : entity 정보를 insert 하거나 update 할 수 있다
    // (2) findAll() : 데이터베이스 정보를 리스트로 받아온다
    // (3) findById(id) : pk 컬럼의 값이 id인 특정 데이터베이스 정보를 entity 타입으로 받아온다
    // (4) delete(id) : pk 컬럼의 값이 id인 데이터베이스의 내용을 삭제한다.

    // JPQL(Java Persistence Query Language)
    // SQL문과 유사한 문법으로 jpa에서 sql문을 사용할 수 있다.

    public ModelAndView mJoin(MemberDTO dto) {
        mav = new ModelAndView();

        // [2] 비밀번호 암호화
        dto.setMPw(pwEnc.encode(dto.getMPw()));

        try {
            MemberEntity entity = MemberEntity.toEntity(dto);
            mrepo.save(entity);
        } catch (Exception e){
            throw new RuntimeException(e);
        }

        mav.setViewName("index");
        return mav;
    }

    // 회원목록
    public ModelAndView mList() {
        mav = new ModelAndView();

        // DB에서 조회한 정보를 entityList에 담는다
        // SELECT mId, mBrith, mEamil, mGender, mName, mPhone, mPw FROM MJPA
        // select mId, mBirth, mEmail, mGender, mName, mPhone, mPw from MJPA
        List<MemberEntity> entityList = mrepo.findAll();
        System.out.println("[2] entityList : " + entityList);

        // entity를 dto로 변환하기 위한 dtoList 객체 생성
        List<MemberDTO> dtoList = new ArrayList<>();

        // 데이터(entityList)의 갯수만큼 반복문 실행
        // entity를 dto로 변환시켜서 dtoList에 추가(add)한다
        for (MemberEntity entity : entityList) {
            dtoList.add(MemberDTO.toDTO(entity));
        }

        mav.setViewName("list");
        mav.addObject("memberList", dtoList);

        return mav;
    }


    public ModelAndView mView(String mId) {
        mav = new ModelAndView();

        // sql : SELECT * FROM MJPA WHERE MID = #{mId}
        // MemberEntity entity = mrepo.findById(mId);

        // MemberEntity entity = mrepo.findById(mId).get();

        Optional<MemberEntity> entity = mrepo.findById(mId);
        // Optional : 특정 엔티티를 조회할 때 결과가 없을 수도 있다.
        // 따라서 안전하게 null 처리를 하기 위해서

        if(entity.isPresent()) {
            MemberDTO dto = MemberDTO.toDTO(entity.get());
            mav.setViewName("view");
            mav.addObject("view", dto);
        } else {
            mav.setViewName("index");
        }

        return mav;
    }
    public ModelAndView modiForm(String mId) {
        mav = new ModelAndView();

        // public ModelAndView mView(String mId)에서 정보를 불러오기
        MemberDTO member = (MemberDTO) mView(mId).getModel().get("view");

        mav.setViewName("modify");
        mav.addObject("modify", member);

//        Optional<MemberEntity> entity = mrepo.findById(mId);
//        if(entity.isPresent()) {
//            MemberDTO dto = MemberDTO.toDTO(entity.get());
//            mav.setViewName("modify");
//            mav.addObject("modify", dto);
//        } else {
//            mav.setViewName("index");
//        }

        return mav;
    }

    public ModelAndView mModify(MemberDTO dto) {
        mav = new ModelAndView();

        try {
            MemberEntity entity = MemberEntity.toEntity(dto);
            mrepo.save(entity);
        } catch (Exception e){
            throw new RuntimeException(e);
        }

        mav.setViewName("redirect:/mList");
        return mav;
    }

    public ModelAndView mDelete(String mId) {
        mav = new ModelAndView();

        try {
            mrepo.deleteById(mId);
        } catch (Exception e){
            throw new RuntimeException(e);
        }

        mav.setViewName("redirect:/mList");

        return mav;
    }

    public ModelAndView mLogin(MemberDTO member) {
        mav = new ModelAndView();

        Optional<MemberEntity> entity = mrepo.findById(member.getMId());

        if(entity.isPresent()){
            if(pwEnc.matches(member.getMPw(), entity.get().getMPw())){
                member = new MemberDTO();
                member = MemberDTO.toDTO(entity.get());

                session.setAttribute("loginId", member.getMId());
                mav.setViewName("index");
            }

        } else {
            mav.setViewName("index");
        }

        return mav;
    }
}






