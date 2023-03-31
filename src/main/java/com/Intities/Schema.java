package com.Intities;

public class Schema {
    private String[] schemaAttributes;

    public Schema(String ... list){
        schemaAttributes=new String[list.length];
        for (int i = 0 ; i < list.length ; i++ )
            schemaAttributes[i]=list[i];
    }
    public boolean contains(String s){
        boolean b = false;
        for (String s1 : schemaAttributes)
            if (s1.equals(s)){
                b=true;
                break;
            }
        return b;
    }
    public String[] getAttributes(){
        return schemaAttributes;
    }

}
