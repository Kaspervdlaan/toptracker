package com.example.TopTracker.models;

import com.example.TopTracker.models.Area;
import com.example.TopTracker.models.Block;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class AreaTest {

    @Test
    void shouldReturnAreaId() {
        Area area = new Area();
        Long id = 1L;

        area.setId(id);

        assertEquals(id, area.getId());
    }

    @Test
    void shouldReturnAreaName() {
        Area area = new Area();
        String result = "Avalonia";

        area.setName(result);

        assertEquals(result, area.getName());
    }

    @Test
    void shouldReturnAreaAddress() {
        Area a = new Area();
        String result = "Germany";

        a.setAddress(result);

        assertEquals(result, a.getAddress());
    }

    @Test
    void shouldReturnAreaDescription() {
        Area a = new Area();
        String result = "Hand dug by Daniel";

        a.setDescription(result);

        assertEquals(result, a.getDescription());
    }

    @Test
    void shouldReturnAreaBlocks() {
        Area a = new Area();
        List<Block> blocks = new ArrayList<>();
        blocks.add(new Block());
        blocks.add(new Block());

        a.setBlocks(blocks);

        assertEquals(blocks, a.getBlocks());
    }
}
