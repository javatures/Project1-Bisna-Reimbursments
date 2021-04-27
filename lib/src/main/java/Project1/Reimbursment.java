package Project1;


public class Reimbursment {

private int emp_id_fk ;
private int sup_id_fk ;
private Float r_amt ;
private String r_image ;
private String r_remarks ;
private String r_status  ;

public Reimbursment() {
}


public Reimbursment( String r_image, String r_remarks, String r_status) {

    this.r_image = r_image;
    this.r_remarks = r_remarks;
    this.r_status = r_status;
}

public Float getR_amt() {
    return r_amt;
}
public void setR_amt(Float r_amt) {
    this.r_amt = r_amt;
}
public String getR_image() {
    return r_image;
}
public void setR_image(String r_image) {
    this.r_image = r_image;
}
public String getR_remarks() {
    return r_remarks;
}
public void setR_remarks(String r_remarks) {
    this.r_remarks = r_remarks;
}
public String getR_status() {
    return r_status;
}
public void setR_status(String r_status) {
    this.r_status = r_status;
}

@Override
public String toString() {
    return "Reimbursment [r_amt=" + r_amt + ", r_image=" + r_image + ", r_remarks=" + r_remarks + ", r_status="
            + r_status + "]";
}

    

    
}
