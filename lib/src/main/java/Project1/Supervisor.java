
package Project1 ;

public class Supervisor {  int sup_id;
    private String sup_fn;
    private String sup_ln;

    
    public Supervisor() {
    }

    public Supervisor(int sup_id, String sup_fn, String sup_ln) {
        this.sup_id = sup_id;
        this.sup_fn = sup_fn;
        this.sup_ln = sup_ln;
    }

    public Supervisor(String sup_fn) {
        
        this.sup_fn = sup_fn;
    
    }

    public int getSup_id() {
        return sup_id;
    }

    public void setSup_id(int sup_id) {
        this.sup_id = sup_id;
    }

    public String getSup_fn() {
        return sup_fn;
    }

    public void setSup_fn(String sup_fn) {
        this.sup_fn = sup_fn;
    }

    public String getSup_ln() {
        return sup_ln;
    }

    public void setSup_ln(String sup_ln) {
        this.sup_ln = sup_ln;
    }

    @Override
    public String toString() {
        return "Supervisor [sup_fn=" + sup_fn + ", sup_id=" + sup_id + ", sup_ln=" + sup_ln + "]";
    }

    
}
