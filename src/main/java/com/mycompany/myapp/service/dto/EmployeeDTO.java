package com.mycompany.myapp.service.dto;

import java.io.Serializable;
import java.util.Objects;

public class EmployeeDTO implements Serializable {

    private Long id;

    private String employeeId;

    private String name;

    private Long salary;

    private String designation;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getEmployeeId() {
        return employeeId;
    }

    public void setEmployeeId(String employeeId) {
        this.employeeId = employeeId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Long getSalary() {
        return salary;
    }

    public void setSalary(Long salary) {
        this.salary = salary;
    }

    public String getDesignation() {
        return designation;
    }

    public void setDesignation(String designation) {
        this.designation = designation;
    }


    @Override  public boolean equals(Object o){
    if (this == o)
    {   return true;
    }
    if (!(o instanceof EmployeeDTO)){
        return false;       }
    EmployeeDTO employeeDTO = (EmployeeDTO) o;
if (this.id == null) {
    return false;      }
    return Objects.equals(this.id, employeeDTO.id);

}
@Override
public int hashCode() {
            return Objects.hash(this.id);
        }
        // prettier-ignore
 @Override
public String toString() {
    return "EmployeeDTO{" +            "id=" + getId() +
               ", employeeId='" + getEmployeeId() + "'" +
               ", name='" + getName() + "'" +
               ", salary=" + getSalary() +
               ", designation='" + getDesignation() + "'" +"}";
        }
    }

