package com.babobird.Toto.repository;

import com.babobird.Toto.domain.To_to_list;

import java.util.*;

public class MemoryListRepository implements ToToListRepository{
    private static Map<Integer, To_to_list> store = new HashMap<Integer, To_to_list>();
    private static int sequence = 0;

    @Override
    public To_to_list save(To_to_list to_to_list) {
        to_to_list.setList_no(sequence++);
        store.put(to_to_list.getList_no(), to_to_list);
        return to_to_list;
    }

    @Override
    public Optional<To_to_list> findById(int id) {
        return Optional.ofNullable(store.get(id));
    }

    @Override
    public Optional<To_to_list> findByName(String name) {
        return store.values().stream()
                .filter(toToList -> toToList.getList_nm().equals(name)).findAny();
    }

    @Override
    public List<To_to_list> findAll() {
        return new ArrayList<To_to_list>(store.values());
    }

    public void clearStore() {
        store.clear();
    }
}