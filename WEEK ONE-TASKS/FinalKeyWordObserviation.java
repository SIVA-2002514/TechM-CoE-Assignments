import java.util.Scanner;

public final class FinalKeyWordObserviation {
   Scanner sc;
   final int number;

   public FinalKeyWordObserviation() {
      this.sc = new Scanner(System.in);
      this.number = this.sc.nextInt();
   }

   public final void finalMethod() {
      System.out.println("IT'S A FINAL METHOD");
   }
}
