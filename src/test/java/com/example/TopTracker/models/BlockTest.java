package com.example.TopTracker.models;

import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class BlockTest {

    @Test
    void shouldReturnBlockId() {
        Block block = new Block();
        Long id = 1L;

        block.setId(id);

        assertEquals(id, block.getId());
    }

    @Test
    void shouldReturnBlockName() {
        Block block = new Block();
        String result = "Avalonia";

        block.setBlockName(result);

        assertEquals(result, block.getBlockName());
    }

    @Test
    void shouldReturnBlockStoneType() {
        Block block = new Block();
        String result = "Granite";

        block.setStoneType(result);

        assertEquals(result, block.getStoneType());
    }

    @Test
    void shouldReturnBlockBoulders() {
        Block block = new Block();
        List<Boulder> boulders = new ArrayList<>();
        boulders.add(new Boulder());
        boulders.add(new Boulder());

        block.setBoulders(boulders);

        assertEquals(boulders, block.getBoulders());
    }

    @Test
    void shouldReturnBlockArea() {
        Block block = new Block();
        Area area = new Area();

        block.setArea(area);

        assertEquals(area, block.getArea());
    }
}