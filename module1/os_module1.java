import java.util.Scanner;
import java.util.Stack;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.Reader;

public class module_1 {
   
   static Scanner sc = new Scanner(System.in);
   static String file_read =" ";//띄어쓰기로 구분
   static String[] value;// 읽은 값
   static String DR=null;
   static String memory_A;
   static String memory_B;
   static String memory_C;
   
   static Stack<String> stack = new Stack<>();
   /*static void clean() {
      value[0]=null;
      value[1]=null;
   }*/
   static void read() throws IOException {
       BufferedReader reader = null;
       try {          
           reader = new BufferedReader(new FileReader("C:\\OS\\module2.pl"));
           while((file_read = reader.readLine()) != null) {
              System.out.println("진행하시겠습니까? (y/n)");
              System.out.println(file_read);
              value = file_read.split(" ");
              String yn = sc.nextLine();
              if (yn.equals("y")) {
                 if (value[0].equals("0000"))// Load num
                  {
                    DR = value[1];
                    System.out.println("DR에 들어갔습니다"+DR);
                    //clean();
                    continue;
                  }
                 else if (value[0].equals("0001"))// Load [a]
                  {
                    DR = value[1];
                    System.out.println("DR에 들어갔습니다"+DR);
                    //clean();
                    continue;
                  }
                 else if (value[0].equals("0010"))// store
                  {
                    store();
                    //clean();
                    continue;
                  }
                 else if (value[0].equals("0011"))// push num
                  {
                      stack.push(value[1]);
                      System.out.println("스택에 들어갔습니다."+stack);
                      //clean();
                      continue;
                  }
                 else if (value[0].equals("0100"))// push
                  {
                    stack.push(DR);
                    System.out.println("stack에 들어갔습니다"+stack);
                    //clean();
                   
                    continue;
                  }
                 else if (value[0].equals("0101"))// pop
                  {
                    if (stack.isEmpty()) {
                         System.out.println("스택이 비어있습니다.");
                     } else {
                         DR = stack.pop();
                         System.out.println("DR에 들어갔습니다" + DR);
                     }
                      //clean();
                      continue;
                  }
                 else if (value[0].equals("0110"))// add
                  {
                      stack.push(Integer.toString(Integer.parseInt(stack.pop()) + Integer.parseInt(DR)));
                      System.out.println("DR에 들어갔습니다"+DR);
                      //clean();
                      continue;
                  }
                      /*else if(value[0].equals("0111"))//sub
                      {
                          stack.push(stack.pop()-DR);
                      }*/
                 else if(value[0].equals("1000"))
                 {
                    memory();
                    //clean();
                    continue;
                 }
                  else if (value[0].equals("1001"))
                  {
                      String result = null;
                      result = memory_C;
                      System.out.println("result : "+value[1]);
                      break;
                  }
                  else
                  {
                     break;
                  }
                 
              }
         
           }

       }
       finally {
           if (reader != null) {
               reader.close();
           }
       }
   }
   static void store() {
      if(value[1].equals("0111"))
      {
         memory_A=DR;
         System.out.println(memory_A);
      }
      if(value[1].equals("0100"))
      {
         memory_B=DR;
         System.out.println(memory_B);
      }
      if(value[1].equals("0001"))
      {
         memory_C=DR;
         System.out.println(memory_C);
      }
   }
   
   static String memory() {
      if(value[1].equals("0111"))
      {
         memory_A=stack.peek();
         System.out.println("메모리"+memory_A);
      }
      if(value[1].equals("0100"))
      {
         memory_B=stack.peek();
         System.out.println(memory_B);
      }
      if(value[1].equals("0001"))
      {
         memory_C=stack.peek();
         System.out.println(memory_C);
      }
      return null;
   }
   

public static void main(String[] args) {
   
      try {
         read();
      }
      catch (IOException e) {
         e.printStackTrace();
      }
    }
}