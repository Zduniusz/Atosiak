package com.zduniusz.discord.commandlist;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class InfectionsTest {

    @Test
    void formatNumber() {
        assertEquals("1 623 634 354", com.zduniusz.discord.commandlist.Infections.formatNumber(1623634354));
        assertEquals("1 634 354", com.zduniusz.discord.commandlist.Infections.formatNumber(1634354));
        assertEquals("1", com.zduniusz.discord.commandlist.Infections.formatNumber(1));
        assertEquals("-1 423 543", com.zduniusz.discord.commandlist.Infections.formatNumber(-1423543));
    }
}