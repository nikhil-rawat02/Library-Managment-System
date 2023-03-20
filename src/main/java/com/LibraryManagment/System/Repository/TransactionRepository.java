package com.LibraryManagment.System.Repository;

import com.LibraryManagment.System.Entity.Transaction;
import com.LibraryManagment.System.Enum.TransactionStatus;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface TransactionRepository extends JpaRepository<Transaction,Integer> {

    @Query (value ="select * from transaction t where t.card_card_no=:cardNo",nativeQuery = true)
    public List<Transaction> getAllTransaction(int cardNo);

}
