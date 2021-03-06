package Model;

public class KeyValue implements Comparable<KeyValue> {
    private String key;
    private String value;

    public KeyValue(){}

    public KeyValue(String key, String value){
        this.key = key;
        this.value = value;
    }

    public String getKey(){
        return key;
    }

    public void setKey(String key){
        this.key = key;
    }

    public String getValue(){
        return value;
    }

    public void setValue(String value){
        this.value = value;
    }

    @Override
    public int compareTo(KeyValue kv) {
        return this.value.compareTo(kv.value);
    }  
}