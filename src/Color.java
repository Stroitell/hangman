public enum Color {
    RESET("\u001B[0m"),
    RED("\u001B[31m"),
    YELLOW ("\u001B[93m"),
    GREEN ("\u001B[32m");

    private final String color;
    Color(String color) {
        this.color = color;
    }
    public String getColor(){
        return color;
    }
}
