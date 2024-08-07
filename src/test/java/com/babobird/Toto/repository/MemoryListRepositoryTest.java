package com.babobird.Toto.repository;

import com.babobird.Toto.domain.To_to_list;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.time.LocalDate;
import java.util.Date;

import static org.junit.jupiter.api.Assertions.*;

class MemoryListRepositoryTest {

    MemoryListRepository repository = new MemoryListRepository();

    @AfterEach
    public void afeterEach() {
        repository.clearStore();
    }

    @Test
    public void save() {
        To_to_list toToList = new To_to_list();
        toToList.setList_no(4);
        toToList.setList_nm("오늘의 할 일");
        toToList.setTask_no(1);
        toToList.setTask_nm("고양이랑 놀기");
        toToList.setStatus("N");
        toToList.setWrite_dt(new Date());
        toToList.setModify_dt(new Date());

        repository.save(toToList);

        To_to_list result = repository.findById(toToList.getList_no()).get();
        Assertions.assertEquals(toToList, result);
    }

    @Test
    public void findAll() {

    }

}