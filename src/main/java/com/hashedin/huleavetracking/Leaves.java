package com.hashedin.huleavetracking;

import javax.persistence.Entity;
import javax.persistence.ForeignKey;
import javax.persistence.Id;
import java.time.LocalDate;

@Entity
public class Leaves {
    @Id
    private int id;
    private int employeeId;
    private LocalDate startDate;
    private LocalDate endDate;
    private String reason;

}
