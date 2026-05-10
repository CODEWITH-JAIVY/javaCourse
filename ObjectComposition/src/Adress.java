public class Adress {
   private String village_name  ;
    private String city  ;
    private String block_number  ;
    private  String state  ;

    Adress (String Village_number , String city  , String block_number , String state ) {
        this.block_number = block_number ;
        this.city = city  ;
        this.state = state ;
        this.village_name  = Village_number  ;
    }
    public String getVillage_name() {
        return village_name;
    }

    public void setVillage_name(String village_name) {
        this.village_name = village_name;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getBlock_number() {
        return block_number;
    }

    public void setBlock_number(String block_number) {
        this.block_number = block_number;
    }

    public String getState() {
        return state;
    }

    public void setState(String state) {
        this.state = state;
    }
}
