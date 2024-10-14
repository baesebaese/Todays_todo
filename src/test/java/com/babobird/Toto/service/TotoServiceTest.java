package com.babobird.Toto.service;

import com.babobird.Toto.entity.Toto;
import com.babobird.Toto.repository.TotoRepository;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@SpringBootTest
class TotoServiceTest {

    @Mock
    private TotoRepository totoRepository;

    @InjectMocks
    private TotoService totoService;

    @Test
    public void testGetAllTotos() {
        // Given
        Toto toto1 = new Toto();
        toto1.setTotoNo(1);
        toto1.setTotoNm("Task 1");

        Toto toto2 = new Toto();
        toto2.setTotoNo(2);
        toto2.setTotoNm("Task 2");

        List<Toto> todos = Arrays.asList(toto1, toto2);

        // When
        when(totoRepository.findAll()).thenReturn(todos);

        // Then
        List<Toto> result = totoService.getAllTotos();
        assertEquals(2, result.size());
        verify(totoRepository, times(1)).findAll();
    }

    @Test
    public void testGetTotoById() {
        // Given
        Toto toto = new Toto();
        toto.setTotoNo(1);
        toto.setTotoNm("Task 1");

        // When
        when(totoRepository.findById(1)).thenReturn(Optional.of(toto));

        // Then
        Toto result = totoService.getTotoById(1);
        assertNotNull(result);
        assertEquals(1, result.getTotoNo());
        assertEquals("Task 1", result.getTotoNm());
        verify(totoRepository, times(1)).findById(1);
    }

    @Test
    public void testGetTotoByIdNotFound() {
        // Given
        when(totoRepository.findById(1)).thenReturn(Optional.empty());

        // Then
        Toto result = totoService.getTotoById(1);
        assertNull(result);  // null 반환 시 확인
        verify(totoRepository, times(1)).findById(1);
    }

    @Test
    public void testSaveToto() {
        // Given
        Toto toto = new Toto();
        toto.setTotoNm("New Task");

        // When
        totoService.saveToto(toto);

        // Then
        verify(totoRepository, times(1)).save(toto);
    }
}
