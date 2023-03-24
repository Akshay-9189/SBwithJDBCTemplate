package com.csi.constants;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Getter
@ToString
@NoArgsConstructor
@AllArgsConstructor
public enum Queries {

    INSERT("insert into employee(empid, empname, empaddress, empcontactnumber, empdob, empsalary, empemailid, emppassword)values(?,?,?,?,?,?,?,?)"),

    GET_ALL("select * from employee"),

    UPDATE("update employee set empname=?, empaddress=?, empcontactnumber=?, empdob=?, empsalary=?, empemailid=?, emppassword=? where empid=?"),

    DELETE_BY_ID("delete from employee where empid=?"),

    GET_BY_ID("select * from employee where empid=?"),

    GET_BY_CONTACT("select * from employee where empcontactnumber=?"),

    GET_BY_EMAIL("select * from employee where empemailid=?"),

    GET_BY_DOB("select * from employee where empdob=?"),

    PATCH_ADDRESS_CONTACT("update employee set empaddress=?, empcontactnumber=? where empid=?"),

    DELETE_ALL("truncate table employee"),

    GET_BY_NAME_MAIL("select * from employee where empname=? and empemailid=?");

    private String query;
}