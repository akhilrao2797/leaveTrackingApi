package com.hashedin.huleavetracking;

import java.time.LocalDate;

public class OptionalLeaves {
    public LocalDate getLocalDate1() {
        return localDate1;
    }

    public LocalDate getLocalDate2() {
        return localDate2;
    }

    private LocalDate localDate1;
    private LocalDate localDate2;

    public void setUsed(boolean used) {
        this.used = used;
    }

    private boolean used;

    public boolean isUsed() {
        return used;
    }

    public OptionalLeaves(LocalDate localDate1, LocalDate localDate2) {
        this.localDate1 = localDate1;
        this.localDate2 = localDate2;
        this.used=false;
    }
}
