package org.etriks.etriksLabs

class EhsController {

    def index() {}
    def test(){}
    def addition(String a,String b){
        def aN=Integer.parseInt(a);
        println(a);
        def bN=Integer.parseInt(b);
        println(b);
       render  aN+bN;
    }
}
