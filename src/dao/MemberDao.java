package dao;

import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;

import dto.MemberDto;
import mybatis.config.DBService;

public class MemberDao {

	// SqlSession 을 생성할 수 있는 factory
	private SqlSessionFactory factory;
	
	// BBSDao 의 singleton 처리
	private MemberDao() {
		factory = DBService.getInstance().getFactory();
	}
	private static MemberDao mDao = new MemberDao();
	public static MemberDao getInstance() {
		return mDao;
	}
	
	// DB 접근 실제 Method
	
	public MemberDto login(MemberDto mDto) {
		SqlSession ss = factory.openSession();
		MemberDto resultDto = ss.selectOne("mybatis.mapper.member.login", mDto);
		ss.close();
		return resultDto;
	}
	
	// ID 체크
	public MemberDto IDCheck(String mId) {
		SqlSession ss = factory.openSession();
		MemberDto mDto = ss.selectOne("mybatis.mapper.member.IDCheck", mId);
		ss.close();
		return mDto;
	}
	
	// Email 체크
	public MemberDto EmailCheck(String mEmail) {
		SqlSession ss = factory.openSession();
		MemberDto mDto = ss.selectOne("mybatis.mapper.member.EmailCheck", mEmail);
		ss.close();
		return mDto;
	}
	
	// Phone 체크
	public MemberDto PhoneCheck(String mPhone) {
		SqlSession ss = factory.openSession();
		MemberDto mDto = ss.selectOne("mybatis.mapper.member.PhoneCheck", mPhone);
		ss.close();
		return mDto;
	}
	
	// 회원가입
	public int memberInsert(MemberDto mDto) {
		SqlSession ss = factory.openSession(false);
		int result = ss.insert("mybatis.mapper.member.memberInsert", mDto);
		if(result > 0) {
			ss.commit();
		}
		ss.close();
		return result;
	}
	
	// 회원정보 수정
	public int memberUpdateInfo(MemberDto mDto) {
		SqlSession ss = factory.openSession();
		int result = ss.update("mybatis.mapper.member.memberUpdateInfo", mDto);
		if(result > 0) {
			ss.commit();
		}
		ss.close();
		return result;
	}
	
	// 비밀번호 수정
	public int memberUpdatePw(MemberDto mDto) {
		SqlSession ss = factory.openSession(false);
		int result = ss.update("mybatis.mapper.member.memberUpdatePw", mDto);
		if(result > 0) {
			ss.commit();
		}
		ss.close();
		return result;
	}
	
	// 회원 탈퇴
	public int leaveInfo(int mNo) {
		SqlSession ss = factory.openSession(false);
		int result = ss.delete("mybatis.mapper.member.leaveInfo", mNo);
		if(result > 0) {
			ss.commit();
		}
		ss.close();
		return result;
	}
	
	// 아이디 찾기
	public MemberDto findId(String mEmail) {
		SqlSession ss = factory.openSession();
		MemberDto mDto = ss.selectOne("mybatis.mapper.member.findId", mEmail);
		ss.close();
		return mDto;
	}
	
	// 비밀번호 찾기
	public MemberDto findPw(MemberDto mDto) {
		SqlSession ss = factory.openSession();
		MemberDto resultDto = ss.selectOne("mybatis.mapper.member.findPw", mDto);
		ss.close();
		return resultDto;
	}
}













