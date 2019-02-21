package com.hashedin.huleavetracking;

import javax.persistence.Entity;
import javax.persistence.ForeignKey;
import javax.persistence.Id;
import java.time.LocalDate;


public class Leaves {

    private int id;
    private int employeeId;
    private LocalDate startDate;
    private LocalDate endDate;
    private String reason;

}
