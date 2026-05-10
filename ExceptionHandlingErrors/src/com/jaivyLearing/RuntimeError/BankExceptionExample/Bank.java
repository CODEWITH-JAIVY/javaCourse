package com.jaivyLearing.RuntimeError.BankExceptionExample;

public class Bank {
    String name ;
     final double  Mini_blance  =  10000.00 ;
     double total_blance =  Mini_blance  ;

     public Bank(String name ) throws  MinimalBlanceException {
         if (name != null ||  name.isEmpty() ) {
             this.name = name;
         }else {
             throw new MinimalBlanceException(" Enter a valid name.") ;
         }
     }
     public  void withdrowl ( double amount ) throws  MinimalBlanceException  {
         if (amount  <  Mini_blance - Mini_blance   ){
             throw  new MinimalBlanceException(" Cannot withdraw: balance would fall below the minimum required.") ;
         }else  {
//             total_blance = Mini_blance  ;
             total_blance -= amount   ;
             System.out.println("✅ Withdrawn: ₹" + amount + " | Remaining Balance: ₹" + total_blance );
             System.out.println();
         }

     }
     public void deposite ( double balnce ) throws  MinimalBlanceException  {
         if (balnce <= 0 ) {
             throw new MinimalBlanceException("❌ Deposit amount must be greater than zero.") ;
         }else  {
             total_blance += balnce  ;
             System.out.println("✅ Deposite balance : ₹" + balnce + " | Remaining Balance: ₹" + total_blance );
             System.out.println();
         }
     }
    public void displayBalance() {
        System.out.println("👤 Account Holder: " + name);
        System.out.println("💰 Current Balance: ₹" + total_blance );
    }

    public static void main(String[] args) {
       try{
           Bank bank = new Bank("Sanjeet ") ;
           bank.deposite(500);
           bank.withdrowl(100);
           bank.displayBalance();
       }catch ( MinimalBlanceException e ) {
           System.out.println(e.getMessage() );
       }
    }
}
