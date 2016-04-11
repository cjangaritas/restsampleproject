package com.restsample.data.model;


import cz.jirutka.validator.spring.SpELAssert;
import org.hibernate.validator.constraints.NotEmpty;
import org.joda.time.LocalDate;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.index.Indexed;
import org.springframework.data.mongodb.core.mapping.Document;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.io.Serializable;

@SpELAssert(value = "startDate.compareTo(endDate) <= 0 ",
        message = "{validator.startDateMustBeBefore}")
@Document
public class Festivity implements Serializable {

    @Id
    private String id;

    @NotNull
    @NotEmpty
    @Size(max=255)
    @Indexed(unique=true)
    private String name;

    private LocalDate startDate;

    private LocalDate endDate;

    @Size(max=255)
    private String locationName;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public LocalDate getStartDate() {
        return startDate;
    }

    public void setStartDate(LocalDate startDate) {
        this.startDate = startDate;
    }

    public LocalDate getEndDate() {
        return endDate;
    }

    public void setEndDate(LocalDate endDate) {
        this.endDate = endDate;
    }

    public String getLocationName() {
        return locationName;
    }

    public void setLocationName(String locationName) {
        this.locationName = locationName;
    }


    @Override
    public boolean equals(Object o) {
        if (this == o) return true;

        if (o == null || getClass() != o.getClass()) return false;

        Festivity festivity = (Festivity) o;

        return new org.apache.commons.lang3.builder.EqualsBuilder()
                .append(id, festivity.id)
                .isEquals();
    }

    @Override
    public int hashCode() {
        return new org.apache.commons.lang3.builder.HashCodeBuilder(17, 37)
                .append(id)
                .toHashCode();
    }
}