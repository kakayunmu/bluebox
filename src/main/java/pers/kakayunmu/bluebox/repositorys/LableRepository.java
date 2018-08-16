package pers.kakayunmu.bluebox.repositorys;

import org.springframework.data.jpa.repository.JpaRepository;
import pers.kakayunmu.bluebox.entity.Lable;

public interface LableRepository extends JpaRepository<Lable, String> {
    long countLableByCreateBy(String memberId);
    Iterable<Lable> findLablesByCreateBy(String memberId);
}
