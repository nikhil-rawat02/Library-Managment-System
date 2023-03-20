package com.LibraryManagment.System.Repository;

import com.LibraryManagment.System.Entity.LibraryCard;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.Date;

@Repository
public interface LibraryCardRepository extends JpaRepository<LibraryCard,Integer> {

    @Query(value = "select updation_date from library_card c where c.card_no=:cardId",nativeQuery = true)
    public Date findLastUpdateDate(int cardId);

}
