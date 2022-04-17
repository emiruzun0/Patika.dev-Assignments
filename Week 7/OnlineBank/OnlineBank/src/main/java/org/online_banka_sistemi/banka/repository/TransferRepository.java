package org.kodluyoruz.mybank.repository;

import org.kodluyoruz.mybank.entity.Transfer;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface TransferRepository extends CrudRepository<Transfer, Integer> {

    List<Transfer> findAll();

}
