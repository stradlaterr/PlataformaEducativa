package com.example.gigabyte.plataformaeducativa;

public class MenuMateDatos {
    private String name;
    private int imageResourceId;

    public MenuMateDatos(String name, int imageResourceId){
        this.name = name;
        this.imageResourceId = imageResourceId;
    }
    public static final MenuMateDatos[] datos = {
            new MenuMateDatos("Suma", R.drawable.espanol),
            new MenuMateDatos("Resta", R.drawable.geografia),
            new MenuMateDatos("Multiplicación", R.drawable.matem),
            new MenuMateDatos("División", R.drawable.hst),
    };

    public String getName(){
        return name;
    }
    public int getImageResourceId(){
        return imageResourceId;
    }
}
