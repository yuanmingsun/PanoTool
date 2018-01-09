package utils;

public enum DirectionEnum {

    FRONT("前面",0),RIGHT("右面",2),BACK("后面",3),LEFT("左面",4),TOP("上面",5),Bottom("地面",6);
    private String name;
    private int direction;

    private DirectionEnum(String name,int direction)

    {
        this.name=name;
        this.direction=direction;

    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getDirection() {
        return direction;
    }

    public void setDirection(int direction) {
        this.direction = direction;
    }
}
