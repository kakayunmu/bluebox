package pers.kakayunmu.bluebox.repositorys;

import org.springframework.data.jpa.repository.JpaRepository;
import pers.kakayunmu.bluebox.entity.Member;

public interface MemberRepository extends JpaRepository<Member,String> {
    Member findByOpenid(String openid);
}
