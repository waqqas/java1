public class SubtractionFun {
    static {
      System.loadLibrary("native");
    }
  
    private native int subtractValues(double num1, double num2);
  
    public static void main(String[] args) {
      double num1 = 1;
      double num2 = 2;
      int difference = new SubtractionFun().subtractValues(num1, num2);
    }
  }