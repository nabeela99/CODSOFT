public class Validation {
    public static void checkInput(String text) throws IllegalAccessException {
        if(Utility.n == 1 && text.isEmpty()){
            throw new IllegalAccessException("No input is Entered");
        }else if(Utility.n == 2 && text.isEmpty()){
            throw new IllegalAccessException("File is Empty");
        }
    }
}
