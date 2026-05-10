package com.practisecodewithjaivy;
public class count  {
    int count = 0  ;
    public synchronized void    increment () {
        count ++ ;
    }
    public int getCount () { return  count  ; } ;

}
