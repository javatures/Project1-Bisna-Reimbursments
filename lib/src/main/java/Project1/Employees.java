package Project1;

public class Employees {


    private int emp_id ;
    private String emp_fn ;
    private String emp_ln ;
    private String emp_dep ;
    private int sup_id_fk ;

    

    public Employees(int emp_id, String emp_fn, String emp_ln, String emp_dep, int sup_id_fk) {
        this.emp_id = emp_id;
        this.emp_fn = emp_fn;
        this.emp_ln = emp_ln;
        this.emp_dep = emp_dep;
        this.sup_id_fk = sup_id_fk;
    }
    public Employees(String emp_fn, String emp_ln, String emp_dep) {
        this.emp_fn = emp_fn;
        this.emp_ln = emp_ln;
        this.emp_dep = emp_dep;
    }
    public Employees() {
    }
    public String getEmp_fn() {
        return emp_fn;
    }
    public void setEmp_fn(String emp_fn) {
        this.emp_fn = emp_fn;
    }
    public String getEmp_ln() {
        return emp_ln;
    }
    public void setEmp_ln(String emp_ln) {
        this.emp_ln = emp_ln;
    }
    public String getEmp_dep() {
        return emp_dep;
    }
    public void setEmp_dep(String emp_dep) {
        this.emp_dep = emp_dep;
    }
    public int getEmp_id() {
        return emp_id;
    }
    public void setEmp_id(int emp_id) {
        this.emp_id = emp_id;
    }
    public int getSup_id_fk() {
        return sup_id_fk;
    }
    public void setSup_id_fk(int sup_id_fk) {
        this.sup_id_fk = sup_id_fk;
    }
    


    
}
