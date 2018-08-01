package pers.kakayunmu.bluebox.repositorys;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import pers.kakayunmu.bluebox.entity.Family;

import java.util.Optional;

@Repository
public interface FamilyRepository extends JpaRepository<Family,Long> {

}
